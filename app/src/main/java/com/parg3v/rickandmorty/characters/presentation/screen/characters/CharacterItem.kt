package com.parg3v.rickandmorty.characters.presentation.screen.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.parg3v.rickandmorty.R
import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.common_presentation.util.Pink40
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    character: CharacterDomainModel,
    onItemClick: (id: String) -> Unit,
    onFavouriteClick: (id: String, character: CharacterDomainModel) -> Unit,
) {

    var favourite by rememberSaveable { mutableStateOf(character.favourite) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.background, RoundedCornerShape(15.dp, 0.dp, 15.dp, 0.dp)
            )
            .border(2.dp, Color.Gray, RoundedCornerShape(15.dp, 0.dp, 15.dp, 0.dp))
            .clickable(
                onClick = { onItemClick(character.id) },
            )
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
                modifier = Modifier.height(100.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(1F))
                Row {
                    Column(
                        modifier = Modifier
                            .weight(1F)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(text = character.name)
                        Text(text = "${character.species} · ${character.status}")
                    }


                    Icon(
                        imageVector = if (favourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple(color = Pink40),
                                onClick = {
                                    onFavouriteClick(
                                        character.id,
                                        character.copy(favourite = !favourite)
                                    )
                                    scope.launch {
                                        delay(200)
                                        favourite = !favourite
                                    }
                                }
                            )
                            .padding(4.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1F))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "origin: ${character.origin}",
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.labelSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontSize = 12.sp
                )
            }

        }
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
            episodes = emptyList(),
            favourite = false,
        ),
        onItemClick = {},
        onFavouriteClick = { _, _ -> }
    )
}