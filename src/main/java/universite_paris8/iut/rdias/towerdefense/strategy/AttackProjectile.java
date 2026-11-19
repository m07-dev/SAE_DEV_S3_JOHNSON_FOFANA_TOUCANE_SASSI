package universite_paris8.iut.rdias.towerdefense.strategy;

import universite_paris8.iut.rdias.towerdefense.model.Factory.ActorFactory;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;

import static universite_paris8.iut.rdias.towerdefense.utils.ActorUtils.isWithinRange;

public class AttackProjectile implements AttackStrategy {
    private Environnement env;
    private Actor attacker;
    private int lastAttackTime;
    private final ActorFactory projectileFactory;

    public AttackProjectile(Actor attacker, ActorFactory projectileFactory) {
        this.attacker = attacker;
        this.env = attacker.getEnvironnement();
        this.lastAttackTime = 0;
        this.projectileFactory = projectileFactory;
    }

    @Override
    public void attack() {
        if (attacker != null) {
            Actor target = attacker.getTarget();
            if (target != null
                    && env.getCurrentTime()-lastAttackTime > attacker.getCooldown()
                    && isWithinRange(attacker, target, attacker.getRange())) {
                projectileFactory.spawn();
                lastAttackTime = env.getCurrentTime();
            }
        }
    }
}
