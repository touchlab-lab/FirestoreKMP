package co.touchlab.firebase.firestore

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.FlowCollector


class MainActivity : AppCompatActivity(), CoroutineScope by MainScope()  {

    private val flow = getFirebaseInstance().collection("testdata")
        .asFlow()

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch {
            flow.collect(object : FlowCollector<QuerySnapshot> {
                override suspend fun emit(value: QuerySnapshot) {
                    findViewById<ListView>(R.id.games).adapter =
                        ArrayAdapter<String>(
                            this@MainActivity,
                            android.R.layout.simple_list_item_1,
                            value.documents.map { it.data?.get("name") as String? }
                                .filterNotNull()
                        )

                }
            })
        }

        findViewById<Button>(R.id.addButton).setOnClickListener {
            getFirebaseInstance().collection("testdata")
                .document().set(mapOf(Pair("name", "Some data ${System.currentTimeMillis()}")))
        }

    }


}
