package universite_paris8.iut.rdias.towerdefense.strategy;

import javafx.collections.ObservableList;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;

import java.util.ArrayList;
import java.util.function.Predicate;

import static universite_paris8.iut.rdias.towerdefense.utils.ActorUtils.calcSquaredDistanceBetween;

public class FindTargetFromListWithCondition implements FindTargetStrategy {
    private Actor targeter;
    private ObservableList<? extends Actor> targetableList;
    private Predicate<Actor> condition;

    public FindTargetFromListWithCondition(Actor targeter, ObservableList<? extends Actor> targetableList, Predicate<Actor> condition) {
        this.targeter = targeter;
        this.targetableList = targetableList;
        this.condition = condition;
    }

    @Override
    public Actor findTarget() {
        Actor closestTarget = null;
        double minSquaredDistance = Double.MAX_VALUE;
        if (targetableList != null) {
            for (Actor targetable : targetableList) {
                if (condition == null || condition.test(targetable)) {
                    double squaredDistance = calcSquaredDistanceBetween(targeter, targetable);
                    if (squaredDistance < minSquaredDistance) {
                        minSquaredDistance = squaredDistance;
                        closestTarget = targetable;
                    }
                }
            }
        }

        return closestTarget;
    }
}