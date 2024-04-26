package sptech.school.exerciciodynamicjpql.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sptech.school.exerciciodynamicjpql.util.ControllerEnum;
import sptech.school.exerciciodynamicjpql.util.ErrorMessageValidator;
import sptech.school.exerciciodynamicjpql.util.JsonFormatter;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("2. Dado que o usuário deseja criar um filme")
class FilmeCriacaoTest {

    @Nested
    @DisplayName("2.1 Cenários válidos")
    public class CenarioValido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("Quando o usuário informa os dados corretamente e nota 8, então o filme é criado com sucesso e analise é 'Absolute cinema!'")
        public void teste1() throws Exception {

            Map<String, Object> filme = Map.of(
                    "titulo", "Matrix",
                    "genero", "Ficção",
                    "dataLancamento", "1999-03-31",
                    "custoProducao", 63000000.0,
                    "nota", 9
            );

            String json = JsonFormatter.format(filme);

            mockMvc.perform(MockMvcRequestBuilders.post(ControllerEnum.BASE_PATH.path)
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").isNumber())
                    .andExpect(jsonPath("$.titulo").value("Matrix"))
                    .andExpect(jsonPath("$.genero").value("Ficção"))
                    .andExpect(jsonPath("$.dataLancamento").value("1999-03-31"))
                    .andExpect(jsonPath("$.custoProducao").value(63000000.0))
                    .andExpect(jsonPath("$.nota").value(9))
                    .andExpect(jsonPath("$.analise").value("Absolute cinema!"));
        }

        @Test
        @DisplayName("Quando o usuário informa os dados corretamente e nota 4, então o filme é criado com sucesso e analise é 'Flopou!'")
        public void teste2() throws Exception {

            Map<String, Object> filme = Map.of(
                    "titulo", "Cats",
                    "genero", "Musical",
                    "dataLancamento", "2019-12-20",
                    "custoProducao", 95000000.0,
                    "nota", 4
            );

            String json = JsonFormatter.format(filme);

            mockMvc.perform(MockMvcRequestBuilders.post(ControllerEnum.BASE_PATH.path)
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").isNumber())
                    .andExpect(jsonPath("$.titulo").value("Cats"))
                    .andExpect(jsonPath("$.genero").value("Musical"))
                    .andExpect(jsonPath("$.dataLancamento").value("2019-12-20"))
                    .andExpect(jsonPath("$.custoProducao").value(95000000.0))
                    .andExpect(jsonPath("$.nota").value(4))
                    .andExpect(jsonPath("$.analise").value("Flopou!"));
        }

        @Test
        @DisplayName("Quando o usuário informa os dados corretamente e nota 6, então o filme é criado com sucesso e analise é 'Sessão da tarde'")
        public void teste3() throws Exception {

            Map<String, Object> filme = Map.of(
                    "titulo", "Velozes e Furiosos 9",
                    "genero", "Ação",
                    "dataLancamento", "2021-06-25",
                    "custoProducao", 200000000.0,
                    "nota", 6
            );

            String json = JsonFormatter.format(filme);

            mockMvc.perform(MockMvcRequestBuilders.post(ControllerEnum.BASE_PATH.path)
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").isNumber())
                    .andExpect(jsonPath("$.titulo").value("Velozes e Furiosos 9"))
                    .andExpect(jsonPath("$.genero").value("Ação"))
                    .andExpect(jsonPath("$.dataLancamento").value("2021-06-25"))
                    .andExpect(jsonPath("$.custoProducao").value(200000000.0))
                    .andExpect(jsonPath("$.nota").value(6))
                    .andExpect(jsonPath("$.analise").value("Sessão da tarde"));
        }
    }

    @Nested
    @DisplayName("2.2 Cenários inválidos")
    public class CenarioInvalidos {

        @Nested
        @DisplayName("2.2.1 campo titulo")
        public class AtributoTitulo {

            @Autowired
            private MockMvc mockMvc;

            @Test
            @DisplayName("Quando o usuário informa o título vazio, então o filme não é criado e é retornado status 400")
            public void teste1() throws Exception {

                List<String> expectedErrorMessages = List.of("must not be blank");

                Map<String, Object> filme = Map.of(
                        "titulo", "",
                        "genero", "Ficção",
                        "dataLancamento", "1999-03-31",
                        "custoProducao", 63000000.0,
                        "nota", 8
                );

                String json = JsonFormatter.format(filme);

                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(ControllerEnum.BASE_PATH.path)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest()).andReturn();

                ErrorMessageValidator.assertContainsErrorMessages(mvcResult, expectedErrorMessages);
            }

            @Test
            @DisplayName("Quando o usuário informa o título nulo, então o filme não é criado e é retornado status 400")
            public void teste2() throws Exception {

                List<String> expectedErrorMessages = List.of("must not be blank");

                Map<String, Object> filme = Map.of(
                        "genero", "Ficção",
                        "dataLancamento", "1999-03-31",
                        "custoProducao", 63000000.0,
                        "nota", 8
                );

                var json = JsonFormatter.format(filme);

                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(ControllerEnum.BASE_PATH.path)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andReturn();

                ErrorMessageValidator.assertContainsErrorMessages(mvcResult, expectedErrorMessages);
            }

            @Test
            @DisplayName("Quando o usuário informa o título com apenas espaços, então o filme não é criado e é retornado status 400")
            public void teste3() throws Exception {

                List<String> expectedErrorMessages = List.of("must not be blank");

                Map<String, Object> filme = Map.of(
                        "titulo", "   ",
                        "genero", "Ficção",
                        "dataLancamento", "1999-03-31",
                        "custoProducao", 63000000.0,
                        "nota", 8
                );

                var json = JsonFormatter.format(filme);

                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(ControllerEnum.BASE_PATH.path)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andReturn();

                ErrorMessageValidator.assertContainsErrorMessages(mvcResult, expectedErrorMessages);
            }
        }

        @Nested
        @DisplayName("2.2.2 campo genero")
        public class AtributoGenero {

            @Autowired
            private MockMvc mockMvc;

            @Test
            @DisplayName("Quando o usuário informa o gênero vazio, então o filme não é criado e é retornado status 400")
            public void teste1() throws Exception {

                List<String> expectedErrorMessages = List.of("must not be blank");

                Map<String, Object> filme = Map.of(
                        "titulo", "Matrix",
                        "genero", "",
                        "dataLancamento", "1999-03-31",
                        "custoProducao", 63000000.0,
                        "nota", 8
                );

                var json = JsonFormatter.format(filme);

                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(ControllerEnum.BASE_PATH.path)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andReturn();

                ErrorMessageValidator.assertContainsErrorMessages(mvcResult, expectedErrorMessages);
            }

            @Test
            @DisplayName("Quando o usuário informa o gênero nulo, então o filme não é criado e é retornado status 400")
            public void teste2() throws Exception {

                List<String> expectedErrorMessages = List.of("must not be blank");

                Map<String, Object> filme = Map.of(
                        "titulo", "Matrix",
                        "dataLancamento", "1999-03-31",
                        "custoProducao", 63000000.0,
                        "nota", 8
                );

                var json = JsonFormatter.format(filme);

                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(ControllerEnum.BASE_PATH.path)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andReturn();

                ErrorMessageValidator.assertContainsErrorMessages(mvcResult, expectedErrorMessages);
            }

            @Test
            @DisplayName("Quando o usuário informa o gênero com espaços, então o filme não é criado e é retornado status 400")
            public void teste5() throws Exception {
                List<String> expectedErrorMessages = List.of("must not be blank");
                Map<String, Object> filme = Map.of(
                        "titulo", "Matrix",
                        "genero", "    ",
                        "dataLancamento", "1999-03-31",
                        "custoProducao", 63000000.0,
                        "nota", 8
                );
                var json = JsonFormatter.format(filme);
                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(ControllerEnum.BASE_PATH.path)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andReturn();
                ErrorMessageValidator.assertContainsErrorMessages(mvcResult, expectedErrorMessages);
            }
        }

        @Nested
        @DisplayName("2.2.3 campo dataLancamento")
        public class AtributoDataLancamento {

            @Autowired
            private MockMvc mockMvc;

            @Test
            @DisplayName("Quando o usuário informa a data de lançamento nula, então o filme não é criado e é retornado status 400")
            public void teste1() throws Exception {

                List<String> expectedErrorMessages = List.of("must not be null");

                Map<String, Object> filme = Map.of(
                        "titulo", "Matrix",
                        "genero", "Ficção",
                        "custoProducao", 63000000.0,
                        "nota", 8
                );

                var json = JsonFormatter.format(filme);

                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(ControllerEnum.BASE_PATH.path)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andReturn();

                ErrorMessageValidator.assertContainsErrorMessages(mvcResult, expectedErrorMessages);
            }

            @Test
            @DisplayName("Quando o usuário informa a data de lançamento futura, então o filme não é criado e é retornado status 400")
            public void teste2() throws Exception {

                List<String> expectedErrorMessages = List.of("must be a date in the past or in the present");

                Map<String, Object> filme = Map.of(
                        "titulo", "Matrix",
                        "genero", "Ficção",
                        "dataLancamento", "2025-03-31",
                        "custoProducao", 63000000.0,
                        "nota", 8
                );

                var json = JsonFormatter.format(filme);

                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(ControllerEnum.BASE_PATH.path)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andReturn();

                ErrorMessageValidator.assertContainsErrorMessages(mvcResult, expectedErrorMessages);
            }
        }

        @Nested
        @DisplayName("2.2.4 campo custoProducao")
        public class AtributoCustoProducao {

            @Autowired
            private MockMvc mockMvc;

            @Test
            @DisplayName("Quando o usuário informa o custo de produção menor que 20000000.50, então o filme não é criado e é retornado status 400")
            public void teste1() throws Exception {

                List<String> expectedErrorMessages = List.of("must be greater than or equal to 20000000.50");

                Map<String, Object> filme = Map.of(
                        "titulo", "Matrix",
                        "genero", "Ficção",
                        "dataLancamento", "1999-03-31",
                        "custoProducao", 20000000.0,
                        "nota", 8
                );

                var json = JsonFormatter.format(filme);

                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(ControllerEnum.BASE_PATH.path)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andReturn();

                ErrorMessageValidator.assertContainsErrorMessages(mvcResult, expectedErrorMessages);
            }

            @Test
            @DisplayName("Quando o usuário informa o custo de produção nulo, então o filme não é criado e é retornado status 400")
            public void teste2() throws Exception {

                List<String> expectedErrorMessages = List.of("must not be null");

                Map<String, Object> filme = Map.of(
                        "titulo", "Matrix",
                        "genero", "Ficção",
                        "dataLancamento", "1999-03-31",
                        "nota", 8
                );

                var json = JsonFormatter.format(filme);

                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(ControllerEnum.BASE_PATH.path)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andReturn();

                ErrorMessageValidator.assertContainsErrorMessages(mvcResult, expectedErrorMessages);
            }
        }

        @Nested
        @DisplayName("2.2.5 campo nota")
        public class AtributoNota {

            @Autowired
            private MockMvc mockMvc;

            @Test
            @DisplayName("Quando o usuário informa a nota menor que 1, então o filme não é criado e é retornado status 400")
            public void teste1() throws Exception {

                List<String> expectedErrorMessages = List.of("must be greater than or equal to 1");

                Map<String, Object> filme = Map.of(
                        "titulo", "Matrix",
                        "genero", "Ficção",
                        "dataLancamento", "1999-03-31",
                        "custoProducao", 63000000.0,
                        "nota", 0
                );

                var json = JsonFormatter.format(filme);

                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(ControllerEnum.BASE_PATH.path)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andReturn();

                ErrorMessageValidator.assertContainsErrorMessages(mvcResult, expectedErrorMessages);
            }

            @Test
            @DisplayName("Quando o usuário informa a nota maior que 10, então o filme não é criado e é retornado status 400")
            public void teste2() throws Exception {

                List<String> expectedErrorMessages = List.of("must be less than or equal to 10");

                Map<String, Object> filme = Map.of(
                        "titulo", "Matrix",
                        "genero", "Ficção",
                        "dataLancamento", "1999-03-31",
                        "custoProducao", 63000000.0,
                        "nota", 11
                );

                var json = JsonFormatter.format(filme);

                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(ControllerEnum.BASE_PATH.path)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andReturn();

                ErrorMessageValidator.assertContainsErrorMessages(mvcResult, expectedErrorMessages);
            }

            @Test
            @DisplayName("Quando o usuário informa a nota nula, então o filme não é criado e é retornado status 400")
            public void teste3() throws Exception {

                List<String> expectedErrorMessages = List.of("must not be null");

                Map<String, Object> filme = Map.of(
                        "titulo", "Avatar",
                        "genero", "Ficção",
                        "dataLancamento", "2009-12-18",
                        "custoProducao", 237000000.0
                );

                var json = JsonFormatter.format(filme);

                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(ControllerEnum.BASE_PATH.path)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andReturn();
            }
        }
    }
}