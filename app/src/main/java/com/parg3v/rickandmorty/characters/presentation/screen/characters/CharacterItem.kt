package com.parg3v.rickandmorty.characters.presentation.screen.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.parg3v.rickandmorty.R
import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel

@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    character: CharacterDomainModel,
    onItemClick: (id: String) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.background, RoundedCornerShape(15.dp, 0.dp, 15.dp, 0.dp)
            )
            .border(2.dp, Color.Gray, RoundedCornerShape(15.dp, 0.dp, 15.dp, 0.dp))
            .clickable {
                onItemClick(character.id)
            }
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp)),
                model = character.image,
                placeholder = painterResource(R.drawable.placeholder),
                contentDescription = null,
            )
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = character.name)
                Text(text = "${character.species} · ${character.status}")
            }
        }
        Text(
            modifier = Modifier.align(Alignment.BottomEnd),
            text = character.origin,
            textAlign = TextAlign.End,
            fontSize = 12.sp
        )
    }
}

@Preview
@Composable
private fun CharacterPreview() {
    CharacterItem(
        character = CharacterDomainModel(
            id = "3",
            name = "Summer Smith",
            status = "Alive",
            species = "Human",
            image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
            origin = "Earth (Replacement Dimension)",
            location = "Earth (Replacement Dimension)",
            type = "",
            gender = "Female",
            episode = emptyList()
        )
    ){}
}