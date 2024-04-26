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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("4. Dado que o usuário deseja buscar o filme com menor custo de produção")
public class FilmeMenorCustoTest {

    @Nested
    @DisplayName("4.1 Cenários válidos")
    public class CenarioValido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("4.1.1 Deve retornar status 200 e o filme")
        public void teste1() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get(ControllerEnum.CHEAPER.path)
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.titulo").value("Lagoa Azul"))
                    .andExpect(jsonPath("$.genero").value("Romance"))
                    .andExpect(jsonPath("$.dataLancamento").value("1980-07-02"))
                    .andExpect(jsonPath("$.custoProducao").value(20000000))
                    .andExpect(jsonPath("$.nota").value(4))
                    .andExpect(jsonPath("$.analise").value("Flopou!"));
        }
    }

    @Nested
    @DisplayName("4.2 Cenários inválidos")
    public class CenarioInvalido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @Sql(scripts = "/data/truncate_table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        @DisplayName("6.2.1 Deve retornar status 404 quando não houver filmes")
        public void teste1() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get(ControllerEnum.CHEAPER.path)
                            .contentType("application/json"))
                    .andExpect(status().isNotFound());
        }
    }
}
