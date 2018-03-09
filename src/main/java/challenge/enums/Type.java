package challenge.enums;

public enum Type {

    BICHO("Bicho"),
    DRAGON("Dragon"),
    HADA("Hada"),
    FUEGO("Fuego"),
    FANTASMA("Fantasma"),
    TIERRA("Tierra"),
    NORMAL("Normal"),
    PSIQUICO("Psiquico"),
    ACERO("Acero"),
    SINIESTRO("Siniestro"),
    ELECTRICO("Electrico"),
    LUCHA("Lucha"),
    VOLADOR("Volador"),
    PLANTA("Planta"),
    HIELO("Hielo"),
    VENENO("Veneno"),
    ROCA("Roca"),
    AGUA("Agua");

    private final String value;

    public String getValue() {
        return value;
    }

    Type(String value) {
        this.value=value;
    }


}
