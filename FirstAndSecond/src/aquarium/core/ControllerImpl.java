package aquarium.core;

import aquarium.models.aquariums.Aquarium;
import aquarium.models.aquariums.FreshwaterAquarium;
import aquarium.models.aquariums.SaltwaterAquarium;
import aquarium.models.decorations.Decoration;
import aquarium.models.decorations.Ornament;
import aquarium.models.decorations.Plant;
import aquarium.models.fish.Fish;
import aquarium.models.fish.FreshwaterFish;
import aquarium.models.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControllerImpl implements Controller {
    private DecorationRepository decoration;
    private List<Aquarium> aquariums;
    public ControllerImpl() {
        decoration=new DecorationRepository();
        aquariums=new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        if(aquariumType.equals("FreshwaterAquarium")){
            this.aquariums.add(new FreshwaterAquarium(aquariumName));
        }else if(aquariumType.equals("SaltwaterAquarium")) {
            this.aquariums.add(new SaltwaterAquarium(aquariumName));
        }else {
            throw new IllegalArgumentException("Invalid aquarium type.");
        }
        return String.format("Successfully added %s.",aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        if(type.equals("Ornament")){
            decoration.add(new Ornament());
        }else if (type.equals("Plant")){
decoration.add(new Plant());
        }else {
            throw new IllegalArgumentException("Invalid decoration type.");
        }
        return String.format("Successfully added %s.",type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration decoration;
        if(decorationType.equals("Ornament")){
            decoration=new Ornament();
        }else if (decorationType.equals("Plant")){
            decoration=new Plant();
        }else {
            throw new IllegalArgumentException(String.format("There isn't a decoration of type %s}.",decorationType));
        }
        for (Aquarium aquarium : aquariums) {
            if(aquarium.getName().equals(aquariumName)){
                aquarium.addDecoration(decoration);
                return String.format("Successfully added %s to %s.",decorationType,aquariumName);
            }
        }
        return "nqma takuv tip na akwariym";
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName,
                          String fishSpecies, double price) {
        if(!(fishType.equals("FreshwaterFish")||fishType.equals("SaltwaterFish"))){
            throw new IllegalArgumentException("Invalid fish type.");
        }
        Fish fi;
        if(fishType.equals("FreshwaterFish")){
            fi=new FreshwaterFish(fishName,fishSpecies,price);
        }else{
            fi=new SaltwaterFish(fishName,fishSpecies,price);
        }

            for (Aquarium aquarium : aquariums) {
                if (aquarium.getName().equals(aquariumName)) {
                    aquarium.addFish(fi);
                }
            }

        return String.format("Successfully added %s to %s.",fishType,aquariumName );
    }

    @Override
    public String feedFish(String aquariumName) {
        Collection<Fish> res=new ArrayList<>();
        for (Aquarium aquarium : aquariums) {
            if (aquarium.getName().equals(aquariumName)){
                aquarium.feed();
                res=aquarium.getFish();
                return String.format("Fish fed: %d",res.size());
            }
        }
        return null;
    }

    @Override
    public String calculateValue(String aquariumName) {
        double sum=0;
        for (Aquarium aquarium : aquariums) {
            if(aquarium.getName().equals(aquariumName)){
                for (Fish fi: aquarium.getFish()) {
                    sum+=fi.getPrice();
                }
                for (Decoration Decoration : aquarium.getDecorations()) {
                    sum+=Decoration.getPrice();
                }
            }
        }
        return String.format("The value of Aquarium %s is %.2f.",aquariumName,sum);
    }

    @Override
    public String report() {
        StringBuilder sb=new StringBuilder();
        for (Aquarium aquarium : aquariums) {
            sb.append(aquarium.getInfo()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
