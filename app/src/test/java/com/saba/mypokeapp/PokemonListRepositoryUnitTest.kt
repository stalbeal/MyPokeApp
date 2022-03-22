package com.saba.mypokeapp

import com.saba.mypokeapp.pokemonlist.model.Pokemon
import com.saba.mypokeapp.pokemonlist.network.ApiPokemonListService
import com.saba.mypokeapp.pokemonlist.network.ApiPokemonService
import com.saba.mypokeapp.pokemonlist.network.model.*
import com.saba.mypokeapp.pokemonlist.repository.list.PokemonListRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PokemonListRepositoryUnitTest {

    private val listService: ApiPokemonListService = mockk()
    private val pokemonService: ApiPokemonService = mockk()

    private lateinit var repository: PokemonListRepositoryImpl

    @Before
    fun setUp() {
        repository = PokemonListRepositoryImpl(listService, pokemonService)
    }

    @Test
    fun `when getPokemonList is called then got from backend and api pokemon list mapped to pokemon list`() =
        runTest {
            coEvery { listService.getPokemonList(2, 0) } answers {
                ApiPokemonListResponse(
                    2, "", null, listOf(
                        ApiPokemonItemResponse("bulbasaur", "bulbasaururl"),
                        ApiPokemonItemResponse("ivysaur", "ivysaururl")
                    )
                )
            }

            coEvery { pokemonService.getPokemonDetail("bulbasaururl") } answers {
                ApiPokemonDetailResponse(
                    id = 1,
                    name = "bulbasaur",
                    forms = listOf(),
                    height = 10,
                    abilities = listOf(),
                    baseExperience = 10,
                    isDefault = true,
                    locationAreaEncounters = "",
                    moves = listOf(),
                    order = 1,
                    species = ApiPokemonItemResponse("bulbasaur", ""),
                    stats = listOf(),
                    sprites = ApiSpritesResponse(
                        other = ApiOtherSpritesResponse(
                            ApiImagesResponse(),
                            ApiImagesResponse(),
                            ApiImagesResponse()
                        )
                    ),
                    types = listOf(),
                    weight = 5
                )
            }
            coEvery { pokemonService.getPokemonDetail("ivysaururl") } answers {
                ApiPokemonDetailResponse(
                    id = 2,
                    name = "ivysaur",
                    forms = listOf(),
                    height = 10,
                    abilities = listOf(),
                    baseExperience = 10,
                    isDefault = true,
                    locationAreaEncounters = "",
                    moves = listOf(),
                    order = 2,
                    species = ApiPokemonItemResponse("ivysaur", ""),
                    stats = listOf(),
                    sprites = ApiSpritesResponse(
                        other = ApiOtherSpritesResponse(
                            ApiImagesResponse(),
                            ApiImagesResponse(),
                            ApiImagesResponse()
                        )
                    ),
                    types = listOf(),
                    weight = 5
                )
            }

            val response = repository.getList(2, 0)

            Assert.assertEquals(
                listOf(
                    Pokemon(
                        id = 1,
                        name = "bulbasaur",
                        image = "",
                        order = 1,
                        types = listOf(),
                        nextCall = ""
                    ),
                    Pokemon(
                        id = 2,
                        name = "ivysaur",
                        image = "",
                        order = 2,
                        types = listOf(),
                        nextCall = ""
                    )
                ),
                response
            )
            coVerify(exactly = 1) { listService.getPokemonList(2, 0) }
            coVerify(exactly = 1) { pokemonService.getPokemonDetail("ivysaururl") }
            coVerify(exactly = 1) { pokemonService.getPokemonDetail("bulbasaururl") }
        }
}
