# Avaliação de Desenvolvimento de API REST com Spring Boot 🚀

O objetivo é implementar alguns endpoints em um projeto de site que avalia filmes, utilizando conceitos como mapeamento de entidade para DTO, uso de repositórios JPA e validações. Além disso, será necessário configurar adequadamente o controller para gerenciar os status HTTP, empregar os verbos HTTP corretos e definir as URIs apropriadas para cada operação.


<hr style="background-color:gray; height:6px">
<h1 style="color:red">🚨Atenção!🚨</h1>
<h3>Não altere:</h3>
<ul>
  <li><h3>Entidade (classe Filme)</h3></li>
  <li><h3>application.properties</h3></li>
  <li><h3>data.sql, que já inclui alguns filmes cadastrados.</h3></li>
</ul>

<h2 style="color:yellow">⚠️Regras⚠️</h2>

- A prova permite consulta.
- **A prova é individual. Caso seja detectada qualquer forma de cola, todos os envolvidos receberão nota zero.**
- O uso de plugins e inteligência artificial generativa (como Copilot, GPT e similares) **é permitido** ; é aconselhável realizar uma análise crítica das sugestões oferecidas; utilizar essas ferramentas não assegura a resolução do problema.
- O uso de Lombok é opcional **SE DESEJAR**; caso não se lembre de como utilizá-lo, opte por não usar.
- É permitido usar JPQL para as consultas; se não se recordar de como fazê-lo, opte por não usar.
- Se não for adepto de testes, não é obrigatório executá-los; pode usar ferramentas como Insomnia, Postman, etc., mas
  esteja ciente de que os testes serão utilizados na correção da prova.
- Alterar os testes apenas para que todos sejam aprovados, além de não atender ao **propósito**, assegurará uma nota
  zero.
- Caso suspeite de algum erro no enunciado ou nos testes, proceda conforme acredita ser correto e converse com o
  professor da disciplina posteriormente.
- **E mais uma vez para garantir, modificar o arquivo data.sql, application.properties e a classe Filme resultará em falhas!**

<br>
<hr style="background-color:gray; height:6px">

# Endpoints

## 🔍 Busca de Filme por Título

- **Endpoint**: `GET /filmes/titulo?nome={nome}`
- **Descrição**: Permite a busca de filmes por parte do título (busca insensível a maiúsculas e minúsculas).

## 🎞️ Criação de um Novo Filme

- **Endpoint**: `POST /filmes`
- **Descrição**: Permite a criação de um novo filme com validações nos campos recebidos. O corpo da requisição deve ser
  validado.

## 📅 Busca de Filmes por Intervalo de Datas de Lançamento

- **Endpoint**: `GET /filmes/data?dataInicio={dataInicio}&dataFim={dataFim}`
- **Descrição**: Retorna filmes lançados entre duas datas especificadas como parâmetros de consulta.

## 💸 Busca do Filme com o Menor Custo de Produção

- **Endpoint**: `GET /filmes/menor-custo`
- **Descrição**: Retorna o filme com o menor custo de produção.

<br>
<hr style="background-color:gray; height:6px">

## 📝 Modelo de Dados

### Entidade (não altere, já implementada):

- **Filme**
    - ID: Integer
    - Título: String
    - Gênero: String
    - Data Lançamento: LocalDate
    - Custo Produção: Double
    - Nota: Integer

### Criação:

- **FilmeCriacaoDto**
    - Título: String
        - Obrigatório, não deve ser deixado em branco nem preenchido exclusivamente com espaços
    - Gênero: String
        - Obrigatório, não deve ser deixado em branco nem preenchido exclusivamente com espaços
    - Data Lançamento: LocalDate
        - Obrigatório, somente datas passadas ou presente
    - Custo Produção: Double
        - Obrigatório, Min: 20000000.50 (Considerar casas decimais)
    - Nota: Integer
        - Obrigatório, Min: 1, Max: 10

### Retorno:

- **FilmeConsultaDto**
    - ID: Integer
    - Título: String
    - Gênero: String
    - Data Lançamento: LocalDate
    - Custo Produção: Double
    - Nota: Integer
    - Análise: String
        - Nota inferior a 5: Flopou!
        - Nota inferior a 8: Sessão da tarde
        - Nota 8 ou superior: Absolute cinema!