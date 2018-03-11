package challenge.controllers;

import challenge.entities.Pokemon;
import challenge.enums.Type;
import challenge.exception.types.ChallengeControllerException;
import challenge.exception.types.ChallengeServiceException;
import challenge.search.PokemonSearch;
import challenge.services.PokemonService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;


public class PokemonControllerTest {

    private List<Pokemon> emptyList;
    private Pokemon pokemon;
    private PokemonSearch pokemonSearch;


    @Mock
    private PokemonService pokemonService;

    @InjectMocks
    private PokemonController pokemonController;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.emptyList = new ArrayList<>();
        fillPokemon();
        this.pokemonSearch = new PokemonSearch();
    }

    private void fillPokemon() {
        this.pokemon = new Pokemon();
        this.pokemon.setId("sfdsfdsfdsfdfds");
        this.pokemon.setName("Named");
        this.pokemon.setDescription("lkfdlksjfkljsdflkjsldkfjalksdjflkjdlñkjasdklfjlñkjsdflkjasflkajdfklfja");
        this.pokemon.getTypes().add(Type.ACERO);
    }

    @Test
    public void testGetAllPokemons() {
        Mockito.when(pokemonService.findPokemons(this.pokemonSearch)).thenReturn(this.emptyList);
        List<Pokemon> result = this.pokemonController.findPokemons(pokemonSearch);
        Assert.assertEquals(result, null);
    }

    @Test(expected = ChallengeControllerException.class)
    public void testGetAllPokemonsException() {
        Mockito.when(this.pokemonService.findPokemons(pokemonSearch)).thenThrow(new ChallengeServiceException("error", new Throwable()));
        this.pokemonController.findPokemons(pokemonSearch);
    }

    @Test
    public void testCreatePokemon() {
        Mockito.when(pokemonService.savePokemon(pokemon)).thenReturn(this.pokemon);
        Pokemon result = pokemonController.savePokemon(pokemon, null);
        Assert.assertEquals(result, this.pokemon);
    }

    @Test(expected = ChallengeControllerException.class)
    public void testCreatePokemonException() {
        Mockito.when(pokemonService.savePokemon(pokemon)).thenThrow(new ChallengeServiceException("error", new Throwable()));
        this.pokemonController.savePokemon(pokemon, null);
    }

    @Test
    public void testUpdatePokemon() {
        Mockito.when(pokemonService.updatePokemon(pokemon)).thenReturn(this.pokemon);
        Pokemon result = pokemonController.updatePokemon(pokemon, null);
        Assert.assertEquals(result, this.pokemon);
    }

    @Test(expected = ChallengeControllerException.class)
    public void testUpdatePokemonException() {
        Mockito.when(pokemonService.updatePokemon(pokemon)).thenThrow(new ChallengeServiceException("error", new Throwable()));
        pokemonController.updatePokemon(pokemon, null);
    }

    @Test
    public void testDeletePokemon() {
        Mockito.when(pokemonService.findPokemonById("")).thenReturn(this.pokemon);
        Mockito.doNothing().when(pokemonService).deletePokemon("");
        pokemonController.deletePokemon("");
    }

    @Test(expected = ChallengeControllerException.class)
    public void testDeletePokemonException() {
        Mockito.when(pokemonService.findPokemonById("")).thenReturn(this.pokemon);
        Mockito.doThrow(new ChallengeServiceException("error", new Throwable())).when(pokemonService).deletePokemon("");
        pokemonController.deletePokemon("");
    }

    @Test
    public void testMakeFavouritePokemon() {
        Mockito.when(pokemonService.makePokemonFavourite("")).thenReturn(this.pokemon);
        Pokemon pokemonFav = pokemonController.makePokemonFavourite("");
        Assert.assertEquals(pokemonFav, this.pokemon);
    }

    @Test(expected = ChallengeControllerException.class)
    public void testMakeFavouritePokemonException() {
        Mockito.when(this.pokemonService.makePokemonFavourite("")).thenThrow(new ChallengeServiceException("error", new Throwable()));
        this.pokemonController.makePokemonFavourite("");
    }


    @Test
    public void testUnmakeFavouritePokemon() {
        Mockito.when(pokemonService.unMakePokemonFavourite("")).thenReturn(this.pokemon);
        Pokemon pokemonFav = pokemonController.unMakePokemonFavourite("");
        Assert.assertEquals(pokemonFav, this.pokemon);
    }

    @Test(expected = ChallengeControllerException.class)
    public void testUnmakeFavouritePokemonException() {
        Mockito.when(this.pokemonService.unMakePokemonFavourite("")).thenThrow(new ChallengeServiceException("error", new Throwable()));
        this.pokemonController.unMakePokemonFavourite("");
    }
}
