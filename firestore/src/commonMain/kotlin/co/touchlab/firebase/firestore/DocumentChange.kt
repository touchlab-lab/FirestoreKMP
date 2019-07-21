package co.touchlab.firebase.firestore

expect class DocumentChange

expect val DocumentChange.document:QueryDocumentSnapshot
expect val DocumentChange.type_:DocumentChangeType
expect val DocumentChange.newIndex_:Int
expect val DocumentChange.oldIndex_:Int

enum class DocumentChangeType{
    ADDED, MODIFIED, REMOVED
}

