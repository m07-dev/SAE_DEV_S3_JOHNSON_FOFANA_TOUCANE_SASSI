package universite_paris8.iut.rdias.towerdefense.model.Factory;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;

public interface ActorFactory {
    public Actor create();
    public void spawn();
}
