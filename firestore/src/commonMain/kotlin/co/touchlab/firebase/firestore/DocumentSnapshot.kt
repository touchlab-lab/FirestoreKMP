package co.touchlab.firebase.firestore

expect open class DocumentSnapshot

expect fun DocumentSnapshot.contains(fieldPath: FieldPath):Boolean
expect fun DocumentSnapshot.contains(field: String):Boolean
expect val DocumentSnapshot.exists: Boolean
expect val DocumentSnapshot.reference: DocumentReference
expect val DocumentSnapshot.documentId: String
expect fun DocumentSnapshot.get(fieldPath: String):Any?
expect fun DocumentSnapshot.get(fieldPath: FieldPath):Any?
expect fun DocumentSnapshot.get(fieldPath: String, serverTimestampBehavior:DocumentSnapshotServerTimestampBehavior):Any?
expect fun DocumentSnapshot.get(fieldPath: FieldPath, serverTimestampBehavior:DocumentSnapshotServerTimestampBehavior):Any?
expect fun DocumentSnapshot.data_():Map<String, Any?>?
expect fun DocumentSnapshot.data_(serverTimestampBehavior:DocumentSnapshotServerTimestampBehavior):Map<String, Any?>?
//TODO: Type-specific get calls

enum class DocumentSnapshotServerTimestampBehavior{
    NONE,
    ESTIMATE,
    PREVIOUS
}