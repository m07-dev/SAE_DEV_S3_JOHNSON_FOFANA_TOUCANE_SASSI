package universite_paris8.iut.rdias.towerdefense.model.algorithm.Targeting;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

public interface TargetingStrategy {
    Actor findTarget(Tower tower);
}
