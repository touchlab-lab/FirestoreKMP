# Firestore SDK for Kotlin Multiplatform

## Not Supported!

This library was produced for a client project, but open sourced in support of a talk on [Kotlin Multiplatform library development](https://vimeo.com/371460823). We don't plan for ongoing support, unless there is renewed client interest.

## Overview

Library wrapping the Firestore SDK for Kotlin Multiplatform with clients for Android and iOS. Shared common code can
run methods that get delegated to the platform specific SDKs.

## Usage

Use Koltin 1.3.50.

Dependency config

```groovy
sourceSets {
    commonMain {
        dependencies {
            implementation 'co.touchlab:firestore:0.2.5'
        }
    }
}
```

You'll need to config both Android and iOS as you normally would for Firestore, but then you should be able to get the
Firestore instance like this.

```kotlin
getFirebaseInstance()
```

Then you can do Firestore things.

## Droidcon

[See live example here](https://github.com/touchlab/DroidconKotlin/blob/master/sessionize/lib/src/commonMain/kotlin/co/touchlab/sessionize/SponsorModel.kt#L25)
