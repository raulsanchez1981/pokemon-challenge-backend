package challenge.services;

import challenge.entities.Pokemon;
import challenge.exception.types.ChallengeServiceException;
import challenge.repositories.PokemonRepository;
import challenge.search.PokemonSearch;
import challenge.utils.ErrorCodes;
import challenge.utils.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@EnableConfigurationProperties
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    PokemonRepository pokemonRepository;

    @Autowired
    ErrorMessages errorMessages;

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
        try {
            pokemon.setId(null);
            return (Pokemon) this.pokemonRepository.save(pokemon);
        } catch (DuplicateKeyException e) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.DUPLICATE_POKEMON));
        }
    }

    @Override
    public Pokemon updatePokemon(Pokemon pokemon) {
        try {
            this.pokemonRepository.updatePokemon(pokemon);
            return this.findPokemonById(pokemon.getId());
        } catch (Exception e) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.UPDATE_ERROR));
        }
    }

    @Override
    public void deletePokemon(String id) {
        Pokemon pokemon = this.findPokemonById(id);
        if (null == pokemon) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.POKEMON_NOT_FOUND));
        }
        try {
            this.pokemonRepository.delete(id);
        } catch (Exception e) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.DELETE_ERROR));
        }
    }
}
