package aquarium.models.aquariums;

import aquarium.models.decorations.Decoration;
import aquarium.models.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    protected BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException("Aquarium name cannot be null or empty.");
        }
        this.name = name;
    }

    private void setCapacity(int capacity) {
        if(capacity>0) {
            this.capacity = capacity;
        }
    }

    @Override
    public int calculateComfort() {
        int sum = 0;
        for (Decoration decoration : decorations) {
            sum += decoration.getComfort();
            return sum;
        }

        return sum;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {
        if (this.fish.size() + 1 <= capacity) {
            this.fish.add(fish);
        } else {
            throw new IllegalArgumentException("Not enough capacity.");
        }
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        for (Fish fi : fish) {
            fi.eat();
        }
    }

    @Override
    public String getInfo() {


        StringBuilder sb = new StringBuilder();
        String line = String.format("%s (%s):", this.name, this.getClass().getSimpleName());
        sb.append(line).append(System.lineSeparator()).append("Fish: ");
        if (fish.size() == 0) {
            sb.append("none");
        } else {
            for (Fish fish1 : fish) {
                sb.append(fish1.getName()).append(" ");
            }
        }
        sb.append(System.lineSeparator());
        sb.append(String.format("Decorations: %d", this.decorations.size()))
                .append(System.lineSeparator());
        sb.append(String.format("Comfort: %d", this.calculateComfort())).append(System.lineSeparator());
        return sb.toString().trim();
    }

    @Override
    public Collection<Fish> getFish() {
        return Collections.unmodifiableCollection(this.fish);
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return Collections.unmodifiableCollection(this.decorations);
    }
}
