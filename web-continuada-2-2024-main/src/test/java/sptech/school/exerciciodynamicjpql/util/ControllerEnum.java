package sptech.school.exerciciodynamicjpql.util;

public enum ControllerEnum {

    BASE_PATH("http://localhost:8080/filmes"),
    BY_TITULO("%s/titulo".formatted(BASE_PATH.path)),
    BY_DATA("%s/data".formatted(BASE_PATH.path)),
    CHEAPER("%s/menor-custo".formatted(BASE_PATH.path));

    ControllerEnum(String path) {
        this.path = path;
    }

    public final String path;
}
