package challenge.repositories;

import challenge.entities.Pokemon;
import challenge.search.PokemonSearch;

import java.util.List;

public interface CustomPokemonRepository {

    List<Pokemon> findPokemonsBySearchFilter(PokemonSearch pokemonSearch);
}
