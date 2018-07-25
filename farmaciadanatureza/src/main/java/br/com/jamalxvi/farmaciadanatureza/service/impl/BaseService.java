package br.com.jamalxvi.farmaciadanatureza.service.impl;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class BaseService {

    protected static Validator validator;
    BaseService(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
}
