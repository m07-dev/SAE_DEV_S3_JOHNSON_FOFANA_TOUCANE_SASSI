package universite_paris8.iut.rdias.towerdefense.model.algorithm.Targeting;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

// strategie par default visant les ennemies les plus proches
public class NearestEnemyStrategy implements TargetingStrategy {

    @Override
    public Actor findTarget(Tower tower) {
        Enemy closest = null;
        double minDist = Math.pow(tower.getRange(), 2);
        for (Enemy e : tower.getEnvironnement().getEnemies()) {
            if (!e.isLiving()) continue;
            double dist = tower.calcDistanceFromTargerUsingPyth(e);
            if (dist < minDist) {
                minDist = dist;
                closest = e;
            }
        }
        return closest;
    }
}
