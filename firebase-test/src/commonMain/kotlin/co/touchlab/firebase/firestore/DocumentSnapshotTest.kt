package co.touchlab.firebase.firestore

import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.test.*

class DocumentSnapshotTest : BaseCoroutineTest() {
    @Test
    fun contains() {
        launch {
            val documentSnapshot = testdataCollection(firestore)
                .suspendGet()
                .documents_.get(0)
            assertTrue(documentSnapshot.contains("name"))
            assertFalse(documentSnapshot.contains("namettt"))
        }
    }

    @Test
    fun exists() {
        launch {
            val documentSnapshot = testdataCollection(firestore)
                .suspendGet()
                .documents_.get(0)

            assertTrue(documentSnapshot.exists)
        }
    }

    @Test
    fun referenceId() {
        launch {
            val documentSnapshot = testdataCollection(firestore).suspendGet()
                .documents_.find { it.id == "ivGVhL20WeUyfmeJvAWu" }!!

            assertEquals(documentSnapshot.reference.id, "ivGVhL20WeUyfmeJvAWu")
        }
    }

    @Test
    fun metadata() {
        launch {
            val documentMetadata = testdataCollection(firestore).suspendGet()
                .documents_.first().metadata

            //TODO: Need a way to test this. Just accessing values.
            println("fromCache: ${documentMetadata.fromCache}")
            documentMetadata.fromCache
        }
    }

    @Test
    fun get() {
        launch {
            val documentSnapshot = testdataCollection(firestore).suspendGet()
                .documents_.filter { it.id == "0sOVY8dKfdyEAk7MaoCT" }.first()

            assertEquals("ppp", documentSnapshot.get("name"))
            assertNull(documentSnapshot.get("ttarst"))
        }
    }

    @Test
    fun data() {
        launch {
            val documentSnapshot = testdataCollection(firestore).suspendGet()
                .documents_.filter { it.id == "0sOVY8dKfdyEAk7MaoCT" }.first()

            assertEquals("ppp", documentSnapshot.data_()?.get("name"))
        }
    }

    @Test
    fun getTypes() {
        launch {
            val documentSnapshot = firestore.collection("testreads").suspendGet()
                .documents_.first()
            asserstBetween(
                documentSnapshot.getGeoPoint("ageo")!!.latitude,
                40.7916,
                .001
            )
            asserstBetween(
                documentSnapshot.getGeoPoint("ageo")!!.longitude,
                -73.9448,
                .001
            )
            assertTrue(documentSnapshot.getBoolean("abool")!!)
            assertNotNull(documentSnapshot.getTimestamp("atime"))
            assertEquals(123, documentSnapshot.getLong("aval"))
            asserstBetween(123.toDouble(), documentSnapshot.getDouble("aval")!!, .0001)
        }
    }
}

internal fun asserstBetween(aval: Double, bval: Double, variance: Double) {
    assertTrue(abs(aval - bval) <= variance, "Values differ by more than $variance - $aval/$bval")
}