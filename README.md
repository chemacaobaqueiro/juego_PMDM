# Juego Simon Dice

***Los objetivos de este mini programa son los siguientes:***
1. Crear una interfaz gráfica decente
2. Realizar las funcionalidades de nuestro programa
3. Implementar el estilo MVM
4. Utilizar los Estados y corrutinas

   ***Primer objetivo***
   
   - Lo que vamos a hacer en la interfaz gráfica va a ser muy sencillo
       - Primero necesitamos crear los 4 botones que tengan los colores del simon dice
       - Segundo necesitamos mostrar por pantalla el numero de rondas, el record
       - Por último para finalizar esta interfaz gráfica vamos a añadir un botón start, el cual de momento no tendrá ninguna función

  ***Segundo objetivo***
  
  -Las primeras funcionalidades del programa tampoco serán tan complejas
       - Primero tenemos que crear una clase enum en la cual pondremos los colores de cada botón y le adjudicaremos un número para posteriormente compararlo con una secuencia
       - Segundo creamos una función la cual genere un número aleatorio de todos los adjudicados al boton al pulsar el botón start
       - Tercero guardamos este numero generado en una lista que va a ser la secuencia que tendremos que seguir para ganar la ronda
       - Cuarto tenemos que crear otra función que compare si el numero introducido por el usuario se corresponde con el generado


  ***Tercer objetivo***
  
   - Para implementar el estilo MVM vamos a seguir los siguientes pasos
      - Primero para implementarlo vamos a separar todos los datos necesarios a nuevas clases
      - Segundo tenemos que separar la view y el viewModel, para ello pondremos la view en una clase llamada iu y el viewModel en una clase llamada ViewModel

  ***Cuarto objetivo***
  
  - Aquí se complica un poco, para implementar los estados y corrutinas debemos:
       - Primero tenemos que crear una clase nueva en el directorio de datos la cual usaremos solo para los estados
       - En esta clase creamos los estados, en este caso en mi programa solo son dos, un estado de inicio y otro de adivinando
       - Para implementarlos debemos ir al ViewModel, estos estados se lanzarán cuando detecte que se han realizado cambios en la aplicación
       - El estado de iniciando ocurrirá cuando estemos  en la pantalla y no le demos al boton de start
           - En este caso el boton de start se econtrará activo y los botones se encontraran inactivos
       - El estado de adivinando ocurrirá cuando el botón start haya sido presionado y el número aleatorio haya sido generado
           - En este caso el boton de start se encontrará desactivado y los botones activados


