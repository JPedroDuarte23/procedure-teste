package sptech.school.exerciciodynamicjpql.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sptech.school.exerciciodynamicjpql.util.ControllerEnum;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("3. Dado que o usuário deseja buscar filmes por periodo")
public class FilmeBuscaPorDataTest {

    @Nested
    @DisplayName("3.1 Cenários válidos")
    public class CenarioValido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("3.1.1 Quando o usuário buscar por filmes lançados entre 2000-01-01 e 2005-12-31 deve retornar 5 filmes")
        public void teste1() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get(ControllerEnum.BY_DATA.path)
                            .param("dataInicio", "2000-01-01")
                            .param("dataFim", "2005-12-31")
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(3)))
                    .andExpect(jsonPath("$[0].titulo").value("O Senhor dos Anéis"))
                    .andExpect(jsonPath("$[1].titulo").value("Super Size Me"))
                    .andExpect(jsonPath("$[2].titulo").value("Gladiador"));
        }

        @Test
        @DisplayName("3.1.2 Quando o usuário buscar por filmes lançados entre 2006-01-01 e 2010-12-31 deve retornar 1 filmes")
        public void teste2() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get(ControllerEnum.BY_DATA.path)
                            .param("dataInicio", "2006-01-01")
                            .param("dataFim", "2010-12-31")
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].titulo").value("Norbit"));
        }
    }

    @Nested
    @DisplayName("3.2 Cenários inválidos")
    public class CenarioInvalido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("3.2.1 Quando o usuário buscar por filmes lançados entre 2023-01-01 e 2024-12-31 deve retornar 204")
        public void teste1() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get(ControllerEnum.BY_DATA.path)
                            .param("dataInicio", "2023-01-01")
                            .param("dataFim", "2024-12-31")
                            .contentType("application/json"))
                    .andExpect(status().isNoContent());
        }

        @Test
        @Sql(scripts = "/data/truncate_table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        @DisplayName("3.2.2 Quando o usuário buscar por filmes lançados entre 2023-01-01 e 2024-12-31 e não há filmes na tabela deve retornar 204")
        public void teste2() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get(ControllerEnum.BY_DATA.path)
                            .param("dataInicio", "2023-01-01")
                            .param("dataFim", "2024-12-31")
                            .contentType("application/json"))
                    .andExpect(status().isNoContent());
        }
    }
}
