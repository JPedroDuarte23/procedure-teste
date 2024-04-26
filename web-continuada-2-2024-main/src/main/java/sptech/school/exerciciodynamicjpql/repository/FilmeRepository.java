package sptech.school.exerciciodynamicjpql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.transaction.annotation.Transactional;
import sptech.school.exerciciodynamicjpql.dto.FilmeConsultaDto;
import sptech.school.exerciciodynamicjpql.dto.FilmeTituloCusto;
import sptech.school.exerciciodynamicjpql.entity.Filme;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {

    @Transactional
    @Procedure("obter_todos_os_filmes")
    List<Filme> procedureObterTodosOsFilmes();

    @Transactional
    @Procedure("obter_filme_mais_caro_que")
    List<Filme> procedureObterFilmeMaisCaro(Double valor);

    @Transactional
    @Procedure(name = "obter_titulo_e_custo")
    List<FilmeTituloCusto> procedureObterTituloECusto();

    @Transactional
    @Procedure("obter_titulo_e_custo")
    List<Object[]> procedureObterTituloECustoMap();



    List<Filme> findByTituloContainingIgnoreCase(String titulo);

    @Query("select f from Filme f where f.dataLancamento >= ?1 and f.dataLancamento <= ?2")
    List<Filme> buscaFilmeEntreDatas(LocalDate dataInicio, LocalDate dataFim);

    @Query("select f from Filme f order by f.custoProducao asc limit 1")
    Filme buscaFilmeComMenorCusto();

}