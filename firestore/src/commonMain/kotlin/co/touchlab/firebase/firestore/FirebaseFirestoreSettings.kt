package co.touchlab.firebase.firestore

data class FirebaseFirestoreSettings(
    val host:String,
    val sslEnabled: Boolean,
    val persistenceEnabled:Boolean,
    val areTimestampsInSnapshotsEnabled:Boolean,
    val cacheSizeBytes:Long
)
