package co.touchlab.firebase.firestore

import co.touchlab.firebase.firestore.coroutines.awaitCallback

expect class FirebaseFirestore

expect fun FirebaseFirestore.batch(): WriteBatch
expect fun FirebaseFirestore.collection(collectionPath:String):CollectionReference
expect fun FirebaseFirestore.collectionGroup(collectionId:String):Query
expect fun FirebaseFirestore.disableNetwork_():TaskVoid
expect fun FirebaseFirestore.document(documentPath:String):DocumentReference
expect fun FirebaseFirestore.enableNetwork_():TaskVoid

expect var FirebaseFirestore.settings:FirebaseFirestoreSettings

expect fun getFirebaseInstance():FirebaseFirestore

suspend fun FirebaseFirestore.suspendDisableNetwork() = awaitCallback<Unit> { callback ->
    disableNetwork_().addListeners(
        { callback.onComplete(Unit) },
        { exception -> callback.onError(exception) }
    )
}

suspend fun FirebaseFirestore.suspendEnableNetwork() = awaitCallback<Unit> { callback ->
    enableNetwork_().addListeners(
        { callback.onComplete(Unit) },
        { exception -> callback.onError(exception) }
    )
}