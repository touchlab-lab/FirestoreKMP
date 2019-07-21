package co.touchlab.firebase.firestore

import co.touchlab.firebase.firestore.coroutines.awaitCallback

expect class CollectionReference : Query

//TODO: I think this needs some kind of sealed class instead of "Any"
expect fun CollectionReference.add_(data: Map<String, Any?>):TaskData<DocumentReference>

expect fun CollectionReference.document():DocumentReference
expect fun CollectionReference.document(documentPath:String):DocumentReference

expect val CollectionReference.id:String
expect val CollectionReference.path:String
expect val CollectionReference.parent:DocumentReference?

suspend fun CollectionReference.suspendAdd(data: Map<String, Any?>):DocumentReference{
    return awaitCallback { callback ->
        add_(data).addListeners(
            {callback.onComplete(it)},
            {exception -> callback.onError(exception)}
        )
    }
}