package parag.ui.contactlistapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeView(
    navController: NavController,
    viewModel: ContactViewModel
) {
    Scaffold(
        topBar = {
            AppBarView(
                title = "Contacts", navController = navController,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it) //it --> is used to respect the padding of the top app bar by the scaffold
        ) {
            SearchBar()
            ContactListScreenDisplay(viewModel = viewModel, navController = navController)
        }
    }
}


@Composable
fun ContactListScreenDisplay(
    navController: NavController,
    viewModel: ContactViewModel
) {

    val contactlist = viewModel.getAllContacts.collectAsState(initial = listOf())

    LazyColumn {
        items(contactlist.value) { data ->
            ContactsItems(data = data) {
                val id = data.id
                navController.navigate(Screen.AddScreen.route + "/$id")
            }
        }
    }
}

@Preview
@Composable
fun SearchBar() {
    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 12.dp, bottom = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .height(40.dp)
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .clickable {

                }
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(8.dp)
            )
        }
    }
}