package universite_paris8.iut.rdias.towerdefense.model.Factory;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Barrack;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;
import universite_paris8.iut.rdias.towerdefense.strategy.AttackMelee;
import universite_paris8.iut.rdias.towerdefense.strategy.FindTargetFromList;
import universite_paris8.iut.rdias.towerdefense.strategy.FindTargetMultiple;

import java.util.EnumMap;
import java.util.Map;

public class KnightFactory implements ActorFactory {
    private ActorStats stat = ActorStats.movingActor(60, 8, 1, 10, 0.05, 0);
    private final Environnement env;
    private Barrack base;

    public KnightFactory(Environnement env, Barrack base) {
        this.env = env;
        this.base = base;
    }

    public Actor create() {
        if (base.getNbKnight() < base.getNbKnightMax()) {
            ActorStats s = stat;
            int[] closestPathBase = base.getClosestpath();
            Knight knight = new Knight(env, env.getNextId(), s.hp(), s.hp(), s.dmg(), s.range(), closestPathBase[0], closestPathBase[1],
                    base, s.cooldown(), s.speed(), base);

            FindTargetMultiple find = new FindTargetMultiple(knight);
            find.addFindTargetStrategy(new FindTargetFromList(knight, env.getEnemies()));
            find.addFindTargetStrategy(() -> {
                if (env.getEnemies().isEmpty()) {
                    return base;
                }
                else {
                    return null;
                }
            });
            knight.setFindTargetStrategy(find);
            knight.setAttackStrategy(new AttackMelee(knight));

            return knight;
        }
        return null;
    }

    @Override
    public void spawn() {
        Knight knight = (Knight)create();
        if (knight != null) {
            base.incrementNbKnight();
            env.addKnight(knight);
        }
    }
}
