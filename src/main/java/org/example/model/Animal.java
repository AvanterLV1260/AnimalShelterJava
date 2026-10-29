package org.example.model;
public sealed abstract class Animal permits Dog,Cat,Bird,Rabbit {
    private final AnimalId id;
    private String name;
    private int age;
    private AdoptionStatus adoptionStatus;

    protected Animal(AnimalId id, String name, int age) {
        if (id == null) {
            System.out.println("Animal ID cannot be null");
        }
        if (name == null || name.isBlank()) {
            System.out.println("Animal name cannot be empty");
        }
        if (age < 0) {
            System.out.println("Animal age cannot be negative");
        }
        this.id = id;
        this.name = name.trim();
        this.age = age;
        this.adoptionStatus = AdoptionStatus.AVAILABLE;
    }

    public AnimalId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public AdoptionStatus getAdoptionStatus() {
        return adoptionStatus;
    }

    public void markAsAdopted(){
        this.adoptionStatus = AdoptionStatus.ADOPTED;
    }

    public abstract String getSpecies();

    @Override
    public String toString(){
        return id + " | " + name + " | " + age + " years old | " + getSpecies() + " | " + adoptionStatus;
    }
}
