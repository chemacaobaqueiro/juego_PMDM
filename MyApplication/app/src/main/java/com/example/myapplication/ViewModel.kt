import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.*
import com.example.myapplication.Colores.kt
import com.example.myapplication.Datos.kt
import com.example.myapplication.Estados
import kotlin.random.Random
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val random = Random
    val estadoLiveData = MutableLiveData(Estados.INICIO)

    //Variable liveData para actualizar las variables en la vista
    private val _recordLiveData = MutableLiveData(Datos.record)
    val recordLiveData: LiveData<Int> get() = _recordLiveData

    private val _rondasLiveData = MutableLiveData(Datos.rondas)
    val rondasLiveData: LiveData<Int> get() = _rondasLiveData

    private val _aciertosLiveData = MutableLiveData(Datos.aciertos)
    val aciertosLiveData: LiveData<Int> get() = _aciertosLiveData

    //Mapeado de los colores de los botones y sus estados
    private val colorLiveDataMap = mapOf(
        1 to MutableLiveData(ColoresIluminados.ROJO_PARPADEO.colorNomal),
        2 to MutableLiveData(ColoresIluminados.VERDE_PARPADEO.colorNomal),
        3 to MutableLiveData(ColoresIluminados.AZUL_PARPADEO.colorNomal),
        4 to MutableLiveData(ColoresIluminados.AMARILLO_PARPADEO.colorNomal)
    )

    //Acceso a cada color individualmente
    val colorRojoLiveData: LiveData<Color> get() = colorLiveDataMap[1]!!
    val colorVerdeLiveData: LiveData<Color> get() = colorLiveDataMap[2]!!
    val colorAzulLiveData: LiveData<Color> get() = colorLiveDataMap[3]!!
    val colorAmarilloLiveData: LiveData<Color> get() = colorLiveDataMap[4]!!

    //Incremento del contador de aumentos y actualización del liveData

    fun incrementAciertos() {
        Datos.aciertos++
        _aciertosLiveData.value = Datos.aciertos
    }

    //Incrementa el contador de rondas y actualiza el LiveData.

    fun incrementRondas() {
        Datos.rondas++
        _rondasLiveData.value = Datos.rondas
    }

    //Actualiza el record si los aciertos actuales son mayores.

    fun updateRecord() {
        if (Datos.aciertos > Datos.record) {
            Datos.record = Datos.aciertos
            _recordLiveData.value = Datos.record
        }
    }

    //Reinicia los valores de aciertos y rondas.

    fun reinicioValores() {
        Datos.aciertos = 0
        Datos.rondas = 0
        _aciertosLiveData.value = Datos.aciertos
        _rondasLiveData.value = Datos.rondas
    }

    //Genera numero random y lo añade a la secuencia

    fun setRandom() {
        Datos.numRandom = random.nextInt(4) + 1
        Datos.listaNumerosRandom.add(Datos.numRandom)
        estadoLiveData.value = Estados.ADIVINANDO
        highlightSequence(Datos.listaNumerosRandom)
        Log.d("Random", Datos.listaNumerosRandom.toString())
    }


    //Agrega un color seleccionado por el jugador y verifica si la secuencia es correcta
    fun addColor(numero: Int, listaColores: MutableList<Int>, listaRandom: MutableList<Int>) {
        listaColores.add(numero)
        Datos.listaColores = listaColores
        checkWinOrLose(listaRandom, listaColores)
    }

    //Comprobatoria de si gana o pierde

    fun checkWinOrLose(listaRandom: MutableList<Int>, listaColores: MutableList<Int>) {
        when {
            listaColores.size <= listaRandom.size -> verifySequence(listaRandom, listaColores)
        }
    }

    //Verifica si las secuencias coinciden
    private fun verifySequence(listaRandom: MutableList<Int>, listaColores: MutableList<Int>) {
        when {
            listaRandom == listaColores -> onWin(listaColores)
            listaRandom.subList(0, listaColores.size) == listaColores -> Log.d("TAG", "CORRECTO")
            else -> onLose(listaColores)
        }
    }

    //Metodo para la victoria en una ronda
    private fun acierto(listaColores: MutableList<Int>) {
        estadoLiveData.value = Estados.INICIO
        incrementAciertos()
        incrementRondas()
        updateRecord()
        clearList(listaColores)
    }

    //Metodo para la derrota
    private fun fallo(listaColores: MutableList<Int>) {
        estadoLiveData.value = Estados.INICIO
        resetValues()
        clearList(listaColores)
        clearList(Datos.listaNumerosRandom)
    }

    //Limpia la lista de enteros
    private fun limpiarLista(list: MutableList<Int>) = list.clear()

    //Resalta loos botones segun la secuencia
    private fun secuencia(sequence: List<Int>) {
        viewModelScope.launch {
            sequence.forEach { num ->
                colorLiveDataMap[num]?.apply {
                    delay(500)
                    value = getHighlightColor(num)
                    delay(500)
                    value = getDefaultColor(num)
                    delay(500)
                }
            }
        }
    }

    //Devuelve color iluminado
    private fun getColorBrillando(num: Int): Color = when (num) {
        1 -> Color(0xFFFF9999)
        2 -> Color(0xFFA8FFAA)
        3 -> Color(0xFF5F85FF)
        4 -> Color(0xFFFCFFBE)
        else -> Color.Unspecified
    }

    //Devuelve el color normal
    private fun getColorDefecto(num: Int): Color = when (num) {
        1 -> ColoresIluminados.ROJO_PARPADEO.colorNomal
        2 -> ColoresIluminados.VERDE_PARPADEO.colorNomal
        3 -> ColoresIluminados.AZUL_PARPADEO.colorNomal
        4 -> ColoresIluminados.AMARILLO_PARPADEO.colorNomal
        else -> Color.Unspecified
    }
}
