package co.touchlab.firebase.firestore

fun wrapListenerRegistration(lr:com.google.firebase.firestore.ListenerRegistration):ListenerRegistration =
    WrapLR(lr)

internal class WrapLR(private val lr:com.google.firebase.firestore.ListenerRegistration):ListenerRegistration{
    override fun remove() {
        lr.remove()
    }
}