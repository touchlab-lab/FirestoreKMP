package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRListenerRegistrationProtocol

fun wrapListenerRegistration(lr:FIRListenerRegistrationProtocol):ListenerRegistration = WrapLR(lr)

internal class WrapLR(private val lr:FIRListenerRegistrationProtocol):ListenerRegistration{
    override fun remove() {
        lr.remove()
    }
}