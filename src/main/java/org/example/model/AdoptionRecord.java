package org.example.model;

import java.time.LocalDate;

public class AdoptionRecord {
    private final Animal animal;
    private final LocalDate adoptionDate;
    private final String adopterName;

    public AdoptionRecord(Animal animal, LocalDate adoptionDate, String adopterName) {
        this.animal = animal;
        this.adoptionDate = adoptionDate;
        this.adopterName = adopterName;
    }

    @Override
    public String toString() {
        return animal.getName() + " | " + adoptionDate + " | " + adopterName;
    }
}
