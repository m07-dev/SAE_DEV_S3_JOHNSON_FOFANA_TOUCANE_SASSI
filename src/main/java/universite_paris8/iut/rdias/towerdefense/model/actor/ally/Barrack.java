package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.StaticActor;


/*La sous-classe Barrack qui hérite de Tower.
 * Cette tour ne possède pas d'attaque, elle produit
 * des chevaliers (Knight) qui vont combattre les ennemis.
 * Sa portée d'attaque et ses dégâts sont de zéro.
 * Une barrack a comme attribut spécifique:
 * -les coordonnées de la tile chemin la plus proche
 */

public class Barrack extends StaticActor {
    private int nbKnight;
    private int nbKnightMax;
    public Barrack(Environnement env, int aId, int aHp, int maxHp, int aDmg, double aRange,
                    double aLin, double aCol, Actor target, int coolDownDuration, int cost, int level, int nbKnightMax) {
        super(env,
                aId,
                aHp,
                maxHp,
                aDmg,
                aRange,
                aLin,
                aCol,
                target,
                null,
                null,
                coolDownDuration,
                cost,
                level
        );
        this.nbKnight = 0;
        this.nbKnightMax = nbKnightMax;
    }

    public void act() {
        if (nbKnight < nbKnightMax) {
            getAttackStrategy().attack();
        }
    }

    @Override
    public int[] getClosestpath() {
        return getEnvironnement().getGround().getClosestPath((int)getLin(), (int)getCol());
    }

    public int getNbKnight() {
        return nbKnight;
    }
    public int getNbKnightMax() {
        return nbKnightMax;
    }
    public void incrementNbKnight() {
        nbKnight++;
    }
    public void decrementNbKnight() {
        nbKnight--;
    }
}
