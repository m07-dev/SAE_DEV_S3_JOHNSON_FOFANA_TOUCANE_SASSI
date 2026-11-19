package universite_paris8.iut.rdias.towerdefense.model.Factory;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Barrack;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Bramble;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Palissade;
import universite_paris8.iut.rdias.towerdefense.model.actor.enemy.*;
import universite_paris8.iut.rdias.towerdefense.strategy.*;

import java.util.EnumMap;
import java.util.Map;

public class VikingFactory {

    public enum Type { BASIC_VIKING, ARCHER_VIKING, BERSERKER, SHIELD_VIKING, BATTERING_RAM }

    private static final Map<Type, ActorStats> STATS = new EnumMap<>(Type.class);
    static {
        STATS.put(Type.BASIC_VIKING,  ActorStats.movingActor(50, 5, 1, 10, 0.03, 10));
        STATS.put(Type.ARCHER_VIKING, ActorStats.movingActor(35, 6, 4, 15, 0.03, 10));
        STATS.put(Type.BERSERKER,     ActorStats.movingActor(70, 8, 1, 8, 0.06, 10));
        STATS.put(Type.SHIELD_VIKING, ActorStats.movingActor(150, 4, 1, 12, 0.015, 10));
        STATS.put(Type.BATTERING_RAM, ActorStats.movingActor(100, 20, 0.5, 20, 0.02, 10));
    }

    private final Environnement env;


    public VikingFactory(Environnement env) {
        this.env = env;
    }

    public static ActorStats getStats(Type type) {
        return STATS.get(type);
    }

    public Actor create(Type type, double lin, double col) {
        ActorStats s = STATS.get(type);
        int id = env.getNextId();
        Actor castle = env.getCastle();

        Viking viking;
        FindTargetMultiple find;

        switch (type) {
            case BASIC_VIKING -> {
                viking = new BasicViking(env, id, s.hp(), s.hp(), s.dmg(), s.range(), lin, col,
                        castle, s.cooldown(), s.speed(), s.deathValue());
                find = new FindTargetMultiple(viking);
                find.addFindTargetStrategy(new FindTargetFromList(viking, env.getKnights()));
            }
            case ARCHER_VIKING -> {
                viking = new ArcherViking(env, id, s.hp(), s.hp(), s.dmg(), s.range(), lin, col,
                        castle, s.cooldown(), s.speed(), s.deathValue());
                find = new FindTargetMultiple(viking);
                find.addFindTargetStrategy(new FindTargetFromList(viking, env.getKnights()));
                find.addFindTargetStrategy(new FindTargetFromListWithCondition(viking, env.getTowers(),
                        (Actor a) -> !(a instanceof Palissade) && !(a instanceof Bramble)));
            }
            case BERSERKER -> {
                viking = new Berserker(env, id, s.hp(), s.hp(), s.dmg(), s.range(), lin, col,
                        castle, s.cooldown(), s.speed(), s.deathValue());
                find = new FindTargetMultiple(viking);
                find.addFindTargetStrategy(new FindTargetFromList(viking, env.getKnights()));
            }
            case SHIELD_VIKING -> {
                viking = new ShieldViking(env, id, s.hp(), s.hp(), s.dmg(), s.range(), lin, col,
                        castle, s.cooldown(), s.speed(), s.deathValue());
                find = new FindTargetMultiple(viking);
                find.addFindTargetStrategy(new FindTargetFromList(viking, env.getKnights()));
            }
            case BATTERING_RAM -> {
                viking = new BatteringRam(env, id, s.hp(), s.hp(), s.dmg(), s.range(), lin, col,
                        castle, s.cooldown(), s.speed(), s.deathValue());
                find = new FindTargetMultiple(viking);
                find.addFindTargetStrategy(new FindTargetFromList(viking, env.getKnights()));
                find.addFindTargetStrategy(new FindTargetFromListWithCondition(viking, env.getTowers(),
                        (Actor a) -> a instanceof Palissade));
            }
            default -> throw new IllegalArgumentException("Ce type n'est pas un viking : " + type);
        }
        find.addFindTargetStrategy(() -> env.getCastle());
        viking.setFindTargetStrategy(find);

        AttackStrategy attack = (type == Type.ARCHER_VIKING)
                ? new AttackProjectile(viking, new ArrowFactory(env, viking))
                : new AttackMelee(viking);
        viking.setAttackStrategy(attack);

        return viking;
    }


}
