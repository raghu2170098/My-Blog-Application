package com.myblog3.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
    private String message;
    private Date date;
    private String uri;

}
