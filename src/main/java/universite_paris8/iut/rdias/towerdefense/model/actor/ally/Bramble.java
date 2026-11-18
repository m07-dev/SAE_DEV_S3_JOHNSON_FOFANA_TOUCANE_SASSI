package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.StaticActor;
import universite_paris8.iut.rdias.towerdefense.model.Ground;
import universite_paris8.iut.rdias.towerdefense.strategy.AttackStrategy;
import universite_paris8.iut.rdias.towerdefense.strategy.FindTargetStrategy;

/*La sous-classe Bramble qui hérite de Tower.
 * Cette tour ne possède pas d'attaque, elle se place
 * directement sur le chemin et ralentit les ennemis
 * qui passent sur sa case. Ses dégâts sont de zéro.
 * Une bramble a comme attribut spécifique:
 * -son facteur de ralentissement
 * -la durée de son ralentissement
 */
public class Bramble extends StaticActor {
    private double slowFactor;
    private int slowDuration;

    public Bramble(Environnement env, int aId, int aHp, int maxHp, int aDmg, double aRange,
                    double aLin, double aCol, Actor target, int coolDownDuration, int cost, int level, double slowFactor, int slowDuration) {
        super(env,
                aId,
                aHp,
                maxHp,
                aDmg,
                aRange,
                aLin,
                aCol,
                target,
                null,
                null,
                coolDownDuration,
                cost,
                level
        );
        this.slowFactor = slowFactor;
        this.slowDuration = slowDuration;
    }

    @Override
    public boolean isLiving(){
        return true;    // indestructible
    }

    @Override
    public void takeDamage(Actor attacker, int amount) {
        // Bramble ignore les dégâts
    }

    @Override
    public boolean canBePlaced(int line, int col) {
        Ground ground = getEnvironnement().getGround();
        if (line < 0 || line >= ground.heigth() || col < 0 || col >= ground.width()) {
            return false;
        }
        return ground.isPath(line, col);
    }

    @Override
    public int maxAllowed() {
        return 10;
    }

    @Override
    public boolean canBeUpgraded() {
        return false;
    }

    public double getSlowFactor() {
        return slowFactor;
    }

    public int getSlowDuration() {
        return slowDuration;
    }
}
