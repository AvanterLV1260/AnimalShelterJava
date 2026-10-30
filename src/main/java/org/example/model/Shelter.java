package org.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Shelter {
    private final List<Animal> animals = new ArrayList<>();
    private final List<AdoptionRecord> adoptionRecords = new ArrayList<>();

    public void addAnimal(Animal animal) {
        if (animal == null) {
            System.out.println("Animal cannot be null");
            return;
        }
        animals.add(animal);
    }

    public List<Animal> getAllAnimals() {
        return List.copyOf(animals);
    }

    public List<Animal> findBySpecies(String species) {
        if (species == null || species.isBlank()) {
            return List.of();
        }
        return animals.stream()
                .filter(animal -> animal.getSpecies().equalsIgnoreCase(species))
                .toList();
    }

    public List<Animal> findAvailableAnimals() {
        return animals.stream()
                .filter(animal -> animal.getAdoptionStatus() == AdoptionStatus.AVAILABLE)
                .toList();
    }

    public boolean markAsAdopted(String id, String adopterName) {
        Animal animalToAdopt = animals.stream()
                .filter(animal -> animal.getId().toString().equals(id))
                .filter(animal -> animal.getAdoptionStatus() == AdoptionStatus.AVAILABLE)
                .findFirst()
                .orElse(null);

        if (animalToAdopt == null) {
            return false;
        }

        animalToAdopt.markAsAdopted();
        adoptionRecords.add(new AdoptionRecord(
                animalToAdopt, LocalDate.now(), adopterName));
        return true;
    }

    public List<AdoptionRecord> getAdoptionRecords() {
        return List.copyOf(adoptionRecords);
    }

    public List<Animal> sortByAge() {
        return animals.stream()
                .sorted(Comparator.comparingInt(Animal::getAge))
                .toList();
    }

    public List<Animal> sortByName() {
        return animals.stream()
                .sorted(Comparator.comparing(Animal::getName))
                .toList();
    }
}