package universite_paris8.iut.rdias.towerdefense.model.actor;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.strategy.AttackStrategy;
import universite_paris8.iut.rdias.towerdefense.strategy.FindTargetStrategy;

/*La sous-classe Tower qui hérite de Actor.
* Une tower a comme attribut spécifique:
* -son coût d'achat
* -sa vitesse d'attaque (en demi-seconde)
* -son temps de recharge (cooldown)
* -son niveau (pour les améliorations)
*/

public abstract class StaticActor extends Actor {
    private int cost;
    private IntegerProperty level;


    public StaticActor(Environnement env, int aId, int aHp, int maxHp, int aDmg, double aRange,
                       double aLin, double aCol, Actor target, AttackStrategy attackStrategy, FindTargetStrategy findTargetStrategy,
                       int coolDownDuration, int cost, int level) {
        super(env, aId, aHp, maxHp, aDmg,  aRange, aLin, aCol, target, attackStrategy, findTargetStrategy, coolDownDuration);
        this.cost = cost;
        this.level = new SimpleIntegerProperty(level);
    }

    //Getter

    @Override
    public int[] getClosestpath() {
        return getEnvironnement().getGround().getClosestPath((int)getLin(), (int)getCol());
    }

    public int getCost() {
        return cost;
    }
    public int getLevel(){ return level.get();}

    public void upgrade() {
        double factor = getEnvironnement().getSettings().getUpgradeFactor();
        int newMaxHp = (int) (getMaxHp() * factor);
        int newDmg   = (int) (getDmg() * factor);
        setMaxHp(newMaxHp);
        setHp(newMaxHp);
        setDmg(newDmg);
        lvlUp();
    }

    public int getUpgradeCost() {
        double f = getEnvironnement().getSettings().getUpgradeCostFactor();
        return (int) (getCost() * f);
    }

    public boolean canBeUpgraded() {
        return getLevel() < 2;
    }

    public void lvlUp() {
        this.level.set(this.level.get() + 1);
    }

    public void sell(){
        int refund = this.cost / 2;
        getEnvironnement().earn(refund);
        getEnvironnement().delTower(this);
    }
    public boolean canBePlaced(int lin, int col) {
        var ground = getEnvironnement().getGround();
        if (lin < 0 || lin >= ground.heigth() || col < 0 || col >= ground.width()) {
            return false;
        }
        if (!ground.isGrass(lin, col)) {
            return false;
        }
        return isAdjacentToPath(lin, col);
    }


    public boolean isAdjacentToPath(int line, int col) {
        var ground = getEnvironnement().getGround();
        int[][] dirs = {{-1,-1}, {-1, 0}, {-1,1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        int range = placementRange();
        for (int[] d : dirs) {
            for (int step = 1; step <= range; step++) {
                int nL = line + d[0] * step;
                int nC = col + d[1] * step;
                if (nL >= 0 && nL < ground.heigth() && nC >= 0 && nC < ground.width()) {
                    if (ground.isPath(nL, nC)) return true;
                }
            }
        }
        return false;
    }

    public int maxAllowed() {
        return Integer.MAX_VALUE;
    }

    public int placementRange(){
        return (int)this.getRange()/2;
    }

    public IntegerProperty getLevelProperty() {
        return level;
    }
}
