package challenge.controllers;

import challenge.entities.Pokemon;
import challenge.search.PokemonSearch;
import challenge.services.PokemonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        return this.pokemonService.findPokemons(pokemonSearch);
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
    public Pokemon savePokemon(@RequestBody Pokemon pokemon) {
        return this.pokemonService.savePokemon(pokemon);
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
        return this.pokemonService.updatePokemon(pokemon);
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
        this.pokemonService.deletePokemon(id);
    }

}
