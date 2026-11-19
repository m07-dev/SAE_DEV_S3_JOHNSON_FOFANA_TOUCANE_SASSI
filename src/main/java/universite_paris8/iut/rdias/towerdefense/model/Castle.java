package universite_paris8.iut.rdias.towerdefense.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;

/*La classe Castle représente le château que le joueur
 * doit protéger.
 * Le château perd des points de vie lorsque les ennemis
 * atteignent sa position. Si ses points de vie tombent à 0,
 * la partie est perdue.
 * Un castle a comme attribut:
 * -ses points de vie maximum
 * -ses points de vie actuels (sous forme de propriété observable)
 * Deux attributs PV pour la barre de vie.
 */

public class Castle extends Actor {

    private final int maxHp = 500;
    private IntegerProperty hpPlayer;

    public Castle(Environnement env, int id, int maxHp) {//40 42
        super(env, id, maxHp, maxHp, 0, 0, 42, 40, null, null, null, 0);
        this.hpPlayer = new SimpleIntegerProperty(maxHp);
    }

    public IntegerProperty getHpPlayerProperty() {
        return hpPlayer;
    }

    public int getHp() {
        return hpPlayer.get();
    }

    public void takeDamage(Actor attacker) {
        super.takeDamage(attacker);
        this.getEnvironnement().addDyingActor(attacker);
    }

    public int getMaxHp() {
        return maxHp;
    }

}
