package universite_paris8.iut.rdias.towerdefense.model.Factory;

import universite_paris8.iut.rdias.towerdefense.model.Arrow;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.strategy.AttackMelee;

public class ArrowFactory implements ActorFactory {
    private Environnement env;
    private Actor attacker;

    public ArrowFactory(Environnement env, Actor attacker) {
        this.env = env;
        this.attacker = attacker;
    }

    @Override
    public Actor create() {
        Arrow p = new Arrow(env, env.getNextId(), 0, 0, attacker.getDmg(), 0.2,
                attacker.getLin(), attacker.getCol(), attacker.getTarget(),
                0,  0.2);
        p.setAttackStrategy(new AttackMelee(p));
        return p;
    }

    public void spawn() {
        Arrow arrow = (Arrow)create();
        if (arrow != null) {
            env.addEffect(arrow);
        }
    }
}
