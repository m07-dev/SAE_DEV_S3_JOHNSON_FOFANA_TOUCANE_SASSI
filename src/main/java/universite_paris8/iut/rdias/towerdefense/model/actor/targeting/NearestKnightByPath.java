package universite_paris8.iut.rdias.towerdefense.model.actor.targeting;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;

import java.util.List;

public class NearestKnightByPath extends NearestTargetStrategy{
    @Override
    protected List<? extends Actor> candidates(Actor seeker) {
        return seeker.getEnvironnement().getKnights();
    }

    @Override
    protected double distance(Actor seeker, Actor candidate) {
        return seeker.calcDistanceFromTargerUsingBFS(candidate);
    }
    @Override protected double maxDistance(Actor s) { return 6.0; }

}
