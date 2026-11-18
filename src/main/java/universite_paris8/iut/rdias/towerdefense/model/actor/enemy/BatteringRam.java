package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.strategy.AttackStrategy;
import universite_paris8.iut.rdias.towerdefense.strategy.FindTargetStrategy;

/*La sous-classe BatteringRam qui hérite de Enemy.
 * Cet ennemi attaque au corps à corps et cible
 * uniquement les Palissade. Il est le seul ennemi
 * capable de détruire les palissades. Sa portée
 * d'attaque est de 0.5. Il utilise le BFS pour
 * trouver le chemin le plus proche vers sa cible.
 * Un ramwarrior a comme attribut spécifique:
 * -sa cible (target) qu'il doit attaquer
 */

public class BatteringRam extends Viking {

    public BatteringRam(Environnement env, int id, int hp, int maxHp, int dmg, double range, double lin, double col,
                        Actor target, int coolDown, double speed, int deathValue) {
        super(env,
                id,
                hp,
                maxHp,
                dmg,
                range,
                lin,
                col,
                target,
                null,
                null,
                coolDown,
                speed,
                deathValue
        );
    }
}
