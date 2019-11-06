package co.touchlab.firebase.firestore

import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertNotNull

class FirebaseFirestoreTest : BaseCoroutineTest() {
    @Test
    fun batch(){
        val batch = firestore.batch()
        val collection = testdataCollection(firestore)
        val newDoc = collection.document()
        batch.set(newDoc, mapOf(Pair("name", "uuu ${Random.nextInt()}")))
        batch.commit_().addSuccessListener {
            newDoc.delete_()
        }
    }

    @Test
    fun collection(){
        assertNotNull(firestore.collection("testdata"))
    }

    @Test
    fun disableEnableNetwork(){
        launch {
            val doc = testdataCollection(firestore).suspendGet().documents_.get(0).reference

            firestore.suspendDisableNetwork()

            assertFails {
                doc.suspendGet(Source.SERVER)
            }

            assertEquals(doc.suspendGet(Source.CACHE).id, doc.id)

            firestore.suspendEnableNetwork()

            assertEquals(doc.suspendGet(Source.SERVER).id, doc.id)
        }
    }

    //TODO: I'm not really sure how this feature works
    fun collectionGroup(){

    }

    //TODO: This may be difficult to actually verify other than that calling it works
    fun settings(){

    }
}