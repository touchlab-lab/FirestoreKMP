package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRTransaction

actual typealias Transaction = FIRTransaction

//TODO: The "null" is a cpointer to error. maybe want to sort that out.x
actual fun Transaction.getDocument(documentRef: DocumentReference): DocumentSnapshot? = getDocument(documentRef, null)

actual fun Transaction.setData(
    documentRef: DocumentReference,
    key: Map<String, Any?>,
    options: SetOptions?
):Transaction {
    if (options == null) {
        setData(data = key as Map<Any?, *>, forDocument = documentRef)
    } else {
        when (options) {
            is SetOptions.Merge -> setData(
                data = key as Map<Any?, *>,
                forDocument = documentRef,
                merge = true
            )
            is SetOptions.MergeStrings -> setData(
                data = key as Map<Any?, *>,
                forDocument = documentRef,
                mergeFields = options.fields
            )
            is SetOptions.MergeFields -> setData(
                data = key as Map<Any?, *>,
                forDocument = documentRef,
                mergeFields = options.fields
            )
        }
    }

    return this
}

actual fun Transaction.updateData(documentRef: DocumentReference, key: Map<String, Any?>): Transaction =
    updateData(fields = key as Map<Any?, *>, forDocument = documentRef)

actual fun Transaction.deleteDocument(documentRef: DocumentReference): Transaction =
    deleteDocument(document = documentRef)