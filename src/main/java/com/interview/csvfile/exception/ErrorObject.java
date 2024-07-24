package com.interview.csvfile.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorObject {

    private String title;
    private Integer statusCode;
    private String description;
    private LocalDateTime timeStamp;
}
