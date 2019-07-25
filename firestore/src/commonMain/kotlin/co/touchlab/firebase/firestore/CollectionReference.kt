package co.touchlab.firebase.firestore

import co.touchlab.firebase.firestore.coroutines.awaitCallback

expect class CollectionReference : Query

expect fun CollectionReference.addDocument(data: Map<String, Any?>):TaskData<DocumentReference>

expect fun CollectionReference.document(documentPath:String? = null):DocumentReference

expect val CollectionReference.id:String
expect val CollectionReference.path:String
expect val CollectionReference.parent:DocumentReference?

suspend fun CollectionReference.suspendAdd(data: Map<String, Any?>):DocumentReference{
    return awaitCallback { callback ->
        addDocument(data).addListeners(
            {callback.onComplete(it)},
            {exception -> callback.onError(exception)}
        )
    }
}