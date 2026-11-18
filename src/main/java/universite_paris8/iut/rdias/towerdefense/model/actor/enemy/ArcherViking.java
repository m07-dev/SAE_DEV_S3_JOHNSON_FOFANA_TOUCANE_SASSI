package universite_paris8.iut.rdias.towerdefense.model.actor.enemy;

import universite_paris8.iut.rdias.towerdefense.model.actor.Environnement.Environnement;
import universite_paris8.iut.rdias.towerdefense.model.actor.Actor;
import universite_paris8.iut.rdias.towerdefense.strategy.AttackStrategy;
import universite_paris8.iut.rdias.towerdefense.strategy.FindTargetStrategy;

/*La sous-classe ArcherViking qui hérite de Enemy.
 * Cet ennemi attaque à distance avec des flèches.
 * Il peut cibler les chevaliers (Knight) ainsi que
 * les tours, à l'exception des Palissade et Bramble.
 * Il utilise le BFS pour se déplacer vers les chevaliers
 * et le calcul de distance de pythagore pour les tours.
 * Un archer viking a comme attribut spécifique:
 * -sa cible (target) qu'il doit attaquer
 */

public class ArcherViking extends Viking {
    public ArcherViking(Environnement env, int id, int hp, int maxHp, int dmg, double range, double lin, double col,
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
