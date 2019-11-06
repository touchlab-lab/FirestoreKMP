package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRWriteBatch

actual typealias WriteBatch = FIRWriteBatch

actual fun WriteBatch.commit_(): TaskVoid {
    val taskPair = makeVoid()
    commitWithCompletion(taskPair.second)
    return taskPair.first
}

actual fun WriteBatch.delete(documentRef: DocumentReference): WriteBatch =
    deleteDocument(documentRef)

actual fun WriteBatch.set(
    documentRef: DocumentReference,
    data: Map<String, Any?>,
    options: SetOptions?
): WriteBatch = if (options == null) {
    setData(data as Map<Any?, *>, documentRef)
} else {
    when (options) {
        is SetOptions.Merge -> setData(
            data = data as Map<Any?, *>,
            merge = true,
            forDocument = documentRef
        )
        is SetOptions.MergeStrings -> setData(
            data = data as Map<Any?, *>,
            mergeFields = options.fields,
            forDocument = documentRef
        )
        is SetOptions.MergeFields -> setData(
            data = data as Map<Any?, *>,
            mergeFields = options.fields,
            forDocument = documentRef
        )
    }
}

actual fun WriteBatch.update(
    documentRef: DocumentReference,
    data: Map<String, Any?>
): WriteBatch = updateData(fields = data as Map<Any?, *>, forDocument = documentRef)