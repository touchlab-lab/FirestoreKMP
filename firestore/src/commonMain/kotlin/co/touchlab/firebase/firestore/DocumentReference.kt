package co.touchlab.firebase.firestore

import co.touchlab.firebase.firestore.coroutines.awaitCallback

expect class DocumentReference

expect fun DocumentReference.addSnapshotListener_(
    metadataChanges: MetadataChanges? = null,
    listener: (DocumentSnapshot?, FirebaseFirestoreException?) -> Unit
): ListenerRegistration

expect fun DocumentReference.collection(collectionPath: String): CollectionReference
expect fun DocumentReference.delete_(): TaskVoid
expect fun DocumentReference.getDocument(source: Source? = null): TaskData<DocumentSnapshot>
expect val DocumentReference.firestore: FirebaseFirestore
expect val DocumentReference.id: String
expect val DocumentReference.parent: CollectionReference
expect val DocumentReference.path: String
expect fun DocumentReference.setData(key: Map<String, Any?>, options: SetOptions? = null): TaskVoid

expect fun DocumentReference.updateData(key: Map<String, Any?>): TaskVoid

sealed class SetOptions {
    object Merge : SetOptions()
    data class MergeStrings(val fields: List<String>) : SetOptions() {
        constructor(vararg field: String) : this(field.toList())
    }

    data class MergeFields(val fields: List<FieldPath>) : SetOptions() {
        constructor(vararg field: FieldPath) : this(field.toList())
    }
}

suspend fun DocumentReference.suspendSet(key: Map<String, Any?>) = awaitCallback<Unit> { callback ->
    setData(key).addListeners(
        { callback.onComplete(Unit) },
        { exception -> callback.onError(exception) }
    )
}

suspend fun DocumentReference.suspendDelete() = awaitCallback<Unit> { callback ->
    delete_().addListeners(
        { callback.onComplete(Unit) },
        { exception -> callback.onError(exception) }
    )
}

suspend fun DocumentReference.suspendGet(source: Source? = null): DocumentSnapshot = awaitCallback { callback ->
    getDocument(source).addListeners(
        { callback.onComplete(it) },
        { exception -> callback.onError(exception) }
    )
}