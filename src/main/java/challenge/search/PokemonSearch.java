package challenge.search;

import org.springframework.data.mongodb.core.mapping.Document;

public class PokemonSearch {

    private String name;
    private Boolean favourite;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }
}
