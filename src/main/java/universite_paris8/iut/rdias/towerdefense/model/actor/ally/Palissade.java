package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.Ground;
import universite_paris8.iut.rdias.towerdefense.model.actor.StaticActor;
import universite_paris8.iut.rdias.towerdefense.strategy.AttackStrategy;
import universite_paris8.iut.rdias.towerdefense.strategy.FindTargetStrategy;

/*La sous-classe Palissade qui hérite de Tower.
 * Cette tour n'a pas d'attaque, elle se place sur le chemin
 * pour bloquer les ennemis. Elle ne peut être détruite
 * que par le bélier viking (RamWarrior). Ses dégâts,
 * sa portée d'attaque et sa vitesse d'attaque sont de zéro.
 */

public class Palissade extends StaticActor {
    public Palissade(Environnement env, int aId, int aHp, int maxHp, int aDmg, double aRange,
                     double aLin, double aCol, Actor target, int coolDownDuration, int cost, int level) {
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
    }


    @Override
    public boolean canBePlaced(int line, int col) {
        boolean result = false;
        Ground ground = getEnvironnement().getGround();

        if (line >= 0 && line < ground.heigth() && col >= 0 && col < ground.width() && ground.isPath(line, col)) {
            result = true;
        }
        return result;
    }

    public void place() {
        getEnvironnement().getGround().setTile((int)this.getLin(), (int)this.getCol(), 4);
        getEnvironnement().getGround().refreshBFS();
    }

    @Override
    public void die() {
        super.die();
        this.deleted();
    }

    public void sell() {
        this.deleted();
        super.sell();
    }

    public void deleted() {
        getEnvironnement().getGround().setTile((int)this.getLin(), (int)this.getCol(), 1);
        getEnvironnement().getGround().refreshBFS();
    }

    @Override
    public int maxAllowed() {
        return 4;
    }
}
