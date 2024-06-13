package parag.ui.contactlistapp.ui.theme.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact-table")
data class Contact(
    @PrimaryKey(autoGenerate = true) //Increment our ID by adding entries to our database
    val id : Long = 0L,

    @ColumnInfo(name="contact-name")
    var name : String = "",

    @ColumnInfo(name="contact-number")
    val mobileNumber: String = "",

    @ColumnInfo(name="contact-mail")
    val mailID : String = ""
)