package universite_paris8.iut.rdias.towerdefense.model.actor.targeting;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;

import java.util.List;

public abstract class NearestTargetStrategy implements TargetingStrategy{
    protected abstract List<? extends Actor> candidates(Actor seeker);
    protected abstract double distance(Actor seeker, Actor candidate);
    protected double maxDistance(Actor seeker) { return Double.MAX_VALUE; }
    protected boolean accepts(Actor candidate) { return true; }
    protected boolean isCloser(double d, double best) { return d <= best; }

    @Override
    public Actor findTarget(Actor seeker) {
        Actor closest = null;
        double best = maxDistance(seeker);
        for (Actor c : candidates(seeker)) {
            if (!c.isLiving() || !accepts(c)) continue;
            double d = distance(seeker, c);
            if (isCloser(d, best)) { best = d; closest = c; }
        }
        return closest;
    }
}

