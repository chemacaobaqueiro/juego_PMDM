import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.Datos.Colores
import com.example.myapplication.ViewModel.ViewModel



@Composable
fun showText(text: String, fontSize: Int, fontWeight: FontWeight, modifier: Modifier) {
    Text(
        text = text,
        fontSize = fontSize.sp,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

//Muestra los aciertos actuales

@Composable
fun showAciertos(aciertos: Int) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 90.dp, start = 126.dp)
    ) {
        showText(
            text = "Aciertos: $aciertos",
            fontSize = 25,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
        )
        Log.d("Record", aciertos.toString())
    }
}

//Muestra el record actual

@Composable
fun RecordMaximo(record: Int) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 50.dp, start = 130.dp)
    ) {
        showText(
            text = "Record: $record",
            fontSize = 25,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
        )
    }
}

/**
 * Botón de color específico que interactúa con el estado de la aplicación.
 * @param viewModel Instancia de ViewModel para manejar la lógica del juego.
 * @param listaColores Lista de colores seleccionados por el usuario.
 * @param listaRandom Lista generada aleatoriamente para el patrón del juego.
 * @param colorValor Valor asociado al color del botón.
 * @param color Color que se mostrará en el botón.
 */

@Composable
fun buttonColor(
    viewModel: MyViewModel,
    listaColores: MutableList<Int>,
    colorValor: Int,
    color: Color
) {
    val _activo by remember {
        mutableStateOf(viewModel.estadoLiveData.value?.botonesColoresActivos ?: true)
    }

    viewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        // Actualiza el estado local según el LiveData
    }

    Button(
        enabled = _activo,
        onClick = { viewModel.addColor(colorValor, listaColores, viewModel.getRandom()) },
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = Modifier
            .clip(CircleShape)
            .padding(3.dp)
            .size(95.dp)
    ) { /* Botón vacío */ }
}

/**
 * Muestra el número de rondas actuales del usuario.
 * @param numeroRondas El número actual de rondas completadas.
 */
@Composable
fun showRondas(numeroRondas: Int) {
    showText(
        text = "Ronda: $numeroRondas",
        fontSize = 20,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 100.dp)
    )
}

/**
 * Botón de inicio para empezar el juego.
 * @param viewModel Instancia de ViewModel para manejar la lógica del juego.
 */

@Composable
fun showButtonStart(viewModel: MyViewModel) {
    val _activo by remember {
        mutableStateOf(viewModel.estadoLiveData.value?.startActivo ?: true)
    }

    val rosa = Color(0xFFFF00C9)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 40.dp)
    ) {
        Button(
            enabled = _activo,
            onClick = { viewModel.setRandom() },
            colors = ButtonDefaults.buttonColors(
                containerColor = rosa,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .size(145.dp)
                .clip(RectangleShape)
        ) {
            showText(
                text = "Start",
                fontSize = 25,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
        }
    }
}


/**
 * Pantalla principal de la aplicación.
 * @param viewModel Instancia de ViewModel para manejar la lógica del juego.
 */

@Composable
fun gameButtons(viewModel: MyViewModel, listaColores: MutableList<Int>) {
    val colorRojo by viewModel.colorRojoLiveData.observeAsState(viewModel.getColorRed())
    val colorVerde by viewModel.colorVerdeLiveData.observeAsState(viewModel.getColorGreen())
    val colorAzul by viewModel.colorAzulLiveData.observeAsState(viewModel.getColorBlue())
    val colorAmarillo by viewModel.colorAmarilloLiveData.observeAsState(viewModel.getColorYellow())

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(70.dp).padding(top = 190.dp, start = 15.dp)
    ) {
        Row {
            buttonColor(viewModel, listaColores, Colores.ROJO.valorColor, colorRojo)
            buttonColor(viewModel, listaColores, Colores.VERDE.valorColor, colorVerde)
        }
        Row {
            buttonColor(viewModel, listaColores, Colores.AZUL.valorColor, colorAzul)
            buttonColor(viewModel, listaColores, Colores.AMARILLO.valorColor, colorAmarillo)
        }
    }
}

@Composable
fun myApp(viewModel: MyViewModel) {
    val record by viewModel.recordLiveData.observeAsState(viewModel.getRecord())
    val rondas by viewModel.rondasLiveData.observeAsState(viewModel.getRondas())
    val aciertos by viewModel.aciertosLiveData.observeAsState(viewModel.getAciertos())

    val listaColores = remember { mutableStateListOf<Int>() }

    Box(modifier = Modifier.fillMaxSize()) {
        // Fondo de la app
        val backgroundImage = painterResource(id = R.drawable.fondo)
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Mostrar récord y aciertos
        Column {
            RecordMaximo(record)
            showAciertos(aciertos)
        }

        // Botones y rondas
        gameButtons(viewModel, listaColores)
        showRondas(rondas)
        showButtonStart(viewModel)
    }
}
