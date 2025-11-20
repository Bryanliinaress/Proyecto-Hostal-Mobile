package com.alanturing.proyecto_general_punto_de_control_i_textimage.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alanturing.proyecto_general_punto_de_control_i_textimage.R
import com.alanturing.proyecto_general_punto_de_control_i_textimage.ui.theme.HostalTheme


@Composable
fun TextoInformativoSobreNosotros(modifier: Modifier) {
    val context = LocalContext.current;


    LazyColumn(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {


        item {
            Text(
                text = context.getString(R.string.Titulo_infoSobreNosotros),
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier.padding(2.dp))
            Text(
                text = context.getString(R.string.TextoPrincipalSobreNosotros),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify,
            )
        }


        item {
            Spacer(modifier.padding(30.dp))
            Text(
                text = context.getString(R.string.Titulo_InfoNegocio),
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier.padding(2.dp))
            Text(
                text = context.getString(R.string.Info_Negocio),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify
            )
        }


        item {
            Spacer(modifier.padding(30.dp))
            Text(
                text = context.getString(R.string.Titulo_FuncionApp),
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier.padding(2.dp))
            Text(
                text = context.getString(R.string.FuncionApp),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify
            )
        }

        item {
            Spacer(modifier.padding(30.dp))
            Text(
                text = context.getString(R.string.Licencia),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier.padding(30.dp))

        }
    }
}


@Composable
fun SobreNosostros(modifier: Modifier = Modifier) {
    val context = LocalContext.current;

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            16.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier.padding(0.5.dp))
        Text(
            text = context.getString(R.string.SobreNosotros),
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

        TextoInformativoSobreNosotros(Modifier)

    }

}

@Preview(
    name = "Sobre Nosotros",
    showBackground = true,
    backgroundColor = 0xFF252323,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun PreviewSobreNosotros() {
    HostalTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            SobreNosostros(
                modifier = Modifier.padding(innerPadding)
            )

        }
    }
}