package com.example.firstaplicacion.ui.usuario

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun UsuarioScreen(viewModel: UsuarioViewModel = hiltViewModel()) {

    val usuarios by viewModel.usuarios.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }
    var textFiledSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    LaunchedEffect(Unit) {
        viewModel.isMessageShownFlow.collectLatest {
            if (it) {
                snackbarHostState.showSnackbar(
                    message = "Usuario guardado",
                    duration = SnackbarDuration.Short
                )
            }
        }
    }
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
        OutlinedTextField(
            value = viewModel.telefono,
            onValueChange = {viewModel.telefono = it},
            label = { Text(text = "Teléfono")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = viewModel.celular,
            onValueChange = {viewModel.celular = it},
            label = { Text(text = "Celular")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = viewModel.email,
            onValueChange = {viewModel.email = it},
            label = { Text(text = "Email")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = viewModel.direccion,
            onValueChange = {viewModel.direccion = it},
            label = { Text(text = "Dirección")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = viewModel.fecha,
            onValueChange = {viewModel.fecha = it},
            label = { Text(text = "Fecha")},
            leadingIcon ={ Icon(imageVector = Icons.Filled.DateRange, contentDescription = "fechaIcon")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Column {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = {
                    selectedItem = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFiledSize = coordinates.size.toSize()
                    },
                label = { Text("Ocupación") },
                trailingIcon = {
                    Icon(icon, "", Modifier.clickable { expanded = !expanded })
                },
                readOnly = true
            )
            DropdownMenu(expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(
                    with(LocalDensity.current) { textFiledSize.width.toDp() }
                )) {
                viewModel.ocupacionlista.forEach { label ->
                    DropdownMenuItem(text = { Text(text = label) }, onClick = {
                        selectedItem = label
                        expanded = false
                        viewModel.ocupacion = selectedItem
                    })
                }
            }
        }
        OutlinedButton(
            onClick = {
                viewModel.save()
                keyboardController?.hide()
                selectedItem = ""
            },modifier = Modifier.fillMaxWidth()
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