package co.touchlab.firebase.firestore

import com.google.firebase.firestore.FirebaseFirestore.getInstance


actual typealias FirebaseFirestore = com.google.firebase.firestore.FirebaseFirestore

actual fun FirebaseFirestore.batch(): WriteBatch = batch()

actual fun FirebaseFirestore.collection(collectionPath: String): CollectionReference =
    collection(collectionPath)

actual fun FirebaseFirestore.collectionGroup(collectionId: String): Query =
    collectionGroup(collectionId)

actual fun FirebaseFirestore.enableNetwork_(): TaskVoid =
    TaskVoid(enableNetwork())

actual fun getFirebaseInstance(): FirebaseFirestore = getInstance()

actual fun FirebaseFirestore.disableNetwork_(): TaskVoid =
    TaskVoid(disableNetwork())

actual var FirebaseFirestore.settings: FirebaseFirestoreSettings
    get() {
        val fset = firestoreSettings
        return FirebaseFirestoreSettings(
            host = fset.host,
            sslEnabled = fset.isSslEnabled,
            persistenceEnabled = fset.isPersistenceEnabled,
            areTimestampsInSnapshotsEnabled = fset.areTimestampsInSnapshotsEnabled(),
            cacheSizeBytes = fset.cacheSizeBytes
        )
    }
    set(value) {
        val settingsBuilder = com.google.firebase.firestore.FirebaseFirestoreSettings.Builder()
        settingsBuilder.setHost(value.host)
            .setSslEnabled(value.sslEnabled)
            .setPersistenceEnabled(value.persistenceEnabled)
            .setTimestampsInSnapshotsEnabled(value.areTimestampsInSnapshotsEnabled)
            .setCacheSizeBytes(value.cacheSizeBytes)
        firestoreSettings = settingsBuilder.build()
    }

actual fun FirebaseFirestore.document(documentPath: String): DocumentReference =
    document(documentPath)
