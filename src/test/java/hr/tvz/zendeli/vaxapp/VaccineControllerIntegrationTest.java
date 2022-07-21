package hr.tvz.zendeli.vaxapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VaccineControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvcM;

    @Autowired
    private ObjectMapper objectMapper;

    private String TestResearchName = "AZD12221";
    private String TestResearchNameForUpdate = "AZD1222";
    private String TestManufacturerName = "Vaccine AstraZeneca";
    private String TestType = "Adenovirus vaccine";
    private Integer TestNumberOfShots = 1;
    private Integer TestavailableDOses = 10;
    private Integer TestNumberOfShotsForSaveWrongParam = -1;
    private Integer TestavailableDOsesForSaveWrongParam = -10;
    private String TestManufacturerNameForSaveWrongParam="";
    private String TestResearchNameForSaveWrongParam ="";
    private String TestTypeForSaveWrongParam="";

    private VaccineCommand vaccineCommandSave = new VaccineCommand(TestResearchName, TestManufacturerName, TestType, TestNumberOfShots, TestavailableDOses);
    private VaccineCommand vaccineCommandUpdate = new VaccineCommand(TestResearchNameForUpdate, TestManufacturerName, TestType, TestNumberOfShots, TestavailableDOses);
    private VaccineCommand vaccineCommandSaveWrongParam = new VaccineCommand(TestResearchNameForSaveWrongParam, TestManufacturerNameForSaveWrongParam, TestTypeForSaveWrongParam, TestNumberOfShotsForSaveWrongParam, TestavailableDOsesForSaveWrongParam);


    @Test
    void getAllVaccines() throws Exception {
        this.mockMvcM.perform(
                get("/vaccine")
                        .with(user("test").password("test").roles("ADMIN")
                        ).with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(9)
                ));


    }

    @Test
    void getVaccineByResearchName() throws Exception {

        this.mockMvcM.perform(
                get("/vaccine/AZD1222")
                        .with(
                                user("admin").password("admin").roles("ADMIN")
                        ).with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists()
                );


    }

    @Test
    void getVaccineByDose() throws Exception {

        this.mockMvcM.perform(
                get("/vaccine/?singleDose=true")
                        .with(
                                user("test").password("test").roles("ADMIN")
                        ).with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)
                ));


    }

    @Test
    @DirtiesContext
    void getVaccineByResearchNameWithWildCards() throws Exception {
        this.mockMvcM.perform(
                get("/vaccine/?contains=JnJ")
                        .with(
                                user("admin").password("admin").roles("ADMIN")
                        ).with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)
                ));

    }

    @Test
    @Transactional
    @DirtiesContext
    void save() throws Exception {

        this.mockMvcM.perform(
                post("/vaccine")
                        .with(user("admin")
                                .password("admin")
                                .roles("ADMIN")
                        )
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vaccineCommandSave))
                        .accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.researchName").value(TestResearchName))
                .andExpect(jsonPath("$.manufacturerName").value(TestManufacturerName))
                .andExpect(jsonPath("$.type").value(TestType))
                .andExpect(jsonPath("$.numberOfShots").value(TestNumberOfShots))
                .andExpect(jsonPath("$.availableDoses").value(TestavailableDOses));


    }

    @Test
    @Transactional
    @DirtiesContext
    void saveWithWrongParams() throws Exception {

        this.mockMvcM.perform(
                post("/vaccine")
                        .with(user("admin")
                                .password("admin")
                                .roles("ADMIN")
                        )
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vaccineCommandSaveWrongParam))
                        .accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().isBadRequest());

    }


    @Test
    @Transactional
    @DirtiesContext
    void delete() throws Exception {

        this.mockMvcM.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/vaccine/AZD1222")
                        .with(
                                user("admin").password("admin").roles("ADMIN")
                        ) .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());



    }

    @Test
    @Transactional
    @DirtiesContext
    void update() throws Exception{

        this.mockMvcM.perform(
                put("/vaccine/AZD1222")
                        .with(user("admin")
                                .password("admin")
                                .roles("ADMIN")
                        )
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vaccineCommandUpdate))
                        .accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.researchName").value(TestResearchNameForUpdate))
                .andExpect(jsonPath("$.manufacturerName").value(TestManufacturerName))
                .andExpect(jsonPath("$.type").value(TestType))
                .andExpect(jsonPath("$.numberOfShots").value(TestNumberOfShots))
                .andExpect(jsonPath("$.availableDoses").value(TestavailableDOses));


    }
}
