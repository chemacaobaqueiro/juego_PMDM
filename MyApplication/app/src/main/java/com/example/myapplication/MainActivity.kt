package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.Colores
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val Text = "Pruebita perri"
        val duracion = Toast.LENGTH_SHORT
        Toast.makeText(this, Text, duracion).show()

        setContent {
            MyApplicationTheme {
                Login()
            }
        }
    }
}

@Composable
fun Login() {
    val buttonPressed = remember { mutableStateOf("") }
    val buttons = listOf(Colores.Amarillo,Colores.Azul,Colores.Rojo,Colores.Verde)
    var record = remember { mutableStateOf<Int>(0) }
    var datos= remember { mutableListOf<Int>()}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Button(
                onClick = {
                    record.value++
                    datos.add(buttons[0].valorcolor)
                    },
                modifier = Modifier
                    .padding(3.dp)
                    .size(120.dp, 120.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttons[0].color)
            ) {
            }

            Button(
                onClick = {
                    record.value++
                    datos.add(buttons[1].valorcolor)
                     },
                modifier = Modifier
                    .padding(3.dp)
                    .size(120.dp, 120.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttons[1].color)
            ) {
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Button(
                onClick = {
                    record.value++
                    datos.add(buttons[2].valorcolor)
                    },
                modifier = Modifier
                    .padding(3.dp)
                    .size(120.dp, 120.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttons[2].color)
            ) {
            }

            Button(
                onClick = {
                    record.value++
                    datos.add(buttons[3].valorcolor) },
                modifier = Modifier
                    .padding(3.dp)
                    .size(120.dp, 120.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttons[3].color)
            ) {
            }


        }

        Text(
            text = buttonPressed.value,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "Record: ${record.value}",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun showToast() {
    var secuencia = mutableListOf<Int>()
    secuencia += (1..4).shuffled().take(5)
    var textosecuencia = secuencia.toString()
    val duracion = Toast.LENGTH_LONG
    val context = LocalContext.current
    Toast.makeText(context, textosecuencia, duracion).show()
}
@Preview(showBackground = true)
@Composable
fun ColorButtonsPreview() {
    MyApplicationTheme {
        Login()
    }
}
