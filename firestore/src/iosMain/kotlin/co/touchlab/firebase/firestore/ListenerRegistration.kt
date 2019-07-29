package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRListenerRegistrationProtocol
import kotlinx.cinterop.StableRef

actual interface ListenerRegistration// = FIRListenerRegistrationProtocol
{
    actual fun remove()
}

internal class StableRefListenerRegistration<T:Any>(internal val sr:StableRef<T>, internal val platformListener: FIRListenerRegistrationProtocol) : ListenerRegistration {
    override fun remove() {
        sr.dispose()
        platformListener.remove()
    }
}