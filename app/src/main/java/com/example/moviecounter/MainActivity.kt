package com.example.moviecounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GloveStatus() // Llama a tu composable aquí
        }
    }
}

@Composable
fun GloveStatus(modifier: Modifier = Modifier) {
    var isConnected by rememberSaveable { mutableStateOf(false) }
    val percentage = if (isConnected) 100 else 0

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Control del Agua",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (isConnected) {
            Image(
                painter = painterResource(id = R.drawable.ppp), // Imagen cuando está conectado
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .clip(CircleShape)
                    .then(modifier)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.app), // Imagen cuando está desconectado
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .clip(CircleShape)
                    .then(modifier)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "$percentage%", // Muestra el porcentaje
            style = MaterialTheme.typography.headlineLarge,
            color = if (isConnected) Color.Green else Color.Red
        )

        Spacer(modifier = Modifier.height(35.dp))

        ProgressIndicator(
            progress = percentage / 100f,
            color = if (isConnected) Color.Green else Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { isConnected = !isConnected },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = if (isConnected) "Lleno" else "Vacio")
        }
    }
}

@Composable
fun ProgressIndicator(progress: Float, color: Color, modifier: Modifier) {

}

@Preview(showBackground = true)
@Composable
fun GloveStatusPreview() {
    GloveStatus()
}
