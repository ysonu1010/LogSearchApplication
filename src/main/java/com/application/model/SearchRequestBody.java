package com.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Data
@AllArgsConstructor
public class SearchRequestBody {
    String searchKeyword;
    LocalDateTime from;
    LocalDateTime to;
}
