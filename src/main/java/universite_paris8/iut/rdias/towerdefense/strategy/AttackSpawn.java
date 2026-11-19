package universite_paris8.iut.rdias.towerdefense.strategy;

import universite_paris8.iut.rdias.towerdefense.model.Factory.ActorFactory;
import universite_paris8.iut.rdias.towerdefense.model.Factory.KnightFactory;
import universite_paris8.iut.rdias.towerdefense.model.Factory.StaticActorFactory;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Barrack;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;

public class AttackSpawn implements AttackStrategy {
    private Environnement env;
    private Actor attacker;
    private ActorFactory factory;
    private int lastAttackTime;


    public AttackSpawn(Actor attacker, ActorFactory factory) {
        this.attacker = attacker;
        this.env = attacker.getEnvironnement();
        this.factory = factory;
        this.lastAttackTime = 0;
    }

    @Override
    public void attack() {
        if (env.getCurrentTime()-lastAttackTime > attacker.getCooldown()) {
            factory.spawn();
            lastAttackTime = env.getCurrentTime();
        }
    }
}
