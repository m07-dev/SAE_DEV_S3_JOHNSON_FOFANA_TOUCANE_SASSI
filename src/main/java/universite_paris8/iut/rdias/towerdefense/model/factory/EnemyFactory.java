package universite_paris8.iut.rdias.towerdefense.model.factory;

import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;

public class EnemyFactory {

    public static Enemy createForWave(int waveIndex, Environnement env, int id, int col, int line) {
        EnemyType type = chooseType(waveIndex);
        return type.create(env, id, col, line);
    }

    private static EnemyType chooseType(int waveIndex) {
        int roll = (int) (Math.random() * 100);
        EnemyType type;

        if (waveIndex <= 1) {
            type = EnemyType.VIKING;
        }
        else if (waveIndex <= 3) {
            if (roll < 30) {
                type = EnemyType.ARCHER_VIKING;
            } else {
                type = EnemyType.VIKING;
            }
        }
        else if (waveIndex <= 5) {
            if (roll < 30) {
                type = EnemyType.ARCHER_VIKING;
            } else if (roll < 50) {
                type = EnemyType.BERSERKER;
            } else {
                type = EnemyType.VIKING;
            }
        }
        else if (waveIndex <= 8) {
            if (roll < 10) {
                type = EnemyType.SHIELD_VIKING;
            } else if (roll < 20) {
                type = EnemyType.ARCHER_VIKING;
            } else if (roll < 30) {
                type = EnemyType.BERSERKER;
            } else {
                type = EnemyType.VIKING;
            }
        }
        else if (waveIndex <= 10) {
            if (roll < 10) {
                type = EnemyType.BATTERING_RAM;
            } else if (roll < 15) {
                type = EnemyType.SHIELD_VIKING;
            } else if (roll < 20) {
                type = EnemyType.ARCHER_VIKING;
            } else if (roll < 25) {
                type = EnemyType.BERSERKER;
            } else {
                type = EnemyType.VIKING;
            }
        }
        else {
            if (roll < 15) {
                type = EnemyType.BATTERING_RAM;
            } else if (roll < 25) {
                type = EnemyType.SHIELD_VIKING;
            } else if (roll < 35) {
                type = EnemyType.ARCHER_VIKING;
            } else if (roll < 50) {
                type = EnemyType.BERSERKER;
            } else {
                type = EnemyType.VIKING;
            }
        }
        return type;
    }
}
