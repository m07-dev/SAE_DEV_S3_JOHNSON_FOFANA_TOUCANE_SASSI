package universite_paris8.iut.rdias.towerdefense.controller;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rdias.towerdefense.model.actor.enemy.Viking;
import universite_paris8.iut.rdias.towerdefense.view.MovingActorView;

import java.util.ArrayList;
import java.util.List;

/*
 * Observer pour la gestion des ennemis à l'écran.
 *
 * Écoute les changements dans la liste des ennemis et synchronise l'affichage.
 * Ajoute/supprime les ennemis visuels et gère l'animation de mort avec fondu progressif.
 */

public class ObsEnemy implements ListChangeListener<Viking> {
    private ArrayList<MovingActorView> enemiesSprite;
    private Pane grid;

    public ObsEnemy(Pane oGrid) {
        this.grid = oGrid;
        this.enemiesSprite = new ArrayList<>();
    }

    public void animate() {
        for (MovingActorView s : enemiesSprite) {
            try {
                s.switchImage();
            } catch (Exception ex) {
                System.out.println("  EXCEPTION: " + ex.getMessage());
            }

        }
    }

    @Override
    public void onChanged(Change<? extends Viking> change) {
        while (change.next()) {
            List<Viking> ajout = (List<Viking>) change.getAddedSubList();
            List<Viking> retirer = (List<Viking>) change.getRemoved();

            for (Viking v : ajout) {
                MovingActorView s = new MovingActorView(v);
                grid.getChildren().add(s.getPaneViewContainer());
                enemiesSprite.add(s);
            }
            for (Viking v : retirer) {
                for (MovingActorView sV : enemiesSprite) {
                    if (sV.getId() == v.getId()) {
                        sV.blow();
                    }
                }
            }
        }
    }

    public ArrayList<MovingActorView> getEnemiesSprite() {
        return enemiesSprite;
    }


}
