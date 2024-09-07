package com.kikplan.backend.validator;

import com.kikplan.backend.exceptions.ObjectToValidateException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
@Component
public class ObjectValidator<T> {

    private final ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();
    private final Validator validator=validatorFactory.getValidator();

    public void validate(T object){
      Set<ConstraintViolation<T>>violations= validator.validate(object);

      if(!violations.isEmpty()){
          Set<String>errosMessage=violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());

          /*TODO an exeception */
          throw new ObjectToValidateException(errosMessage,object.getClass().getName());
      }
    }

}
