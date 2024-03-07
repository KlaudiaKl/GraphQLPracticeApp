package com.klaudia.graphqlpracticeapp.util

import com.klaudia.CharacterQuery
import com.klaudia.graphqlpracticeapp.domain.DetailedCharacter

fun CharacterQuery.Character.toDetailedCharacter(): DetailedCharacter{
    return DetailedCharacter(
        name = name?:"",
        species = species?:"",
        gender = gender?:"",
        episode = episode.mapNotNull { it?.episode },
        image = image?:"",
        origin = origin?.name?:""
    )
}