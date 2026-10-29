package util;

import org.example.model.Animal;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public final class AnimalUtils {
    private AnimalUtils() {
    }

    public static double averageAge(Collection<Animal> animals) {
        if (animals.isEmpty()) {
            return 0;
        }

        int totalAge = 0;
        for (Animal animal : animals) {
            totalAge += animal.getAge();
        }
        return (double) totalAge / animals.size();
    }

    public static Animal oldestAnimal(Collection<Animal> animals) {
        Animal oldest = null;
        for (Animal animal : animals) {
            if (oldest == null || animal.getAge() > oldest.getAge()) {
                oldest = animal;
            }
        }
        return oldest;
    }

    public static List<Animal> filterBySpecies(
            Collection<Animal> animals, String species) {
        List<Animal> result = new ArrayList<>();
        if (species == null || species.isBlank()) {
            return result;
        }

        for (Animal animal : animals) {
            if (animal.getSpecies().equalsIgnoreCase(species)) {
                result.add(animal);
            }
        }
        return result;
    }
}
