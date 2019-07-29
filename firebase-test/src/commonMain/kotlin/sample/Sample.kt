package sample

import co.touchlab.firebase.firestore.FirebaseFirestore

expect class Sample() {
    fun checkMe(): Int
}

expect object Platform {
    val name: String
}

fun hello(): String = "Hello from ${Platform.name}"

class Proxy {
    fun proxyHello() = hello()
}



fun main() {
    println(hello())
}

expect fun makeTestFirestore():FirebaseFirestore