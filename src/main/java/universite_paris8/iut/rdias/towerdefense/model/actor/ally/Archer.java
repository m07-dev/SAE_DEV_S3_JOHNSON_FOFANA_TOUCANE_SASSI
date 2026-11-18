package universite_paris8.iut.rdias.towerdefense.model.actor.ally;


import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.StaticActor;
import universite_paris8.iut.rdias.towerdefense.strategy.AttackStrategy;
import universite_paris8.iut.rdias.towerdefense.strategy.FindTargetStrategy;

/*La sous-classe de la tour archer qui hérite de Tower
 * est un allié que nous posons et qui attaque de loin
 * avec des fléches.*/


public class Archer extends StaticActor {
    public Archer(Environnement env, int aId, int aHp, int maxHp, int aDmg, double aRange,
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
                level);

    }
    public Archer(Environnement env, int aId, int aHp, int maxHp, int aDmg, double aRange,
                  double aLin, double aCol, Actor target,
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
                null,
                null,
                coolDownDuration,
                cost,
                level);

    }
}
