package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRDocumentReference
import cocoapods.FirebaseFirestore.FIRDocumentSnapshot
import cocoapods.FirebaseFirestore.FIRFirestoreSource
import kotlinx.cinterop.StableRef
import platform.Foundation.NSError
import kotlin.native.concurrent.freeze

actual typealias DocumentReference = FIRDocumentReference

actual fun DocumentReference.addSnapshotListener_(
    metadataChanges: MetadataChanges?,
    listener: (DocumentSnapshot?, FirebaseFirestoreException?) -> Unit
): ListenerRegistration {
    val taskRef = StableRef.create(listener)

    val rawReg = if (metadataChanges == null) {
        val nativeListener: (FIRDocumentSnapshot?, NSError?) -> Unit = { documentSnapshot, firebaseFirestoreException ->
            taskRef.get()(
                documentSnapshot,
                firebaseFirestoreException?.let { FirebaseFirestoreException(DarwinException(it)) })
        }
        addSnapshotListener(nativeListener.freeze())
    } else {
        val nativeListener: (FIRDocumentSnapshot?, NSError?) -> Unit = { documentSnapshot, firebaseFirestoreException ->
            taskRef.get()(
                documentSnapshot,
                firebaseFirestoreException?.let { FirebaseFirestoreException(DarwinException(it)) })
        }
        addSnapshotListenerWithIncludeMetadataChanges(
            metadataChanges == MetadataChanges.INCLUDE,
            nativeListener.freeze()
        )
    }

    return StableRefListenerRegistration(
        taskRef, rawReg
    )
}

actual fun DocumentReference.collection(collectionPath: String): CollectionReference =
    collectionWithPath(collectionPath)

actual fun DocumentReference.delete_(): TaskVoid {
    val taskPair = makeVoid()
    deleteDocumentWithCompletion(taskPair.second)
    return taskPair.first
}

actual fun DocumentReference.getDocument(source: Source?): TaskData<DocumentSnapshot> {
    val taskData = TaskData<DocumentSnapshot>()
    val taskRef = StableRef.create(taskData)

    val completion = { snapshot: FIRDocumentSnapshot?, err: NSError? ->
        val task = taskRef.get()
        taskRef.dispose()
        if (err == null && snapshot != null) {
            task.ref = snapshot
            task.success()
        } else {
            task.fail(err!!)
        }
    }

    if (source == null) {
        getDocumentWithCompletion(completion.freeze())
    } else {
        getDocumentWithSource(sourceToDarwinSource(source), completion.freeze())
    }

    return taskData
}

actual val DocumentReference.firestore: FirebaseFirestore
    get() = firestore
actual val DocumentReference.id: String
    get() = documentID
actual val DocumentReference.parent: CollectionReference
    get() = parent
actual val DocumentReference.path: String
    get() = path()

actual fun DocumentReference.setData(
    key: Map<String, Any?>,
    options: SetOptions?
): TaskVoid {
    val taskPair = makeVoid()

    if (options == null) {
        setData(key as Map<Any?, *>, taskPair.second)
    } else {
        when (options) {
            is SetOptions.Merge -> setData(
                documentData = key as Map<Any?, *>,
                merge = true,
                completion = taskPair.second
            )
            is SetOptions.MergeStrings -> setData(
                documentData = key as Map<Any?, *>,
                mergeFields = options.fields,
                completion = taskPair.second
            )
            is SetOptions.MergeFields -> setData(
                documentData = key as Map<Any?, *>,
                mergeFields = options.fields,
                completion = taskPair.second
            )
        }
    }

    return taskPair.first
}

actual fun DocumentReference.updateData(key: Map<String, Any?>): TaskVoid {
    val taskPair = makeVoid()
    updateData(key as Map<Any?, *>, taskPair.second)
    return taskPair.first
}

internal fun sourceToDarwinSource(source: Source): FIRFirestoreSource = when (source) {
    Source.CACHE -> FIRFirestoreSource.FIRFirestoreSourceCache
    Source.DEFAULT -> FIRFirestoreSource.FIRFirestoreSourceDefault
    Source.SERVER -> FIRFirestoreSource.FIRFirestoreSourceServer
}
