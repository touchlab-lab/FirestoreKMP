# Firestore SDK for Kotlin Multiplatform

Library wrapping the Firestore SDK for Kotlin Multiplatform with clients for Android and iOS. Shared common code can
run methods that get delegated to the platform specific SDKs.

This was originally for a client and now is being used as an example library for a talk on KMP libraries. We'll probably
archive this pretty soon because it'll be a lot of support.

## Usage

Use Koltin 1.3.70.

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


## UPDATE

To build the project you should the option `-x check` to build gradle command line.

```
./gradlew build -x check
```


## Droidcon

[See live example here](https://github.com/touchlab/DroidconKotlin/blob/master/sessionize/lib/src/commonMain/kotlin/co/touchlab/sessionize/SponsorModel.kt#L25)