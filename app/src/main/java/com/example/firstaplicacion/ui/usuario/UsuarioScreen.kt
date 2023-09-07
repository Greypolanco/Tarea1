package com.example.firstaplicacion.ui.usuario

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsuarioScreen(viewModel: UsuarioViewModel = hiltViewModel()) {

    val usuarios by viewModel.usuarios.collectAsStateWithLifecycle()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        OutlinedTextField(
            value = viewModel.nombre, 
            onValueChange = {viewModel.nombre = it},
            label = { Text(text = "Nombre")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        OutlinedButton(
            onClick = {
                viewModel.save()

            }
        ) {
            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null)
            Text(text = "Guardar")
        }

        Text(text = "Lista de Usuarios", style = MaterialTheme.typography.titleMedium)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(usuarios){ usuario->
                Text(text = usuario.nombre)
            }
        }
    }
}