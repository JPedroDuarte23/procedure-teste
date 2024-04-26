package sptech.school.exerciciodynamicjpql.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
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
@DisplayName("1. Dado que o usuário deseja buscar filmes por título")
public class FilmeBuscaPorTituloTest {

    @Nested
    @DisplayName("1.1 Cenários válidos")
    public class CenarioValido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("1.1.1 Quando o usuário buscar por 'la' deve retornar 6 filmes")
        public void teste1() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.get(ControllerEnum.BY_TITULO.path)
                            .param("nome", "la")
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(6)))
                    .andExpect(jsonPath("$[0].titulo").value("Interestelar"))
                    .andExpect(jsonPath("$[1].titulo").value("A Vida é Bela"))
                    .andExpect(jsonPath("$[2].titulo").value("La La Land"))
                    .andExpect(jsonPath("$[3].titulo").value("Blade Runner"))
                    .andExpect(jsonPath("$[4].titulo").value("Gladiador"));
        }

        @Test
        @DisplayName("1.1.2 Quando o usuário buscar por 'cHuva' deve retornar 1 filme")
        public void teste2() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.get(ControllerEnum.BY_TITULO.path)
                            .param("nome", "cHuva")
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].titulo").value("Cantando na Chuva"));
        }
    }

    @Nested
    @DisplayName("1.2 Cenários inválidos")
    public class CenarioInvalido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("1.2.1 Quando o usuário buscar por 'xpto' deve retornar status 204")
        public void teste1() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.get(ControllerEnum.BY_TITULO.path)
                            .param("nome", "xpto")
                            .contentType("application/json"))
                    .andExpect(status().isNoContent());
        }

        @Test
        @DisplayName("1.2.2 Quando o usuário buscar por 'Cidade' deve retornar status 204")
        public void teste2() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.get(ControllerEnum.BY_TITULO.path)
                            .param("nome", "Cidade")
                            .contentType("application/json"))
                    .andExpect(status().isNoContent());
        }

        @Test
        @DisplayName("1.2.3 Quando o usuário buscar por 'testa direito' deve retornar status 204")
        public void teste3() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get(ControllerEnum.BY_TITULO.path)
                            .param("nome", "testa direito")
                            .contentType("application/json"))
                    .andExpect(status().isNoContent());
        }
    }
}
