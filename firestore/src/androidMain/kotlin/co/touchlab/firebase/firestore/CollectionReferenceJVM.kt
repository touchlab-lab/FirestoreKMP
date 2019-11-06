package co.touchlab.firebase.firestore


actual typealias CollectionReference  = com.google.firebase.firestore.CollectionReference

actual fun CollectionReference.addDocument(data: Map<String, Any?>): TaskData<DocumentReference> =
    TaskData(add(data))

actual fun CollectionReference.document(documentPath: String?): DocumentReference =
    if(documentPath == null){document()}else{document(documentPath)}

actual val CollectionReference.id: String
    get() = getId()

actual val CollectionReference.parent: DocumentReference?
    get() = getParent()

actual val CollectionReference.path: String
    get() = getPath()