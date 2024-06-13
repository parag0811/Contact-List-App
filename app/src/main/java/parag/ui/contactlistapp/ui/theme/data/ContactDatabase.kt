package parag.ui.contactlistapp.ui.theme.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (
    entities = [Contact::class],
    version = 1,
    exportSchema = false
)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao () : ContactDAO
}