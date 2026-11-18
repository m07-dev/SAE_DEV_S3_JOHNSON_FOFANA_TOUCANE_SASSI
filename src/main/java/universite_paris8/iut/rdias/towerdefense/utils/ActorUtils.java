package universite_paris8.iut.rdias.towerdefense.utils;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;

public final class ActorUtils {
    public static double calcSquaredDistanceBetween(Actor actor1, Actor actor2) {
        double dx = actor1.getLin() - actor2.getLin();
        double dy = actor1.getCol() - actor2.getCol();

        return dx * dx + dy * dy;
    }

    public static boolean isWithinRange(Actor actor1, Actor actor2, double range) {
        double squaredDistance = calcSquaredDistanceBetween(actor1, actor2);
        return squaredDistance < (range * range);
    }

    
}