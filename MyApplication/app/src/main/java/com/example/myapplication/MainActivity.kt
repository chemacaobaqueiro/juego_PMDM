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
fun botones(coloresBotones: MutableList<Int>){
    val listaColor = listOf(Color.Red,Color.Green)

    @Composable
    fun crearBotones(color:Color,colorValor: Int){
        Button(
            onclick = {coloresBotones.add(colorValor)},
            colors = ButtonDefaults.buttonColors(containerColor = color),
            modifier = Modifier
                .clip(CircleShape)
                .padding(3.dp)
                .size(95.dp)
        ){

        }

    }

    for (color in listaColor){
        when (color){
            Color.Red -> crearBotones(color,Colores.ROJO.valorcolor)
            Color.Green -> crearBotones(color,Colores.AZUL.valorcolor)

        }
    }
}

@Composable
fun botones2(coloresBotones: MutableList<Int>){
    val listaColor = listOf(Color.Blue,Color.Yellow)

    @Composable
    fun crearBotones(color:Color,colorValor: Int){
        Button(
            onclick = {coloresBotones.add(colorValor)},
            colors = ButtonDefaults.buttonColors(containerColor = color),
            modifier = Modifier
                .clip(CircleShape)
                .padding(3.dp)
                .size(95.dp)
        ){

        }

    }

    for (color in listaColor){
        when (color){
            Color.Blue -> crearBotones(color,Colores.VERDE.valorcolor)
            Color.Yellow -> crearBotones(color,Colores.AMARILLO.valorcolor)
        }
    }
}

@Composable
fun myApp(viewModel: MyViewModel) {
    var lista_colores = remember { mutableStateListOf<Int>() }
    val isStartButtonPressed = remember { mutableStateOf(true) }
    var presioneStart = remember { mutableStateOf(false) }


    Box (modifier = Modifier
        .fillMaxSize()
    ){

        Column {
            RecordMaximo(viewModel.getMaxRecord())
            initialText(viewModel.getSaludoInicio())
            showRecord(viewModel.getRecord())
        }


        Column(

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(70.dp)
                .padding(top = 190.dp, start = 15.dp)
        ) {

            Row {

                botones(lista_colores)
            }

            Row {
                botones2(lista_colores)
            }

            showRondas(viewModel.getRondas())

            startGame(isStartButtonPressed, presioneStart1 = presioneStart, viewModel = viewModel)


            if(lista_colores.size == 3){

                game(viewModel, lista_colores)

            }


        }
    }

}

@Composable
fun showRondas(Ronda: Int) {
    Text(
        text = "Ronda : $Ronda",

        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier
            .padding(top.100.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ColorButtonsPreview() {
    MyApplicationTheme {
        Login()
    }
}
