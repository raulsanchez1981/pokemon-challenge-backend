package challenge.repositories;

import challenge.entities.Pokemon;
import challenge.exception.types.ChallengeServiceException;
import challenge.search.PokemonSearch;
import challenge.utils.ErrorCodes;
import challenge.utils.ErrorMessages;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.MongoRegexCreator;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;

import java.util.List;

@EnableConfigurationProperties
public class PokemonRepositoryImpl implements CustomPokemonRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ErrorMessages errorMessages;

    @Override
    public List<Pokemon> findPokemonsBySearchFilter(PokemonSearch pokemonSearch) {
        Query query = new Query();

        if (!StringUtils.isEmpty(pokemonSearch.getName())) {
            query.addCriteria(Criteria.where("name").regex(toLikeRegex(pokemonSearch.getName()), "i"));
        }
        if (null != pokemonSearch.getFavourite()) {
            query.addCriteria(Criteria.where("favourite").is(pokemonSearch.getFavourite()));
        }
        try {
            List<Pokemon> list = this.mongoTemplate.find(query, Pokemon.class);
            return list;
        } catch (Exception e) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.FIND_POKEMON_ERROR));

        }
    }

    @Override
    public void updatePokemon(Pokemon pokemon) {
        Document pokemonDocument = (Document) mongoTemplate.getConverter().convertToMongoType(pokemon);
        Update setUpdate = Update.fromDocument(new Document("$set", pokemonDocument));
        mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(pokemon.getId())), setUpdate, Pokemon.class);
    }


    private String toLikeRegex(String source) {
        return MongoRegexCreator.INSTANCE.toRegularExpression(source, MongoRegexCreator.MatchMode.LIKE);
    }

}
