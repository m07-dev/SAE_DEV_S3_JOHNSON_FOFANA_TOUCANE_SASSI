package universite_paris8.iut.rdias.towerdefense.model.factory;

import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.enemy.*;


public enum EnemyType {
    VIKING {
        public Enemy create(Environnement env, int id, int col, int line) {
            return new Viking(env, id, col, line);
        }
    },
    ARCHER_VIKING {
        public Enemy create(Environnement env, int id, int col, int line) {
            return new ArcherViking(env, id, col, line);
        }
    },
    BERSERKER {
        public Enemy create(Environnement env, int id, int col, int line) {
            return new Berserker(env, id, col, line);
        }
    },
    SHIELD_VIKING {
        public Enemy create(Environnement env, int id, int col, int line) {
            return new ShieldViking(env, id, col, line);
        }
    },
    BATTERING_RAM {
        public Enemy create(Environnement env, int id, int col, int line) {
            return new BatteringRam(env, id, col, line);
        }
    };

    public abstract Enemy create(Environnement env, int id, int col, int line);
}
