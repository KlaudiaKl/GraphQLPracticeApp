package com.klaudia.graphqlpracticeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.klaudia.graphqlpracticeapp.presentation.MainScreen
import com.klaudia.graphqlpracticeapp.presentation.ScreenViewModel
import com.klaudia.graphqlpracticeapp.ui.theme.GraphQLPracticeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphQLPracticeAppTheme {
                val viewModel: ScreenViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                MainScreen(
                    state = state,
                    onCharacterSelect = {
                        viewModel.selectCharacter(it)
                    },
                    onDialogDismiss = viewModel::dismissCharacterDialog
                    )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GraphQLPracticeAppTheme {
        Greeting("Android")
    }
}