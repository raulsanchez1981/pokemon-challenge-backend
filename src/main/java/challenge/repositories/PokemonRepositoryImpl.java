package challenge.repositories;

import challenge.entities.Pokemon;
import challenge.search.PokemonSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.MongoRegexCreator;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.util.StringUtils;

import java.util.List;

public class PokemonRepositoryImpl implements CustomPokemonRepository {

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public List<Pokemon> findPokemonsBySearchFilter(PokemonSearch pokemonSearch) {
        Query query = new Query();

        if (!StringUtils.isEmpty(pokemonSearch.getName())) {
            query.addCriteria(Criteria.where("name").regex(toLikeRegex(pokemonSearch.getName()), "i"));
        }
        if (null != pokemonSearch.getFavourite()) {
            query.addCriteria(Criteria.where("favourite").is(pokemonSearch.getFavourite()));
        }
        List<Pokemon> list = this.mongoTemplate.find(query, Pokemon.class);
        return list;
    }


    private String toLikeRegex(String source) {
        return MongoRegexCreator.INSTANCE.toRegularExpression(source, MongoRegexCreator.MatchMode.LIKE);
    }

}
