package co.touchlab.testing

import kotlin.reflect.KClass

expect annotation class RunWith(val value: KClass<out Runner>)
expect abstract class Runner
expect class AndroidJUnit4 : Runner