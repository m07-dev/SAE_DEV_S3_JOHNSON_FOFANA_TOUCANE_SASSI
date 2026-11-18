package universite_paris8.iut.rdias.towerdefense.model.Factory;

/* Regroupe les stats de base d'un type d'acteur.
 * speed : 0 pour les acteurs immobiles.
 * cost  : 0 pour les acteurs non achetables (ennemis, chevaliers). */
public record ActorStats(int hp, int dmg, double range, int cooldown, double speed, int cost, int deathValue) {

    public static ActorStats movingActor(int hp, int dmg, double range, int cooldown, double speed, int deathValue) {
        return new ActorStats(hp, dmg, range, cooldown, speed, 0, deathValue);
    }

    public static ActorStats staticActor(int hp, int dmg, double range, int cooldown, int cost) {
        return new ActorStats(hp, dmg, range, cooldown, 0, cost, 0);
    }
}
