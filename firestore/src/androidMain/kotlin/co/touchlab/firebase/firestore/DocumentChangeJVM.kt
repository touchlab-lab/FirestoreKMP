package co.touchlab.firebase.firestore

actual typealias DocumentChange = com.google.firebase.firestore.DocumentChange

actual val DocumentChange.document: QueryDocumentSnapshot
    get() = getDocument()

actual val DocumentChange.type_: DocumentChangeType
    get() = when(type){
        com.google.firebase.firestore.DocumentChange.Type.ADDED -> DocumentChangeType.ADDED
        com.google.firebase.firestore.DocumentChange.Type.MODIFIED -> DocumentChangeType.MODIFIED
        com.google.firebase.firestore.DocumentChange.Type.REMOVED -> DocumentChangeType.REMOVED
    }

actual val DocumentChange.newIndex_: Int
    get() = newIndex

actual val DocumentChange.oldIndex_: Int
    get() = oldIndex