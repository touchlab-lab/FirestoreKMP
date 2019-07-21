package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRQuery
import cocoapods.FirebaseFirestore.FIRQuerySnapshot
import kotlinx.cinterop.StableRef
import platform.Foundation.NSError
import kotlin.native.concurrent.freeze

actual typealias Query = FIRQuery

actual fun Query.orderBy(
    field: String,
    direction: QueryDirection
): Query = queryOrderedByField(field, direction == QueryDirection.DESCENDING)

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

actual fun Query.addSnapshotListener_(listener: (QuerySnapshot?, FirebaseFirestoreException?) -> Unit): ListenerRegistration =
    wrapListenerRegistration(addSnapshotListener {querySnapshot, firebaseFirestoreException -> listener(querySnapshot, firebaseFirestoreException?.let { FirebaseFirestoreException(DarwinException(it)) }) })

actual val Query.firestore: FirebaseFirestore
    get() = firestore()

actual fun Query.addSnapshotListener_(
    metadataChanges: MetadataChanges,
    listener: (QuerySnapshot?, FirebaseFirestoreException?) -> Unit
): ListenerRegistration =
    wrapListenerRegistration(addSnapshotListenerWithIncludeMetadataChanges(metadataChanges == MetadataChanges.INCLUDE) { querySnapshot, firebaseFirestoreException -> listener(querySnapshot, firebaseFirestoreException?.let { FirebaseFirestoreException(DarwinException(it)) }) })

actual fun Query.endAt(documentSnapshot: DocumentSnapshot): Query = queryEndingAtDocument(documentSnapshot)
actual fun Query.endBefore(documentSnapshot: DocumentSnapshot): Query = queryEndingBeforeDocument(documentSnapshot)
actual fun Query.orderBy(field: String): Query = queryOrderedByField(field)
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