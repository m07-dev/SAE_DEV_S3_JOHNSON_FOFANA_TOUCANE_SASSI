package universite_paris8.iut.rdias.towerdefense.model.actor.Environnement;

import universite_paris8.iut.rdias.towerdefense.model.Factory.VikingFactory;
import universite_paris8.iut.rdias.towerdefense.model.actor.enemy.*;

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
    private VikingFactory vikingFactory;

    public Wave(Environnement env, int waveIndex) {
        this.env = env;
        this.waveIndex = waveIndex;
        this.nbEnemies = (int)(10 + 20 * Math.log(waveIndex + 1));
        this.delayBetweenSpawns = 3;
        this.waveCptLap = 0;
        this.vikingFactory = new VikingFactory(env);

        if (waveIndex > 4) {
            int random = (int)(Math.random()*4);
            this.macronInflationActivated = random==3;
        }
        if (macronInflationActivated) {
            env.getSettings().inflation();
        }

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
            int line = spawn[1];
            int col = spawn[0];

            Viking v = createNewEnemy(line, col);
            env.addViking(v);
            env.incrementId();
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

    public Viking createNewEnemy(int col, int lin) {
        Viking viking = null;
        int radomSelectEnemy = (int) (Math.random() * 100);

        if (waveIndex <= 1) {
            viking = (Viking) vikingFactory.create(VikingFactory.Type.BASIC_VIKING, lin, col);
        }
        else if (waveIndex <= 3) {
            if (radomSelectEnemy < 30) {
                viking = (Viking) vikingFactory.create(VikingFactory.Type.BERSERKER, lin, col);
            }
            else {
                viking = (Viking) vikingFactory.create(VikingFactory.Type.BASIC_VIKING, lin, col);
            }
        }
        else if (waveIndex <= 5) {
            if (radomSelectEnemy < 30) {
                viking = (Viking) vikingFactory.create(VikingFactory.Type.BERSERKER, lin, col);
            }
            else if (radomSelectEnemy < 50) {
                viking = (Viking) vikingFactory.create(VikingFactory.Type.ARCHER_VIKING, lin, col);
            }
            else {
                viking = (Viking) vikingFactory.create(VikingFactory.Type.BASIC_VIKING, lin, col);
            }
        }
        else if (waveIndex <= 8) {
            if (radomSelectEnemy < 10) {
                viking = (Viking) vikingFactory.create(VikingFactory.Type.SHIELD_VIKING, lin, col);
            }
            else if (radomSelectEnemy < 20) {
                viking = (Viking) vikingFactory.create(VikingFactory.Type.ARCHER_VIKING, lin, col);
            }
            else if (radomSelectEnemy < 30) {
                viking = (Viking) vikingFactory.create(VikingFactory.Type.BERSERKER, lin, col);
            }
            else {
                viking = (Viking) vikingFactory.create(VikingFactory.Type.BASIC_VIKING, lin, col);
            }
        }
        else if (waveIndex <= 10) {
            if (radomSelectEnemy < 10) {
                viking = (Viking) vikingFactory.create(VikingFactory.Type.BATTERING_RAM, lin, col);
            }
            else if (radomSelectEnemy < 15) {
                viking = (Viking) vikingFactory.create(VikingFactory.Type.SHIELD_VIKING, lin, col);
            }
            else if (radomSelectEnemy < 20) {
                viking = (Viking) vikingFactory.create(VikingFactory.Type.ARCHER_VIKING, lin, col);
            }
            else if (radomSelectEnemy < 25) {
                viking = (Viking) vikingFactory.create(VikingFactory.Type.BERSERKER, lin, col);
            }
            else {
                viking = (Viking) vikingFactory.create(VikingFactory.Type.BASIC_VIKING, lin, col);
            }
        }
        return viking;
    }

}
