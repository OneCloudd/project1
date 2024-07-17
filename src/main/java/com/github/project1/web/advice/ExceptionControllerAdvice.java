package com.github.project1.web.advice;

import com.github.project1.service.exception.InvalidValueException;
import com.github.project1.service.exception.NotAcceptException;
import com.github.project1.service.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)  // 404 DB에서 해당값이 없을때
    public String handleNotFoundException(NotFoundException nfe){
        log.error("Client 요청이후 DB검색중 에러로 다음처럼 출력합니다.. " + nfe.getMessage());
        return nfe.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(NotAcceptException.class) // 클라이언트가 잘못 요청해 서버에서 처리할 수 없는 경우
    public String handleNotFoundException(NotAcceptException nae){
        log.error("Client 요청이 모종의 이유로 거부됩니다. " + nae.getMessage());
        return nae.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidValueException.class)  // 오타예외처리
    public String handleNotFoundException(InvalidValueException ive){
        log.error("Client 요청에 문제가 있어 다음처럼 출력합니다. " + ive.getMessage());
        return ive.getMessage();
    }
}
