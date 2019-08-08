package co.touchlab.firebase.auth

import cocoapods.FirebaseAuth.FIRAuth
import cocoapods.FirebaseAuth.FIRAuthStateDidChangeListenerHandle
import cocoapods.FirebaseAuth.FIRIDTokenDidChangeListenerHandle
import kotlinx.cinterop.StableRef

actual typealias FirebaseAuth = FIRAuth

actual fun FirebaseAuth.addAuthStateListener(onAuthStateChanged: (FirebaseAuth) -> Unit): ListenerHandle {
    val listenerRef = StableRef.create(onAuthStateChanged)
    val handle = addAuthStateDidChangeListener { firAuth, firUser ->
        firAuth?.let {
            listenerRef.get().invoke(it)
        }
    }

    return ListenerHandle(handle!!, listenerRef)
}

actual fun FirebaseAuth.removeAuthStateListener(listener: ListenerHandle) {
    removeAuthStateDidChangeListener(listener.handle as FIRAuthStateDidChangeListenerHandle)
    listener.sr.dispose()
}

actual fun FirebaseAuth.addIdTokenListener(onIdTokenChanged: (FirebaseAuth) -> Unit): ListenerHandle {
    val listenerRef = StableRef.create(onIdTokenChanged)
    val handle = addIDTokenDidChangeListener { firAuth, firUser ->
        firAuth?.let {
            listenerRef.get().invoke(it)
        }
    }
    return ListenerHandle(handle!!, listenerRef)
}

actual class ListenerHandle(val handle:Any, val sr:StableRef<*>)

actual fun FirebaseAuth.removeIdTokenListener(listener: ListenerHandle) {
    removeIDTokenDidChangeListener(listener.handle as FIRIDTokenDidChangeListenerHandle)
    listener.sr.dispose()
}

