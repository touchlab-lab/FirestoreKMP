package sample

import co.touchlab.firebase.firestore.FirebaseFirestore
import co.touchlab.firebase.firestore.getFirebaseInstance

import kotlin.native.internal.test.testLauncherEntryPoint

@Suppress("unused")
fun kickOffTest():Int {

    return testLauncherEntryPoint(emptyArray())
}


actual class Sample {
    actual fun checkMe() = 7
}

actual object Platform {
    actual val name: String = "iOS"
}

actual fun makeTestFirestore(): FirebaseFirestore = getFirebaseInstance()