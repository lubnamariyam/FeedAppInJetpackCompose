package com.lubnamariyam.zapp.ui.view

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lubnamariyam.zapp.R
import com.lubnamariyam.zapp.ui.theme.Purple500
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    // AnimationEffect
    //ConnectivityStatus()

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )

        delay(2000L)
        navController.navigate("home_screen")
    }

    // Image -> Logo
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier.padding(top = 70.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .scale(scale.value)
                    .size(300.dp, 300.dp)
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Z App",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium, textAlign = TextAlign.Center , color = Purple500 , fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "Connecting People !!" , textAlign = TextAlign.Center , color = Purple500,fontSize = 14.sp,fontFamily = FontFamily.SansSerif
                ,fontWeight = FontWeight.Light)
        }

    }

}
