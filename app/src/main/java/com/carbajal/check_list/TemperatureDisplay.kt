package com.carbajal.check_list

import android.widget.Space
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TemperatureDisplay(){

    //Declaramos el estado mutable
    var temperatura by remember { mutableStateOf(20) }
    //Evaluamos la variable temperatura

    val colorTexto = when {
        temperatura >= 30 -> Color.Red
        temperatura >= 20 -> Color.Green
        temperatura < 20 -> Color.Blue
        else -> Color.Black
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(text = "Temperatura: $temperatura °C",
        fontSize = 32.sp,
            color = colorTexto)

        Spacer(modifier = Modifier.height(16.dp))
        //FIla de botones para subir y bajar

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp) //organizar espacios en el eje horizontal
        ) {
            Button(
                onClick = { temperatura++ }
            ) {
                Text(text = "Subir")
            }
            Button( onClick = { temperatura-- }) {
                Text(text = "Bajar")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        //Boton para reiniciar a 20
        Button(
            onClick = {temperatura = 20}
        ) {
            Text(text = "Resetear")
        }
     }
}