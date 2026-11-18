package universite_paris8.iut.rdias.towerdefense.model.Factory;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.StaticActor;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.*;
import universite_paris8.iut.rdias.towerdefense.model.actor.enemy.*;
import universite_paris8.iut.rdias.towerdefense.strategy.*;

import java.util.EnumMap;
import java.util.Map;

public class StaticActorFactory {

    public enum Type { ARCHER, BALLISTA, BARRACK, BRAMBLE, PALISSADE, SORCERER_TOWER }

    private static final Map<StaticActorFactory.Type, ActorStats> STATS = new EnumMap<>(StaticActorFactory.Type.class);
    static {
        STATS.put(Type.ARCHER, ActorStats.staticActor(150, 45, 4, 60, 125));
        STATS.put(Type.BALLISTA, ActorStats.staticActor(325, 70, 8, 60, 550));
        STATS.put(Type.SORCERER_TOWER, ActorStats.staticActor(300, 60, 6, 90, 750));
        STATS.put(Type.BARRACK, ActorStats.staticActor(300, 0, 4, 180, 275));
        STATS.put(Type.PALISSADE, ActorStats.staticActor(350, 0, 0, 0, 300));
        STATS.put(Type.BRAMBLE, ActorStats.staticActor(1, 0, 1, 0, 75));
    }

    private static final int BARRACK_MAX_KNIGHTS = 3;
    private static final double BRAMBLE_SLOW_FACTOR = 0.6;
    private static final int BRAMBLE_SLOW_DURATION = 120;
    private static final int START_LEVEL = 1;

    private Environnement env;


    public StaticActorFactory(Environnement env) {
        this.env = env;
    }

    public static ActorStats getStats(StaticActorFactory.Type type) {
        return STATS.get(type);
    }

    public Actor create(StaticActorFactory.Type type, double lin, double col) {
        ActorStats s = STATS.get(type);
        int id = env.getNextId();
        StaticActor staticActor;
        AttackStrategy attack;

        switch (type) {
            case ARCHER -> {
                staticActor = new Archer(env, id, s.hp(), s.hp(), s.dmg(), s.range(), lin, col,
                        null, s.cooldown(), s.cost(), START_LEVEL);
                attack = new AttackProjectile(staticActor, new ArrowFactory(env, staticActor));
            }
            case BALLISTA -> {
                staticActor = new Ballista(env, id, s.hp(), s.hp(), s.dmg(), s.range(), lin, col,
                        null, s.cooldown(), s.cost(), START_LEVEL);
                attack = new AttackProjectile(staticActor, new ArrowFactory(env, staticActor));
            }
            case SORCERER_TOWER -> {
                staticActor = new SorcererTower(env, id, s.hp(), s.hp(), s.dmg(), s.range(), lin, col,
                        null, s.cooldown(), s.cost(), START_LEVEL);
                attack = new AttackProjectile(staticActor, new ZoneSpellFactory(env, staticActor));
            }
            case BARRACK -> {
                Barrack barrack = new Barrack(env, id, s.hp(), s.hp(), s.dmg(), s.range(), lin, col,
                        null, s.cooldown(), s.cost(), START_LEVEL, BARRACK_MAX_KNIGHTS);
                staticActor = barrack;
                attack = new AttackSpawn(barrack, new KnightFactory(env, barrack));
            }
            case PALISSADE -> {
                staticActor = new Palissade(env, id, s.hp(), s.hp(), s.dmg(), s.range(), lin, col,
                        null, s.cooldown(), s.cost(), START_LEVEL);
                attack = null;
            }
            case BRAMBLE -> {
                Bramble bramble = new Bramble(env, id, s.hp(), s.hp(), s.dmg(), s.range(), lin, col,
                        null, s.cooldown(), s.cost(), START_LEVEL, BRAMBLE_SLOW_FACTOR, BRAMBLE_SLOW_DURATION);
                staticActor = bramble;
                attack = new AttackSlowFactor(bramble);
            }
            default -> throw new IllegalStateException("Unexpected type: " + type);
        }
        FindTargetFromList find = new FindTargetFromList(staticActor, env.getEnemies());
        staticActor.setFindTargetStrategy(find);
        staticActor.setAttackStrategy(attack);

        return staticActor;
    }
}
