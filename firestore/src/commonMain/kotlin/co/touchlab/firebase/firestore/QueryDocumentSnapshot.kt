package co.touchlab.firebase.firestore

expect class QueryDocumentSnapshot:DocumentSnapshot

expect val QueryDocumentSnapshot.data_:Map<String, Any?>
expect fun QueryDocumentSnapshot.data(serverTimestampBehavior:DocumentSnapshotServerTimestampBehavior):Map<String, Any?>