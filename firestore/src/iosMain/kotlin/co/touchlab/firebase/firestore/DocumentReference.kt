package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRDocumentReference
import cocoapods.FirebaseFirestore.FIRDocumentSnapshot
import cocoapods.FirebaseFirestore.FIRFirestoreSource
import cocoapods.FirebaseFirestore.FIRQuerySnapshot
import kotlinx.cinterop.StableRef
import platform.Foundation.NSError
import kotlin.native.concurrent.freeze

actual typealias DocumentReference = FIRDocumentReference

actual fun DocumentReference.set_(key: Map<String, Any?>): TaskVoid {
    val taskData = TaskVoid()
    val taskRef = StableRef.create(taskData)

    val completion = { err: NSError? ->
        val task = taskRef.get()
        taskRef.dispose()

        if (err == null) {
            task.success()
        } else {
            task.fail(err)
        }
    }

    setData(key as Map<Any?, *>, completion.freeze())

    return taskData
}

actual fun DocumentReference.delete_(): TaskVoid {
    val taskData = TaskVoid()
    val taskRef = StableRef.create(taskData)

    deleteDocumentWithCompletion { err:NSError? ->
        val task = taskRef.get()
        taskRef.dispose()

        if(err == null)
        {
            task.success()
        }
        else{
            task.fail(err)
        }
    }

    return taskData
}

actual fun DocumentReference.get_(): TaskData<DocumentSnapshot> {
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
    getDocumentWithCompletion(completion.freeze())

    return taskData
}

actual val DocumentReference.id_: String
    get() = documentID

actual fun DocumentReference.addSnapshotListener_(listener: (DocumentSnapshot?, FirebaseFirestoreException?) -> Unit): ListenerRegistration =
    wrapListenerRegistration(addSnapshotListener {documentSnapshot, firebaseFirestoreException -> listener(documentSnapshot, firebaseFirestoreException?.let { FirebaseFirestoreException(DarwinException(it)) }) })

actual fun DocumentReference.addSnapshotListener_(
    metadataChanges: MetadataChanges,
    listener: (DocumentSnapshot?, FirebaseFirestoreException?) -> Unit
): ListenerRegistration =
    wrapListenerRegistration(addSnapshotListenerWithIncludeMetadataChanges(metadataChanges == MetadataChanges.INCLUDE) {documentSnapshot, firebaseFirestoreException -> listener(documentSnapshot, firebaseFirestoreException?.let { FirebaseFirestoreException(DarwinException(it)) }) })

actual fun DocumentReference.collection(collectionPath: String): CollectionReference =
    collectionWithPath(collectionPath)

actual fun DocumentReference.get_(source: Source): TaskData<DocumentSnapshot> {
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
    getDocumentWithSource(sourceToDarwinSource(source), completion.freeze())

    return taskData
}

internal fun sourceToDarwinSource(source: Source): FIRFirestoreSource = when(source){
    Source.CACHE -> FIRFirestoreSource.FIRFirestoreSourceCache
    Source.DEFAULT -> FIRFirestoreSource.FIRFirestoreSourceDefault
    Source.SERVER -> FIRFirestoreSource.FIRFirestoreSourceServer
}

actual val DocumentReference.firestore_: FirebaseFirestore
    get() = firestore
actual val DocumentReference.parent_: CollectionReference
    get() = parent
actual val DocumentReference.path: String
    get() = path()

actual fun DocumentReference.update_(key: Map<String, Any?>): TaskVoid {
    val taskData = TaskVoid()
    val taskRef = StableRef.create(taskData)

    val completion = { err: NSError? ->
        val task = taskRef.get()
        taskRef.dispose()

        if (err == null) {
            task.success()
        } else {
            task.fail(err)
        }
    }

    updateData(key as Map<Any?, *>, completion.freeze())

    return taskData
}