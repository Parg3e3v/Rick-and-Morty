package com.parg3v.rickandmorty.characters.presentation.screen.common

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.presentation.screen.characters.CharacterItem

@Composable
fun CharactersColumn(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    characters: List<CharacterDomainModel>,
    localDBCharacters: Map<String, CharacterDomainModel>,
    localDBStoreMessage: String,
    onItemClick: (id: String) -> Unit,
    onFavouriteClick: (id: String, character: CharacterDomainModel) -> Unit,
) {

    val context = LocalContext.current

    if (localDBStoreMessage.isNotEmpty()) {
        LaunchedEffect(localDBStoreMessage) {
            Toast.makeText(context, localDBStoreMessage, Toast.LENGTH_SHORT).show()
        }
    }

    LazyColumn(modifier = modifier, state = lazyListState) {
        items(characters, key = { it.id }) { character ->
            CharacterItem(
                modifier = Modifier.padding(8.dp),
                character = character.copy(
                    favourite = localDBCharacters[character.id]?.favourite ?: false
                ),
                onItemClick = onItemClick,
                onFavouriteClick = onFavouriteClick
            )
        }
    }
}