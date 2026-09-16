package com.carbajal.check_list

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items


data class Tarea (
    val id: Int,
    val nombre: String,
    val completada: Boolean = false

)

@Composable
fun ItemTarea(
    tarea: Tarea,
    onEliminar: () -> Unit,
    onCambiarEstado: (Boolean) -> Unit
){
    //Implementamos el componente Card
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Checkbox(
                    checked = tarea.completada,
                    onCheckedChange = {
                        onCambiarEstado(it)

                    }
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(text = tarea.nombre,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 15.dp))
            }
            Button(
                onClick = onEliminar
            ) {
                Text("Eliminar")
            }
        }
    }
}

@Composable
fun PantallaTareas(){

    //declaramos nuestras variables
    var textotarea by remember { mutableStateOf("") }
    var contadorId by remember { mutableIntStateOf(1)}
    //variable de almacenamiento
    val listaTareas = remember { mutableStateListOf<Tarea>() }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)

    ) {
        Text(text = "LISTA DE TAREAS TECBOOK",
            fontWeight = FontWeight.Bold,
            )

        Spacer(modifier = Modifier.height(8.dp))

        //Pedimos un texto de entrada

        OutlinedTextField(
            value = textotarea,
            onValueChange = {textotarea = it},
            label = {Text(text = "INgrese una tarea")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {

                //Logica
                if (textotarea.isNotBlank()){
                    listaTareas.add(
                        Tarea (
                            id = contadorId,
                            nombre = textotarea,
                        )
                    )
                    contadorId++
                    textotarea = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Agregar Tarea",
                fontWeight = FontWeight.Bold)

        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Total tareas: ${listaTareas.size}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn{
            items(listaTareas, key = {it.id}){tarea ->
                ItemTarea(
                    tarea = tarea,
                    onEliminar = {
                        listaTareas.remove(tarea)
                    },
                    onCambiarEstado = { completada ->
                        val index = listaTareas.indexOf(tarea)
                        if(index != -1){
                            listaTareas[index] = listaTareas[index].copy(completada = completada)
                        }
                    }

                )
            }
        }
    }
}