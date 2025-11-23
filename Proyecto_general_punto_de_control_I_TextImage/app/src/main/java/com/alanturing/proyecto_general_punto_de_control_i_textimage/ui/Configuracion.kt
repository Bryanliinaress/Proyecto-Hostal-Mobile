package com.alanturing.proyecto_general_punto_de_control_i_textimage.ui

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alanturing.proyecto_general_punto_de_control_i_textimage.datos.ConfiguracionDataStore
import com.alanturing.proyecto_general_punto_de_control_i_textimage.ui.theme.HostalTheme
import kotlinx.coroutines.launch
import com.alanturing.proyecto_general_punto_de_control_i_textimage.R


@Composable
fun Configuracion(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope ()
    val dataStore = remember{ ConfiguracionDataStore(context) }
    val scrollState= rememberScrollState()


    var switchNotificaciones by rememberSaveable { mutableStateOf(true) }
    var switchModoOscuro by rememberSaveable { mutableStateOf(false) }

    var checkboxParking by rememberSaveable { mutableStateOf(false) }
    var checkboxDesayuno by rememberSaveable() { mutableStateOf(false) }

    var radiobuttonMoneda by rememberSaveable { mutableStateOf("Euro (€)") }

    var dropdownIdiomas by remember { mutableStateOf(false) }
    var idiomaSeleccionado by rememberSaveable { mutableStateOf("Español") }

    val monedas = listOf(
        context.getString(R.string.Moneda_Euro),
        context.getString(R.string.Moneda_Dolar ),
        context.getString(R.string.Moneda_Libra)
    )

    val idiomas = listOf(
        context.getString(R.string.Idiomas_Español),
        context.getString(R.string.Idiomas_Ingles))




    // Aqui cargo los valores guardados en el DataStore
    LaunchedEffect(Unit) {
        dataStore.getSwitchNotificaciones.collect { switchNotificaciones = it }
    }
    LaunchedEffect(Unit) {
        dataStore.getSwitchModoOscuro.collect { switchModoOscuro = it }
    }

    LaunchedEffect(Unit) {
        dataStore.getCheckBoxParking.collect { checkboxParking = it }
    }
    LaunchedEffect(Unit) {
        dataStore.getCheckBoxBreakfast.collect { checkboxDesayuno = it }
    }

    LaunchedEffect(Unit) {
        dataStore.getRadioButtonMoneda.collect { radiobuttonMoneda = it }
    }

    LaunchedEffect(Unit) {
        dataStore.getMenuDesplegableIdioma.collect { idiomaSeleccionado = it }
    }



    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        Text(
            text = context.getString(R.string.Configuracion_Configuracion), // Use context.getString(R.string.Configuracion)
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        HorizontalDivider()

        // Aqui llamo a los switch
        ImplementarSwitches(
            estado1 = switchNotificaciones,
            onEstado1Change = { nuevoValor -> switchNotificaciones = nuevoValor },
            estado2 = switchModoOscuro,
            onEstado2Change = { nuevoValor -> switchModoOscuro = nuevoValor },
            context = context
        )

        HorizontalDivider()

        Text(
            text = context.getString(R.string.Configuracion_PreferenciasDeBusqueda),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(text = context.getString(R.string.Configuracion_MostrarSoloHabitaciones), style = MaterialTheme.typography.bodyMedium)

        // Aqui llamo alos CheckBoxes
        ImplementarCheckBoxes(
            estado1 = checkboxParking,
            onEstado1Change = { nuevoValor -> checkboxParking = nuevoValor },
            estado2 = checkboxDesayuno,
            onEstado2Change = { nuevoValor -> checkboxDesayuno = nuevoValor },
            context = context
        )

        HorizontalDivider()

        Text(
            text = context.getString(R.string.Configuracion_Moneda),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        // Aqui llamo al RadioButton
        ImplementarRadioButtons(
            monedas,
            estado1 = radiobuttonMoneda,
            onEstado1Change = { nuevoValor -> radiobuttonMoneda = nuevoValor })

        HorizontalDivider()

        Text(
            text = context.getString(R.string.Configuracion_Idioma),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        ImplementarMenuDespegable(
            context = context,
            idiomaSeleccionado = idiomaSeleccionado,
            onIdiomaChange = { nuevoIdioma -> idiomaSeleccionado = nuevoIdioma },
            dropdownIdiomas,
            onDespegableChange = { nuevoEstado -> dropdownIdiomas = nuevoEstado },

            idiomas = idiomas
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                scope.launch {
                    dataStore.saveSwitchNotificaciones(switchNotificaciones)
                    dataStore.saveSwitchModoOscuro(switchModoOscuro)
                    dataStore.saveCheckBoxParking(checkboxParking)
                    dataStore.saveCheckBoxBreakfast(checkboxDesayuno)
                    dataStore.saveRadioButtonMoneda(radiobuttonMoneda)
                    dataStore.saveMenuDesplegableIdioma(idiomaSeleccionado)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(context.getString(R.string.Configuracion_GuardarCambios))
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}



@Composable
fun ImplementarSwitches(
    estado1: Boolean,
    onEstado1Change: (Boolean) -> Unit, // Nuevo parámetro para el evento de cambio
    estado2: Boolean,
    onEstado2Change: (Boolean) -> Unit, // Nuevo parámetro para el evento de cambio
    context: Context
) {
    Column {
        Text(
            text = context.getString(R.string.Configuracion_General),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = context.getString(R.string.Configuracion_Switch_RecibirNotificaciones),
                style = MaterialTheme.typography.bodyLarge
            )
            Switch(
                checked = estado1,
                onCheckedChange = { onEstado1Change(it) }
            )
        }

        // Dark Mode Switch
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = context.getString(R.string.Configuracion_Switch_ModoOscuro),
                style = MaterialTheme.typography.bodyLarge
            )
            Switch(
                checked = estado2,
                onCheckedChange = { onEstado2Change(it) }
            )
        }
    }
}



@Composable
fun ImplementarCheckBoxes(estado1: Boolean,
                          onEstado1Change: (Boolean) -> Unit, // Nuevo parámetro para el evento de cambio
                          estado2: Boolean,
                          onEstado2Change: (Boolean) -> Unit, // Nuevo parámetro para el evento de cambio
                          context: Context){

    // Checkbox 1
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEstado1Change(!estado1) }
    ) {
        Checkbox(
            checked = estado1,
            onCheckedChange = {onEstado1Change(it) }
        )
        Text(text = context.getString(R.string.Configuracion_CheckBox_ParkingIncluido), style = MaterialTheme.typography.bodyLarge)
    }

    // Checkbox 2
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEstado2Change(!estado2) }
    ) {
        Checkbox(
            checked = estado2,
            onCheckedChange = { onEstado2Change(it) }
        )
        Text(text = context.getString(R.string.Configuracion_CheckBox_DesayunoIncluido), style = MaterialTheme.typography.bodyLarge)
    }
}


