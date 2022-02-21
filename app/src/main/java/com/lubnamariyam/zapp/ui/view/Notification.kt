package com.lubnamariyam.zapp.ui.view


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.lubnamariyam.zapp.R

@Composable
fun Notification(navController: NavController) {
    Scaffold(topBar = {
        TopBar(navController = navController)
    }, content = {
        NotificationContent()
    }, bottomBar = {
        BottomBar(navController = navController)
    })
}

@Composable
fun NotificationContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val isPlaying by remember {
            mutableStateOf(true)
        }
        val speed by remember {
            mutableStateOf(1f)
        }
        val composition by rememberLottieComposition(
            LottieCompositionSpec
                .RawRes(R.raw.notification)
        )
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever,
            isPlaying = isPlaying,
            speed = speed,
            restartOnPlay = false
        )
        LottieAnimation(
            composition,
            progress,
            modifier = Modifier.size(250.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = "No Notification's Found",
            textAlign = TextAlign.Center,
            color = Color.LightGray,
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }
}

@Composable
fun TopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(text = "Notification")
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate("home_screen") }) {
                Icon(Icons.Filled.ArrowBack, "Navigation")
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        elevation = 10.dp
    )
}

