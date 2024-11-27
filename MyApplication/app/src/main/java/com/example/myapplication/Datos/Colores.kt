package com.example.myapplication.ui.theme

import androidx.compose.ui.graphics.Color

enum class Colores (val valorcolor:Int){
    ROJO(1),
    VERDE(2),
    AZUL(3),
    AMARILLO(4)
}

enum class ColoresIluminados(var colorNomal : Color, var colorIluminado:Color = Color.Transparent){
    ROJO_PARPADEO(colorNomal = Color(0xFFFF0000)),
    VERDE_PARPADEO(colorNomal = Color(0xFF00FE08)),
    AZUL_PARPADEO(colorNomal = Color(0xFF0017FF)),
    AMARILLO_PARPADEO(colorNomal = Color(0xFFF0FF00))
}