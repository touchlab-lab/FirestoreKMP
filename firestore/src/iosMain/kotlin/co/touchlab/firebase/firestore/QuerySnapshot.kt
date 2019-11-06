package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRQuerySnapshot

actual typealias QuerySnapshot = FIRQuerySnapshot

actual val QuerySnapshot.documentChanges_: List<DocumentChange>
    get() = documentChanges as List<DocumentChange>
actual val QuerySnapshot.documents_: List<DocumentSnapshot>
    get() = documents as List<DocumentSnapshot>

actual fun QuerySnapshot.getDocumentChanges_(metadataChanges: MetadataChanges): List<DocumentChange> =
    documentChangesWithIncludeMetadataChanges(metadataChanges == MetadataChanges.INCLUDE) as List<DocumentChange>

actual val QuerySnapshot.metadata: SnapshotMetadata
    get() = metadata()

actual val QuerySnapshot.empty: Boolean
    get() = isEmpty()

actual val QuerySnapshot.query: Query
    get() = query()

actual val QuerySnapshot.size: Int
    get() = count().toInt()