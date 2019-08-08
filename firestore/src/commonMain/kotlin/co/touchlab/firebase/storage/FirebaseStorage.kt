package co.touchlab.firebase.storage

expect class FirebaseStorage

expect var FirebaseStorage.maxDownloadRetryTimeMillis_: Long
expect var FirebaseStorage.maxOperationRetryTimeMillis_: Long
expect var FirebaseStorage.maxUploadRetryTimeMillis_: Long

expect fun FirebaseStorage.getReference(): StorageReference
expect fun FirebaseStorage.getReference(location: String): StorageReference
expect fun FirebaseStorage.getReferenceFromUrl(fullUrl: String): StorageReference

expect fun getInstance():FirebaseStorage
expect fun getInstance(url:String):FirebaseStorage