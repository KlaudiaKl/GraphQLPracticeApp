package com.klaudia.graphqlpracticeapp.domain

class GetCharactersUseCase(private val characterClient: CharacterClient) {
    suspend fun execute(): CharactersResponse {
        return characterClient.getCharacters()
    }
}