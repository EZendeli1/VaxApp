package hr.tvz.zendeli.vaxapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SideEffectContorllerTest {

    @Autowired
    private MockMvc mockMvcM;

    @MockBean
    SideEffectServiceImpl sideEffectService;



    @Test
    void getAllSideEffects_empty() throws Exception{

        when(sideEffectService.findAllSideEffects()).thenReturn(Collections.emptyList());

      this.mockMvcM.perform(
                get("/side-effect")
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