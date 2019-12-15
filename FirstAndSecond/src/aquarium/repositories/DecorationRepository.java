package aquarium.repositories;

import aquarium.models.decorations.Decoration;

import java.util.ArrayList;
import java.util.Collection;

public class DecorationRepository implements Repository{
    private Collection<Decoration> decorations;

    public DecorationRepository() {
        this.decorations = new ArrayList<>();
    }

    @Override
    public void add(Decoration decoration) {
decorations.add(decoration);
    }

    @Override
    public boolean remove(Decoration decoration) {
        return decorations.remove(decoration);
    }

    @Override
    public Decoration findByType(String type) {
        return decorations.stream().filter(a->a.getClass().getSimpleName().equals(type)).findFirst().orElse(null);
    }
}