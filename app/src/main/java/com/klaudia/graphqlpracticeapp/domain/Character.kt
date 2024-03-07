package com.klaudia.graphqlpracticeapp.domain

data class CharactersResponse(
    val characters: CharactersPage
)

data class CharactersPage(
    val info: PageInfo,
    val results: List<Character>
)

data class PageInfo(
    val count: Int,
    val pages: Int
)

data class Character(
    val id: String,
    val name: String,
    val species: String,
    val image: String // Assuming 'image' is a URL in String format
)

data class DetailedCharacter(
    //val id: String,
    val name: String,
    val species: String,
    val image: String,
    val gender: String,
    val origin: String,
    val episode: List<String>
)
