package co.touchlab.firebase.firestore

import kotlinx.coroutines.launch
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse

class CollectionReferenceTest : BaseCoroutineTest() {
    @Test
    fun addDocument() {
        val collection = testdataCollection(firestore)
        val taskData = collection.addDocument(mapOf(Pair("name", "coll test")))
        var docRef: DocumentReference? = null
        taskData.addSuccessListener {
            it.getDocument().addSuccessListener {
                assertEquals(it.getString("name"), "coll test")
            }
        }
    }


    @Test
    fun documentNew() {
        val documentReference = testdataCollection(firestore).document()
        documentReference.setData(mapOf(Pair("name", "documentNew test")))
            .addSuccessListener {
                documentReference.getDocument().addSuccessListener {
                    assertEquals(it.getString("name"), "documentNew test")
                }
            }
    }

    @Test
    fun documentExists() {
        testdataCollection(firestore).document("d4X2xqsqCBsxrRNuiDZ2")
            .getDocument().addSuccessListener {
                assertEquals(it.getString("name"), "MyData")
            }
    }

    @Test
    fun documentDoesNotExist() {
        testdataCollection(firestore).document("d4X2xqsqCBsxrRNuiDZ")
            .getDocument().addSuccessListener {
                assertFalse(it.exists)
            }
    }

    @Test
    fun id() {
        testdataCollection(firestore).document("d4X2xqsqCBsxrRNuiDZ2")
            .getDocument().addSuccessListener {
                assertEquals(it.getString("name"), "MyData")
            }
    }

    @Test
    fun path() {
        launch {
            assertEquals(
                "testdata/9P8gSdopBC0MAY0TGzw7/childcollection/I9VF1JXB4NhIQ04xiVod",
                testdataCollection(firestore).suspendGet()
                    .documents_.filter { it.id == "9P8gSdopBC0MAY0TGzw7" }.first()
                    .reference.collection("childcollection")
                    .suspendGet().documents_.first().reference.path
            )
        }
    }

    private fun assertEquals(string: Any?, s1: Any?) {
        if (string != s1)
            throw AssertionError("not equal $string $s1")
    }
}