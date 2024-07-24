package com.interview.csvfile.controller;


import com.interview.csvfile.model.FileRecordsDto;
import com.interview.csvfile.service.CsvFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class CsvFileController {

    private final CsvFileService csvFileService;

    @PostMapping(value = "/records-count")
    public ResponseEntity<FileRecordsDto> computeRecord(@RequestPart("file") MultipartFile file) throws IOException {

        FileRecordsDto recordCount = csvFileService.computeRecords(file);

        return ResponseEntity.ok(recordCount);
    }
}
