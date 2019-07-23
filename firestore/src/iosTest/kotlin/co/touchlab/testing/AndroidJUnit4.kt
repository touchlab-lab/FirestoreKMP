package co.touchlab.testing

import kotlin.reflect.KClass

actual annotation class RunWith(actual val value: KClass<out Runner>)
actual abstract class Runner
actual class AndroidJUnit4 : Runner()
