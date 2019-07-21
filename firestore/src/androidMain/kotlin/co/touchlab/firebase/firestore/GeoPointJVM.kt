package co.touchlab.firebase.firestore

actual typealias GeoPoint = com.google.firebase.firestore.GeoPoint

actual val GeoPoint.latitude: Double
    get() = getLatitude()
actual val GeoPoint.longitude: Double
    get() = getLongitude()