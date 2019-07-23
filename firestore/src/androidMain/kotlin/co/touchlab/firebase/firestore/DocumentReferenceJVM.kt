package co.touchlab.firebase.firestore

import com.google.firebase.firestore.EventListener

actual typealias DocumentReference = com.google.firebase.firestore.DocumentReference

actual fun DocumentReference.set_(key: Map<String, Any?>): TaskVoid =
    TaskVoid(set(key))

actual fun DocumentReference.delete_(): TaskVoid =
    TaskVoid(delete())
actual fun DocumentReference.get_(): TaskData<DocumentSnapshot> =
    TaskData(get())
actual val DocumentReference.id_: String
    get() = getId()

actual fun DocumentReference.addSnapshotListener_(listener: (DocumentSnapshot?, FirebaseFirestoreException?) -> Unit): ListenerRegistration =
    wrapListenerRegistration(addSnapshotListener { querySnapshot, firebaseFirestoreException ->
        listener(querySnapshot, firebaseFirestoreException?.let
        { FirebaseFirestoreException(firebaseFirestoreException) })
    })

actual fun DocumentReference.addSnapshotListener_(
    metadataChanges: MetadataChanges,
    listener: (DocumentSnapshot?, FirebaseFirestoreException?) -> Unit
): ListenerRegistration =
    wrapListenerRegistration(
        addSnapshotListener(
            metadataChanges as com.google.firebase.firestore.MetadataChanges,
            object : EventListener<DocumentSnapshot> {
                override fun onEvent(
                    documentSnapshot: DocumentSnapshot?,
                    firebaseFirestoreException: com.google.firebase.firestore.FirebaseFirestoreException?
                ) {
                    listener(
                        documentSnapshot,
                        firebaseFirestoreException?.let { FirebaseFirestoreException(firebaseFirestoreException) })
                }
            })
    )

actual fun DocumentReference.collection(collectionPath:String): CollectionReference = collection(collectionPath)
actual fun DocumentReference.get_(source: Source): TaskData<DocumentSnapshot> =
    TaskData(get(sourceToJvmSource(source)))

internal fun sourceToJvmSource(source: Source): com.google.firebase.firestore.Source = when(source){
    Source.CACHE -> com.google.firebase.firestore.Source.CACHE
    Source.DEFAULT -> com.google.firebase.firestore.Source.DEFAULT
    Source.SERVER -> com.google.firebase.firestore.Source.SERVER
}

actual val DocumentReference.firestore_: FirebaseFirestore
    get() = firestore
actual val DocumentReference.parent_: CollectionReference
    get() = parent
actual val DocumentReference.path: String
    get() = getPath()

actual fun DocumentReference.update_(key: Map<String, Any?>): TaskVoid =
    TaskVoid(update(key))

actual fun DocumentReference.set_(
    key: Map<String, Any?>,
    options: SetOptions
): TaskVoid =
    TaskVoid(set(key, setOptionsToJvmSetOptions(options)))

internal fun setOptionsToJvmSetOptions(so:SetOptions):com.google.firebase.firestore.SetOptions =
    when(so){
        is SetOptions.Merge -> com.google.firebase.firestore.SetOptions.merge()
        is SetOptions.MergeFields -> com.google.firebase.firestore.SetOptions.mergeFieldPaths(so.fields)
        is SetOptions.MergeStrings -> com.google.firebase.firestore.SetOptions.mergeFields(so.fields)
    }