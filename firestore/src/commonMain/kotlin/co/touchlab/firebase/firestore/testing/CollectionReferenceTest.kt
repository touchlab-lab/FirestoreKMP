package co.touchlab.firebase.firestore.testing

import co.touchlab.firebase.firestore.*

class CollectionReferenceTest : BaseFirestoreTest() {
    fun addDocument() {
        val collection = testdataCollection()
        val taskData = collection.addDocument(mapOf(Pair("name", "coll test")))
        var docRef: DocumentReference? = null
        taskData.addSuccessListener {
            it.getDocument().addSuccessListener {
                assertEquals(it.getString("name"), "coll test")
            }
        }
    }

    private fun testdataCollection() = firestore.collection("testdata")

    fun documentNew() {
        val documentReference = testdataCollection().document()
        documentReference.setData(mapOf(Pair("name", "documentNew test")))
            .addSuccessListener {
                documentReference.getDocument().addSuccessListener {
                    assertEquals(it.getString("name"), "documentNew test")
                }
            }
    }

    fun documentExists() {
        testdataCollection().document("d4X2xqsqCBsxrRNuiDZ2")
            .getDocument().addSuccessListener {
                assertEquals(it.getString("name"), "MyData")
            }
    }

    fun documentDoesNotExist() {
        testdataCollection().document("d4X2xqsqCBsxrRNuiDZ")
            .getDocument().addListeners({
                throw AssertionError("Should be an error")
            }) {

            }
    }

    fun id() {
        testdataCollection().document("d4X2xqsqCBsxrRNuiDZ2")
            .getDocument().addSuccessListener {
                assertEquals(it.getString("name"), "MyData")
            }
    }

    private fun assertEquals(string: Any?, s1: Any?) {
        if (string != s1)
            throw AssertionError("not equal $string $s1")
    }
}