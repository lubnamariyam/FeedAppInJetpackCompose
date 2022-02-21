package com.lubnamariyam.zapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lubnamariyam.zapp.R
import com.lubnamariyam.zapp.database.FeedEntity
import com.lubnamariyam.zapp.ui.theme.Purple200
import com.lubnamariyam.zapp.ui.theme.VeryLightGray
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun SearchScreen(feed: State<List<FeedEntity>>){
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column() {
        Search(state = textState)
        FeedSearchList(feed,state = textState)
    }
}

@Composable
fun FeedSearchList(feed: State<List<FeedEntity>>,state: MutableState<TextFieldValue>) {
    var persons = feed
    var filteredPerson: ArrayList<FeedEntity> = arrayListOf()
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        val searchedText = state.value.text
        if(searchedText.isNotEmpty()){
            val resultList = ArrayList<FeedEntity>()
            for (person in persons.value) {
                if (person.title.lowercase(Locale.getDefault()).contains(searchedText.lowercase(Locale.getDefault()))||
                    person.body.lowercase(Locale.getDefault()).contains(searchedText.lowercase(Locale.getDefault()))
                ) {
                    resultList.add(person)
                }
            }
            filteredPerson = resultList
        }else{
            filteredPerson.clear()
        }

        items(filteredPerson.size) { filteredData ->

            FeedSearchCard(filteredPerson[filteredData])

        }
    }
}



@Composable
fun Search(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Green,
            cursorColor = Color.Black,
            leadingIconColor = Color.Black,
            trailingIconColor = Color.Black,
            backgroundColor = VeryLightGray,
            focusedIndicatorColor = Purple200,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}


@Composable
fun FeedSearchCard(it: FeedEntity){
    Card(
        modifier = Modifier.padding(8.dp, 4.dp),
        shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Column() {
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.profile_pic),
                    contentDescription = "contact profile picture",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, Color.LightGray, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column() {
                    Text(
                        text = "Ricardlo Chandler",
                        color = Color.Black,
                        modifier = Modifier.padding(start = 4.dp),
                        style = MaterialTheme.typography.subtitle2
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Android Developer at Zoho",
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 4.dp),
                        style = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "12h .edited",
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 4.dp),
                        style = MaterialTheme.typography.body2
                    )
                }
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Column(modifier = Modifier.padding(all = 8.dp)) {
                Text(
                    text = it.title,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 10.dp),
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = it.body,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2,
                    maxLines = 3,
                    modifier = Modifier.padding(),
                    fontFamily = FontFamily.SansSerif
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(8.dp))
            Row(
                modifier = Modifier
                    .height(46.dp)
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_like),
                    tint = Color.DarkGray,
                    contentDescription = "Like",
                    modifier = Modifier
                        .padding(2.dp)
                        .size(20.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_comment),
                    tint = Color.DarkGray,
                    contentDescription = "Comment",
                    modifier = Modifier
                        .padding(2.dp)
                        .size(20.dp)
                )
                Icon(
                    Icons.Filled.Share,
                    contentDescription = "Share",
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .padding(2.dp)
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "10 likes",
                    modifier = Modifier.padding(end = 2.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold, textAlign = TextAlign.End
                )
            }
        }
    }
}