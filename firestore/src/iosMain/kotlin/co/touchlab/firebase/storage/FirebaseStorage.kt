package co.touchlab.firebase.storage

import cocoapods.FirebaseStorage.FIRStorage

actual typealias FirebaseStorage = FIRStorage

actual var FirebaseStorage.maxDownloadRetryTimeMillis_: Long
    get() = (maxDownloadRetryTime * 1000).toLong()
    set(value) {
        maxDownloadRetryTime = value.toDouble() / 1000
    }

actual var FirebaseStorage.maxOperationRetryTimeMillis_: Long
    get() = (maxOperationRetryTime * 1000).toLong()
    set(value) {
        maxOperationRetryTime = value.toDouble() / 1000
    }

actual var FirebaseStorage.maxUploadRetryTimeMillis_: Long
    get() = (maxUploadRetryTime * 1000).toLong()
    set(value) {
        maxUploadRetryTime = value.toDouble() / 1000
    }

actual fun FirebaseStorage.getReference(): StorageReference = reference()

actual fun FirebaseStorage.getReference(location: String): StorageReference
        = referenceWithPath(location)

actual fun FirebaseStorage.getReferenceFromUrl(fullUrl: String): StorageReference =
    referenceForURL(fullUrl)

actual fun getInstance(): FirebaseStorage = FIRStorage.storage()
actual fun getInstance(url: String): FirebaseStorage =
    FIRStorage.storageWithURL(url)