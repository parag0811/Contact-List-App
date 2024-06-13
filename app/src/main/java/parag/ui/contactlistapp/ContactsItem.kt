package parag.ui.contactlistapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import parag.ui.contactlistapp.ui.theme.data.Contact


@Composable
fun ContactsItems(
    data: Contact,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .padding(start = 32.dp, end = 8.dp, top = 14.dp, bottom = 14.dp)
            .clickable { onClick() }
    ) {
        Row {
            Box(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .align(Alignment.CenterVertically)
                    .clickable { }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user_preview),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(25.dp)
                        .align(Alignment.Center)
                )
            }
            Column {
                Text(
                    text = data.name,
                    fontSize = 18.sp,
                )
                Text(
                    text = data.mailID,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            }
        }
    }
    Divider(modifier = Modifier.padding(horizontal = 16.dp))
}
