set -ev

PODS_ROOT=/Users/kgalligan/devel_kmp/FirestoreKMP/iosApp/Pods
./gradlew :firestore:build \
-Pkotlin.native.cocoapods.target=ios_x64 \
-Pkotlin.native.cocoapods.paths.headers="/Users/kgalligan/devel_kmp/FirestoreKMP/iosApp/Pods/Headers/Public" \
-Pkotlin.native.cocoapods.paths.frameworks="/Users/kgalligan/devel_kmp/FirestoreKMP/iosApp/Pods/FirebaseCore /Users/kgalligan/devel_kmp/FirestoreKMP/iosApp/Pods/FirebaseFirestore"
