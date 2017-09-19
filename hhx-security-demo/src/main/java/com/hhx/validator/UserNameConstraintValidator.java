package com.hhx.validator;

import com.hhx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//spring 自动管理 实现ConstraintValidator接口的类，无需注解
public class UserNameConstraintValidator implements ConstraintValidator<UsernameValidtor,String>{

    //@Autowired
    //private UserService service;
    //可以调用service里的方法做校验

    @Override
    public void initialize(UsernameValidtor usernameValidtor) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.contains("admin")){
            return false;
        }
        return true;
    }
}
