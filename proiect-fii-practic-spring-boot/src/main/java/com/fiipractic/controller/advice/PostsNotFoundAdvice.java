package com.fiipractic.controller.advice;

import com.fiipractic.exception.PostsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostsNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PostsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postsNotFoundHandler(PostsNotFoundException ex){
        return ex.getMessage();
    }
}
