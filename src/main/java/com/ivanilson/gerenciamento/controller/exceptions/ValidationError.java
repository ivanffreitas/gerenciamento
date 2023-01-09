package com.ivanilson.gerenciamento.controller.exceptions;

import com.ivanilson.gerenciamento.service.exceptions.StandarError;

import java.util.ArrayList;
import java.util.List;

public class ValidationError  extends StandarError {
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError() {
        super();
    }

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fielName, String message) {
        this.errors.add(new FieldMessage(fielName, message));
    }

}
