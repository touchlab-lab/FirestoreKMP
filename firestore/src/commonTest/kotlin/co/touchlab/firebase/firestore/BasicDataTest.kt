package co.touchlab.firebase.firestore

/*import co.touchlab.testing.makeTestFirebase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals*/

class BasicDataTest {
    /*var sb:FirebaseFirestore? = null

    @BeforeTest
    fun setup(){
        sb = makeTestFirebase()
    }

    @Test
    fun testBasicData(){
        if(sb == null)
            return

        val collection = testdataCollection()
        val taskData = collection.addDocument(mapOf(Pair("name", "coll test")))
        var docRef: DocumentReference? = null
        taskData.addSuccessListener {
            it.getDocument().addSuccessListener {
                assertEquals(it.getString("name"), "coll test")
            }
        }
    }

    private fun testdataCollection() = sb!!.collection("testdata")*/
}