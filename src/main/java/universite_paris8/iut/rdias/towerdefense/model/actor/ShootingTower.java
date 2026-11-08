package universite_paris8.iut.rdias.towerdefense.model.actor;

import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;

public abstract class ShootingTower extends Tower{
    public ShootingTower(Environnement env, int tHp, int tDmg, int tId, double tRange, double tX, double tY, int speedAct, int tCost) {
        super(env, tHp, tDmg, tId, tRange, tX, tY, speedAct, tCost);
    }

    @Override
    public void act() {
        tick();
        if (canAct()) {
            Enemy target = (Enemy) searchTarget();
            if (target != null) {
                attack(target);
                resetCooldown();
            }
        }
    }
    protected abstract void attack(Enemy target);
    }

