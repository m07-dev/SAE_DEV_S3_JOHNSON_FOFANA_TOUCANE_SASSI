package universite_paris8.iut.rdias.towerdefense.model;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;

/*La sous-classe ZoneSpell qui hérite de Effect.
 * Le sort se déplace vers sa cible et, à l'impact,
 * inflige des dégâts à tous les ennemis se trouvant
 * dans le rayon défini (effet de zone).
 * Il est utilisé par la tour SorcererTower.
 * Un zonespell a comme attribut spécifique:
 * -le rayon de la zone d'explosion
 */

public class ZoneSpell extends Effect {
    private double zoneRadius;


    public ZoneSpell(Environnement zEnv, double zX, double zY, int zDmg, Actor zTarget, double zZoneRadius) {
        super(zEnv, zX, zY, zDmg, zTarget, 0.10);
        zoneRadius = zZoneRadius;
    }

    @Override
    protected void onImpact(Actor target) {
        damageInZoneAround(target);
    }

    private void damageInZoneAround(Actor target) {
        for (Enemy e : getEnvironnement().getEnemies()) {
            if (!e.isLiving()) continue;
            double dist = Math.hypot(e.getX() - target.getX(), e.getY() - target.getY());
            if (dist <= zoneRadius) {
                e.takeDamage(getDmg());
            }
        }
    }

    public double getZoneRadius() {
        return zoneRadius;
    }
}
