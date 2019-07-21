package co.touchlab.firebase.firestore

expect class QuerySnapshot

expect val QuerySnapshot.documentChanges_:List<DocumentChange>
expect val QuerySnapshot.documents_:List<DocumentSnapshot>