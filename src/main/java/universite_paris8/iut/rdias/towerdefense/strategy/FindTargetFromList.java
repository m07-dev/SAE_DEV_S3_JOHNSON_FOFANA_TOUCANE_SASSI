package universite_paris8.iut.rdias.towerdefense.strategy;

import javafx.collections.ObservableList;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;

import java.util.ArrayList;

import static universite_paris8.iut.rdias.towerdefense.utils.ActorUtils.calcSquaredDistanceBetween;

public class FindTargetFromList implements FindTargetStrategy{
    private Actor targeter;
    private ObservableList<? extends Actor> targetableList;

    public FindTargetFromList(Actor targeter, ObservableList<? extends Actor> targetableList) {
        this.targeter = targeter;
        this.targetableList = targetableList;
    }

    @Override
    public Actor findTarget() {
        Actor closestTarget = null;
        double minSquaredDistance = Double.MAX_VALUE;
        if (targetableList != null) {
            for (Actor targetable : targetableList) {
                double squaredDistance = calcSquaredDistanceBetween(targeter, targetable);
                if (squaredDistance < minSquaredDistance) {
                    minSquaredDistance =  squaredDistance;
                    closestTarget = targetable;
                }
            }
        }

        return closestTarget;
    }
}
