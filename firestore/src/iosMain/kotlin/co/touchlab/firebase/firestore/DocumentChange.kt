package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRDocumentChange
import cocoapods.FirebaseFirestore.FIRDocumentChangeType

actual typealias DocumentChange = FIRDocumentChange

actual val DocumentChange.document: QueryDocumentSnapshot
    get() = document()

actual val DocumentChange.type_: DocumentChangeType
    get() = when(type){
        FIRDocumentChangeType.FIRDocumentChangeTypeAdded -> DocumentChangeType.ADDED
        FIRDocumentChangeType.FIRDocumentChangeTypeModified -> DocumentChangeType.MODIFIED
        FIRDocumentChangeType.FIRDocumentChangeTypeRemoved -> DocumentChangeType.REMOVED
    }

actual val DocumentChange.newIndex_: Int
    get() = newIndex.toInt()

actual val DocumentChange.oldIndex_: Int
    get() = oldIndex.toInt()