package com.example.cars_springboot.util;

import com.example.cars_springboot.dto.CarDTO;
import com.example.cars_springboot.exception.BadHorsePowerException;
import com.example.cars_springboot.exception.BadModelException;
import org.springframework.stereotype.Component;

@Component
public class CarValidator {

    public void validateCar(CarDTO car) throws BadModelException, BadHorsePowerException {
        validateModel(car.getModel());
        validateHorsePower(car.getHorsepower());
    }

    private void validateModel(String modelFull) throws BadModelException {
        boolean modelIsCorrect = true;
        String[] modelArray = modelFull.split("-");
        if (modelArray.length != 2){
            throw new BadModelException("Model must be like vendor-model");
        }
        String vendor = modelArray[0];
        String model = modelArray[1];

        if (vendor.isBlank() || model.isBlank()){
            throw new BadModelException("Vendor/model mustn't be blank");
        }

    }

    private void validateHorsePower(int hp) throws BadHorsePowerException {
        if (hp <= 0){
            throw new BadHorsePowerException("HorsePower must be > 0");
        }
    }
}
