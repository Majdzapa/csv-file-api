package com.interview.csvfile.service;

import com.interview.csvfile.model.FileRecordsDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;


class CsvFileServiceTest {

    CsvFileService csvFileService = new CsvFileService();

    @Test
    void returnRecordsNumber_WHEN_given_empty_file_THEN_compute_zero_record_count() throws IOException {

        testComputeRecord("empty.csv",0L);

    }

    @Test
    void returnRecordsNumber_WHEN_given_file_contains_2_lines_THEN_compute_2_record_count() throws IOException {

        testComputeRecord("twoRecords.csv",2L);

    }

    @SneakyThrows
    public void testComputeRecord(String fileName, long expectedCount)  {

        InputStream content = new ClassPathResource("csv/".concat(fileName)).getInputStream();
        MockMultipartFile firstFile = new MockMultipartFile("data", fileName, "text/csv",content);

        FileRecordsDto result = csvFileService.computeRecords(firstFile);
        Assertions.assertAll(
                () ->  Assertions.assertEquals(expectedCount,result.count()),
                () -> Assertions.assertEquals(fileName,result.fileName())
        );
    }
}