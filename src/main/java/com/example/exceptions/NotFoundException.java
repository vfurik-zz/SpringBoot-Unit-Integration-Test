package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@ResponseBody
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Can not find message with such id");
    }

}
