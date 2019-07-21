package co.touchlab.firebase.firestore

import co.touchlab.firebase.firestore.coroutines.awaitCallback

expect class DocumentReference

expect fun DocumentReference.addSnapshotListener_(listener:(DocumentSnapshot?, FirebaseFirestoreException?)->Unit):ListenerRegistration
expect fun DocumentReference.addSnapshotListener_(metadataChanges: MetadataChanges, listener:(DocumentSnapshot?, FirebaseFirestoreException?)->Unit):ListenerRegistration
expect fun DocumentReference.collection(collectionPath:String): CollectionReference
expect fun DocumentReference.delete_(): TaskVoid
expect fun DocumentReference.get_(): TaskData<DocumentSnapshot>
expect fun DocumentReference.get_(source: Source): TaskData<DocumentSnapshot>
expect val DocumentReference.firestore_: FirebaseFirestore
expect val DocumentReference.id_: String
expect val DocumentReference.parent_:CollectionReference
expect val DocumentReference.path: String
expect fun DocumentReference.set_(key: Map<String, Any?>): TaskVoid
//TODO: Sort out merge options
//expect fun DocumentReference.set_(key: Map<String, Any?>, options:SetOptions): TaskVoid
//TODO: Add other update forms
expect fun DocumentReference.update_(key: Map<String, Any?>): TaskVoid

suspend fun DocumentReference.suspendSetData(key: Map<String, Any?>) = awaitCallback<Unit> { callback ->
    set_(key).addListeners(
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

suspend fun DocumentReference.suspendGet(): DocumentSnapshot = awaitCallback { callback ->
    get_().addListeners(
        { callback.onComplete(it) },
        { exception -> callback.onError(exception) }
    )
}