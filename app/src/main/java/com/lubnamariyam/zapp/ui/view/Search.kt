package com.lubnamariyam.zapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.lubnamariyam.zapp.ui.theme.Purple500
import com.lubnamariyam.zapp.ui.theme.VeryLightGray
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun SearchScreen(feed: State<List<FeedEntity>>,navController: NavController){
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Search(state = textState,navController)
        },
        content ={
            FeedSearchList(feed,state = textState)
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    )
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
fun Search(state: MutableState<TextFieldValue> , navController: NavController) {
    Row(modifier = Modifier
        .padding(10.dp)
        .background(Purple500)) {
        IconButton(onClick = { navController.navigate("home_screen") }, modifier = Modifier.padding(top = 4.dp)) {
            Icon(Icons.Filled.ArrowBack, contentDescription = null , tint = Color.White)
        }
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
                                TextFieldValue("")
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

}


@Composable
fun FeedSearchCard(it: FeedEntity){
    Card(
        modifier = Modifier
            .padding(10.dp, 5.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp), elevation = 6.dp
    ) {
        Column(modifier = Modifier
            .padding(10.dp, 5.dp)
            .fillMaxWidth()) {
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
            //Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}

