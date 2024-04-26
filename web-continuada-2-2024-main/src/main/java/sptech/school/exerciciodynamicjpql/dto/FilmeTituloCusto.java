package sptech.school.exerciciodynamicjpql.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sptech.school.exerciciodynamicjpql.entity.Filme;

@Getter
@Setter
@NoArgsConstructor
public class FilmeTituloCusto {
    private String titulo;
    private Double custoProducao;

    public FilmeTituloCusto(Filme filme) {
        this.titulo = filme.getTitulo();
        this.custoProducao = filme.getCustoProducao();
    }

    public FilmeTituloCusto(String titulo, Double custoProducao) {
        this.titulo = titulo;
        this.custoProducao = custoProducao;
    }
}
