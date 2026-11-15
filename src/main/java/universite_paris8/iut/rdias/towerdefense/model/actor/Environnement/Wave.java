package universite_paris8.iut.rdias.towerdefense.model.actor.Environnement;

import universite_paris8.iut.rdias.towerdefense.model.actor.Enemy;
import universite_paris8.iut.rdias.towerdefense.model.actor.enemy.*;
import universite_paris8.iut.rdias.towerdefense.model.factory.EnemyFactory;

/*La classe Wave représente une vague d'ennemis.
 * Cette classe gère la création et le spawn des ennemis
 * en fonction de l'index de la vague. Plus la vague est élevée,
 * plus le nombre d'ennemis augmente, les types d'ennemis
 * deviennent variés et forts.
 * Une wave a comme attribut:
 * -L'environnement du jeu
 * -L'index de la vague
 * -Les points de spawn des ennemis
 * -Le nombre d'ennemis à faire spawner
 * -Le délai entre chaque spawn
 * -Un booléen indiquant si l'inflation Macron est activée (mode difficile)
 */

public class Wave {
    private Environnement env;
    private int waveIndex;
    private static final int[][] spawnPoints = {{4, 8}, {4, 38}, {4, 60}};
    private static final int[] spawnPointsStartGame = {4, 38};
    private int nbEnemies;
    private int delayBetweenSpawns;
    private boolean macronInflationActivated;
    private int waveCptLap;

    public Wave(Environnement env, int waveIndex) {
        this.env = env;
        this.waveIndex = waveIndex;
        this.nbEnemies = (int)(10 + 20 * Math.log(waveIndex + 1));
        this.delayBetweenSpawns = 3;
        this.waveCptLap = 0;
        // Ajout du système d'inflation
        if (waveIndex > 4) {
            int random = (int)(Math.random()*4);
            this.macronInflationActivated = random==3;
        }
        if (macronInflationActivated) {
            env.getSettings().inflation();
        }
        // Augmentation de la difficulter des waves
        if (waveIndex >= 10 && waveIndex%5 == 0) {
            env.getSettings().multiplierStatsEnemy(1.3);
        }
    }

    public void waveLoop() {
        if (waveCptLap == 0) {
            env.startingWaveAnimation(); //Animation de début de jeu
        }
        else if (waveCptLap > delayBetweenSpawns*30*4 &&
                waveCptLap % (delayBetweenSpawns*30) == 0 && nbEnemies>0) {
            int[] spawn;
            if (waveIndex <= 1){
                spawn = spawnPointsStartGame;
            }
            else {
                int random = (int)(Math.random() * spawnPoints.length);
                spawn = spawnPoints[random];
            }
            int line = spawn[0];
            int col = spawn[1];

            Enemy e = createNewEnemy(col, line);
            env.addEnemy(e);
            nbEnemies--;
        }
        else if (nbEnemies==0 && waveCptLap % (delayBetweenSpawns*30*2) == 0) {
            if (env.getEnemies().isEmpty()) {
                if (isMacronInflationActivated()) {
                    env.getSettings().disinflation();
                }
                env.nextWave();
            }
        }
        this.waveCptLap++;

    }

    public int getNbEnemies() {
        return nbEnemies;
    }

    public int getDelayBetweenSpawns() {
        return delayBetweenSpawns;
    }

    public boolean isMacronInflationActivated() {
        return macronInflationActivated;
    }

    public Enemy createNewEnemy(int col, int line) {
        return EnemyFactory.createForWave(waveIndex, env, env.getNextId(), col, line);
    }

}
