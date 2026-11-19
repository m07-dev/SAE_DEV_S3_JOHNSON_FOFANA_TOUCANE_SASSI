package universite_paris8.iut.rdias.towerdefense.strategy;

import javafx.collections.ObservableList;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;

import java.util.ArrayList;

import static universite_paris8.iut.rdias.towerdefense.utils.ActorUtils.calcSquaredDistanceBetween;

public class FindTargetFromList extends FindTargetFromListWithCondition {
    public FindTargetFromList(Actor targeter, ObservableList<? extends Actor> targetableList) {
        super(targeter, targetableList, null);
    }
}
