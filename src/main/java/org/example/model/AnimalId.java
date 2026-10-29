package org.example.model;

import java.util.UUID;
import java.util.Objects;

public final class AnimalId {
    private final String value;
    public AnimalId(){
        this.value = UUID.randomUUID().toString();
    }


    public AnimalId(String value){
        if (value == null ) {
            System.out.println("Animal ID cannot be empty");
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnimalId animalId)) {
            return false;
        }
        return value.equals(animalId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString(){
        return value;
    }
}
