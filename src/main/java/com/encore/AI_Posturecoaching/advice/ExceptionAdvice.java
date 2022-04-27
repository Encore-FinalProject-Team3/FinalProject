package com.encore.AI_Posturecoaching.advice;

import com.encore.AI_Posturecoaching.exception.LoginFailureException;
import com.encore.AI_Posturecoaching.exception.MemberEmailAlreadyExistsException;
import com.encore.AI_Posturecoaching.exception.MemberMemberNameAlreadyExistsException;
import com.encore.AI_Posturecoaching.exception.MemberNotFoundException;
import com.encore.AI_Posturecoaching.member.dto.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response exception(Exception e){
        log.info("e = {}",e.getMessage());
        return Response.failure(-1000,"오류가 발생했습니다.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodNotArgumentNotValidException(MethodArgumentNotValidException e){
        return Response.failure(-1003,e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(LoginFailureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response loginFailureException(LoginFailureException e){
        return Response.failure(-1004,"로그인에 실패하였습니다.");
    }

    @ExceptionHandler(MemberEmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Response memberEmailAlreadyExistsException(MemberEmailAlreadyExistsException e) {
        return Response.failure(-1005,e.getMessage()+"은 중복된 이메일입니다");
    }

    @ExceptionHandler(MemberMemberNameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Response memberMemberNameAlreadyExistsException(MemberMemberNameAlreadyExistsException e) {
        return Response.failure(-1006,e.getMessage()+"은 중복된 이름입니다");
    }

    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response memberNotFoundException(MemberNotFoundException e) {
        return Response.failure(-1007,"요청한 회원을 찾을수가 없습니다");
    }




}
