package com.parg3v.rickandmorty.characters.presentation.screen.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onButtonPress: () -> Unit,
) {
    val isButtonPressed = remember { mutableStateOf(false) }

    Button(
        modifier = modifier.padding(5.dp),
        onClick = {
            if (!isButtonPressed.value) {
                // for button not to be able to be pressed many times
                isButtonPressed.value = true
                onButtonPress()
            }
        }
    ) {
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
    }
}