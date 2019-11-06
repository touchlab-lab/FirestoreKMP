package co.touchlab.firebase.firestore

actual typealias DocumentReference = com.google.firebase.firestore.DocumentReference

actual fun DocumentReference.addSnapshotListener_(
    metadataChanges: MetadataChanges?,
    listener: (DocumentSnapshot?, FirebaseFirestoreException?) -> Unit
): ListenerRegistration =
    if(metadataChanges == null){
        addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            listener(querySnapshot, firebaseFirestoreException?.let
            { FirebaseFirestoreException(firebaseFirestoreException) })
        }
    }else {
        addSnapshotListener(
            metadataChanges as com.google.firebase.firestore.MetadataChanges
        ) { documentSnapshot, firebaseFirestoreException ->
            listener(
                documentSnapshot,
                firebaseFirestoreException?.let { FirebaseFirestoreException(firebaseFirestoreException) })
        }
    }

actual fun DocumentReference.collection(collectionPath: String): CollectionReference = collection(collectionPath)

actual fun DocumentReference.delete_(): TaskVoid =
    TaskVoid(delete())

actual fun DocumentReference.getDocument(source: Source?): TaskData<DocumentSnapshot> =
    if(source == null){
        TaskData(get())
    }else{
        TaskData(get(sourceToJvmSource(source)))
    }

actual val DocumentReference.firestore: FirebaseFirestore
    get() = firestore

actual val DocumentReference.id: String
    get() = getId()

actual val DocumentReference.parent: CollectionReference
    get() = parent

actual val DocumentReference.path: String
    get() = getPath()

actual fun DocumentReference.setData(key: Map<String, Any?>, options:SetOptions?): TaskVoid =
    if(options == null){
        TaskVoid(set(key))
    }else {
        TaskVoid(set(key, setOptionsToJvmSetOptions(options)))
    }

actual fun DocumentReference.updateData(key: Map<String, Any?>): TaskVoid =
    TaskVoid(update(key))

internal fun sourceToJvmSource(source: Source): com.google.firebase.firestore.Source = when (source) {
    Source.CACHE -> com.google.firebase.firestore.Source.CACHE
    Source.DEFAULT -> com.google.firebase.firestore.Source.DEFAULT
    Source.SERVER -> com.google.firebase.firestore.Source.SERVER
}

internal fun setOptionsToJvmSetOptions(so: SetOptions): com.google.firebase.firestore.SetOptions =
    when (so) {
        is SetOptions.Merge -> com.google.firebase.firestore.SetOptions.merge()
        is SetOptions.MergeFields -> com.google.firebase.firestore.SetOptions.mergeFieldPaths(so.fields)
        is SetOptions.MergeStrings -> com.google.firebase.firestore.SetOptions.mergeFields(so.fields)
    }

