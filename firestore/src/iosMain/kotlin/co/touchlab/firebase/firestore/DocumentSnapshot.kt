package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRDocumentSnapshot
import cocoapods.FirebaseFirestore.FIRGeoPoint
import cocoapods.FirebaseFirestore.FIRServerTimestampBehavior

actual typealias DocumentSnapshot = FIRDocumentSnapshot

actual fun DocumentSnapshot.contains(fieldPath: FieldPath): Boolean = contains(fieldPath)
actual fun DocumentSnapshot.contains(field: String): Boolean = contains(field)

actual val DocumentSnapshot.exists: Boolean
    get() = exists

actual val DocumentSnapshot.reference: DocumentReference
    get() = reference

actual val DocumentSnapshot.id: String
    get() = documentID

actual fun DocumentSnapshot.get(
    fieldPath: String,
    serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior?
): Any? = if (serverTimestampBehavior == null) {
    valueForField(fieldPath)
} else {
    valueForField(fieldPath, serverTimestampBehavior.toObjc())
}

actual fun DocumentSnapshot.get(
    fieldPath: FieldPath,
    serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior?
): Any? = if (serverTimestampBehavior == null) {
    valueForField(fieldPath)
} else {
    valueForField(fieldPath, serverTimestampBehavior.toObjc())
}

actual fun DocumentSnapshot.data_(serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior?): Map<String, Any?>? =
    if (serverTimestampBehavior == null) {
        data()
    } else {
        dataWithServerTimestampBehavior(serverTimestampBehavior.toObjc())
    } as Map<String, Any?>?

internal fun DocumentSnapshotServerTimestampBehavior.toObjc(): FIRServerTimestampBehavior =
    when (this) {
        DocumentSnapshotServerTimestampBehavior.ESTIMATE -> FIRServerTimestampBehavior.FIRServerTimestampBehaviorEstimate
        DocumentSnapshotServerTimestampBehavior.NONE -> FIRServerTimestampBehavior.FIRServerTimestampBehaviorNone
        DocumentSnapshotServerTimestampBehavior.PREVIOUS -> FIRServerTimestampBehavior.FIRServerTimestampBehaviorPrevious
    }

actual fun DocumentSnapshot.getGeoPoint(field: String): GeoPoint? = valueForField(field) as FIRGeoPoint?
actual val DocumentSnapshot.metadata: SnapshotMetadata
    get() = metadata

actual fun DocumentSnapshot.getBoolean(field: String): Boolean? = valueForField(field) as Boolean?

actual fun DocumentSnapshot.getDocumentReference(field: String): DocumentReference? =
    valueForField(field) as DocumentReference?

actual fun DocumentSnapshot.getDouble(field: String): Double? =
    valueForField(field) as Double?

actual fun DocumentSnapshot.getLong(field: String): Long? =
    valueForField(field) as Long?

actual fun DocumentSnapshot.getString(field: String): String? =
    valueForField(field) as String?

actual fun DocumentSnapshot.getTimestamp(
    field: String,
    serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior?
): Timestamp? =
    if(serverTimestampBehavior == null){
        valueForField(field)
    }else{
        valueForField(field, serverTimestampBehavior.toObjc())
    } as Timestamp?