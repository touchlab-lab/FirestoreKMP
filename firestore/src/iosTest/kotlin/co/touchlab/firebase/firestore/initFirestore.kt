package co.touchlab.firebase.firestore

import cocoapods.FirebaseCore.FIRApp
import cocoapods.FirebaseFirestore.FIRFirestore

actual fun initFirestore() {
    FIRApp.configure()
    FIRFirestore.initialize()
}