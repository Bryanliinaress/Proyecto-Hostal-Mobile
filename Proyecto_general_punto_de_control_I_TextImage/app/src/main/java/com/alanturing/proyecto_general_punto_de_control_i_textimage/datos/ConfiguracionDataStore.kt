package com.alanturing.proyecto_general_punto_de_control_i_textimage.datos

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConfiguracionDataStore(private val context: Context) {


    // Aqui declaro los objetos que quiero guardar.
    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "configuracion_hostal")
        val switchNotificaciones = booleanPreferencesKey("switchNotificaciones")
        val switchModoOscuro = booleanPreferencesKey("switchModoOscuro")
        val checkboxParking = booleanPreferencesKey("checkboxParking")
        val checkboxBreakfast = booleanPreferencesKey("checkboxDesayuno")
        val radiobuttonMoneda = stringPreferencesKey("radiobuttonMoneda")
        val despegableIdiomas = stringPreferencesKey("dropdownIdiomas")
    }

    // Aqui declaro los getter de todos los objetos para obtener el valor y guardarlo

    val getSwitchNotificaciones: Flow<Boolean> = context.dataStore.data.map{
        preferences -> preferences[switchNotificaciones] ?: true
    }

    val getSwitchModoOscuro: Flow<Boolean> = context.dataStore.data.map {
        preferences -> preferences[switchModoOscuro] ?: false
    }

    val getCheckBoxParking: Flow<Boolean> = context.dataStore.data.map {
        preferences -> preferences[checkboxParking] ?: true
    }

    val getCheckBoxBreakfast: Flow<Boolean> = context.dataStore.data.map {
            preferences -> preferences[checkboxBreakfast] ?: true
    }

    val getRadioButtonMoneda: Flow<String> = context.dataStore.data.map {
        preferences -> preferences[radiobuttonMoneda] ?: "Euro (€)"
    }

    val getMenuDesplegableIdioma: Flow<String> = context.dataStore.data.map {
            preferences -> preferences[despegableIdiomas] ?: "Español"
    }

    // Aqui guardo todos los valores de los getters

    suspend fun saveSwitchNotificaciones(estado: Boolean){
        context.dataStore.edit {
            preferences -> preferences[switchNotificaciones]= estado
        }
    }

    suspend fun saveSwitchModoOscuro(estado: Boolean){
        context.dataStore.edit {
                preferences -> preferences[switchModoOscuro]= estado
        }
    }

    suspend fun saveCheckBoxParking(estado: Boolean){
        context.dataStore.edit {
                preferences -> preferences[checkboxParking]= estado
        }
    }

    suspend fun saveCheckBoxBreakfast(estado: Boolean){
        context.dataStore.edit {
                preferences -> preferences[checkboxBreakfast]= estado
        }
    }

    suspend fun saveRadioButtonMoneda(moneda: String){
        context.dataStore.edit {
                preferences -> preferences[radiobuttonMoneda]= moneda
        }
    }

    suspend fun saveMenuDesplegableIdioma(idioma: String){
        context.dataStore.edit {
                preferences -> preferences[despegableIdiomas]= idioma
        }
    }
}