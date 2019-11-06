package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRFirestore
import cocoapods.FirebaseFirestore.FIRFirestoreSettings
import kotlinx.cinterop.StableRef
import platform.Foundation.NSError
import kotlin.native.concurrent.DetachedObjectGraph
import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.freeze

actual typealias FirebaseFirestore = FIRFirestore

actual fun FirebaseFirestore.collection(collectionPath: String): CollectionReference =
    collectionWithPath(collectionPath)

actual fun getFirebaseInstance(): FirebaseFirestore = FIRFirestore.firestore()
actual fun FirebaseFirestore.batch(): WriteBatch = batch()

actual fun FirebaseFirestore.collectionGroup(collectionId: String): Query =
    collectionGroupWithID(collectionId)

actual fun FirebaseFirestore.disableNetwork_(): TaskVoid {
    val mv = makeVoid()
    disableNetworkWithCompletion(mv.second)
    return mv.first
}

actual fun FirebaseFirestore.document(documentPath: String): DocumentReference =
    documentWithPath(documentPath)

actual fun FirebaseFirestore.enableNetwork_(): TaskVoid {
    val mv = makeVoid()
    enableNetworkWithCompletion(mv.second)
    return mv.first
}

actual var FirebaseFirestore.settings: FirebaseFirestoreSettings
    get() {
        val s = settings
        return FirebaseFirestoreSettings(
            host = s.host,
            sslEnabled = s.isSSLEnabled(),
            persistenceEnabled = s.isPersistenceEnabled(),
            areTimestampsInSnapshotsEnabled = s.areTimestampsInSnapshotsEnabled(),
            cacheSizeBytes = s.cacheSizeBytes
        )
    }
    set(value) {
        val s = FIRFirestoreSettings()
        s.host = value.host
        s.sslEnabled = value.sslEnabled
        s.persistenceEnabled = value.persistenceEnabled
        s.timestampsInSnapshotsEnabled = value.areTimestampsInSnapshotsEnabled
        s.cacheSizeBytes = value.cacheSizeBytes
        settings = s
    }
