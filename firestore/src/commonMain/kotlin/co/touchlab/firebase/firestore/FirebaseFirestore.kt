package co.touchlab.firebase.firestore

expect class FirebaseFirestore

expect fun FirebaseFirestore.collection(collectionPath:String):CollectionReference

expect fun getFirebaseInstance():FirebaseFirestore
