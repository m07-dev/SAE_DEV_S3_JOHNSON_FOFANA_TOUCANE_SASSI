package universite_paris8.iut.rdias.towerdefense.controller;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

import universite_paris8.iut.rdias.towerdefense.model.actor.MovingActor;
import universite_paris8.iut.rdias.towerdefense.model.actor.ally.Knight;
import universite_paris8.iut.rdias.towerdefense.view.MovingActorView;

/*
 * Observer pour la gestion des chevaliers alliés à l'écran.
 *
 * Écoute les changements dans la liste des chevaliers et synchronise l'affichage.
 * Ajoute/supprime les chevaliers visuels et gère leur animation.
 */

public class ObsKnight implements ListChangeListener<Knight> {
    private ArrayList<MovingActorView> knightSprite;
    private Pane grid;

    public ObsKnight(Pane oGrid) {
        this.grid = oGrid;
        this.knightSprite = new ArrayList<>();
    }

    public void animate() {
        for (MovingActorView s : knightSprite) {
            s.switchImage();
        }
    }

    @Override
    public void onChanged(Change<? extends Knight> change) {
        while (change.next()) {
            List<Knight> ajout = (List<Knight>) change.getAddedSubList();
            List<Knight> retirer = (List<Knight>) change.getRemoved();

            for (Knight k : ajout) {
                MovingActorView s = new MovingActorView(k);
                grid.getChildren().add(s.getPaneViewContainer());
                knightSprite.add(s);
            }
            for (Knight k : retirer) {
                grid.getChildren().remove(grid.lookup("#" + "k" + k.getId()));
            }
        }
    }

    public ArrayList<MovingActorView> getKnightSprite() {
        return knightSprite;
    }


}
