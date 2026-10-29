package org.example.shelter;
import org.example.model.AdoptionStatus;
import org.example.model.AdoptionRecord;
import org.example.model.Animal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Shelter {
    private final List<Animal> animals = new ArrayList<>();
    private final List<AdoptionRecord> adoptionHistory = new ArrayList<>();

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

    public void markAsAdopted(String id, String adopterName) {
        if (id == null || id.isBlank() || adopterName == null || adopterName.isBlank()) {
            return;
        }
        animals.stream()
                .filter(animal -> animal.getId().toString().equals(id))
                .filter(animal -> animal.getAdoptionStatus() == AdoptionStatus.AVAILABLE)
                .findFirst()
                .ifPresent(animal -> {animal.markAsAdopted();
                    adoptionHistory.add(new AdoptionRecord(
                            animal, LocalDate.now(), adopterName));
                });
    }

    public List<AdoptionRecord> getAdoptionHistory() {
        return List.copyOf(adoptionHistory);
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