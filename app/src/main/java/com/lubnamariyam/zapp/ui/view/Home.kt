package com.lubnamariyam.zapp.ui.view

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.lubnamariyam.zapp.R
import com.lubnamariyam.zapp.database.FeedEntity
import com.lubnamariyam.zapp.viewModel.FeedViewModel

@Composable
fun Home(
    feed: LazyPagingItems<FeedEntity>,
    navController: NavController,
    feedViewModel: FeedViewModel, activity: Activity
) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val sortitem = remember { mutableStateOf(0) }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(sortitem) },
        content = {
            when (sortitem.value) {
                0 -> {
                    FeedCard(feed, feedViewModel)
                }
                1 -> {
                    SortList(feed, feedViewModel)
                }
                2 -> {
                    SortListDesc(feed, feedViewModel)
                }
            }
            Spacer(modifier = Modifier.padding(bottom = 60.dp))
        },
        bottomBar = { BottomBar(navController) }

    )
    BackHandler() {
        val alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle("Exit App")
        alertDialogBuilder.setMessage("Are you sure you want to exit?")
        alertDialogBuilder.setPositiveButton("Yes") { _: DialogInterface, _: Int -> activity.finish() }
        alertDialogBuilder.setNegativeButton("No", { dialogInterface: DialogInterface, i: Int -> })
        alertDialogBuilder.create()
        alertDialogBuilder.show()
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FeedCard(feed: LazyPagingItems<FeedEntity>, feedViewModel: FeedViewModel) {
    LazyColumn(content = {
        itemsIndexed(feed) { index, value ->
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
                                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column() {
                            Text(
                                text = "Ricardlo Chandler",
                                color = MaterialTheme.colors.primary,
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
                            text = value!!.title,
                            style = MaterialTheme.typography.body1,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(bottom = 10.dp),
                            fontFamily = FontFamily.SansSerif,
                        )
                        Text(
                            text = value.body,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.body2,
                            maxLines = 3,
                            modifier = Modifier.padding(),
                            fontFamily = FontFamily.SansSerif, color = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(8.dp)
                    )
                    Row(
                        modifier = Modifier
                            .height(46.dp)
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            val likecount = value!!.likes + 1
                            feedViewModel.updateFeed(value.id, likecount)
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_like),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(24.dp)
                            )
                        }
                        IconButton(onClick = { }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_comment),
                                tint = Color.DarkGray,
                                contentDescription = "Comment",
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(24.dp)
                            )
                        }
                        IconButton(onClick = { }) {
                            Icon(
                                Icons.Filled.Share,
                                contentDescription = "Share",
                                tint = Color.DarkGray,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${value!!.likes} likes",
                            modifier = Modifier
                                .padding(end = 2.dp),
                            color = Color.Black,
                            fontWeight = FontWeight.Bold, textAlign = TextAlign.End
                        )


                    }
                }
            }
            feed.apply {
                when (loadState.append) {
                    is LoadState.Loading -> {
                        CircularProgressIndicator()
                    }
                }
            }

        }
    }
    )

}

@Composable
fun SortList(feed: LazyPagingItems<FeedEntity>, feedViewModel: FeedViewModel) {
    LazyColumn(content = {
        val sortAsc = arrayListOf<FeedEntity>()
        feed.itemSnapshotList.items.sortedBy { it.title }.forEach { sortAsc.add(it) }
        items(items = sortAsc , itemContent ={
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
                                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column() {
                            Text(
                                text = "Ricardlo Chandler",
                                color = MaterialTheme.colors.primary,
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
                            style = MaterialTheme.typography.body1,
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
                            fontFamily = FontFamily.SansSerif,color = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(8.dp)
                    )
                    Row(
                        modifier = Modifier
                            .height(46.dp)
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            val likecount = it.likes + 1
                            feedViewModel.updateFeed(it.id, likecount)
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_like),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(24.dp)
                            )
                        }
                        IconButton(onClick = { }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_comment),
                                tint = Color.DarkGray,
                                contentDescription = "Comment",
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(24.dp)
                            )
                        }
                        IconButton(onClick = { }) {
                            Icon(
                                Icons.Filled.Share,
                                contentDescription = "Share",
                                tint = Color.DarkGray,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${it.likes} likes",
                            modifier = Modifier.padding(end = 2.dp),
                            color = Color.Black,
                            fontWeight = FontWeight.Bold, textAlign = TextAlign.End
                        )
                    }
                }
            }
        } )
    })
}

@Composable
fun SortListDesc(feed: LazyPagingItems<FeedEntity>, feedViewModel: FeedViewModel) {
    LazyColumn(content = {
        val sortDesc = arrayListOf<FeedEntity>()
        feed.itemSnapshotList.items.sortedByDescending { it.title }.forEach { sortDesc.add(it) }
        items(items = sortDesc , itemContent ={
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
                                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column() {
                            Text(
                                text = "Ricardlo Chandler",
                                color = MaterialTheme.colors.primary,
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
                            style = MaterialTheme.typography.body1,
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
                            fontFamily = FontFamily.SansSerif,color = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(8.dp)
                    )
                    Row(
                        modifier = Modifier
                            .height(46.dp)
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            val likecount = it.likes + 1
                            feedViewModel.updateFeed(it.id, likecount)
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_like),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(24.dp)
                            )
                        }
                        IconButton(onClick = { }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_comment),
                                tint = Color.DarkGray,
                                contentDescription = "Comment",
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(24.dp)
                            )
                        }
                        IconButton(onClick = { }) {
                            Icon(
                                Icons.Filled.Share,
                                contentDescription = "Share",
                                tint = Color.DarkGray,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${it.likes} likes",
                            modifier = Modifier.padding(end = 2.dp),
                            color = Color.Black,
                            fontWeight = FontWeight.Bold, textAlign = TextAlign.End
                        )
                    }
                }
            }
        } )
    })
}

@Composable
fun TopBar(sort: MutableState<Int>) {
    val expanded = remember { mutableStateOf(false) }
    val menuItems = listOf("Sort A-Z", "Sort Z-A")

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
    BottomNavigation(elevation = 10.dp, modifier = Modifier.height(60.dp)) {
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
                navController.navigate("notification_screen")
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
                navController.navigate("settings_screen")
            })


    }
}

@Preview
@Composable
fun DefaultPreview() {

}
