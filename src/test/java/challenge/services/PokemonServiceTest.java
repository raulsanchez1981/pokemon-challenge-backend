package challenge.services;

import challenge.controllers.PokemonController;
import challenge.entities.Pokemon;
import challenge.exception.types.ChallengeControllerException;
import challenge.exception.types.ChallengeDataAccessException;
import challenge.exception.types.ChallengeServiceException;
import challenge.repositories.PokemonRepository;
import challenge.search.PokemonSearch;
import challenge.utils.ErrorCodes;
import challenge.utils.ErrorMessages;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PokemonServiceTest {

    private List<Pokemon> emptyList;
    private Pokemon pokemon;
    private PokemonSearch pokemonSearch;


    @Mock
    PokemonRepository pokemonRepository;

    @Mock
    ErrorMessages errorMessages;

    @InjectMocks
    private PokemonServiceImpl pokemonService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.emptyList = new ArrayList<>();
        this.pokemon = new Pokemon();
        this.pokemonSearch = new PokemonSearch();
    }

    @Test
    public void testGetAllPokemons() {
        Mockito.when(pokemonRepository.findPokemonsBySearchFilter(this.pokemonSearch)).thenReturn(this.emptyList);
        List<Pokemon> result = this.pokemonService.findPokemons(pokemonSearch);
        Assert.assertEquals(result, this.emptyList);
    }

    @Test(expected = ChallengeServiceException.class)
    public void testGetAllPokemonsException() {
        Mockito.when(this.pokemonRepository.findPokemonsBySearchFilter(pokemonSearch)).thenThrow(new ChallengeServiceException("error", new Throwable()));
        this.pokemonService.findPokemons(pokemonSearch);
    }


    @Test
    public void testCreatePokemon() {
        Mockito.when(pokemonRepository.save(pokemon)).thenReturn(this.pokemon);
        Pokemon result = pokemonService.savePokemon(pokemon);
        Assert.assertEquals(result, this.pokemon);
    }

    @Test(expected = ChallengeServiceException.class)
    public void testCreatePokemonException() {
        Mockito.when(pokemonRepository.save(pokemon)).thenThrow(new ChallengeServiceException("error", new Throwable()));
        this.pokemonService.savePokemon(pokemon);
    }

    @Test
    public void testUpdatePokemon() {
        Mockito.doNothing().when(pokemonRepository).updatePokemon(pokemon);
        Mockito.when(errorMessages.getProperty(ErrorCodes.UPDATE_ERROR)).thenReturn(null);
        Mockito.when(pokemonRepository.findById(null)).thenReturn(Optional.of(this.pokemon));
        Pokemon result = pokemonService.updatePokemon(pokemon);
        Assert.assertEquals(result, this.pokemon);
    }

    @Test(expected = ChallengeServiceException.class)
    public void testUpdatePokemonException() {
        Mockito.doThrow(new ChallengeDataAccessException("error", new Throwable())).when(pokemonRepository).updatePokemon(pokemon);
        pokemonService.updatePokemon(pokemon);
    }

    @Test
    public void testDeletePokemon() {
        Mockito.when(pokemonRepository.findById("")).thenReturn(Optional.of(this.pokemon));
        Mockito.doNothing().when(pokemonRepository).delete(this.pokemon);
        pokemonService.deletePokemon("");
    }

    @Test(expected = ChallengeServiceException.class)
    public void testDeletePokemonException() {
        Mockito.when(pokemonRepository.findById(null)).thenReturn(Optional.of(this.pokemon));
        Mockito.doThrow(new ChallengeServiceException("error", new Throwable())).when(pokemonRepository).delete(this.pokemon);
        pokemonService.deletePokemon("");
    }
}
