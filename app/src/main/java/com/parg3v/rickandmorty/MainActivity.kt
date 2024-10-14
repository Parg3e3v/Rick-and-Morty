package com.parg3v.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.parg3v.rickandmorty.characters.presentation.navigation.CharactersDestination
import com.parg3v.rickandmorty.characters.presentation.navigation.LocationsDestination
import com.parg3v.rickandmorty.characters.presentation.screen.BottomBar
import com.parg3v.rickandmorty.characters.presentation.screen.characters.CharactersScreen
import com.parg3v.rickandmorty.characters.presentation.screen.locations.LocationsScreen
import com.parg3v.rickandmorty.common_presentation.RickAndMortyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomBar(navController = navController) }) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = CharactersDestination
                    ) {
                        composable<CharactersDestination> {
                            CharactersScreen()
                        }
                        composable<LocationsDestination> {
                            LocationsScreen()
                        }
                    }
                }
            }
        }
    }
}

