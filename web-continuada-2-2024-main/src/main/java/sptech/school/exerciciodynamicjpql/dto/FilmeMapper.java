package sptech.school.exerciciodynamicjpql.dto;

import sptech.school.exerciciodynamicjpql.entity.Filme;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilmeMapper {

    public static FilmeConsultaDto toDto(Filme filme) {
        return new FilmeConsultaDto(filme);
    }

    public static List<FilmeConsultaDto> toDto(List<Filme> entities) {
        List<FilmeConsultaDto> listaDto = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            listaDto.add(new FilmeConsultaDto(entities.get(i)));
        }
        return listaDto;
    }

    public static Filme toEntity(FilmeCriacaoDto dto) {
        return new Filme(dto.getTitulo(), dto.getGenero(), dto.getDataLancamento(), dto.getCustoProducao(), dto.getNota());
    }
    public static List<FilmeTituloCusto> toTituloCusto(Map<String, Object> entities){
        List<FilmeTituloCusto> listaDto = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            FilmeTituloCusto filmeTituloCusto = new FilmeTituloCusto(
                    (String) entities.get("titulo"),
                    (Double) entities.get("custo_producao"));
            listaDto.add(filmeTituloCusto);
        }
        return listaDto;
    }

    public static List<FilmeTituloCusto> toTituloCusto(List<Object[]> entities){
        List<FilmeTituloCusto> listaDto = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            Object[] entidade = entities.get(i);
            FilmeTituloCusto filmeTituloCusto = new FilmeTituloCusto(
                    (String) entidade[0],
                    (Double) entidade[1]
            );
            listaDto.add(filmeTituloCusto);
        }
        return listaDto;
    }
}
