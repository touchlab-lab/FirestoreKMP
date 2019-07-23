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
expect fun DocumentReference.set_(key: Map<String, Any?>, options:SetOptions): TaskVoid

expect fun DocumentReference.update_(key: Map<String, Any?>): TaskVoid
//TODO: This or equivalent
//expect fun DocumentReference.update(vararg pair: Pair<String, Any?>): TaskVoid
//expect fun DocumentReference.update(vararg pair: Pair<FieldPath, Any?>): TaskVoid

sealed class SetOptions{
    object Merge : SetOptions()
    data class MergeStrings(val fields:List<String>): SetOptions(){
        constructor(vararg field: String) : this(field.toList())
    }
    data class MergeFields(val fields:List<FieldPath>): SetOptions(){
        constructor(vararg field: FieldPath) : this(field.toList())
    }
}

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