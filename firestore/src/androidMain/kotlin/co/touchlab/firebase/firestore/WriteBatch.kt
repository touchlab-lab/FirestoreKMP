package co.touchlab.firebase.firestore

actual typealias WriteBatch = com.google.firebase.firestore.WriteBatch

actual fun WriteBatch.commit_(): TaskVoid =
    TaskVoid(commit())

actual fun WriteBatch.delete(documentRef: DocumentReference): WriteBatch = delete(documentRef)

actual fun WriteBatch.set(
    documentRef: DocumentReference,
    data: Map<String, Any?>,
    options: SetOptions?
): WriteBatch = if(options == null){set(documentRef, data)}else{set(documentRef, data, options)}

actual fun WriteBatch.update(
    documentRef: DocumentReference,
    data: Map<String, Any?>
): WriteBatch = update(documentRef, data)