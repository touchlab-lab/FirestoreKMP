package co.touchlab.firebase.firestore

import co.touchlab.firebase.firestore.coroutines.awaitCallback
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

expect open class Query

enum class QueryDirection {
    ASCENDING, DESCENDING
}

expect val Query.firestore: FirebaseFirestore
expect fun Query.orderBy(field: String, direction: QueryDirection? = null): Query
expect fun Query.limit(limit: Long): Query
expect fun Query.get_(): TaskData<QuerySnapshot>
expect fun Query.addSnapshotListener_(
    metadataChanges: MetadataChanges? = null,
    listener: (QuerySnapshot?, FirebaseFirestoreException?) -> Unit
): ListenerRegistration

expect fun Query.endAt(documentSnapshot: DocumentSnapshot): Query
expect fun Query.endBefore(documentSnapshot: DocumentSnapshot): Query
expect fun Query.startAfter(documentSnapshot: DocumentSnapshot): Query
expect fun Query.startAt(documentSnapshot: DocumentSnapshot): Query
expect fun Query.whereEqualTo(field: String, value: Any): Query
expect fun Query.whereGreaterThan(field: String, value: Any): Query
expect fun Query.whereGreaterThanOrEqualTo(field: String, value: Any): Query
expect fun Query.whereLessThan(field: String, value: Any): Query
expect fun Query.whereLessThanOrEqualTo(field: String, value: Any): Query
expect fun Query.whereArrayContains(field: String, value: Any): Query
expect fun Query.whereArrayContains(field: FieldPath, value: Any): Query

suspend fun Query.suspendGet(): QuerySnapshot {
    return awaitCallback { callback ->
        get_().addListeners(
            { qs -> callback.onComplete(qs) },
            { exception -> callback.onError(exception) }
        )
    }
}

fun Query.asFlow(): Flow<QuerySnapshot> = callbackFlow {
    val registration = addSnapshotListener_ { querySnapshot, firebaseFirestoreException ->
        if (querySnapshot != null) {
            offer(querySnapshot!!)
        } else if(firebaseFirestoreException != null) {
            close(firebaseFirestoreException)
        }
    }

    awaitClose {
        registration.remove()
    }
}
