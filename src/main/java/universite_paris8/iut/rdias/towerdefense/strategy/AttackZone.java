package universite_paris8.iut.rdias.towerdefense.strategy;

import javafx.collections.ObservableList;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;

import static universite_paris8.iut.rdias.towerdefense.utils.ActorUtils.isWithinRange;

public class AttackZone implements AttackStrategy{
    private Environnement env;
    private Actor attacker;
    private ObservableList<? extends Actor> targetableList;

    public AttackZone(Actor attaker, ObservableList<? extends Actor> targetableList) {
        this.env = attaker.getEnvironnement();
        this.attacker = attaker;
        this.targetableList = targetableList;
    }

    public void attack() {
        for (Actor a : targetableList) {
            if (a.isLiving() && isWithinRange(attacker, a, attacker.getRange())) {
                a.takeDamage(attacker, attacker.getDmg());
            }
        }
    }
}
