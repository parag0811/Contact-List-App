package parag.ui.contactlistapp

import android.content.Context
import androidx.room.Room
import parag.ui.contactlistapp.ui.theme.data.ContactDatabase
import parag.ui.contactlistapp.ui.theme.data.ContactRepository


//OBJECT is a sinlgeton and there will be no other instances of it or another obnject named as graph

object Graph {
    lateinit var database: ContactDatabase
    //Until an instance needs to be initialized through dependency injection

    //initialize once only when needed not everytime
    //Used in private val VM to load the repositori operations
    val contactRepository by lazy {
        ContactRepository(contactDAO = database.contactDao())
    }

    fun provide(context: Context) {//in which contact database should be built
        database =
            Room.databaseBuilder(context, ContactDatabase::class.java, "contactList.db").build()
    }
    //the moment we call the provide function it will build our database:)
}
