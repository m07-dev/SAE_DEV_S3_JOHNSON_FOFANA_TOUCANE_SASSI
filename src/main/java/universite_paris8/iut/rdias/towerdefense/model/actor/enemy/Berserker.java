package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.strategy.AttackStrategy;
import universite_paris8.iut.rdias.towerdefense.strategy.FindTargetStrategy;

/* Berserker
 * Viking très rapide et plus résistant qu'un simple viking.
 */

public class Berserker extends Viking {
    public Berserker(Environnement env, int id, int hp, int maxHp, int dmg, double range, double lin, double col,
                     Actor target, int coolDown, double speed, int deathValue) {
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
                speed,
                deathValue
        );
    }
}
