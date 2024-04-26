package sptech.school.exerciciodynamicjpql.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sptech.school.exerciciodynamicjpql.dto.FilmeConsultaDto;
import sptech.school.exerciciodynamicjpql.dto.FilmeCriacaoDto;
import sptech.school.exerciciodynamicjpql.dto.FilmeMapper;
import sptech.school.exerciciodynamicjpql.dto.FilmeTituloCusto;
import sptech.school.exerciciodynamicjpql.entity.Filme;
import sptech.school.exerciciodynamicjpql.repository.FilmeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.*;
@RestController
@RequestMapping("/filmes")
public class FilmeController {
    @Autowired
    private FilmeRepository repository;

    @GetMapping("/titulo")
    public ResponseEntity<List<FilmeConsultaDto>> buscarFilmePorTitulo(@RequestParam String nome) {
        List<Filme> filmes = repository.findByTituloContainingIgnoreCase(nome);
        if (filmes.isEmpty()){
            return status(204).build();
        }
        return status(200).body(FilmeMapper.toDto(filmes));
    }

    @PostMapping
    public ResponseEntity<FilmeConsultaDto> criarFilme(@RequestBody @Valid FilmeCriacaoDto filme) {
        Filme filmeEntity = FilmeMapper.toEntity(filme);

        return status(201).body(FilmeMapper.toDto(repository.save(filmeEntity)));
    }

    @GetMapping("data")
    public ResponseEntity<List<FilmeConsultaDto>> buscarFilmeEntreDatas(
           @RequestParam LocalDate dataInicio,
           @RequestParam LocalDate dataFim
    ) {
        List<Filme> filmesEntreAsDatas = repository.buscaFilmeEntreDatas(dataInicio, dataFim);
        if(filmesEntreAsDatas.isEmpty()){
            return status(204).build();
        }
        return status(200).body(FilmeMapper.toDto(filmesEntreAsDatas));
    }

    @GetMapping("/menor-custo")
    public ResponseEntity<FilmeConsultaDto> buscarFilmeMaisBarato() {
        Filme filmeComMaisBarato = repository.buscaFilmeComMenorCusto();
        if(filmeComMaisBarato == null){
            return status(404).build();
        }
        return status(200).body(FilmeMapper.toDto(filmeComMaisBarato));
    }

    @Transactional
    @GetMapping("/listar")
    public ResponseEntity<List<Filme>> getTodosFilmes(){
        return status(200).body(repository.procedureObterTodosOsFilmes());
    }

    @Transactional
    @GetMapping("/mais-caro-que/{valor}")
    public ResponseEntity<List<Filme>> getMaisCaroQue(@PathVariable Double valor){
        return status(200).body(repository.procedureObterFilmeMaisCaro(valor));
    }

    @Transactional
    @GetMapping("/titulo-custo")
    public ResponseEntity<List<FilmeTituloCusto>> getTituloCusto(){
        return status(200).body(repository.procedureObterTituloECusto());
    }

    @Transactional
    @GetMapping("/titulo-custo-map")
    public ResponseEntity<List<FilmeTituloCusto>> getTituloCustoMap(){

        return status(200).body(FilmeMapper.toTituloCusto(repository.procedureObterTituloECustoMap()));
    }
}