@Composable
fun ImplementarRadioButtons (array: List<String>, estado1: String, onEstado1Change: (String) -> Unit){

    array.forEach { moneda ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .selectable(
                    selected = (moneda == estado1),
                    onClick = { onEstado1Change(moneda) }
                )
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = (moneda == estado1),
                onClick = { onEstado1Change(moneda) }
            )
            Text(
                text = moneda,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}
@Composable
fun ImplementarMenuDespegable(
    context: Context,
    idiomaSeleccionado: String,
    onIdiomaChange: (String) -> Unit,
    despegable: Boolean,
    onDespegableChange: (Boolean) -> Unit,
    idiomas: List<String>
) {

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = idiomaSeleccionado,
            onValueChange = { },
            readOnly = true,
            label = { Text(context.getString(R.string.Configuracion_SeleccionarIdioma)) },
            trailingIcon = {
                Icon(
                    imageVector = if (despegable) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.clickable { onDespegableChange(!despegable) }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onDespegableChange(true)}
        )

        DropdownMenu(
            expanded = despegable,
            onDismissRequest = {onDespegableChange(!despegable) },
            modifier = Modifier.fillMaxWidth(0.9f) // Adjust width as needed
        ) {
            idiomas.forEach { language ->
                DropdownMenuItem(
                    text = { Text(text = language) },
                    onClick = {
                        onIdiomaChange(language)
                        onDespegableChange(false)
                    }
                )
            }
        }
    }

}
// --- PREVIEW ---
@Preview(
    name = "Configuracion Light",
    showBackground = true
)
@Composable
fun PreviewConfiguracion() {
    HostalTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Configuracion(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}