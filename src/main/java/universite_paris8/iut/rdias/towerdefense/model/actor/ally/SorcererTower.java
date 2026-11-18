package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.ZoneSpell;
import universite_paris8.iut.rdias.towerdefense.model.actor.StaticActor;
import universite_paris8.iut.rdias.towerdefense.strategy.AttackStrategy;
import universite_paris8.iut.rdias.towerdefense.strategy.FindTargetStrategy;

/*La sous-classe SorcererTower qui hérite de Tower.
 * Cette tour attaque à distance avec des boules de feu
 * qui explosent à l'impact, infligeant des dégâts à tous
 * les ennemis se trouvant dans le rayon d'explosion.
 * Une tour sorcier a comme attribut spécifique:
 * -le rayon de l'explosion de ses boules de feu
 */

public class SorcererTower extends StaticActor {
    public SorcererTower(Environnement env, int aId, int aHp, int maxHp, int aDmg, double aRange,
                  double aLin, double aCol, Actor target, int coolDownDuration, int cost, int level) {
        super(env,
                aId,
                aHp,
                maxHp,
                aDmg,
                aRange,
                aLin,
                aCol,
                target,
                null,
                null,
                coolDownDuration,
                cost,
                level);
    }
}
