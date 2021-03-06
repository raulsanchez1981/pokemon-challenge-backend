package challenge.services;

import challenge.entities.Pokemon;
import challenge.exception.types.ChallengeControllerException;
import challenge.exception.types.ChallengeServiceException;
import challenge.repositories.PokemonRepository;
import challenge.search.PokemonSearch;
import challenge.utils.ErrorCodes;
import challenge.utils.ErrorMessages;
import challenge.validations.ValidationPokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
        try {
            Optional<Pokemon> pokemon = this.pokemonRepository.findById(id);
            return pokemon.get();
        } catch (NoSuchElementException e) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.POKEMON_NOT_FOUND));
        }
    }

    @Override
    public List<Pokemon> findPokemons(PokemonSearch pokemonSearch) {
        try {
            return this.pokemonRepository.findPokemonsBySearchFilter(pokemonSearch);
        } catch (ChallengeControllerException e) {
            throw new ChallengeServiceException(e.getCause().getMessage());
        }
    }

    @Override
    @ValidationPokemon
    public Pokemon savePokemon(Pokemon pokemon) {
        try {
            pokemon.setId(null);
            return (Pokemon) this.pokemonRepository.save(pokemon);
        } catch (DuplicateKeyException e) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.DUPLICATE_POKEMON));
        }
    }

    @Override
    @ValidationPokemon
    public Pokemon updatePokemon(Pokemon pokemon) {
        try {
            this.pokemonRepository.updatePokemon(pokemon);
            return this.findPokemonById(pokemon.getId());
        } catch (Exception e) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.UPDATE_ERROR));
        }
    }

    @Override
    public Pokemon makePokemonFavourite(String id) {
        try {
            if (getFavouriteNumber() < 10) {
                Pokemon pokemon = this.findPokemonById(id);
                pokemon.setFavourite(true);
                this.pokemonRepository.updatePokemon(pokemon);
                return pokemon;
            }
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.MAX_FAVOURITE));
        } catch (ChallengeServiceException e) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.UPDATE_ERROR));
        }
    }

    private int getFavouriteNumber() {
        PokemonSearch pokemonSearch = new PokemonSearch();
        pokemonSearch.setFavourite(true);
        return this.pokemonRepository.findPokemonsBySearchFilter(pokemonSearch).size();
    }

    @Override
    public Pokemon unMakePokemonFavourite(String id) {
        try {
            Pokemon pokemon = this.findPokemonById(id);
            pokemon.setFavourite(false);
            this.pokemonRepository.updatePokemon(pokemon);
            return pokemon;
        } catch (Exception e) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.UPDATE_ERROR));
        }
    }

    @Override
    public void deletePokemon(String id) {
        Pokemon pokemon = this.findPokemonById(id);
        try {
            this.pokemonRepository.delete(pokemon);
        } catch (Exception e) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.DELETE_ERROR));
        }
    }
}
