package com.gerimedica.temperature.data.integrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TemperatureDataIT {

    public static final String API_UPLOAD_DATA = "/api/upload-data";
    public static final String TEXT_CSV = "text/csv";
    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext wContext;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wContext).alwaysDo(MockMvcResultHandlers.print()).build();

    }

    @Test
    @DisplayName("Test csv files is uploaded successfully")
    void test() throws Exception {

        final MockMultipartFile file = mockInputFile();
        mockMvc.perform(MockMvcRequestBuilders.multipart(API_UPLOAD_DATA).file(file).characterEncoding("UTF-8"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Fetch all the data from in memory database after the upload")
    void fetchAll() throws Exception {
        final MockMultipartFile file = mockInputFile();
        mockMvc.perform(MockMvcRequestBuilders.multipart(API_UPLOAD_DATA).file(file).characterEncoding("UTF-8"))
            .andExpect(MockMvcResultMatchers.status().isOk());

        this.mockMvc.perform(get("/api/temperature-data")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Fetch before the upload")
    void fetchAllBeforeUpload() throws Exception {
        this.mockMvc.perform(get("/api/temperature-data")).andDo(print()) //
            .andExpect(status().isOk()) //
            .andExpect(content().json("[]"));
    }

    @Test
    @DisplayName("Fetch data by code in the database")
    void fetchByCode() throws Exception {
        final MockMultipartFile file = mockInputFile();
        mockMvc.perform(MockMvcRequestBuilders.multipart(API_UPLOAD_DATA).file(file).characterEncoding("UTF-8"))
            .andExpect(MockMvcResultMatchers.status().isOk());

        this.mockMvc.perform(get("/api/temperature-data/{code}", "698832009")).andDo(print()) //
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Fetch data by code which is not present in the database")
    void fetchByWrongCode() throws Exception {
        final MockMultipartFile file = mockInputFile();
        mockMvc.perform(MockMvcRequestBuilders.multipart(API_UPLOAD_DATA).file(file).characterEncoding("UTF-8"))
            .andExpect(MockMvcResultMatchers.status().isOk()) //
            .andExpect(content().json("[]"));

        this.mockMvc.perform(get("/api/temperature-data/{code}", "wrong code")).andDo(print())
            .andExpect(status().isOk()) //
            .andExpect(content().json("[]"));
    }

    @Test
    @DisplayName("delete all data in the database")
    void deleteAll() throws Exception {
        final MockMultipartFile file = mockInputFile();
        mockMvc.perform(MockMvcRequestBuilders.multipart(API_UPLOAD_DATA).file(file).characterEncoding("UTF-8"))
            .andExpect(MockMvcResultMatchers.status().isOk());

        this.mockMvc.perform(delete("/api/temperature-data/")).andDo(print()).andExpect(status().isOk());
    }

    private MockMultipartFile mockInputFile() throws IOException {
        final var csvFile = new ClassPathResource("exercise-with-2-records.csv");
        return new MockMultipartFile("file", "exercise-with-2-records.csv", TEXT_CSV, csvFile.getInputStream());
    }
}
