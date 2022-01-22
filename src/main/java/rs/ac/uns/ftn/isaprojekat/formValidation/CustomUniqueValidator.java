package rs.ac.uns.ftn.isaprojekat.formValidation;


import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.uns.ftn.isaprojekat.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomUniqueValidator implements ConstraintValidator<CustomUnique, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String o, ConstraintValidatorContext constraintValidatorContext) {


        if( userService.existsByEmail(o)){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public void initialize(CustomUnique constraintAnnotation) {

    }
}
