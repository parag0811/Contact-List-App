package parag.ui.contactlistapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable //Contains navhost and controller
fun Navigation(
    viewModel: ContactViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route //Where should Navigation start from
    ) {
        composable(Screen.HomeScreen.route) {
            HomeView(
                navController,
                viewModel
            ) //What screen should be inside of our Home screen route
        }

        composable(
            Screen.AddScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
        ) {entry->
            val id = if (entry.arguments != null) entry.arguments!!.getLong("id") else 0L
            ContactsEditView(id = id, navController = navController, viewModel = viewModel)
        }
    }
}