package co.touchlab.firebase.firestore

import cocoapods.FirebaseCore.FIRApp
import cocoapods.FirebaseFirestore.FIRFirestore
import kotlinx.coroutines.runBlocking
import platform.Foundation.NSThread

actual fun configTestFirestore() {
    FIRApp.configure()
    FIRFirestore.initialize()
}

actual fun <T> runBlockingTest(block: suspend () -> T) { runBlocking { block() } }
actual val isMainThread: Boolean
    get() = NSThread.isMainThread()