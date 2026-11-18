package universite_paris8.iut.rdias.towerdefense.strategy;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.MovingActor;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Bramble;
import universite_paris8.iut.rdias.towerdefense.model.actor.enemy.Viking;

import static universite_paris8.iut.rdias.towerdefense.utils.ActorUtils.isWithinRange;

public class AttackSlowFactor implements AttackStrategy {
    private Bramble attacker;

    public AttackSlowFactor(Bramble attacker) {
        this.attacker = attacker;
    }

    @Override
    public void attack() {
        if (attacker != null) {
            Actor target = attacker.getTarget();
            if ((target instanceof MovingActor movingActor)
                    && isWithinRange(attacker, target, attacker.getRange())) {
                movingActor.applySlow(attacker.getSlowFactor(),
                        attacker.getSlowDuration());
            }
        }

    }
}
