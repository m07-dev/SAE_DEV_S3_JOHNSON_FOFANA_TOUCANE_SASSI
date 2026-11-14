package universite_paris8.iut.rdias.towerdefense.model.algorithm.Targeting;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

public class FastestEnemyStrategy implements TargetingStrategy{

    @Override
    public Actor findTarget(Tower tower) {
        Enemy fastest = null;
        double minDist = Math.pow(tower.getRange(), 2);
        for (Enemy e : tower.getEnvironnement().getEnemies()) {
            if (!e.isLiving()) continue;
            if (tower.calcDistanceFromTargerUsingPyth(e) < minDist && (fastest == null || e.getSpeed() > fastest.getSpeed())) {
                fastest = e;
            }
        }
        return fastest;
    }
}
