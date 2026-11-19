package universite_paris8.iut.rdias.towerdefense.model.actor;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.strategy.AttackStrategy;
import universite_paris8.iut.rdias.towerdefense.strategy.FindTargetStrategy;

/*La super classe abstraite Actor définit le comportement commun
 * à toutes les entités du jeu (ennemis, tours, etc.).
 *
 * Un actor a comme attribut:
 * -L'environnement où il est attribué
 * -Point de vie (et point de vie max
 * pour la barre de vie)
 * -Point d'attaque
 * -Un identifiant
 * -La portéé d'attaque
 * -sa position dans la map
*/

public abstract class Actor {
    private Environnement env;
    private int id;
    private IntegerProperty hp;
    private int maxHp;
    private int dmg;
    private double range;
    private DoubleProperty lin;
    private DoubleProperty col;
    private Actor target;
    private AttackStrategy attackStrategy;
    private FindTargetStrategy findTargetStrategy;
    private int coolDownDuration;


    public Actor(Environnement env, int id, int hp, int maxHp, int dmg, double range, double lin, double col,
                 Actor target, AttackStrategy attackStrategy, FindTargetStrategy findTargetStrategy,
                 int coolDownDuration) {
        this.env = env;
        this.id = id;
        this.hp = new SimpleIntegerProperty(hp);
        this.maxHp = maxHp;
        this.dmg = dmg;
        this.range = range;
        this.lin = new SimpleDoubleProperty(lin);
        this.col = new SimpleDoubleProperty(col);
        this.target = target;
        this.attackStrategy = attackStrategy;
        this.findTargetStrategy = findTargetStrategy;
        this.coolDownDuration = coolDownDuration;
    }

    public void act() {
        this.target = this.findTargetStrategy.findTarget();
        this.attackStrategy.attack();
    }
    public void die() {
        this.hp.setValue(0);
        this.env.addDyingActor(this);
    }
    public void takeDamage(Actor attacker) {
        this.hp.setValue(Math.max(0, this.hp.getValue() - attacker.getDmg()));
        if (hp.getValue() <= 0) {
            this.die();
        }
    }
    public boolean isLiving(){return this.hp.getValue() > 0;}


    //Getters
    public int[] getClosestpath() {
        int[] closest = new int[2];
        closest[0] = (int)getLin();
        closest[1] = (int)getCol();
        return closest;
    }
    public Environnement getEnvironnement() {
        return this.env;
    }
    public int getId() {
        return id;
    }
    public int getHp() {
        return hp.getValue();
    }
    public IntegerProperty getHpProperty() {
        return hp;
    }
    public int getMaxHp(){
        return maxHp;
    }
    public int getDmg() {
        return dmg;
    }
    public double getRange() {
        return range;
    }
    public double getLin() {
        return lin.doubleValue();
    }
    public DoubleProperty getLinProperty() {return lin;}
    public double getCol() {
        return col.doubleValue();
    }
    public DoubleProperty getColProperty() {return col;}
    public Actor getTarget() {
        return target;
    }
    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }
    public FindTargetStrategy getFindTargetStrategy() {
        return findTargetStrategy;
    }
    public int getCooldown() {
        return coolDownDuration;
    }



    //Setters
    public void setHp(int hp){
        this.hp.setValue(hp);
    }
    public void setMaxHp(int maxHp){
        this.maxHp = maxHp;
    }
    public void setDmg(int dmg){
        this.dmg = dmg;
    }
    public void setLin(double valeur) {
        this.lin.set(valeur);
    }
    public void setCol(double valeur) {
        this.col.set(valeur);
    }
    public void setPosition(double lin, double col) {
        this.setLin(lin);
        this.setCol(col);
    }
    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }
    public void setFindTargetStrategy(FindTargetStrategy findTargetStrategy) {
        this.findTargetStrategy = findTargetStrategy;
    }
}
