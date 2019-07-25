package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRSnapshotMetadata

actual typealias SnapshotMetadata = FIRSnapshotMetadata

actual val SnapshotMetadata.pendingWrites: Boolean
    get() = hasPendingWrites()

actual val SnapshotMetadata.fromCache: Boolean
    get() = isFromCache()