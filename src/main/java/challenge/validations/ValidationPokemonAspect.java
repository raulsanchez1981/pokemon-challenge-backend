package challenge.validations;

import challenge.entities.Pokemon;
import challenge.enums.Type;
import challenge.exception.types.ValidationDataException;
import challenge.search.PokemonSearch;
import challenge.services.PokemonService;
import challenge.utils.ErrorCodes;
import challenge.utils.ErrorMessages;
import org.apache.commons.lang3.EnumUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rsanchpa on 29/09/2017.
 */

@Aspect
@Component
@EnableConfigurationProperties
public class ValidationPokemonAspect {

    @Autowired
    PokemonService pokemonService;

    @Autowired
    ErrorMessages errorMessages;


    @Around("@annotation(ValidationPokemon)")
    public Object ckeckPokemonIsValid(ProceedingJoinPoint joinPoint) throws Throwable {
        Pokemon pokemon = (Pokemon) joinPoint.getArgs()[0];
        List<String> errorList = new ArrayList<>();
        checkNamePokemon(pokemon.getName(), errorList);
        checkDescriptionPokemon(pokemon, errorList);
        checkPokemonTypes(pokemon, errorList);
        checkPokemonEvolution(pokemon, errorList);
        checkFavoritePokemons(pokemon, errorList);
        if (!errorList.isEmpty()) {
            throw new ValidationDataException(errorList.toString());
        }
        return joinPoint.proceed();
    }

    private void checkPokemonEvolution(Pokemon pokemon, List<String> errorList) {
        if (null != pokemon.getEvolution()){
            if (pokemon.getEvolution().equalsIgnoreCase(pokemon.getName())) {
                errorList.add(errorMessages.getProperty(ErrorCodes.EVOLUTION_SAME));
            }
            PokemonSearch pokemonSearch = new PokemonSearch();
            pokemonSearch.setName(pokemon.getEvolution());
            List<Pokemon> pokemonList = this.pokemonService.findPokemons(pokemonSearch);
            if (pokemonList.isEmpty() || pokemonList.stream().noneMatch(item -> pokemon.getEvolution().equals(item.getName()))) {
                errorList.add(errorMessages.getProperty(ErrorCodes.EVOLUTION_EXIST));
            }

        }
    }

    private void checkPokemonTypes(Pokemon pokemon, List<String> errorList) {
        if (pokemon.getTypes().size() > 2 || pokemon.getTypes().isEmpty()) {
            errorList.add(errorMessages.getProperty(ErrorCodes.TYPES_NUMBER));
        }
        if (!pokemon.getTypes().isEmpty()){
            if (!pokemon.getTypes().stream().allMatch(item ->EnumUtils.isValidEnum(Type.class, item))) {
                errorList.add(errorMessages.getProperty(ErrorCodes.TYPES_ENUM));
            }
        }
    }

    private void checkDescriptionPokemon(Pokemon pokemon, List<String> errorList) {
        if (pokemon.getDescription().length() < 30) {
            errorList.add(errorMessages.getProperty(ErrorCodes.DESCRIPTION_LENGTH));
        }
    }

    private void checkNamePokemon(String name, List<String> errorList) {
        if (!name.matches("^\\b[a-zA-Z0-9_]+\\b$")) {
            errorList.add(errorMessages.getProperty(ErrorCodes.NAME_ONE_WORD));
        }
        if (name.length() < 4 || name.length() > 24) {
            errorList.add(errorMessages.getProperty(ErrorCodes.NAME_LENGTH));
        }
    }

    private void checkFavoritePokemons(Pokemon pokemon, List<String> errorList) {
        if (pokemon.isFavourite()) {
            PokemonSearch filter = new PokemonSearch();
            filter.setFavourite(true);
            List<Pokemon> pokemonList = this.pokemonService.findPokemons(filter);
            if (pokemonList.size() > 9 && pokemonList.stream().noneMatch(item -> item.getName().equalsIgnoreCase(pokemon.getName()))) {
                errorList.add(errorMessages.getProperty(ErrorCodes.MAX_FAVOURITE));
            }
        }
    }
}
