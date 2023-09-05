package com.example.firstaplicacion.ui.usuario

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsuarioScreen(viewModel: UsuarioViewModel = hiltViewModel()) {
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
    }
}