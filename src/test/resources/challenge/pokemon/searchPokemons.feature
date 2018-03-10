Feature: We want to test the searching service for the project

  *MAIN SCENARIOS*

  Scenario: SC01. Check the search request retrieve the correct number of pokemons from the database.
    Given the next pokemons already created in the system
      | name        | description    | image          |
      | pikachu     | Pika pi!       | www.test.com   |
      | charmander  | Fuego          | www.google.com |
    When the user requests all the pokemons from the system
    Then it should be returned this list of pokemons with '2' elements

  Scenario: SC02. Check the search request retrieve all the pokemons witch name contains a string.
    Given the next pokemons already created in the system
      | name        | description    | image          |
      | pikachu     | Pika pi!       | www.test.com   |
      | charmander  | Fuego          | www.google.com |
    When the user requests all the pokemons from the system witch contains 'ka' in the name field
    Then it should be returned this list of pokemons with '1' elements

  Scenario: SC03. Check the search request retrieve all the favourites pokemon from the database.
    Given the next pokemons already created in the system
      | name        | description    | image          | favourite |
      | pikachu     | Pika pi!       | www.test.com   | true      |
      | charmander  | Fuego          | www.google.com | false     |
    When the user requests all the favourites pokemons from the system
    Then it should be returned this list of pokemons with '1' elements

