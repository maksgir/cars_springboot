package com.example.cars_springboot.util;

import com.example.cars_springboot.exception.BadModelException;
import org.springframework.stereotype.Component;

@Component
public class ModelValidator {
    public boolean modelIsCorrect(String modelFull){
        boolean modelIsCorrect = true;
        String[] modelArray = modelFull.split("-");
        if (modelArray.length != 2){
            return false;
        }
        String vendor = modelArray[0];
        String model = modelArray[1];

        if (vendor.isBlank() || model.isBlank()){
            return false;
        }

        return true;
    }
}
