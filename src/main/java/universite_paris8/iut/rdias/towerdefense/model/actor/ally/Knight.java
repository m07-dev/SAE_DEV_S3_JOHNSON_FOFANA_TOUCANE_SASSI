package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.MovingActor;
import universite_paris8.iut.rdias.towerdefense.strategy.*;

/*La sous-classe Knight qui hérite de Soldier.
 * Le chevalier est spawné par la Barrack et se déplace
 * vers les ennemis pour les attaquer au corps à corps
 * (portée d'attaque de 1). Sa cible par défaut est le château pour le defendre.
 * Un chevalier a comme attribut spécifique:
 * -sa cible (target) qu'il doit attaquer
 */

public class Knight extends MovingActor {
    private Barrack base;

    public Knight(Environnement env, int id, int hp, int maxHp, int dmg, double range, double lin, double col,
                  Actor target, int coolDown, double speed,
                  Barrack base) {
        super(env,
                id,
                hp,
                maxHp,
                dmg,
                range,
                lin,
                col,
                target,
                null,
                null,
                coolDown,
                speed
        );
        this.base = base;
        if (this.base != null)
            this.base.incrementNbKnight();
    }

    @Override
    public void die() {
        super.die();
        base.decrementNbKnight();
    }

    public Barrack getBase() {
        return base;
    }
}
