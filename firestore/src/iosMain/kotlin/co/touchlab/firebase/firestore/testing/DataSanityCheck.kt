package co.touchlab.firebase.firestore


object DataSanityCheck {
    /*fun runSomeData(){
        FIRApp.configure()
        FIRFirestore.initialize()

        EventRepo.getEvents({
            println("Success ${it.count()}")
            printDocs(it.documents_)
        }, {
            println("Failure")
        })

        val newId = Random.nextLong().toString(16)
        EventRepo.createEvent(
            Event(
                id = newId,
                userId = 224,
                title = "From Code",
                description = "arst",
                createdAt = timestampNow(),
                startDate = timestampNow(),
                endDate = timestampNow(),
                location = "Home",
                host = "Kevin"
            ),{
                println("createEvent Success")
            }, {
                println("createEvent Fail")
            })


        EventRepo.getEventWithId(EventRepo.allEventsCollection, newId, {
            try {
                val event = it.data_()?.toEvent()
                event?.let {e ->
                    println("New doc: $e")
                }
            }catch (e:RuntimeException){
//                Log.w(TAG,e.localizedMessage)
            }
        }, {
            //            Log.w(TAG,it.localizedMessage)
        })
    }

    private fun printDocs(docs:List<DocumentSnapshot>){
        docs.map { it.data_()?.toEvent() }.filterNotNull().filter { it.id != null }.map { it.id!! }.forEach {
            EventRepo.getEventWithId(EventRepo.allEventsCollection, it, {
                try {
                    val event = it.data_()?.toEvent()
                    event?.let {e ->
                        println("Found doc: $e")
                    }
                }catch (e:RuntimeException){
//                Log.w(TAG,e.localizedMessage)
                }
            }, {
                //            Log.w(TAG,it.localizedMessage)
            })
        }
    }*/
}