package sptech.school.exerciciodynamicjpql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sptech.school.exerciciodynamicjpql.dto.FilmeTituloCusto;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "filmes")

//@SqlResultSetMappings({
//        @SqlResultSetMapping(
//                name = "FilmeTituloCusto",
//                classes = {
//                        @ConstructorResult(
//                                targetClass = FilmeTituloCusto.class,
//                                columns = {
//                                        @ColumnResult(name = "titulo", type = String.class),
//                                        @ColumnResult(name = "custo_producao", type = Double.class)
//                                }
//                        )
//                }
//        )
//})
//@NamedStoredProcedureQuery(
//        name = "obter_titulo_e_custo",
//        procedureName = "obter_titulo_e_custo",
//        resultSetMappings = "FilmeTituloCusto"
//)
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String genero;
    private LocalDate dataLancamento;
    private Double custoProducao;
    private Integer nota;

    public Filme() {
    }

    public Filme(Integer id, String titulo, String genero, LocalDate dataLancamento, Double custoProducao, Integer nota) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.dataLancamento = dataLancamento;
        this.custoProducao = custoProducao;
        this.nota = nota;
    }

    public Filme(String titulo, String genero, LocalDate dataLancamento, Double custoProducao, Integer nota) {
        this.titulo = titulo;
        this.genero = genero;
        this.dataLancamento = dataLancamento;
        this.custoProducao = custoProducao;
        this.nota = nota;
    }

    public Filme(String titulo, Double custoProducao) {
        this.titulo = titulo;
        this.custoProducao = custoProducao;
    }
}
