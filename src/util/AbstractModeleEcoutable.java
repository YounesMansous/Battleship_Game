package src.util;
import java.util.ArrayList;

public abstract class AbstractModeleEcoutable implements ModeleEcoutable{
    protected ArrayList<EcouteurModele> ecouteurs = new ArrayList<EcouteurModele>();

    public void ajoutEcouteur(EcouteurModele e){
        this.ecouteurs.add(e);
    }

    public void retraitEcouteur(EcouteurModele e){
        this.ecouteurs.remove(e);
    }

    public void fireChangement(){
        for(EcouteurModele e : this.ecouteurs){
            e.modeleMiseAJour(e);
        }
    }

} 