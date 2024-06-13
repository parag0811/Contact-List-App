package parag.ui.contactlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import parag.ui.contactlistapp.ui.theme.data.ContactRepository
import parag.ui.contactlistapp.ui.theme.data.Contact

class ContactViewModel(
    //assigning a contactRepository to initilaize
    private val contactRepository: ContactRepository = Graph.contactRepository
//it is accessing the Repository class that we made to do CRUD operations...
):ViewModel() {
    var contactNameState by mutableStateOf("")
    var contactNumberState by mutableStateOf("")
    var contactEmailState by mutableStateOf("")

    fun onContactNameChanged(newString: String) {
        contactNameState = newString //Now store that in our variable when value is changed
    }

    fun onContactNumberChanged(newString: String) {
        contactNumberState = newString
    }

    fun onContactEmailChanged(newString: String) {
        contactEmailState = newString
    }


    lateinit var getAllContacts: Flow<List<Contact>> //var will be initialized before we actually use it

    init {
        viewModelScope.launch {
            getAllContacts = contactRepository.getContacts()
        }
    }

    fun addContacts(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO)  //Optimise our scope (What threads the courotine will run on)
        {
            contactRepository.addAContact(contact = contact)
        }
    }

    fun getAContactbyID(id: Long): Flow<Contact> {
        return contactRepository.getAContactByID(id)
    }

    fun UpdateAContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO)  //Optimise our scope (What threads the coroutine will run on)
        {
            contactRepository.updateAContact(contact = contact)
        }
    }

    fun DeleteAContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO)  //Optimise our scope (What threads the coroutine will run on)
        {
            contactRepository.deleteAContact(contact = contact)
        }
    }


}