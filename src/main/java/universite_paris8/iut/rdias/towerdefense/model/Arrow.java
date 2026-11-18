package universite_paris8.iut.rdias.towerdefense.model;

import universite_paris8.iut.rdias.towerdefense.model.actor.*;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;

import static universite_paris8.iut.rdias.towerdefense.utils.ActorUtils.calcSquaredDistanceBetween;

/*La sous-classe Projectile qui hérite de Effect.
 * Le projectile se déplace en ligne droite vers sa cible
 * à une vitesse définie. Lorsqu'il atteint sa cible,
 * il lui inflige des dégâts et disparaît.
 * Il met également à jour son angle de rotation
 * pour l'animation visuelle.
 */

public class Arrow extends Projectile {
    public Arrow(Environnement env, int id, int hp, int maxHp, int dmg, double range, double lin, double col,
                 Actor target, int coolDownDuration, double speed) {
        super(env, id, hp, maxHp, dmg, range, lin, col, target, null, null, coolDownDuration, speed);

    }
}
