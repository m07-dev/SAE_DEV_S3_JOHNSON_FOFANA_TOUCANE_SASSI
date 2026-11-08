package universite_paris8.iut.rdias.towerdefense.model.actor.targeting;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;

import java.util.List;

public class NearestEnemyByPath extends NearestTargetStrategy{
    @Override
    protected List<? extends Actor> candidates(Actor seeker) {
        return seeker.getEnvironnement().getEnemies();
    }

    @Override
    protected double distance(Actor seeker, Actor candidate) {
         return seeker.calcDistanceFromTargerUsingBFS(candidate);
    }
}
