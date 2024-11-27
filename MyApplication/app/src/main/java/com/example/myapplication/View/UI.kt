
@Composable
fun botones(coloresBotones: MutableList<Int>, listaColor: List<Color>, valoresColores: List<Int>) {
    // Función interna para crear botones con colores y lógica asociados
    @Composable
    fun crearBoton(color: Color, colorValor: Int) {
        Button(
            onClick = { coloresBotones.add(colorValor) },
            colors = ButtonDefaults.buttonColors(containerColor = color),
            modifier = Modifier
                .clip(CircleShape)
                .padding(3.dp)
                .size(95.dp)
        ) {
            // Contenido vacío porque el botón solo necesita representar color
        }
    }

    // Itera sobre la lista de colores y valores, creando botones
    listaColor.zip(valoresColores).forEach { (color, valor) ->
        crearBoton(color, valor)
    }
}

@Composable
fun myApp(viewModel: MyViewModel) {
    val listaColores = remember { mutableStateListOf<Int>() } // Lista de colores seleccionados
    val isStartButtonPressed = remember { mutableStateOf(true) } // Control de inicio del juego
    val presioneStart = remember { mutableStateOf(false) } // Control para saber si se presionó el botón de inicio

    val listaColor1 = listOf(Color.Red, Color.Green) // Colores para el primer grupo
    val valoresColores1 = listOf(Colores.ROJO.valorColor, Colores.VERDE.valorColor)

    val listaColor2 = listOf(Color.Blue, Color.Yellow) // Colores para el segundo grupo
    val valoresColores2 = listOf(Colores.AZUL.valorColor, Colores.AMARILLO.valorColor)

    Box(modifier = Modifier.fillMaxSize()) {
        // Muestra récord, texto inicial y otros elementos
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
                // Llama a la función `botones` para el primer grupo de botones
                botones(listaColores, listaColor1, valoresColores1)
            }
            Row {
                // Llama a la función `botones` para el segundo grupo de botones
                botones(listaColores, listaColor2, valoresColores2)
            }

            // Muestra la ronda actual
            showRondas(viewModel.getRondas())

            // Botón para iniciar el juego
            startGame(isStartButtonPressed, presioneStart1 = presioneStart, viewModel = viewModel)

            // Comprueba si la lista tiene el tamaño esperado para procesar el juego
            if (listaColores.size == 3) {
                game(viewModel, listaColores)
            }
        }
    }
}

/**
 * Muestra el número de rondas actual.
 */
@Composable
fun showRondas(ronda: Int) {
    Text(
        text = "Ronda: $ronda",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier
            .padding(top = 100.dp)
    )
}

/**
 * Vista previa de la aplicación con tema.
 */
@Preview(showBackground = true)
@Composable
fun ColorButtonsPreview() {
    MyApplicationTheme {
        myApp(viewModel = MyViewModel())
    }
}
