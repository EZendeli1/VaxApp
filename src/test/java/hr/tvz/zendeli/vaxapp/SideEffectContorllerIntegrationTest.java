package hr.tvz.zendeli.vaxapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc

class SideEffectContorllerIntegrationTest {

    @Autowired
    private MockMvc mockMvcM;


    @Test
    void getAllSideEffects() throws Exception {
        this.mockMvcM.perform(
                get("/side-effect")
                        .with(
                                user("test").password("test").roles("ADMIN")
                        ).with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(9)
                ));

    }

    @Test
    void getSideEffectByVaccineResearchName()throws Exception {

        this.mockMvcM.perform(
                get("/side-effect/BNT162b2")
                        .with(
                                user("test").password("test").roles("ADMIN")
                        ).with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(1)
                ));



    }

    @Test
    void getSideEffectsByLongDescription()throws Exception{
        this.mockMvcM.perform(
                get("/side-effect/?longDescription=tromnoza")
                        .with(
                                user("test").password("test").roles("ADMIN")
                        ).with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(0)
                ));

    }
}