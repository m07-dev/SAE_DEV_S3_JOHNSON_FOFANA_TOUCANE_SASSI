package universite_paris8.iut.rdias.towerdefense.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.strategy.AttackStrategy;
import universite_paris8.iut.rdias.towerdefense.strategy.FindTargetStrategy;

import static universite_paris8.iut.rdias.towerdefense.utils.ActorUtils.calcSquaredDistanceBetween;
import static universite_paris8.iut.rdias.towerdefense.utils.ActorUtils.isWithinRange;

/*La classe abstraite Effect représente tous les effets
 * visuels et projectiles dans le jeu (flèches, boules de feu, etc.).
 * Les effets se déplacent vers leur cible et disparaissent
 * lorsqu'ils l'atteignent.
 * Un effect a comme attribut:
 * -L'environnement où il est attribué
 * -Sa position X et Y dans la map
 * -Son identifiant unique
 * -Ses dégâts infligés à la cible
 * -Un booléen indiquant s'il est terminé
 * -Sa cible (Actor)
 * -Sa vitesse de déplacement
 * -Sa direction sur l'axe X
 * -Sa direction sur l'axe Y
 * -Son angle de rotation (pour l'animation)
 */

public abstract class Projectile extends Actor {
    private double speed;
    private boolean finished = false;
    private DoubleProperty directionLin;
    private DoubleProperty directionCol;
    private DoubleProperty angle;

    public Projectile(Environnement env, int id, int hp, int maxHp, int dmg, double range, double lin, double col,
                      Actor target, AttackStrategy attackStrategy, FindTargetStrategy findTargetStrategy,
                      int coolDownDuration, double speed) {
        super(env, id, hp, maxHp, dmg, range, lin, col, target, attackStrategy, findTargetStrategy, coolDownDuration);
        this.speed = speed;
        this.directionLin = new SimpleDoubleProperty(0);
        this.directionCol = new SimpleDoubleProperty(0);
        this.angle = new SimpleDoubleProperty(0);
    }

    public void act() {
        move();
        if (getTarget() == null || !getTarget().isLiving()) {
            die();
        }
        else if (isWithinRange(this, getTarget(), speed)) {
            getAttackStrategy().attack();
            die();
        }
    }

    public void move() {
        double difLin = getTarget().getLin() - getLin();
        double difCol = getTarget().getCol() - getCol();

        double squaredDifLin = Math.pow(difLin, 2);
        double squaredDifCol = Math.pow(difCol, 2);

        double dist = calcSquaredDistanceBetween(this, this.getTarget());

        majDirectionEtAngle(
                squaredDifCol * Math.signum(difCol),
                squaredDifLin * Math.signum(difLin),
                dist
        );

        setLin(getLin() + getDirectionLin() * getSpeed());
        setCol(getCol() + getDirectionCol() * getSpeed());
    }

    public void majDirectionEtAngle(double dCol, double dLin, double dist) {
        if (dist > 0) {
            directionLinProperty().set(dLin / dist);
            directionColProperty().set(dCol / dist);
            double angleDegres = Math.toDegrees(Math.atan2(getDirectionLin(), getDirectionCol()));
            setAngle(angleDegres);
        }
    }

    public boolean isFinished() { return finished; }

//    public DoubleProperty getLinProperty() { return directionLin; }
//
//    public DoubleProperty getColProperty() { return directionCol; }
//
//    public double getLin() { return directionLin.getValue(); }
//
//    public double getCol() { return directionCol.getValue(); }

    public void finished() {
        finished = true;
    }

//    public void setLin(double lin) {
//        directionLin.setValue(lin);
//    }
//
//    public void setCol(double col) {
//        directionCol.setValue(col);
//    }

    public double getSpeed() {
        return speed;
    }

    public double getDirectionCol() {
        return directionCol.getValue();
    }

    public DoubleProperty directionColProperty() {
        return directionCol;
    }

    public double getDirectionLin() {
        return directionLin.getValue();
    }

    public DoubleProperty directionLinProperty() {
        return directionLin;
    }

    public double getAngle() {
        return angle.get();
    }

    public DoubleProperty angleProperty() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle.setValue(angle);
    }
}
