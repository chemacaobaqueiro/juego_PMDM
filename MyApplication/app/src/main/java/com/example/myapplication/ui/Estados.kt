enum class Estados(val startActivo: Boolean, val botonesColoresActivos:Boolean) {

    /**
     * Estados que va a tener la aplicación, un estado en el cual va a estar activo solo el boton de inicio y los botones de los colores apagados y otro estado en el que será al revés
     * El otro estado de adivinando que tiene activos los botones de los colores y desactivado el de start
     */

    INICIO(startActivo = true, botonesColoresActivos = false),
    ADIVINANDO(startActivo = false, botonesColoresActivos = true),

}