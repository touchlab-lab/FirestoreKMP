package sample

import kotlin.system.getTimeMillis

data class TestClass(val s:String, val i:Int)

fun runAlloc() {
    val start = getTimeMillis()
    val repeats = repeatCount()
    for(i in 0 until repeats){
        val t = TestClass("arst", i)
    }
    println("runAlloc took: ${getTimeMillis() - start}")
}

class LooperLocal{
    var tc : TestClass? = null
    fun runAlloc() {
        val start = getTimeMillis()
        val repeats = repeatCount()
        for(i in 0 until repeats){
            tc = TestClass("arst", i)
        }
        println("runAlloc took: ${getTimeMillis() - start}")
    }
}

fun repeatCount() = 100_000_000

fun tm() = getTimeMillis()
