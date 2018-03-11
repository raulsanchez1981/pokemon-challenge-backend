package challenge.controllers;

import challenge.entities.Pokemon;
import challenge.exception.types.ChallengeControllerException;
import challenge.exception.types.ChallengeServiceException;
import challenge.search.PokemonSearch;
import challenge.services.PokemonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;


    @ApiOperation(value = "Obtain a Pokemon By Id",
            notes = "**Id** must be filled<br>",
            response = Pokemon.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Pokemon findPokemonById(@PathVariable String id) {
        return this.pokemonService.findPokemonById(id);
    }

    @ApiOperation(value = "Obtain a List of Pokemons",
            notes = "The search can be done by the following fields:"
                    + "\n"
                    + "\n- **name**<br>"
                    + "\n- **favourite**<br>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public List<Pokemon> findPokemons(@RequestBody PokemonSearch pokemonSearch) {
        List<Pokemon> listPokemons;
        try {
            listPokemons = this.pokemonService.findPokemons(pokemonSearch);
            if (listPokemons.isEmpty()) {
                listPokemons = null;
            }
        } catch (ChallengeServiceException e) {
            throw new ChallengeControllerException(e.getMessage());
        }
        return listPokemons;
    }

    @ApiOperation(value = "Add a new Pokemon to the Collection",
            notes = "You can create any Pokemons as you want. There is a limitation:\n"
                    + "\nThe following validations are applied:"
                    + "\n"
                    + "\n- **name** must be filled<br>"
                    + "\n- **description** must be filled<br>"
                    + "\n- **description** must be filled<br>"
                    + "\n- **type (1 min, 2 max)**<br>"
                    + "\n- **image** must be filled<br>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(method = RequestMethod.POST, value = "")
    public Pokemon savePokemon(@Valid @RequestBody Pokemon pokemon, BindingResult bindingResult) {
        buildErrorMessages(bindingResult);
        try {
            return this.pokemonService.savePokemon(pokemon);
        } catch (ChallengeServiceException e) {
            throw new ChallengeControllerException(e.getMessage());
        }
    }

    @ApiOperation(value = "Update a Pokemon from the Collection",
            notes = "You can create any Pokemons as you want. The required parameters are the same that in create Pokemon\n"
                    + "\nThe following validations are applied too:"
                    + "\n"
                    + "\n- **Id** must be filled<br>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(method = RequestMethod.PUT, value = "")
    public Pokemon updatePokemon(@RequestBody Pokemon pokemon) {
        try {
            return this.pokemonService.updatePokemon(pokemon);
        } catch (ChallengeServiceException e) {
            throw new ChallengeControllerException(e.getMessage());
        }
    }

    @ApiOperation(value = "Delete a Pokemon from the Collection",
            notes = "The following validations are applied:"
                    + "\n"
                    + "\n- **Id** must be filled<br>")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content", response = Void.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method=RequestMethod.DELETE, value="/{id}")
    public void deletePokemon(@PathVariable String id)  {
        try {
            this.pokemonService.deletePokemon(id);
        } catch (ChallengeServiceException e) {
            throw new ChallengeControllerException(e.getMessage());
        }
    }

    @ApiOperation(value = "Make Fvourite a Pokemon from the Collection",
            notes = "The following validations are applied:"
                    + "\n"
                    + "\n- **Id** must be filled<br>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(method = RequestMethod.PUT, value = "/favourite/make/{id}")
    public Pokemon makePokemonFavourite(@PathVariable String id) {
        try {
            return this.pokemonService.makePokemonFavourite(id);
        } catch (ChallengeServiceException e) {
            throw new ChallengeControllerException(e.getMessage());
        }
    }

    @ApiOperation(value = "Unmake Favourite a Pokemon from the Collection",
            notes = "The following validations are applied:"
                    + "\n"
                    + "\n- **Id** must be filled<br>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(method = RequestMethod.PUT, value = "/favourite/unmake/{id}")
    public Pokemon unMakePokemonFavourite(@PathVariable String id) {
        try {
            return this.pokemonService.unMakePokemonFavourite(id);
        } catch (ChallengeServiceException e) {
            throw new ChallengeControllerException(e.getMessage());
        }
    }


    private void buildErrorMessages(BindingResult bindingResult) {
        final StringBuilder builder = new StringBuilder();
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach(item -> {
                builder.append(item.getDefaultMessage());
                builder.append(" <br> ");
            });
            throw new ChallengeControllerException(builder.toString());
        }
    }
}
