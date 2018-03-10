package challenge.services;

import challenge.entities.Pokemon;
import challenge.repositories.PokemonRepository;
import challenge.search.PokemonSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    PokemonRepository pokemonRepository;

    @Override
    public Pokemon findPokemonById(String id) {
        Optional<Pokemon> pokemon = this.pokemonRepository.findById(id);
        return pokemon.get();
    }

    @Override
    public List<Pokemon> findPokemons(PokemonSearch pokemonSearch) {
        return this.pokemonRepository.findPokemonsBySearchFilter(pokemonSearch);
    }

    @Override
    public Pokemon savePokemon(Pokemon pokemon) {
        return (Pokemon) this.pokemonRepository.save(pokemon);
    }

    @Override
    public Pokemon updatePokemon(Pokemon pokemon) {
        return (Pokemon) this.pokemonRepository.save(pokemon);
    }

    @Override
    public void deletePokemon(String id) {
        this.pokemonRepository.delete(id);
    }
}
