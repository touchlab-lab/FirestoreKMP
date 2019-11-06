package co.touchlab.testing

import co.touchlab.firebase.firestore.FirebaseFirestore
import cocoapods.FirebaseCore.FIRApp
import cocoapods.FirebaseCore.FIROptions
import cocoapods.FirebaseFirestore.FIRFirestore

actual fun makeTestFirebase(): FirebaseFirestore? {
    val opts = FIROptions()
    opts.APIKey = "AIzaSyCZ6TNxi4edc_9gVPpAKCxiRH_55H_qy9E"
    opts.GCMSenderID = "1086000685496"
    opts.bundleID = "touchlab.firetore.test"
    opts.clientID = "1086000685496-qchshe5qcmaasnfm2rnij83lqaasg5ip.apps.googleusercontent.com"
    opts.databaseURL = "https://testdatastores.firebaseio.com"
    opts.storageBucket = "testdatastores.appspot.com"
    opts.projectID = "testdatastores"
    opts.googleAppID = "1:1086000685496:ios:e29f4846c01eab3d"

    FIRApp.configureWithOptions(opts)

    return FIRFirestore.firestore()
}