package co.touchlab.firebase.firestore

import co.touchlab.stately.freeze
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.test.*

class DocumentReferenceTest : BaseCoroutineTest() {
    @Test
    fun addSnapshotListener() {
        val collection = testdataCollection(firestore)
        val document = collection.document("1KHhlLi0gwpgD9XvxHcJ")
        var listenerCount = 0
        var registration:ListenerRegistration? = null
        registration = document
            .addSnapshotListener_ { documentSnapshot, firebaseFirestoreException ->
                documentSnapshot?.let {
                    //Only called once?
                    assertEquals(listenerCount++, 0)

                    assertTrue(isMainThread)
                    assertEquals(it.getString("name"), "coll test")
                    registration?.remove()

                    document.updateData(mapOf(Pair("arst", "zzz ${Random.nextInt()}")))
                }
            }

        document.updateData(mapOf(Pair("arst", "ttt ${Random.nextInt()}")))
    }

    @Test
    fun collection() {
        launch {
            val document = firestore.document("testdata/9P8gSdopBC0MAY0TGzw7")
            assertEquals(1, document.collection("childcollection").suspendGet().size)
        }
    }

    @Test
    fun delete() {
        val ref = testdataCollection(firestore).document()
        ref.setData(mapOf(Pair("name", "ppp")))
            .addSuccessListener {
                ref.getDocument().addSuccessListener {
                    assertTrue(it.exists)
                    assertEquals(it.getString("name"), "ppp")

                    ref.delete_().addSuccessListener {
                        ref.getDocument().addSuccessListener {
                            assertFalse(it.exists)
                        }
                    }
                }
            }
    }

    @Test
    fun getDocument() {
        testdataCollection(firestore)
            .document("9P8gSdopBC0MAY0TGzw7")
            .getDocument()
            .addSuccessListener {
                assertTrue { it.exists }
            }
    }

    @Test
    fun getDocumentSource() {
        val document = firestore.collection("games")
            .document("GUS53auHYXYd5BNjENLh")

        document.getDocument(Source.CACHE)
            .addListeners({
                fail("Should no succeed")
            }) {
                assertTrue(it.message!!.contains("Failed to get document from cache"))
            }
    }

    @Test
    fun firestore() {
        assertTrue(testdataCollection(firestore)
            .document("1KHhlLi0gwpgD9XvxHcJ")
            .firestore is FirebaseFirestore)
    }

    @Test
    fun id() {
        assertEquals(
            testdataCollection(firestore)
                .document("1KHhlLi0gwpgD9XvxHcJ").id,
            "1KHhlLi0gwpgD9XvxHcJ"
        )
    }

    @Test
    fun setData() {
        val document = testdataCollection(firestore)
            .document()

        document
            .setData(mapOf(Pair("name", "rrr")))
            .addSuccessListener {
                document.getDocument().addSuccessListener {
                    assertEquals(it.getString("name"), "rrr")
                    document.delete_()
                }
            }
    }

    @Test
    fun updateData() {
        val document = testdataCollection(firestore)
            .document()

        document
            .setData(mapOf(Pair("name", "aaa")))
            .addSuccessListener {
                document.getDocument().addSuccessListener {
                    assertEquals(it.getString("name"), "aaa")
                    document.updateData(mapOf(Pair("name", "bbb")))
                        .addSuccessListener {
                            document.getDocument().addSuccessListener {
                                assertEquals(it.getString("name"), "bbb")
                                document.delete_()
                            }
                        }
                }
            }
    }

    @Test
    fun suspendMethods(){
        val document = testdataCollection(firestore)
            .document()

        launch {
            document.suspendSet(mapOf(Pair("name", "aaa")))
            assertTrue(document.suspendGet().exists)
            assertEquals(document.suspendGet().getString("name"), "aaa")
            document.suspendDelete()
            assertFalse(document.suspendGet().exists)
        }
    }
}