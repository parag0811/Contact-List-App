package parag.ui.contactlistapp.ui.theme.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

//All the CRUD operations
@Dao
abstract class ContactDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE) //Adds a contact to our database
    abstract suspend fun addAContact (contactEntity: Contact)

    //Loads all contacts from the table ascending order of the name
    @Query("Select * from `contact-table` order by trim(`contact-name`) collate nocase asc")
    abstract fun getAllContacts (): Flow<List<Contact>> //returns a list of all the contacts from our database

    @Update
    abstract suspend fun updateAContact(contactEntity: Contact)

    @Delete
    abstract suspend fun deleteAContact(contactEntity: Contact)

    @Query("Select * from `contact-table` where id=:id") //it will filter everything out with the same id as below function has
    abstract fun getAContactByID (id : Long): Flow<Contact> //Returns a singular wish

}