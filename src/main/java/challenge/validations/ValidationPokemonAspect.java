package challenge.validations;

import challenge.entities.Pokemon;
import challenge.exception.types.ValidationDataException;
import challenge.services.PokemonService;
import challenge.utils.ErrorCodes;
import challenge.utils.ErrorMessages;
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
        if (!errorList.isEmpty()) {
            throw new ValidationDataException(errorList.toString());
        }
        return joinPoint.proceed();
    }


    private void checkPokemonTypes(Pokemon pokemon, List<String> errorList) {
        if (pokemon.getTypes().size() > 3 || pokemon.getTypes().isEmpty()) {
            errorList.add(errorMessages.getProperty(ErrorCodes.TYPES_NUMBER));
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
}
