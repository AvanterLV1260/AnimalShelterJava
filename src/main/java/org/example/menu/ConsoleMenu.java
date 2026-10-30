package org.example.menu;

import org.example.model.Animal;
import org.example.model.AnimalId;
import org.example.model.Bird;
import org.example.model.Cat;
import org.example.model.Dog;
import org.example.model.Rabbit;
import org.example.model.Shelter;
import util.AnimalUtils;

import java.util.Scanner;

public class ConsoleMenu {
    private final Shelter shelter;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(Shelter shelter) {
        this.shelter = shelter;
    }

    public void start() {
        while (true) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {

                case "1" -> addAnimal();
                case "2" -> shelter.getAllAnimals().forEach(System.out::println);
                case "3" -> {
                    System.out.print("species-");
                    AnimalUtils.filterBySpecies(
                                    shelter.getAllAnimals(), scanner.nextLine())
                            .forEach(System.out::println);
                }
                case "4" -> shelter.findAvailableAnimals().forEach(System.out::println);
                case "5" -> {
                    System.out.print("animal id-");
                    String id = scanner.nextLine().trim();
                    if (id.isBlank()) {
                        System.out.println("Animal ID cannot be empty");
                        break;
                    }

                    System.out.print("adopter name-");
                    String adopterName = scanner.nextLine().trim();
                    if (adopterName.isBlank()) {
                        System.out.println("Adopter name cannot be empty");
                        break;
                    }

                    if (shelter.markAsAdopted(id, adopterName)) {
                        System.out.println("Animal adopted successfully");
                    } else {
                        System.out.println("Animal with this ID cannot be found or is already adopted");
                    }
                }
                case "6" -> shelter.sortByAge().forEach(System.out::println);
                case "7" -> shelter.sortByName().forEach(System.out::println);
                case "8" -> shelter.getAdoptionRecords().forEach(System.out::println);
                case "9" -> System.out.println("Average age: "
                        + AnimalUtils.averageAge(shelter.getAllAnimals()));
                case "10" -> {
                    Animal oldest = AnimalUtils.oldestAnimal(shelter.getAllAnimals());
                    System.out.println("Oldest animal: " + oldest);
                }
                case "0" -> {
                    return;
                }

                default -> System.out.println("Invalid choice");

            }
        }
    }

    private void addAnimal() {
        System.out.print("Species- dog, cat, bird, rabbit-");
        String species = scanner.nextLine().trim().toLowerCase();
        System.out.print("Name-");
        String name = scanner.nextLine().trim();
        System.out.print("Age-");
        String ageInput = scanner.nextLine().trim();

        if (name.isBlank()) {
            System.out.println("Name cannot be empty");
            return;
        }


        int age;
        try {
            age = Integer.parseInt(ageInput);
        } catch (NumberFormatException exception) {
            System.out.println("Age must be a whole number");
            return;
        }
        if (age < 0) {
            System.out.println("Age cannot be negative");
            return;
        }

        Animal animal = switch (species) {
            case "dog" -> new Dog(new AnimalId(), name, age);
            case "cat" -> new Cat(new AnimalId(), name, age);
            case "bird" -> new Bird(new AnimalId(), name, age);
            case "rabbit" -> new Rabbit(new AnimalId(), name, age);
            default -> null;
        };
        if (animal == null) {
            System.out.println("Unknown species");
            return;
        }
        shelter.addAnimal(animal);
        System.out.println("Animal added successfully");
    }

    private void printMenu() {
        System.out.println("""
                1. Add animal
                2. List all animals
                3. Find animals by species
                4. List available animals
                5. Mark animal as adopted
                6. Sort animals by age
                7. Sort animals by name
                8. Show adoption history
                9. Show average age
                10. Show oldest animal
                0. Exit
                """);
    }
}
