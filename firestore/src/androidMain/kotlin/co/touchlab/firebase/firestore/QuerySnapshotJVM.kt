package co.touchlab.firebase.firestore

actual typealias QuerySnapshot = com.google.firebase.firestore.QuerySnapshot

actual val QuerySnapshot.documentChanges_: List<DocumentChange>
    get() = documentChanges
actual val QuerySnapshot.documents_: List<DocumentSnapshot>
    get() = documents

actual fun QuerySnapshot.getDocumentChanges_(metadataChanges: MetadataChanges): List<DocumentChange> =
    getDocumentChanges(metadataChanges.toJvm())

actual val QuerySnapshot.metadata
    get() = metadata

actual val QuerySnapshot.query: Query
    get() = query

actual val QuerySnapshot.empty: Boolean
    get() = isEmpty

actual val QuerySnapshot.size: Int
    get() = size()