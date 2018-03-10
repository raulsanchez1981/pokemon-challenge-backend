package challenge.functional.steps;

import challenge.Application;
import challenge.entities.Pokemon;
import challenge.repositories.PokemonRepository;
import challenge.search.PokemonSearch;
import challenge.services.PokemonService;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rsanchpa on 17/09/2017.
 */
@WebAppConfiguration
@ContextConfiguration(classes= Application.class)
@SpringBootTest
public class PokemonSearchSteps {

    private List<Pokemon> pokemonList = new ArrayList<>();

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private PokemonRepository pokemonRepository;


    @Given("^the next pokemons already created in the system$")
    public void createNewPokemon (List<Pokemon> pokemonList) {
        pokemonList.stream().forEach(item -> this.pokemonRepository.save(item));
    }

    @When("^the user requests all the pokemons from the system$")
    public void searchPokemons() {
        this.pokemonList = this.pokemonService.findPokemons(new PokemonSearch());
    }

    @When("^the user requests all the pokemons from the system witch contains '(.+)' in the name field$")
    public void searchPokemons(String searchString) {
        PokemonSearch pokemonSearch = new PokemonSearch();
        pokemonSearch.setName(searchString);
        this.pokemonList = this.pokemonService.findPokemons(pokemonSearch);
    }
    @When("^the user requests all the favourites pokemons from the system$")
    public void searchFavouritesPokemons() {
        PokemonSearch pokemonSearch = new PokemonSearch();
        pokemonSearch.setFavourite(true);
        this.pokemonList = this.pokemonService.findPokemons(pokemonSearch);
    }

    @Then("^it should be returned this list of pokemons with '(.+)' elements$")
    public void countFoundIssues(long count) {
        Assert.assertTrue(count == pokemonList.size());
    }

//    @Then("^it should be returned this list of issues with not empty id$")
//    public void checkIdFoundIssues() {
//        List<Issue> listResult = this.listIssues.stream().filter(p -> (!p.getId().equals(null))).collect(Collectors.toList());
//        Assert.assertEquals(listIssues.size(), listResult.size());
//    }
//
//    @Then("^it should be returned this list of issues with all the elemets unlocked$")
//    public void checkFoundIssues() {
//        List<Issue> listResult = this.listIssues.stream().filter(p -> (p.getLocked().equals(false))).collect(Collectors.toList());
//        Assert.assertEquals(listIssues.size(), listResult.size());
//    }

    @After
    public void tearDown() {
        this.pokemonRepository.deleteAll();
    }


}
