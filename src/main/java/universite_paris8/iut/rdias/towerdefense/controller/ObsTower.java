package universite_paris8.iut.rdias.towerdefense.controller;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rdias.towerdefense.model.actor.StaticActor;
import universite_paris8.iut.rdias.towerdefense.view.TowerView;
import java.util.ArrayList;
import java.util.List;

/*
 * Observer pour la gestion des tours à l'écran.
 *
 * Écoute les changements dans la liste des tours et synchronise l'affichage.
 * Ajoute/supprime les tours visuelles et gère leur animation.
 */

public class ObsTower implements ListChangeListener<StaticActor> {
    private ArrayList<TowerView> towerSprite;
    private Pane grid;

    public ObsTower(Pane oGrid) {
        this.grid = oGrid;
        this.towerSprite = new ArrayList<>();
    }

    @Override
    public void onChanged(Change<? extends StaticActor> change) {
        while (change.next()) {
            List<StaticActor> ajout = (List<StaticActor>) change.getAddedSubList();
            List<StaticActor> retirer = (List<StaticActor>) change.getRemoved();

            for (StaticActor t : ajout) {
                TowerView tv = new TowerView(t);
                grid.getChildren().add(tv.getPaneViewContainer());
                towerSprite.add(tv);
            }
            for (StaticActor t : retirer) {
                grid.getChildren().remove(grid.lookup("#" + "t" + t.getId()));
            }
        }
    }

    public void animate() {
        for (TowerView t : towerSprite) {
            try {
                t.switchImage();
            } catch (Exception ex) {
                System.out.println("  EXCEPTION: " + ex.getMessage());
            }

        }
    }

    public ArrayList<TowerView> getTowerSprite() {
        return towerSprite;
    }
}
