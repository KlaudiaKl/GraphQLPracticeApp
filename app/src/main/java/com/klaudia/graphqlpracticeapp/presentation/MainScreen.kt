package com.klaudia.graphqlpracticeapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.klaudia.graphqlpracticeapp.domain.Character
import com.klaudia.graphqlpracticeapp.domain.DetailedCharacter

@Composable
fun MainScreen(
    state: ScreenViewModel.CharactersState,
    onCharacterSelect: (id: String) -> Unit,
    onDialogDismiss: () -> Unit
) {
    Column {


    Text(text = "Rick and Morty characters", style = MaterialTheme.typography.headlineMedium)
    Spacer(modifier = Modifier.height(16.dp) )
    LazyColumn(modifier = Modifier.fillMaxSize() ){
        items(state.characters){
                character ->
            CharacterItem(
                character = character,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCharacterSelect(character.id) }
                    .padding(16.dp)
            )
        }
    }
    if (state.selectedCharacter != null){
        CharacterDialog(character = state.selectedCharacter, onDismiss = onDialogDismiss, modifier = Modifier
            .clip(
                RoundedCornerShape(5.dp)
            )
            .background(Color.White)
            .padding(16.dp))
    }
    }
}

@Composable
fun CharacterDialog(
    character: DetailedCharacter,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {

    Dialog(onDismissRequest = onDismiss) {
        Column (modifier = modifier){
            Row(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(character.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Character image",
                    modifier = Modifier.size(100.dp)
                )
                Text(text = character.name, fontSize = 30.sp)
                Spacer(modifier = Modifier.width(16.dp))


            }
            Text(text = character.species, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Gender: ${character.gender}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Origin: ${character.origin}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Episodes: ${character.episode.joinToString()}")

        }
    }
}

@Composable
fun CharacterItem(
    character: Character,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(character.image)
                .crossfade(true)
                .build(),
            contentDescription = "Character image",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = character.name, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = character.species)
        }

    }
}