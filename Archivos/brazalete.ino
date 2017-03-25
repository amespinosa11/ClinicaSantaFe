 /* 
 * ------------------ 
 * Laboratorio IoT para Arquitectura de Software 2016-20
 * Universidad de los Andes
 * creado el 18 de julio de 2016
 */
 #include <string.h>
 // Selecciona el pin de entrada analoga a leer.
 int sensorFrecuenciaCardiaca = 24;
  // Selecciona el pin de entrada analoga a leer. Este provee los dos valores necesarios de presión sanguinea
 int sensorPresionSanguinea = 25;
  // Selecciona el pin de entrada analoga a leer.
 int sensorNivelDeEstres = 26;
 // variable para guardar el valor de la frecuencia cardiaca del paciente: se mide en (50-100)PPM (Pulsaciones en reposo por minuto) 
 int frecuenciaCardiaca = 0;
  // variable para guardar el valor de la presión sanguinea del paciente: se mide en mmHg (100-180) sistólica
 int presionSanguinea1 = 0;
   // variable para guardar el valor de la presión sanguinea del paciente: se mide en mmHg (60-110) diastólica
 int presionSanguinea2 = 0;
  // variable para guardar el valor del nivel de estres del paciente: se miede en una escala de 1 a 10, con 1 bajo y 10 alto....
 int nivelDeEstres = 0;
 // mensaje para dejar claro cual numero pertenece a qué senso
 String msj = "Frecuencia cardiaca, presion sanguinea y nivel de estres:";
 // arreglo de chars para envio final del datos de sensores.
 String registroArray[4] = {"", "","",""};
 // variable temporal de conteo
 int i = 0;
 // preparacion del proceso
 void setup() { 
   // Abre puerto serial y lo configura a 9600 bps
     Serial.begin(9600);
     // se fija el mensaje inicial de cada registro
     registroArray[0] = msj;
 }
 // ejecuta el procesamiento de sensores
 void loop() {
   /*
   
   Falta leer cada sensor (por variables "sensor...."), extraer la información de cada uno
   y ponerlo en la variable int correspondiente.  
   */
   //frecuenciaCardiaca = analogRead(sensorFrecuenciaCardiaca);
   //presionSanguinea1 = analogRead(sensorFrecuenciaCardiaca);
   //presionSanguinea2 = analogRead(sensorFrecuenciaCardiaca);
   //Numeros random (que tengan sentido) para cada valor. Luego toca hacer que se lea desde el pin
   frecuenciaCardiaca = random(50,100);
   presionSanguinea1 = random(100,180);
   presionSanguinea2 = random(60,110);
   nivelDeEstres = random(1,10);
   
   registroArray[1] = String(frecuenciaCardiaca);
   registroArray[2] = String(presionSanguinea1);
   registroArray[3] = String(presionSanguinea2);
   registroArray[4] = String(nivelDeEstres);
   // Envia los datos uno por uno del arreglo de sensores
   for (i=0; i<5; i++){
     // imprime el elemento del arreglo por el puerto serial
     Serial.print(registroArray[i]);
     // espacio para diferenciar elementos en el arreglo
     if (i < 4){
       Serial.print("\t");      
     }
   }
   // final de la trama de datos
   Serial.println("");
   // espera 1 segundo para repetir el procedimiento
   delay(1000);
   // Serial.println(registroArray[0]);
 }
