package com.interview.csvfile.controller;

import com.interview.csvfile.model.FileRecordsDto;
import com.interview.csvfile.service.CsvFileService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CsvFileController.class)
class CsvFileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CsvFileService csvFileService;

    @Test
    @SneakyThrows
    void computeRecord_WHEN_input_file_THEN_success() {

        MockMultipartFile fileCsv = new MockMultipartFile("file", "csvFile", "text/csv","content".getBytes());

        FileRecordsDto fileRecordsDto = new FileRecordsDto("csvFile",2L);
        when(csvFileService.computeRecords(any())).thenReturn(fileRecordsDto);
        mockMvc.perform(multipart("/file/records-count").file(fileCsv))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fileName").value("csvFile"))
                .andExpect(jsonPath("$.count").value(2));
    }
}