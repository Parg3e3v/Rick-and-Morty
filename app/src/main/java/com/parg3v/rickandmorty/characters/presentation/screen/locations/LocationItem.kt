package com.parg3v.rickandmorty.characters.presentation.screen.locations

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.parg3v.rickandmorty.common_presentation.util.LocationTypeColors
import com.parg3v.rickandmorty.common_presentation.util.RickAndMortyTheme

@Composable
fun LocationItem(
    name: String,
    type: String,
    dimension: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

    val borderColor = LocationTypeColors[type] ?: Color.Gray

    Box(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)
        .border(2.dp, borderColor, shape = RoundedCornerShape(8.dp))
        .clickable { onClick() }
        .padding(16.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Type: $type", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Dimension: $dimension", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview
@Composable
private fun LocationItemPreview() {
    RickAndMortyTheme {
        Surface {
            LocationItem(
                name = "Earth (C-137)",
                type = "Planet",
                dimension = "Dimension C-137",
                onClick = {}
            )
        }
    }
}