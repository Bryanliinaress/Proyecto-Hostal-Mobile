package com.alanturing.proyecto_general_punto_de_control_i_textimage.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alanturing.proyecto_general_punto_de_control_i_textimage.R


@Composable
fun AcercaDe(modifier: Modifier = Modifier) {
    val context = LocalContext.current



    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            16.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = context.getString(R.string.AcercaDe) + " Hostal Linares",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall
        )


        Image(
            modifier = Modifier
                .clip(CircleShape)
                .padding(horizontal = 100.dp)
                .clip(RoundedCornerShape(100.dp)),
            painter = painterResource(id = R.drawable.fotologo),
            contentDescription = context.getString(R.string.Des_FotoLogo),
        )


        Text(
            text = context.getString(R.string.TextoAcercaDe),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(horizontal = 50.dp)

        )


        Text(
            text = context.getString(R.string.Desarrolladores),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )



        Text(
            text = context.getString(R.string.Licencia),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )


    }
}