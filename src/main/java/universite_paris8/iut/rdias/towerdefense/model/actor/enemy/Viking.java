package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.MovingActor;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Barrack;
import universite_paris8.iut.rdias.towerdefense.strategy.AttackStrategy;
import universite_paris8.iut.rdias.towerdefense.strategy.FindTargetStrategy;

/* Viking Simple
 */

public class Viking extends MovingActor {
    private int deathValue;
    public Viking(Environnement env, int id, int hp, int maxHp, int dmg, double range, double lin, double col,
                  Actor target, AttackStrategy attackStrategy, FindTargetStrategy findTargetStrategy, int coolDown,
                  double speed, int deathValue) {
        super(env,
                id,
                hp,
                maxHp,
                dmg,
                range,
                lin,
                col,
                target,
                attackStrategy,
                findTargetStrategy,
                coolDown,
                speed
        );
        this.deathValue = deathValue;
    }

    @Override
    public void die() {
        super.die();
        getEnvironnement().earn(deathValue);
    }
}
