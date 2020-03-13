set -ev

PODS_ROOT=/Users/magnumrocha/personal/FirestoreKMP/iosApp/Pods
./gradlew :firestore:build \
-Pkotlin.native.cocoapods.target=ios_x64 \
-Pkotlin.native.cocoapods.paths.headers="/Users/magnumrocha/personal/FirestoreKMP/iosApp/Pods/Headers/Public" \
-Pkotlin.native.cocoapods.paths.frameworks="/Users/magnumrocha/personal/FirestoreKMP/iosApp/Pods/FirebaseCore /Users/magnumrocha/personal/FirestoreKMP/iosApp/Pods/FirebaseFirestore"
