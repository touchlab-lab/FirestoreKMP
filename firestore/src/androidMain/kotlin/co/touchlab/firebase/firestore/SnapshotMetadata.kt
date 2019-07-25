package co.touchlab.firebase.firestore

actual typealias SnapshotMetadata = com.google.firebase.firestore.SnapshotMetadata

actual val SnapshotMetadata.pendingWrites: Boolean
    get() = hasPendingWrites()

actual val SnapshotMetadata.fromCache: Boolean
    get() = isFromCache