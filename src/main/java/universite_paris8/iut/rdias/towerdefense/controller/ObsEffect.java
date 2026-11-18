package universite_paris8.iut.rdias.towerdefense.controller;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rdias.towerdefense.model.Projectile;
import universite_paris8.iut.rdias.towerdefense.model.ZoneSpell;
import universite_paris8.iut.rdias.towerdefense.view.EffectView;
import java.util.ArrayList;
import java.util.List;

/*
 * Observer pour la gestion des effets (projectiles et sorts de zone).
 *
 * Écoute les changements dans la liste des effets et synchronise l'affichage.
 * Ajoute/supprime les effets visuels et gère l'animation de destruction des sorts de zone.
 */

public class ObsEffect implements ListChangeListener<Projectile> {
    private ArrayList<EffectView> effectSprite;
    private Pane grid;

    public ObsEffect(Pane oGrid) {
        this.grid = oGrid;
        this.effectSprite = new ArrayList<>();
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Projectile> change) {
        while (change.next()) {
            List<Projectile> ajout = (List<Projectile>) change.getAddedSubList();
            List<Projectile> retirer = (List<Projectile>) change.getRemoved();

            for (Projectile e : ajout) {
                EffectView s = new EffectView(e);
                grid.getChildren().add(s.getPaneViewContainer());
                effectSprite.add(s);
            }
            for (Projectile e : retirer) {
                if (e instanceof ZoneSpell) {
                    for (EffectView ef : effectSprite) {
                        if (ef.getId() == e.getId()) {
                            ef.blow();
                        }
                    }
                }
                else {
                    grid.getChildren().remove(grid.lookup("#" + "e" + e.getId()));
                }

            }
        }
    }

    public ArrayList<EffectView> getEffectSprite() {
        return effectSprite;
    }
}
