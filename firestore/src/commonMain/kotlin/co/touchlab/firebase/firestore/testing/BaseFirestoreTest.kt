package co.touchlab.firebase.firestore.testing

import co.touchlab.firebase.firestore.FirebaseFirestore
import co.touchlab.stately.concurrency.AtomicReference
import co.touchlab.stately.concurrency.value
import co.touchlab.stately.freeze

abstract class BaseFirestoreTest {
    private val fsAtom = AtomicReference<FirebaseFirestore?>(null)

    var firestore:FirebaseFirestore
    get() = fsAtom.value!!
    set(value) {
        fsAtom.value = value.freeze()
    }
}