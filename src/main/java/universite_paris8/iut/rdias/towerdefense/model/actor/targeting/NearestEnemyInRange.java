package universite_paris8.iut.rdias.towerdefense.model.actor.targeting;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;

import java.util.List;

public class NearestEnemyInRange extends NearestTargetStrategy{
    @Override
    protected List<? extends Actor> candidates(Actor seeker) {
        return seeker.getEnvironnement().getEnemies();
    }

    @Override
    protected double distance(Actor seeker, Actor candidate) {
        return seeker.calcDistanceFromTargerUsingPyth(candidate);
    }

    @Override
    protected double maxDistance(Actor seeker) {
        return Math.pow(seeker.getRange(), 2);
    }

    @Override
    protected boolean isCloser(double d, double best) {
        return d < best;
    }
}
