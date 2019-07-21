package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRGeoPoint

actual typealias GeoPoint = FIRGeoPoint

actual val GeoPoint.latitude: Double
    get() = latitude()
actual val GeoPoint.longitude: Double
    get() = longitude()