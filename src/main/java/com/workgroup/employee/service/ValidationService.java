package com.workgroup.employee.service;

import com.workgroup.employee.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    public String validate(String name) {
        if (!StringUtils.isAlpha(name)) {
            throw new ValidationException();
        }
        return StringUtils.capitalize(name.toLowerCase());
    }
}
