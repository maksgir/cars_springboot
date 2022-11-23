package com.maksgir.cars_springboot.util;

import com.maksgir.cars_springboot.dto.CarDTO;
import com.maksgir.cars_springboot.exception.BadEntityDataException;
import com.maksgir.cars_springboot.exception.BadHorsePowerException;
import com.maksgir.cars_springboot.exception.BadModelException;
import org.springframework.stereotype.Component;

@Component
public class CarValidator {

    public void validateCar(CarDTO car) throws BadModelException, BadHorsePowerException, BadEntityDataException {
        validateModel(car.getModel());
        validateHorsePower(car.getHorsepower());
        validateId(car.getId());
        validateOwnerId(car.getOwnerId());
    }

    private void validateOwnerId(long ownerId) throws BadEntityDataException {
        if (ownerId == 0) {
            throw new BadEntityDataException("Owner id is empty");
        }
    }

    private void validateId(long id) throws BadEntityDataException {
        if (id == 0) {
            throw new BadEntityDataException("Car id is empty");
        }
    }

    private void validateModel(String modelFull) throws BadModelException, BadEntityDataException {
        if(modelFull == null){
            throw new BadModelException("Model is empty");
        }
        String[] modelArray = modelFull.split("-");

        String vendor = modelArray[0];

        StringBuilder model = new StringBuilder();
        for (int i = 1; i < modelArray.length; i++) {
            model.append(modelArray[i]);
            if (i != modelArray.length - 1) {
                model.append("-");
            }
        }

        if (vendor.isBlank() || model.toString().isBlank()) {
            throw new BadModelException("Vendor/model mustn't be blank");
        }

    }

    private void validateHorsePower(int hp) throws BadHorsePowerException {
        if (hp <= 0) {
            throw new BadHorsePowerException("HorsePower must be > 0");
        }
    }
}
