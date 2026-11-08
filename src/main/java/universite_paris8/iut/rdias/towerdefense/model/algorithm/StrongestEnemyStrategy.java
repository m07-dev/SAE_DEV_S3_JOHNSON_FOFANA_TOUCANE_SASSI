package universite_paris8.iut.rdias.towerdefense.model.algorithm;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

public class StrongestEnemyStrategy implements TargetingStrategy{
    @Override
    public Actor findTarget(Tower tower) {
        Enemy strongest = null;
        double minDist = Math.pow(tower.getRange(), 2);
        for (Enemy e : tower.getEnvironnement().getEnemies()) {
            if (!e.isLiving()) continue;
            if (tower.calcDistanceFromTargerUsingPyth(e) < minDist && (strongest == null || e.getHp() > strongest.getHp())) {
                strongest = e;
            }
        }
        return strongest;
    }
}
