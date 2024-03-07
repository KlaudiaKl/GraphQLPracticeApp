package com.klaudia.graphqlpracticeapp.domain

interface CharacterClient {
    suspend fun getCharacters(): CharactersResponse
    suspend fun getOneCharacter(id: String): DetailedCharacter?

}