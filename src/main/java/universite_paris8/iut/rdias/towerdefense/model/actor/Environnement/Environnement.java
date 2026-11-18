package universite_paris8.iut.rdias.towerdefense.model.actor.Environnement;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import universite_paris8.iut.rdias.towerdefense.model.*;
import universite_paris8.iut.rdias.towerdefense.model.Factory.StaticActorFactory;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.*;
import universite_paris8.iut.rdias.towerdefense.model.actor.StaticActor;
import universite_paris8.iut.rdias.towerdefense.model.actor.enemy.Viking;

import java.util.ArrayList;

/*La classe Environnement représente le monde du jeu.
 * L'environnement gère toutes les entités du jeu,
 * leur ajout, leur suppression et leur cycle d'action.
 * La méthode loop() appelle act() sur toutes les entités
 * et nettoie les entités mortes à la fin de chaque tick.
 * Un environnement a comme attribut:
 * -Le terrain (Ground) sur lequel se déroule le jeu
 * -Le château (Castle) à protéger
 * -Les listes observables des ennemis, chevaliers, tours et effets
 * -Les listes des acteurs et effets mourants à supprimer
 * -Le compteur de spawn et le délai entre les spawns
 * -Les coordonnées de spawn des ennemis
 * -L'ecu total du joueur (argent)
 * -Les paramètres du jeu (Settings)
 * -L'index de la vague actuelle
 * -La vague actuelle (Wave)
 *
 */

public class Environnement {
    private static int id = 1;
    private Ground ground;
    private Castle castle;
    private int time;
    private ObservableList<Viking> vikings;
    private ObservableList<Knight> knights;
    private ObservableList<StaticActor> staticActors;
    private ObservableList<Projectile> effects;
    private ObservableList<Animation> animations;
    private ArrayList<Actor> actorsDying;
    private IntegerProperty balance;
    private Settings settings;
    private IntegerProperty waveIndex;
    private Wave wave;
    private StaticActorFactory staticActorFactory;



    public Environnement(Ground ground) {
        this.ground = ground;
        this.castle = new Castle(this, 0, 500);
        this.time = 0;
        this.vikings = FXCollections.observableArrayList();
        this.knights = FXCollections.observableArrayList();
        this.staticActors = FXCollections.observableArrayList();
        this.effects = FXCollections.observableArrayList();
        this.animations = FXCollections.observableArrayList();
        this.actorsDying = new ArrayList<>();
        this.balance = new SimpleIntegerProperty(800);//350);
        this.waveIndex = new SimpleIntegerProperty(10);
        this.settings = new Settings();
        this.wave = new Wave(this, waveIndex.get());
        this.staticActorFactory = new StaticActorFactory(this);
    }

    public void addViking(Viking v){this.vikings.add(v);}
    public void delViking(Viking v){this.vikings.remove(v);}
    public void addKnight(Knight k){this.knights.add(k);}
    public void delKnight(Knight k){this.knights.remove(k);}
    public void addTower(StaticActor t) {
        if (t instanceof Palissade) {
            ((Palissade) t).place();
        }
        this.staticActors.add(t);
    }

    private long countSameType(StaticActor t) { //Permet de compter le nombre de tours pour gérer le maximum de Bramble et Palissade
        return staticActors.stream().filter(other -> other.getClass() == t.getClass()).count();
    }

    public void delTower(StaticActor t){this.staticActors.remove(t);}
    public void addDyingActor(Actor a) {
        this.actorsDying.add(a);
    }
    public void addEffect(Projectile e) {
        effects.add(e);
    }
    public void delEffect(Projectile e) {
        effects.remove(e);
    }

    public void createTower(int towerType, int lin, int col) {
        StaticActor tower = null;
        switch (towerType) {
            case 1 -> tower = (StaticActor) staticActorFactory.create(StaticActorFactory.Type.ARCHER, lin, col);
            case 2 -> tower = (StaticActor) staticActorFactory.create(StaticActorFactory.Type.BARRACK, lin, col);
            case 3 -> tower = (StaticActor) staticActorFactory.create(StaticActorFactory.Type.BRAMBLE, lin, col);
            case 4 -> tower = (StaticActor) staticActorFactory.create(StaticActorFactory.Type.PALISSADE, lin, col);
            case 5 -> tower = (StaticActor) staticActorFactory.create(StaticActorFactory.Type.SORCERER_TOWER, lin, col);
            case 6 -> tower = (StaticActor) staticActorFactory.create(StaticActorFactory.Type.BALLISTA, lin, col);
        }
        if (tower != null
                && balance.get() >= tower.getCost()
                && tower.canBePlaced(lin, col)
                && countSameType(tower) <= tower.maxAllowed()) {
            balance.setValue(balance.getValue() - tower.getCost());
            addTower(tower);
        }
    }


    public void addAnimation(Animation a) {
        animations.add(a);
    }
    public void delAnimation(Animation a) {
        animations.remove(a);
    }

    public void earn(int gain) {
        balance.setValue(balance.getValue() + gain);
    }
    public IntegerProperty getBalanceProperty() {
        return balance;
    }

    public void loop() {
        wave.waveLoop();
        for (Knight k : knights) {
            k.act();
        }
        for (StaticActor t : staticActors) {
            t.act();
        }
        for (Viking e : vikings) {
            e.act();
        }
        for (Projectile ef : effects) {
            ef.act();
        }

        for (Actor a : actorsDying) {
            if (a instanceof Viking) {
                delViking((Viking) a);
            }
            else if (a instanceof StaticActor) {
                delTower((StaticActor) a);
            }
            else if (a instanceof Knight) {
                delKnight((Knight) a);
            }
            else if (a instanceof Projectile) {
                delEffect((Projectile)a);
            }
        }
        actorsDying.clear();
        time++;
    }

    public ObservableList<Viking> getEnemies() {
        return vikings;
    }
    public ObservableList<Knight> getKnights() {
        return knights;
    }
    public ObservableList<StaticActor> getTowers() {
        return staticActors;
    }
    public ObservableList<Projectile> getEffects() {
        return effects;
    }
    public ObservableList<Animation> getAnimations() {
        return animations;
    }
    public Castle getCastle() {
        return castle;
    }

    public Ground getGround() {
        return ground;
    }
    public int getNextId() {
        incrementId();
        return id;
    }
    public Settings getSettings() {
        return settings;
    }
    public void incrementId() {
        id++;
    }
    public void nextWave() {
        waveIndex.set(waveIndex.get() + 1);
        wave = new Wave(this, waveIndex.get());
        vikings.clear();
    }
    public void startingWaveAnimation() {
        Animation nAnimation = new Animation(waveIndex.get()==1, wave.isMacronInflationActivated());
        addAnimation(nAnimation);
    }

    public IntegerProperty getWaveIndexProperty() {
        return waveIndex;
    }
    public int getCurrentTime() {
        return time;
    }

    public void sellTower(StaticActor t) {
        t.sell();
    }

    public boolean upgradeTower(StaticActor t) {
        if (!t.canBeUpgraded()) return false;
        if (balance.get() < t.getUpgradeCost()) return false;
        balance.setValue(balance.getValue() - t.getUpgradeCost());
        t.upgrade();
        return true;
    }
}
