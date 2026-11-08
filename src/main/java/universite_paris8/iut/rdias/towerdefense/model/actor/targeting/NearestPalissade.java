package universite_paris8.iut.rdias.towerdefense.model.actor.targeting;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Palissade;

import java.util.List;

public class NearestPalissade extends NearestTargetStrategy{
    @Override protected List<? extends Actor> candidates(Actor s) { return s.getEnvironnement().getTowers(); }
    @Override protected boolean accepts(Actor c) { return c instanceof Palissade; }
    @Override protected double distance(Actor s, Actor c) { return s.calcDistanceFromTargerUsingPyth(c); }
}

