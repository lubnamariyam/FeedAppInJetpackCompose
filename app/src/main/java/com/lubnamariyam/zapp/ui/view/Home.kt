package com.lubnamariyam.zapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lubnamariyam.zapp.R
import com.lubnamariyam.zapp.database.FeedEntity
import java.util.*

@Composable
fun Home(feed: State<List<FeedEntity>>, navController: NavController) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val sortitem = remember { mutableStateOf(0) }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(sortitem) },
        content = {
            when (sortitem.value) {
                0 -> {
                    FeedCard(feed)
                }
                1 -> {
                    SortList(feed)
                }
                2 -> {
                    SortListDesc(feed)
                }
            }
        }
            ,
        bottomBar = { BottomBar(navController) }
    )
}



@Composable
fun FeedCard(feed: State<List<FeedEntity>>) {

    LazyColumn( content = {
        items(
            items = feed.value,
            itemContent = {
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
            })})

}

@Composable
fun SortList(feed: State<List<FeedEntity>>){
    LazyColumn(content = {
        val sortasc = arrayListOf<FeedEntity>()
        feed.value.sortedBy { it.title }.forEach {
            sortasc.add(it)
        }
        items(
            items = sortasc , itemContent = {
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
        )
    })
}

@Composable
fun SortListDesc(feed: State<List<FeedEntity>>){
    LazyColumn(content = {
        val sortasc = arrayListOf<FeedEntity>()
        feed.value.sortedByDescending { it.title }.forEach {
            sortasc.add(it)
        }
        items(
            items = sortasc , itemContent = {
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
        )
    })
}


@Composable
fun TopBar(sort : MutableState<Int>) {
    val expanded = remember { mutableStateOf(false) }
    val menuItems = listOf("Sort A-Z", "Sort Z-A", )

    TopAppBar(
        title = {
            Text(text = "Forum")
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Menu, "Navigation")
            }
        },
        actions = {
            IconButton(onClick = {

                expanded.value = true
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sort_icon),
                    contentDescription = null, tint = Color.White
                )
            }
            DropdownMenu(
                expanded = expanded.value,
                offset = DpOffset((-40).dp, (-40).dp),
                onDismissRequest = { expanded.value = false }) {
                menuItems.forEach {

                    DropdownMenuItem(onClick = {
                        when (it) {
                            "Sort A-Z" -> {
                                sort.value = 1
                            }
                            "Sort Z-A" -> {
                                sort.value = 2
                            }
                        }
                        expanded.value = false
                    }) {
                        Text(text = it)
                    }
                }
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        elevation = 10.dp
    )
}

@Composable
fun BottomBar(navController: NavController) {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp) {

        BottomNavigationItem(icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_feed),
                contentDescription = null,
                modifier = Modifier.padding(bottom = 2.dp)
            )
        },
            label = { Text(text = "Feed") },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
                navController.navigate("home_screen")
            })

        BottomNavigationItem(icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.padding(bottom = 2.dp)
            )
        },

            label = { Text(text = "Search") },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
                navController.navigate("search_screen")
            })

        BottomNavigationItem(icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = null,
                modifier = Modifier.padding(bottom = 2.dp)
            )
        },
            label = { Text(text = "Notification") },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
            })

        BottomNavigationItem(icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = null,
                modifier = Modifier.padding(bottom = 2.dp)
            )
        },
            label = { Text(text = "Settings") },
            selected = (selectedIndex.value == 3),
            onClick = {
                selectedIndex.value = 3
            })


    }
}

@Preview
@Composable
fun DefaultPreview() {

}
