package com.klaudia.graphqlpracticeapp.di

import com.apollographql.apollo3.ApolloClient
import com.klaudia.graphqlpracticeapp.data.ApolloCharacterClient
import com.klaudia.graphqlpracticeapp.domain.CharacterClient
import com.klaudia.graphqlpracticeapp.domain.GetCharacterUseCase
import com.klaudia.graphqlpracticeapp.domain.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder().serverUrl("https://rickandmortyapi.com/graphql").build()
    }

    @Provides
    @Singleton
    fun provideCountryClient(apolloClient: ApolloClient): CharacterClient {
        return ApolloCharacterClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(characterClient: CharacterClient): GetCharactersUseCase{
        return GetCharactersUseCase(characterClient)
    }

    @Provides
    @Singleton
    fun provideGetCharacterUseCase(characterClient: CharacterClient): GetCharacterUseCase {
        return GetCharacterUseCase(characterClient)
    }
}