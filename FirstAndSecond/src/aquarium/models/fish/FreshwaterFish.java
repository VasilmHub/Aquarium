package aquarium.models.fish;

public class FreshwaterFish extends BaseFish {
    public FreshwaterFish(String name, String species, double price) {
        super(name, species, 3, price);
    }

    @Override
    public void eat() {
        setSize(getSize()+3);
    }
}
