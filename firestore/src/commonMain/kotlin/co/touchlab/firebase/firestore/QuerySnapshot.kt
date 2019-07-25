package co.touchlab.firebase.firestore

expect class QuerySnapshot

expect val QuerySnapshot.documentChanges_:List<DocumentChange>
expect fun QuerySnapshot.getDocumentChanges_(metadataChanges: MetadataChanges):List<DocumentChange>
expect val QuerySnapshot.documents_:List<DocumentSnapshot>
expect val QuerySnapshot.metadata: SnapshotMetadata
expect val QuerySnapshot.query: Query
expect val QuerySnapshot.empty: Boolean
expect val QuerySnapshot.size: Int