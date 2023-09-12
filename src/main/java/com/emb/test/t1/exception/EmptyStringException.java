package com.emb.test.t1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmptyStringException extends IllegalArgumentException {
    public EmptyStringException() {
        super("Count of characters can not be 0!");
    }
}
