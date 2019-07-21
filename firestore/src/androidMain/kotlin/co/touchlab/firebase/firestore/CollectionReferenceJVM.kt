package co.touchlab.firebase.firestore


actual typealias CollectionReference  = com.google.firebase.firestore.CollectionReference

actual fun CollectionReference.add_(data: Map<String, Any?>): TaskData<DocumentReference> =
    TaskData(add(data))



actual fun CollectionReference.document(documentPath: String): DocumentReference = document(documentPath)
actual fun CollectionReference.document(): DocumentReference = document()
actual val CollectionReference.id: String
    get() = getId()

actual val CollectionReference.parent: DocumentReference?
    get() = getParent()

actual val CollectionReference.path: String
    get() = getPath()