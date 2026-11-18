package universite_paris8.iut.rdias.towerdefense.model;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;

/*La sous-classe ZoneSpell qui hérite de Effect.
 * Le sort se déplace vers sa cible et, à l'impact,
 * inflige des dégâts à tous les ennemis se trouvant
 * dans le rayon défini (effet de zone).
 * Il est utilisé par la tour SorcererTower.
 * Un zonespell a comme attribut spécifique:
 * -le rayon de la zone d'explosion
 */

public class ZoneSpell extends Projectile {
    public ZoneSpell(Environnement env, int id, int hp, int maxHp, int dmg, double range, double lin, double col,
                      Actor target, int coolDownDuration, double speed) {
        super(env, id, hp, maxHp, dmg, range, lin, col, target, null, null, coolDownDuration, speed);
    }
}
