package co.touchlab.firebase.storage

actual typealias FirebaseStorage = com.google.firebase.storage.FirebaseStorage

actual var FirebaseStorage.maxDownloadRetryTimeMillis_: Long
    get() = maxDownloadRetryTimeMillis
    set(value) {
        maxDownloadRetryTimeMillis = value
    }
actual var FirebaseStorage.maxOperationRetryTimeMillis_: Long
    get() = maxOperationRetryTimeMillis
    set(value) {
        maxOperationRetryTimeMillis = value
    }
actual var FirebaseStorage.maxUploadRetryTimeMillis_: Long
    get() = maxUploadRetryTimeMillis
    set(value) {
        maxUploadRetryTimeMillis = value
    }

actual fun FirebaseStorage.getReference(): StorageReference =
    reference

actual fun FirebaseStorage.getReference(location: String): StorageReference =
    getReference(location)

actual fun FirebaseStorage.getReferenceFromUrl(fullUrl: String): StorageReference =
    getReferenceFromUrl(fullUrl)

actual fun getInstance(): FirebaseStorage =
    com.google.firebase.storage.FirebaseStorage.getInstance()

actual fun getInstance(url: String): FirebaseStorage =
    com.google.firebase.storage.FirebaseStorage.getInstance(url)