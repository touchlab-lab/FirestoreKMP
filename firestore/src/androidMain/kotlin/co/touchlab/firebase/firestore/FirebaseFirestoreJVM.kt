package co.touchlab.firebase.firestore


actual typealias FirebaseFirestore = com.google.firebase.firestore.FirebaseFirestore

actual fun FirebaseFirestore.collection(collectionPath: String): CollectionReference = collection(collectionPath)
actual fun getFirebaseInstance(): FirebaseFirestore = com.google.firebase.firestore.FirebaseFirestore.getInstance()