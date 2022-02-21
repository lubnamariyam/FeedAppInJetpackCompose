package com.lubnamariyam.zapp.ui.view


import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.lubnamariyam.zapp.R

@Composable
fun Settings(navController: NavController) {
    Scaffold(topBar = {
        TopBarSettings(navController = navController)
    }, content = {
        SettingsContent()
    }, bottomBar = {
        BottomBar(navController = navController)
        BackHandler() {
            navController.navigate("home_screen")
        }
    })
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun SettingsContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp),
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
                .RawRes(R.raw.settings)
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

        Text(
            text = "You're Upto Date!! \n No need to change the settings..",
            textAlign = TextAlign.Center,
            color = Color.LightGray,
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.h5.copy(
                lineHeight = 25.sp,  // Here line height
            )
        )
    }
}

@Composable
fun TopBarSettings(navController: NavController) {
    TopAppBar(
        title = {
            Text(text = "Settings")
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



