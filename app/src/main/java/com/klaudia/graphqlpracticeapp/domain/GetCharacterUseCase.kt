package com.klaudia.graphqlpracticeapp.domain

class GetCharacterUseCase (private val characterClient: CharacterClient) {
    suspend fun execute(code: String): DetailedCharacter? {
        return characterClient.getOneCharacter(code)
    }
}