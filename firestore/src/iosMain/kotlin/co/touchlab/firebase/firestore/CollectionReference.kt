package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRCollectionReference
import kotlinx.cinterop.StableRef
import platform.Foundation.NSError
import kotlin.native.concurrent.freeze

actual typealias CollectionReference = FIRCollectionReference

actual fun CollectionReference.addDocument(data: Map<String, Any?>): TaskData<DocumentReference> {
    val taskData = TaskData<DocumentReference>()
    val taskRef = StableRef.create(taskData)

    val completion: (NSError?) -> Unit = { err: NSError? ->
        val task = taskRef.get()
        taskRef.dispose()
        if (err == null) {
            task.success()
        } else {
            task.fail(err)
        }
    }
    taskData.ref = addDocumentWithData(data as Map<Any?, Any?>, completion.freeze())

    return taskData
}

actual fun CollectionReference.document(documentPath: String?): DocumentReference =
    if(documentPath == null){documentWithAutoID()}else{documentWithPath(documentPath)}

actual val CollectionReference.parent: DocumentReference?
    get() = parent()
actual val CollectionReference.id: String
    get() = collectionID
actual val CollectionReference.path: String
    get() = path()