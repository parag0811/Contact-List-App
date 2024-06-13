package parag.ui.contactlistapp.ui.theme.data

import kotlinx.coroutines.flow.Flow


//it acts as a mediator between MVVM and DAO operations

class ContactRepository (
    private val contactDAO: ContactDAO //directly from our Dao class (not the database class)
) {

    suspend fun addAContact(contact: Contact) {
        contactDAO.addAContact(contact)
    }

    //TODO changed
    fun getContacts(): Flow<List<Contact>> = contactDAO.getAllContacts()


    fun getAContactByID(id: Long): Flow<Contact> {
        return contactDAO.getAContactByID(id)
    }

    suspend fun updateAContact(contact: Contact) {
        contactDAO.updateAContact(contact)
    }

    suspend fun deleteAContact(contact: Contact) {
        contactDAO.deleteAContact(contact)
    }
}