package sptech.school.exerciciodynamicjpql.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import sptech.school.exerciciodynamicjpql.entity.Filme;

import java.time.LocalDate;
@Getter
@NoArgsConstructor
public class FilmeCriacaoDto {

    @NotBlank
    private String titulo;
    @NotBlank
    private String genero;
    @NotNull
    @PastOrPresent
    private LocalDate dataLancamento;
    @NotNull
    @DecimalMin(value = "20000000.50")
    private Double custoProducao;
    @NotNull
    @Min(1)
    @Max(10)
    private Integer nota;

    public FilmeCriacaoDto(Filme filme) {
        this.titulo = filme.getTitulo();
        this.genero = filme.getGenero();
        this.dataLancamento = filme.getDataLancamento();
        this.custoProducao = filme.getCustoProducao();
        this.nota = filme.getNota();
    }
}
