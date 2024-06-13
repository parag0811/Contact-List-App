package parag.ui.contactlistapp


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TopAppBar //Alpha Version for scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import parag.ui.contactlistapp.ui.theme.BlueMid
import parag.ui.contactlistapp.ui.theme.poppinsFontFamily


@Composable
fun AppBarView(
    title: String,
    navController: NavController
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.background,
        title = {
            Text(
                text = title,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        },
        actions = {
            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .height(40.dp)
                    .width(40.dp)
                    .clip(CircleShape)
                    .background(BlueMid)
                    .clickable {
                        navController.navigate(Screen.AddScreen.route + "/0L") //Sends to the add screen (fresh screen)
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    )
}