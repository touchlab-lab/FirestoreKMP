package co.touchlab.firebase.firestore

actual typealias Transaction = com.google.firebase.firestore.Transaction

actual fun Transaction.deleteDocument(documentRef: DocumentReference): Transaction {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Transaction.getDocument(documentRef: DocumentReference): DocumentSnapshot? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Transaction.setData(
    documentRef: DocumentReference,
    key: Map<String, Any?>,
    options: SetOptions?
): Transaction {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

actual fun Transaction.updateData(
    documentRef: DocumentReference,
    key: Map<String, Any?>
): Transaction {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}