package com.interview.csvfile.service;

import com.interview.csvfile.exception.WrongInputFileException;
import com.interview.csvfile.model.FileRecordsDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@Slf4j
public class CsvFileService {

    @SneakyThrows
    public FileRecordsDto computeRecords(MultipartFile file) {

        String fileName = file.getOriginalFilename();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            long recordsCount = reader.lines().count();
            log.info("The number of records of file {} is  {} ", fileName, recordsCount);
            return new FileRecordsDto(
                    fileName,
                    recordsCount
            );
        } catch (IOException ex) {
            throw new WrongInputFileException(ex);
        }
    }
}
