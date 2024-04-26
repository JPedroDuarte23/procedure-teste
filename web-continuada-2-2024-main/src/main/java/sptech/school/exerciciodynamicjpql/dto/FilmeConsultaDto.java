package sptech.school.exerciciodynamicjpql.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sptech.school.exerciciodynamicjpql.entity.Filme;

import java.time.LocalDate;
@Getter
@NoArgsConstructor
public class FilmeConsultaDto {
    private Integer id;
    private String titulo;
    private String genero;
    private LocalDate dataLancamento;
    private Double custoProducao;
    private Integer nota;
    private String analise;

    public FilmeConsultaDto(Filme filme) {
        this.id = filme.getId();
        this.titulo = filme.getTitulo();
        this.genero = filme.getGenero();
        this.dataLancamento = filme.getDataLancamento();
        this.custoProducao = filme.getCustoProducao();
        this.nota = filme.getNota();
        this.analise = analisarFilme();
    }

    public String analisarFilme(){
        if(this.nota < 5){
            return "Flopou!";
        } else if (this.nota >= 5 && this.nota < 8){
            return "Sessão da tarde";
        } else if (this.nota >= 8 ){
            return "Absolute cinema!";
        }
        return "Nota inválida";
    }
}