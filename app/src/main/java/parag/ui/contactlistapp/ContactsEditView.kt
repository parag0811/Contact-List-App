package parag.ui.contactlistapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import parag.ui.contactlistapp.ui.theme.BlueMid
import parag.ui.contactlistapp.ui.theme.data.Contact
import parag.ui.contactlistapp.ui.theme.poppinsFontFamily

@Composable
fun ContactsEditView(
    id: Long,
    navController: NavController,
    viewModel: ContactViewModel,
) {

    val snackMessage = remember {
        mutableStateOf("")
    }

    //to run asynchronous methods (storing data)
    val scope = rememberCoroutineScope()

    // Updates the UI when item is clicked (Same output as we enter the details)
    //if the contact object is null, the code inside the collect block will not be executed
    LaunchedEffect(id) {
        if (id != 0L) {
            viewModel.getAContactbyID(id).collect { contact ->
                if (contact != null) {
                    viewModel.onContactNameChanged(contact.name)
                    viewModel.onContactNumberChanged(contact.mobileNumber)
                    viewModel.onContactEmailChanged(contact.mailID)
                } else {
                    // Handle the case where the contact is null
                    // For example, you can set default values or show an error message
                    viewModel.onContactNameChanged("")
                    viewModel.onContactNumberChanged("")
                    viewModel.onContactEmailChanged("")
                    snackMessage.value = "Contact not found"
                }
            }
        } else {
            viewModel.onContactNameChanged("")
            viewModel.onContactNumberChanged("")
            viewModel.onContactEmailChanged("")
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 8.dp)
    ) {
        Row {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { navController.navigateUp() }
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = null,
                tint = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        if(
                            viewModel.contactNameState.isNotEmpty() &&
                            viewModel.contactNumberState.isNotEmpty() &&
                            viewModel.contactEmailState.isNotEmpty()
                        ){
                            viewModel.DeleteAContact(Contact(
                                id = id,
                                name = viewModel.contactNameState.trim(),
                                mobileNumber = viewModel.contactNumberState.trim(),
                                mailID = viewModel.contactEmailState.trim()
                            ))
                            navController.navigateUp()
                        }else{
                            snackMessage.value = "No contacts"
                            navController.navigateUp()
                        }
                    }
            ) }
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(id = R.drawable.user_preview),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.padding(24.dp))
        ContactsTextFieldForName(
            label = "Name",
            value = viewModel.contactNameState,
            onValueChange = {//it:String here which i get from Contacttextfield -- > onValuechanged updates our viewModel with it (refer to String )
                viewModel.onContactNameChanged(it)
            })

        Spacer(modifier = Modifier.padding(12.dp))
        ContactsTextFieldforNumber(
            label = "Mobile Number",
            value = viewModel.contactNumberState,
            onValueChange = {//it:String here which i get from Contacttextfield -- > onValuechanged updates our viewModel with it (refer to String )
                viewModel.onContactNumberChanged(it)
            })

        Spacer(modifier = Modifier.padding(12.dp))
        ContactsTextFieldForMail(
            label = "Email ID",
            value = viewModel.contactEmailState,
            onValueChange = {//it:String here which i get from Contacttextfield -- > onValuechanged updates our viewModel with it (refer to String )
                viewModel.onContactEmailChanged(it)
            })

        Spacer(modifier = Modifier.padding(12.dp))
        DefaultButton()

        Spacer(modifier = Modifier.padding(16.dp))
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                if (viewModel.contactNameState.isNotEmpty() &&
                    viewModel.contactNumberState.isNotEmpty() &&
                    viewModel.contactEmailState.isNotEmpty()
                ) {
                    if (id != 0L) {
                        //TODO Update Wish
                        viewModel.UpdateAContact(
                            Contact(
                                id = id,
                                name = viewModel.contactNameState.trim(),
                                mobileNumber = viewModel.contactNumberState.trim(),
                                mailID = viewModel.contactEmailState.trim()
                            )
                        )
                        snackMessage.value = "Contact has been Updated"

                    } else {
                        //TODO Add Wish
                        viewModel.addContacts(
                            Contact(
                                name = viewModel.contactNameState.trim(),
                                mobileNumber = viewModel.contactNumberState.trim(),
                                mailID = viewModel.contactEmailState.trim()
                            )
                        )
                        snackMessage.value = "Contact has been Added"
                    }
                } else {
                    snackMessage.value = "Enter all the details"
                }
                scope.launch {
                    navController.navigateUp()
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = BlueMid
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 2.dp,
                pressedElevation = 4.dp,
                disabledElevation = 2.dp
            )
        ) {
            Text(
                text = if (id != 0L) "Update Contact"
                else "Add Contact",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Composable
fun ContactsTextFieldForName(
    label: String,
    value: String,
    onValueChange: (String) -> Unit //Whenever value changes i am going to pass a string . Checks and update if we change something
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            //using predef colors
            textColor = Color.Black,
            //using our own colors
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black)
        )
    )
}

@Composable
fun ContactsTextFieldforNumber(
    label: String,
    value: String,
    onValueChange: (String) -> Unit //Whenever value changes i am going to pass a string . Checks and update if we change something
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            //using predef colors
            textColor = Color.Black,
            //using our own colors
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black)
        )
    )
}

@Composable
fun ContactsTextFieldForMail(
    label: String,
    value: String,
    onValueChange: (String) -> Unit //Whenever value changes i am going to pass a string . Checks and update if we change something
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            //using predef colors
            textColor = Color.Black,
            //using our own colors
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black)
        )
    )
}


@Composable
fun DefaultButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Call,
            contentDescription = null,
            modifier = Modifier
                .size(36.dp)
                .weight(1f)
                .clickable { }
        )
        Icon(
            imageVector = Icons.Filled.MailOutline,
            contentDescription = null,
            modifier = Modifier
                .size(36.dp)
                .weight(1f)
                .clickable { }
        )
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = null,
            modifier = Modifier
                .size(36.dp)
                .weight(1f)
                .clickable { }
        )
    }
}