package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRQuerySnapshot

actual typealias QuerySnapshot = FIRQuerySnapshot

actual val QuerySnapshot.documentChanges_: List<DocumentChange>
    get() = documentChanges as List<DocumentChange>
actual val QuerySnapshot.documents_: List<DocumentSnapshot>
    get() = documents as List<DocumentSnapshot>