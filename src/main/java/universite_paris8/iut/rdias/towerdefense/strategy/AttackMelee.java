package universite_paris8.iut.rdias.towerdefense.strategy;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;

import static universite_paris8.iut.rdias.towerdefense.utils.ActorUtils.isWithinRange;

public class AttackMelee implements AttackStrategy {
    private Environnement env;
    private Actor attacker;
    private int lastAttackTime;

    public AttackMelee(Actor attacker) {
        this.attacker = attacker;
        this.env = attacker.getEnvironnement();
        this.lastAttackTime = 0;
    }

    public void attack() {
        if (attacker != null) {
            Actor target = attacker.getTarget();
            if (target != null
                    && env.getCurrentTime()-lastAttackTime > attacker.getCooldown()
                    && isWithinRange(attacker, target, attacker.getRange())) {
                target.takeDamage(attacker);
                lastAttackTime = env.getCurrentTime();
            }
        }

    }
}
