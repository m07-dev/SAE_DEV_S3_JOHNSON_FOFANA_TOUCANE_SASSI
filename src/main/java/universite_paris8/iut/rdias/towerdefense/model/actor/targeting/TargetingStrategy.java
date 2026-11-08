package universite_paris8.iut.rdias.towerdefense.model.actor.targeting;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;

public interface TargetingStrategy {
    Actor findTarget(Actor seeker);
}
