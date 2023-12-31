package com.julan.sp3.util.validator.Mobile;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileValidator implements ConstraintValidator<Mobile, String> {

    private final String reg = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$";
    private final Pattern pt = Pattern.compile(reg);

    @Override
    public void initialize(Mobile mobile) {
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