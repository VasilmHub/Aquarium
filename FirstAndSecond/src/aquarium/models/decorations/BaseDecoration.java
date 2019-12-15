package aquarium.models.decorations;

import aquarium.common.ConstantMessages;

public abstract class BaseDecoration implements Decoration {
    private int comfort;
    private double price;

    protected BaseDecoration(int comfort, double price) {
        this.comfort = comfort;
        this.setPrice(price);
    }

    private void setPrice(double price) {
        if(price>0) {
            this.price = price;
        }
    }

    @Override
    public int getComfort() {
        return this.comfort;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
