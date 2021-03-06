package challenge.services;

import challenge.entities.Pokemon;
import challenge.search.PokemonSearch;

import java.util.List;
import java.util.Optional;

public interface PokemonService {

    Pokemon findPokemonById(String id);
    List<Pokemon> findPokemons(PokemonSearch pokemonSearch);
    Pokemon savePokemon(Pokemon pokemon);
    Pokemon updatePokemon(Pokemon pokemon);
    Pokemon makePokemonFavourite(String id);
    Pokemon unMakePokemonFavourite(String id);
    void deletePokemon(String id);
}
