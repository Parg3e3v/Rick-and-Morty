package com.parg3v.rickandmorty.common_presentation.common_error

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.parg3v.rickandmorty.common_domain.Result

@Composable
fun ErrorComposable(modifier: Modifier = Modifier, result: Result.Failure) {
    Box(modifier = modifier.fillMaxSize()) {
        Text("Error: ${result.message}", modifier = Modifier.align(Alignment.Center))
    }
}