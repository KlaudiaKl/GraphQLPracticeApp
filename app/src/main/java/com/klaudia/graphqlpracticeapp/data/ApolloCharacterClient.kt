package com.klaudia.graphqlpracticeapp.data

import com.apollographql.apollo3.ApolloClient
import com.klaudia.CharacterQuery
import com.klaudia.CharactersQuery
import com.klaudia.graphqlpracticeapp.domain.Character
import com.klaudia.graphqlpracticeapp.domain.CharacterClient
import com.klaudia.graphqlpracticeapp.domain.CharactersPage
import com.klaudia.graphqlpracticeapp.domain.CharactersResponse
import com.klaudia.graphqlpracticeapp.domain.DetailedCharacter
import com.klaudia.graphqlpracticeapp.domain.PageInfo
import com.klaudia.graphqlpracticeapp.util.toDetailedCharacter

class ApolloCharacterClient(private val apolloClient: ApolloClient) : CharacterClient {
    override suspend fun getCharacters(): CharactersResponse {
        val response = apolloClient.query(CharactersQuery()).execute()
        if (response.hasErrors()) {
            throw Exception(response.errors?.joinToString { it.message })
        }

        // Map the Apollo response to your Kotlin data model
        val charactersPage = response.data?.characters?.let {
            CharactersPage(
                info = PageInfo(it.info?.count?: 0, it.info?.pages?: 0),
                results = it.results?.map { character ->
                    Character(
                        id = character?.id ?: "", // Handling null with a default value or error handling
                        name = character?.name ?: "Unknown",
                        species = character?.species ?: "Unknown",
                        image = character?.image ?: "Unknown"
                    )
                } ?: emptyList() // Provide an empty list as a default if results is null
            )
        } ?: throw Exception("Characters not found")



        return CharactersResponse(charactersPage)
    }

    override suspend fun getOneCharacter(id: String): DetailedCharacter? {
        val response = apolloClient.query(CharacterQuery(id)).execute()
        return response.data?.character?.toDetailedCharacter()
    }
}