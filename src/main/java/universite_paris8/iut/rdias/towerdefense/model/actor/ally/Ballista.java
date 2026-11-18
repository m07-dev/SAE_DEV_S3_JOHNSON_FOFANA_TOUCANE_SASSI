package universite_paris8.iut.rdias.towerdefense.model.actor.ally;

import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.StaticActor;
import universite_paris8.iut.rdias.towerdefense.strategy.AttackStrategy;
import universite_paris8.iut.rdias.towerdefense.strategy.FindTargetStrategy;

/*La sous-classe Ballista qui hérite de Tower.
 * Cette tour peut attaquer jusqu'à deux ennemis
 * simultanément si deux cibles se trouvent dans sa portée.
 * Un ballista a comme attribut spécifique:
 * -un tableau de cibles de taille deux
 */

public class Ballista extends StaticActor {

    public Ballista(Environnement env, int aId, int aHp, int maxHp, int aDmg, double aRange,
                  double aLin, double aCol, Actor target, AttackStrategy attackStrategy, FindTargetStrategy findTargetStrategy,
                  int coolDownDuration, int cost, int level) {
        super(env,
                aId,
                aHp,
                maxHp,
                aDmg,
                aRange,
                aLin,
                aCol,
                target,
                attackStrategy,
                findTargetStrategy,
                coolDownDuration,
                cost,
                level
        );
    }public Ballista(Environnement env, int aId, int aHp, int maxHp, int aDmg, double aRange,
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
                level
        );
    }
}
