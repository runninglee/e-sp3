package com.julan.sp3.util.validator.IdCard;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdCardValidator implements ConstraintValidator<IdCard, String> {

    /**
     * 身份证规则校验正则表达式
     */
    private final String reg = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";
    private final Pattern pt = Pattern.compile(reg);

    @Override
    public void initialize(IdCard idCard) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext arg1) {
        if (value == null) {
            return false;
        }
        Matcher m = pt.matcher(value);
        return m.find();
    }

}