package aquarium.models.fish;

public class SaltwaterFish extends BaseFish {
    public SaltwaterFish(String name, String species, double price) {
        super(name, species, 5, price);
    }
    @Override
    public void eat() {
        setSize(getSize()+2);
    }
}
