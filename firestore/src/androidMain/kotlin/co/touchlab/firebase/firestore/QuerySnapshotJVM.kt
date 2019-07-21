package co.touchlab.firebase.firestore

actual typealias QuerySnapshot = com.google.firebase.firestore.QuerySnapshot

actual val QuerySnapshot.documentChanges_: List<DocumentChange>
    get() = documentChanges
actual val QuerySnapshot.documents_: List<DocumentSnapshot>
    get() = documents