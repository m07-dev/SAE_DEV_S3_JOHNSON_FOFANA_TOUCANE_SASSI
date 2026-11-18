package universite_paris8.iut.rdias.towerdefense.model.actor;

import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.strategy.AttackStrategy;
import universite_paris8.iut.rdias.towerdefense.strategy.FindTargetStrategy;

public class MovingActor extends Actor {
    private double speed;
    private double slowFactor;
    private int slowDuration;
    private int[] previousCase;
    private int[] direction;

    public MovingActor(Environnement env, int id, int hp, int maxHp, int dmg, double range,
                       double lin, double col, Actor target, AttackStrategy attackStrategy, FindTargetStrategy findTargetStrategy,
                       int coolDownDuration, double speed) {
        super(env, id, hp, maxHp, dmg, range, lin, col, target, attackStrategy, findTargetStrategy, coolDownDuration);
        this.speed = speed;
        previousCase = new int[]{
                (int) lin,
                (int) col
        };
        direction = new int[2];
        setDirection(findDirection());
        this.slowFactor = 1.0;
        this.slowDuration = 0;
    }

    public void act() {
        move();
        super.act();
    }
    public void move() {
        double effectiveSpeed = speed * slowFactor;
        double newLin = getLin() + (effectiveSpeed * direction[0]);
        double newCol = getCol() + (effectiveSpeed * direction[1]);

        setPosition(newLin, newCol);

        if (atteint()) {
            setPosition(previousCase[0] + direction[0], previousCase[1] + direction[1]);
            previousCase[0] = (int)getLin();
            previousCase[1] = (int)getCol();
            setDirection(findDirection());
        }
        updateSlow();
    }
    private void updateSlow() {
        if (slowDuration > 0) {
            slowDuration--;
            if (slowDuration == 0) {
                slowFactor = 1.0;
                System.out.println("j'ai repris ma vitesse normale (l53MovAct)");
            }
        }
    }
    public void applySlow(double factor, int duration) {
        if (factor < this.slowFactor || slowDuration == 0) {
            this.slowFactor = factor;
            System.out.println("j'ai ralenti ma vistesse" + " ma vitesse etait de " + speed + " m " + speed*slowFactor + " (l58MovAct)");
        }
        this.slowDuration = Math.max(this.slowDuration, duration);
    }
    public boolean atteint() {
        return Math.abs((previousCase[0] + direction[0]) - getLin()) < speed * slowFactor
                && Math.abs((previousCase[1] + direction[1]) - getCol()) < speed * slowFactor;
    }
    public int[] findDirection() {
        int[][][][] mapBfs = getEnvironnement().getGround().getMapBFS().getDistancesMap();
        int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}, {0, 0}};
        int shortestDistance =  Integer.MAX_VALUE;
        int[] bestDirection = new int[2];
        for (int[] d : dirs) {
            int nL = (int)getLin() + d[0];
            int nC = (int)getCol() + d[1];
            if (getEnvironnement().getGround().isWalkable(nL, nC)) {
                int[] closestPath = getTarget().getClosestpath();
                int distance = mapBfs[closestPath[0]][closestPath[1]][nL][nC];
                if (distance == shortestDistance) {
                    int r = (int) (Math.random()*2);
                    if (r == 0) {
                        shortestDistance = distance;
                        bestDirection[0] = d[0];
                        bestDirection[1] = d[1];
                    }
                } else if (distance < shortestDistance) {
                    shortestDistance = distance;
                    bestDirection[0] = d[0];
                    bestDirection[1] = d[1];
                }
            }
        }
        return bestDirection;
    }

    public void setDirection(int[] direction) {
        this.direction[0] = direction[0];
        this.direction[1] = direction[1];
    }

    public double getSpeed() {
        return speed;
    }


}
