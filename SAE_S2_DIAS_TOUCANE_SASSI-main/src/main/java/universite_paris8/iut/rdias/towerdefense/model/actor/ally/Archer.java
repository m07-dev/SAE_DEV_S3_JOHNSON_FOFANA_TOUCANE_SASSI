package universite_paris8.iut.rdias.towerdefense.model.actor.ally;


import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Attacker;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.Projectile;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

/*La sous-classe de la tour archer qui hérite de Tower
 * est un allié que nous posons et qui attaque de loin
 * avec des fléches.*/


public class Archer extends Tower implements Attacker {
    public Archer(Environnement aEnv,int archerId, double aX, double aY) {
        super(  aEnv,
                aEnv.getSettings().getArcherHp(),
                aEnv.getSettings().getArcherDmg(),
                archerId,
                aEnv.getSettings().getArcherRange(),
                aX,
                aY,
                aEnv.getSettings().getArcherSpeedAttack(),
                aEnv.getSettings().getArcherCost()
        );
    }

    @Override
    public void act() {
        tick();
        if (canAct()) {
            Actor target = searchTarget();
            if (target != null) {
                attack(target);
            }
        }
    }

    @Override
    public void attack(Actor target) {
        Projectile proj = new Projectile(getEnvironnement(), getX(), getY(), getDmg(), target);
        getEnvironnement().addEffect(proj);
        resetCooldown();
    }

    @Override
    public int placementRange(){
        return 3;
    }
}
