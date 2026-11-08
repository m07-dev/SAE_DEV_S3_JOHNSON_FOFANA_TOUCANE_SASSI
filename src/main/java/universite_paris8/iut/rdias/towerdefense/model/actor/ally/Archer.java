package universite_paris8.iut.rdias.towerdefense.model.actor.ally;


import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.Projectile;
import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.ShootingTower;
import universite_paris8.iut.rdias.towerdefense.model.actor.Tower;

/*La sous-classe de la tour archer qui hérite de Tower
 * est un allié que nous posons et qui attaque de loin
 * avec des fléches.*/


public class Archer extends ShootingTower {
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
    protected void attack(Enemy target) {
        getEnvironnement().addEffect(new Projectile(getEnvironnement(),getX(),getY(),getDmg(),target));
    }

    @Override
    public int placementRange(){
        return 3;
    }
}
