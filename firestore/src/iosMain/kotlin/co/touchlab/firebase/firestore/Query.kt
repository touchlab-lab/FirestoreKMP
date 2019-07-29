package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRQuery
import cocoapods.FirebaseFirestore.FIRQuerySnapshot
import kotlinx.cinterop.StableRef
import platform.Foundation.NSError
import kotlin.native.concurrent.freeze

actual typealias Query = FIRQuery

actual fun Query.orderBy(
    field: String,
    direction: QueryDirection?
): Query =
    if (direction == null) {
        queryOrderedByField(field)
    } else {
        queryOrderedByField(field, direction == QueryDirection.DESCENDING)
    }

actual fun Query.limit(limit: Long): Query = queryLimitedTo(limit)

actual fun Query.get_(): TaskData<QuerySnapshot> {
    val taskData = TaskData<QuerySnapshot>()
    val taskRef = StableRef.create(taskData)

    val completion = { snapshot: FIRQuerySnapshot?, err: NSError? ->
        val task = taskRef.get()
        taskRef.dispose()
        if (err == null && snapshot != null) {
            task.ref = snapshot
            task.success()
        } else {
            task.fail(err!!)
        }
    }
    getDocumentsWithCompletion(completion.freeze())

    return taskData
}

actual val Query.firestore: FirebaseFirestore
    get() = firestore()

actual fun Query.addSnapshotListener_(
    metadataChanges: MetadataChanges?,
    listener: (QuerySnapshot?, FirebaseFirestoreException?) -> Unit
): ListenerRegistration {
    val taskRef = StableRef.create(listener)

    val rawReg = if (metadataChanges == null) {
        val nativeListener: (FIRQuerySnapshot?, NSError?) -> Unit = { documentSnapshot, firebaseFirestoreException ->
            taskRef.get()(
                documentSnapshot,
                firebaseFirestoreException?.let { FirebaseFirestoreException(DarwinException(it)) })
        }
        addSnapshotListener(nativeListener.freeze())
    } else {
        val nativeListener: (FIRQuerySnapshot?, NSError?) -> Unit = { documentSnapshot, firebaseFirestoreException ->
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

actual fun Query.endAt(documentSnapshot: DocumentSnapshot): Query = queryEndingAtDocument(documentSnapshot)
actual fun Query.endBefore(documentSnapshot: DocumentSnapshot): Query = queryEndingBeforeDocument(documentSnapshot)
actual fun Query.startAfter(documentSnapshot: DocumentSnapshot): Query = queryStartingAfterDocument(documentSnapshot)
actual fun Query.startAt(documentSnapshot: DocumentSnapshot): Query = queryStartingAtDocument(documentSnapshot)
actual fun Query.whereGreaterThanOrEqualTo(
    field: String,
    value: Any
): Query = queryWhereField(field = field, isGreaterThanOrEqualTo = value)

actual fun Query.whereLessThan(
    field: String,
    value: Any
): Query = queryWhereField(field = field, isLessThan = value)

actual fun Query.whereEqualTo(
    field: String,
    value: Any
): Query = queryWhereField(field = field, isEqualTo = value)

actual fun Query.whereGreaterThan(
    field: String,
    value: Any
): Query = queryWhereField(field = field, isGreaterThan = value)

actual fun Query.whereLessThanOrEqualTo(
    field: String,
    value: Any
): Query = queryWhereField(field = field, isLessThanOrEqualTo = value)

actual fun Query.whereArrayContains(
    field: String,
    value: Any
): Query = queryWhereField(field = field, arrayContains = value)

actual fun Query.whereArrayContains(
    field: FieldPath,
    value: Any
): Query = queryWhereFieldPath(path = field, arrayContains = value)