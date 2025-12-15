package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(SchoolController.class)
class SchoolControllerTest {

    @Autowired
    private MockMvc mockMvc; // This is the "Fake Browser"

    @MockBean
    private SchoolService service; // We mock the logic so we only test the "Web" part

    @Test
    void testAddStudentEndpoint() throws Exception {
        // 1. Pretend the service works perfectly
        given(service.addStudent(any(Student.class))).willReturn("Student added: Test");

        // 2. Send a fake POST request
        mockMvc.perform(post("/api/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"S1\", \"firstName\":\"John\", \"lastName\":\"Doe\", \"year\":2023, \"programme\":\"CSE\"}"))
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andExpect(content().string("Student added: Test"));
    }

    @Test
    void testGetStudentsEndpoint() throws Exception {
        // 1. Pretend the service has data
        Student s = new Student("S1", "John", "Doe", 2023, "CSE");
        given(service.getAllStudents()).willReturn(Arrays.asList(s));

        // 2. Send a fake GET request
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':'S1'}]"));
    }

    @Test
    void testAddTeacherEndpoint() throws Exception {
        given(service.addTeacher(any(Teacher.class))).willReturn("Teacher added: Snape");

        mockMvc.perform(post("/api/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"T1\", \"name\":\"Snape\", \"department\":\"Potions\", \"courseCount\":5}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Teacher added: Snape"));
    }
}