package co.touchlab.firebase.firestore

expect class Transaction

expect fun Transaction.deleteDocument(documentRef: DocumentReference):Transaction
expect fun Transaction.getDocument(documentRef: DocumentReference): DocumentSnapshot?
expect fun Transaction.setData(documentRef: DocumentReference, key: Map<String, Any?>, options: SetOptions? = null):Transaction
expect fun Transaction.updateData(documentRef: DocumentReference, key: Map<String, Any?>):Transaction