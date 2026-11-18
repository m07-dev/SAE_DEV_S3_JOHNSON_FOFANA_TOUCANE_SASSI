package universite_paris8.iut.rdias.towerdefense.model.Factory;

import universite_paris8.iut.rdias.towerdefense.model.Arrow;
import universite_paris8.iut.rdias.towerdefense.model.ZoneSpell;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.strategy.AttackZone;

public class ZoneSpellFactory implements ActorFactory {
    private Environnement env;
    private Actor attacker;

    public ZoneSpellFactory(Environnement env, Actor attacker) {
        this.env = env;
        this.attacker = attacker;
    }

    @Override
    public Actor create() {
        ZoneSpell z = new ZoneSpell(env, env.getNextId(), 0, 0, attacker.getDmg(), attacker.getRange()/2,
                attacker.getLin(), attacker.getCol(), attacker.getTarget(),
                0,  0.2);
        z.setAttackStrategy(new AttackZone(z, env.getEnemies()));
        return z;
    }

    public void spawn() {
        Arrow zoneSpell = (Arrow)create();
        if (zoneSpell != null) {
            env.addEffect(zoneSpell);
        }
    }
}
