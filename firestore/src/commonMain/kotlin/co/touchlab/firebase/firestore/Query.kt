package co.touchlab.firebase.firestore

import co.touchlab.firebase.firestore.coroutines.awaitCallback

expect open class Query

enum class QueryDirection{
    ASCENDING, DESCENDING
}

expect val Query.firestore:FirebaseFirestore
expect fun Query.orderBy(field:String, direction: QueryDirection):Query
expect fun Query.orderBy(field:String):Query
expect fun Query.limit(limit:Long):Query
expect fun Query.get_():TaskData<QuerySnapshot>
expect fun Query.addSnapshotListener_(listener:(QuerySnapshot?, FirebaseFirestoreException?)->Unit):ListenerRegistration
expect fun Query.addSnapshotListener_(metadataChanges: MetadataChanges, listener:(QuerySnapshot?, FirebaseFirestoreException?)->Unit):ListenerRegistration
expect fun Query.endAt(documentSnapshot: DocumentSnapshot):Query
expect fun Query.endBefore(documentSnapshot: DocumentSnapshot):Query
expect fun Query.startAfter(documentSnapshot: DocumentSnapshot):Query
expect fun Query.startAt(documentSnapshot: DocumentSnapshot):Query
expect fun Query.whereEqualTo(field: String, value: Any):Query
expect fun Query.whereGreaterThan(field: String, value: Any):Query
expect fun Query.whereGreaterThanOrEqualTo(field: String, value: Any):Query
expect fun Query.whereLessThan(field: String, value: Any):Query
expect fun Query.whereLessThanOrEqualTo(field: String, value: Any):Query
expect fun Query.whereArrayContains(field: String, value: Any):Query
expect fun Query.whereArrayContains(field: FieldPath, value: Any):Query

suspend fun Query.suspendGet():QuerySnapshot{
    return awaitCallback { callback ->
        get_().addListeners(
            {qs -> callback.onComplete(qs)},
            {exception -> callback.onError(exception)}
        )
    }
}

