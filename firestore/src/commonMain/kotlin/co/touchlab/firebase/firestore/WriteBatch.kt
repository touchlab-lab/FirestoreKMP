package co.touchlab.firebase.firestore

expect class WriteBatch

expect fun WriteBatch.commit_():TaskVoid
expect fun WriteBatch.delete(documentRef: DocumentReference):WriteBatch
expect fun WriteBatch.set(documentRef: DocumentReference, data: Map<String, Any?>, options: SetOptions? = null):WriteBatch
expect fun WriteBatch.update(documentRef: DocumentReference, data: Map<String, Any?>):WriteBatch