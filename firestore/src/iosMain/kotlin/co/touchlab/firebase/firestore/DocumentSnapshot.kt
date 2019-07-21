package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRDocumentSnapshot
import cocoapods.FirebaseFirestore.FIRServerTimestampBehavior

actual typealias DocumentSnapshot = FIRDocumentSnapshot

actual val DocumentSnapshot.documentId: String
    get() = documentID

actual val DocumentSnapshot.reference: DocumentReference
    get() = reference

actual val DocumentSnapshot.exists: Boolean
    get() = exists

actual fun DocumentSnapshot.data_(serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior): Map<String, Any?>? =
    dataWithServerTimestampBehavior(serverTimestampBehavior.toObjc()) as Map<String, Any?>?

actual fun DocumentSnapshot.data_(): Map<String, Any?>? = data() as Map<String, Any?>?

actual fun DocumentSnapshot.get(fieldPath: String): Any? = valueForField(fieldPath)

actual fun DocumentSnapshot.get(fieldPath: FieldPath): Any? = valueForField(fieldPath)

actual fun DocumentSnapshot.get(
    fieldPath: String,
    serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior
): Any? = valueForField(fieldPath, serverTimestampBehavior.toObjc())

actual fun DocumentSnapshot.get(
    fieldPath: FieldPath,
    serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior
): Any? = valueForField(fieldPath, serverTimestampBehavior.toObjc())

internal fun DocumentSnapshotServerTimestampBehavior.toObjc():FIRServerTimestampBehavior =
    when(this){
        DocumentSnapshotServerTimestampBehavior.ESTIMATE -> FIRServerTimestampBehavior.FIRServerTimestampBehaviorEstimate
        DocumentSnapshotServerTimestampBehavior.NONE -> FIRServerTimestampBehavior.FIRServerTimestampBehaviorNone
        DocumentSnapshotServerTimestampBehavior.PREVIOUS -> FIRServerTimestampBehavior.FIRServerTimestampBehaviorPrevious
    }

actual fun DocumentSnapshot.contains(fieldPath: FieldPath): Boolean = contains(fieldPath)
actual fun DocumentSnapshot.contains(field: String): Boolean = contains(field)