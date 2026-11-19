package universite_paris8.iut.rdias.towerdefense.strategy;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;

import java.util.ArrayList;

import static universite_paris8.iut.rdias.towerdefense.utils.ActorUtils.calcSquaredDistanceBetween;

public class FindTargetMultiple implements FindTargetStrategy {
    private Actor targeter;
    private ArrayList<FindTargetStrategy> findTargetStrategyList;

    public FindTargetMultiple(Actor targeter, ArrayList<FindTargetStrategy> findTargetStrategyList) {
        this.targeter = targeter;
        this.findTargetStrategyList = findTargetStrategyList;
    }

    public FindTargetMultiple(Actor targeter) {
        this.targeter = targeter;
        this.findTargetStrategyList = new ArrayList<>();
    }

    @Override
    public Actor findTarget() {
        Actor closestTarget = null;
        double minSquaredDistance = Double.MAX_VALUE;
        Actor tempActor;
        for (FindTargetStrategy findTargetStrategy : findTargetStrategyList) {
             tempActor = findTargetStrategy.findTarget();
             if (tempActor != null) {
                 double squaredDistance = calcSquaredDistanceBetween(targeter, tempActor);
                 if (squaredDistance < minSquaredDistance) {
                     minSquaredDistance = squaredDistance;
                     closestTarget = tempActor;
                 }
             }
        }

        return closestTarget;
    }

    public void addFindTargetStrategy(FindTargetStrategy findTargetStrategy) {
        this.findTargetStrategyList.add(findTargetStrategy);
    }
}
