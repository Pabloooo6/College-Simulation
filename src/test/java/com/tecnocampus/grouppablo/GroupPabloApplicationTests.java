package com.tecnocampus.grouppablo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecnocampus.grouppablo.application.dto.CourseDTO;
import com.tecnocampus.grouppablo.application.exception.CourseNotFound;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GroupPabloApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void testGetAllCourses() throws Exception {
        mockMvc.perform(get("/courses")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(5)))

                .andExpect(jsonPath("$[*].title", containsInRelativeOrder("Desarrollo Web Full Stack",
                        "Diseño Gráfico Avanzado", "Introducción a la IA", "Marketing Digital", "Programación en Python")))
                .andExpect(jsonPath("$[*].id").isNotEmpty())
                .andExpect(jsonPath("$[*].description").exists())
                .andExpect(jsonPath("$[*].publication").exists())
                .andExpect(jsonPath("$[*].lastUpdate").exists())
                .andExpect(jsonPath("$[*].imageUrl").exists())
                .andExpect(jsonPath("$[*].currentPrice").exists())
                .andExpect(jsonPath("$[*].availability").exists());
    }

    @Test
    void testGetCourseExists() throws Exception{
        LocalDate publication = LocalDate.of(2023, 2, 15);
        LocalDate lastUpdate = LocalDate.of(2023, 3, 10);
        CourseDTO expectedCourse = new CourseDTO("Programacion en Python", "Aprende a programar en Python desde cero.", publication, lastUpdate, "imagen1.jpg", 99.00, true);

        MvcResult mvcResult = mockMvc.perform(get("/courses/4f4e501f-6350-48c9-9f8d-0a4b32b9a1a2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        CourseDTO actualCourse = objectMapper.readValue(actualResponseBody, CourseDTO.class);

        assertThat(actualCourse.getTitle()).isEqualTo(expectedCourse.getTitle());
        assertThat(actualCourse.getDescription()).isEqualTo(expectedCourse.getDescription());
        assertThat(actualCourse.getPublication()).isEqualTo(expectedCourse.getPublication());
        assertThat(actualCourse.getLastUpdate()).isEqualTo(expectedCourse.getLastUpdate());
        assertThat(actualCourse.getImageUrl()).isEqualTo(expectedCourse.getImageUrl());
        assertThat(actualCourse.getCurrentPrice()).isEqualTo(expectedCourse.getCurrentPrice());
        assertThat(actualCourse.getAvailability()).isEqualTo(expectedCourse.getAvailability());
    }

    @Test
    void testGetCourseDoesNotExists() throws Exception{
        String id = UUID.randomUUID().toString();
        mockMvc.perform(get("/courses/"+id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CourseNotFound))
                .andExpect(result -> assertEquals("Course with id: "+ id +" does not exist", result.getResolvedException().getMessage()));
    }

    @Test
    @Order(2)
    void testPostCourse() throws Exception{
        String course = """
                   {\"title\": \"Disenyo de Interfaces de Usuario\",
                    \"description\": \"Aprende a disenyar interfaces de usuario efectivas.\",
                    \"publication\": \"2023-09-10\",
                    \"lastUpdate\": \"2023-09-30\",
                    \"imageUrl\": \"ui_design.jpg\",
                    \"currentPrice\": \"79.00\",
                    \"availability\": \"true\"
                   }""";

        LocalDate publication = LocalDate.of(2023, 9, 10);
        LocalDate lastUpdate = LocalDate.of(2023, 9, 30);
        CourseDTO expectedCourse = new CourseDTO("Disenyo de Interfaces de Usuario", "Aprende a disenyar interfaces de usuario efectivas.", publication, lastUpdate, "ui_design.jpg", 79.00, true);

        MvcResult mvcResult = mockMvc.perform(post("/courses")
                        .contentType("application/json")
                        .content(course))
                .andExpect(status().isCreated())
                .andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        CourseDTO actualCourse = objectMapper.readValue(actualResponseBody, CourseDTO.class);

        assertThat(actualCourse.getTitle()).isEqualTo(expectedCourse.getTitle());
        assertThat(actualCourse.getDescription()).isEqualTo(expectedCourse.getDescription());
        assertThat(actualCourse.getPublication()).isEqualTo(expectedCourse.getPublication());
        assertThat(actualCourse.getLastUpdate()).isEqualTo(expectedCourse.getLastUpdate());
        assertThat(actualCourse.getImageUrl()).isEqualTo(expectedCourse.getImageUrl());
        assertThat(actualCourse.getCurrentPrice()).isEqualTo(expectedCourse.getCurrentPrice());
        assertThat(actualCourse.getAvailability()).isEqualTo(expectedCourse.getAvailability());
    }

    @Test
    void testPostCourseAlreadyExists() throws Exception{
        String newCourse = """
                   {\"title\": \"Introducción a la IA\",
                    \"description\": \"Aprende a disenyar interfaces de usuario efectivas.\",
                    \"publication\": \"2023-09-10\",
                    \"lastUpdate\": \"2023-09-30\",
                    \"imageUrl\": \"ui_design.jpg\",
                    \"currentPrice\": \"79.00\",
                    \"availability\": \"true\"
                   }""";

        mockMvc.perform(post("/courses")
                        .contentType("application/json")
                        .content(newCourse))
                .andExpect(status().isConflict());
    }

    @Test
    void testPostCourseWithoutCapitalLetter() throws Exception{
        String newCourse = """
                   {\"title\": \"introducción a la IA\",
                    \"description\": \"Aprende a disenyar interfaces de usuario efectivas.\",
                    \"publication\": \"2023-09-10\",
                    \"lastUpdate\": \"2023-09-30\",
                    \"imageUrl\": \"ui_design.jpg\",
                    \"currentPrice\": \"79.00\",
                    \"availability\": \"true\"
                   }""";

        mockMvc.perform(post("/courses")
                        .contentType("application/json")
                        .content(newCourse))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.violations").isArray())
                .andExpect(jsonPath("$.violations[0].message").value(
                        "The first letter must be capital letter."));
    }

    @Test
    @Order(3)
    void testPutCourse() throws Exception{

    }

    @Test
    void testPutCourseDoesNotExist() throws Exception{

    }

    @Test
    @Order(4)
    void testPatchPrice() throws Exception{

    }

    @Test
    void testPatchPriceNegative() throws Exception{

    }

    @Test
    @Order(5)
    void testPatchAvailability() throws Exception{

    }
}
