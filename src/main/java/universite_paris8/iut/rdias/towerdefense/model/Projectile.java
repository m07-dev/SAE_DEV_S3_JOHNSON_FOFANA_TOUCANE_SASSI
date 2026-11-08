package universite_paris8.iut.rdias.towerdefense.model;

import universite_paris8.iut.rdias.towerdefense.model.actor.*;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;

/*La sous-classe Projectile qui hérite de Effect.
 * Le projectile se déplace en ligne droite vers sa cible
 * à une vitesse définie. Lorsqu'il atteint sa cible,
 * il lui inflige des dégâts et disparaît.
 * Il met également à jour son angle de rotation
 * pour l'animation visuelle.
 */

public class Projectile extends Effect{

    public Projectile(Environnement pEnv, double pX, double pY, int pDmg, Actor pTarget) {
        super(pEnv, pX, pY, pDmg, pTarget, 0.20);
    }
    @Override
    protected void onImpact(Actor target) {
        target.takeDamage(getDmg());
    }

}
