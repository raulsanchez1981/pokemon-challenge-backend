package challenge.repositories;

import challenge.entities.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository<T extends Pokemon> extends MongoRepository<T, String> {

}
