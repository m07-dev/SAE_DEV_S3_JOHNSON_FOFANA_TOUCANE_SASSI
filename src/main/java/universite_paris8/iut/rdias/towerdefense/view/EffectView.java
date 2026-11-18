package universite_paris8.iut.rdias.towerdefense.view;

import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import universite_paris8.iut.rdias.towerdefense.model.Projectile;
import universite_paris8.iut.rdias.towerdefense.model.Arrow;
import universite_paris8.iut.rdias.towerdefense.model.ZoneSpell;

/*
 * Gère l'affichage et l'animation des effets (projectiles et sorts de zone).
 *
 * Affiche les projectiles (flèches) et les sorts de zone (boules de feu).
 * Anime la destruction des sorts de zone avec un effet de fondu.
 * Synchronise la position et la rotation avec le modèle.
 */

public class EffectView {
    private static String pathBegin = "/universite_paris8/iut/rdias/towerdefense/sprite/projectile";
    private static Image spriteArrow = new Image(GroundView.class.getResourceAsStream(pathBegin + "/sprite_arrow.png"));
    private static Image spriteFireBall = new Image(GroundView.class.getResourceAsStream(pathBegin + "/sprite_fireball.png"));
    private static Image spriteFireBallBlowing = new Image(GroundView.class.getResourceAsStream(pathBegin + "/sprite_blow.png"));
    private ImageView image;
    private Pane paneViewContainer;
    private Projectile effect;
    private int id;

    public EffectView(Projectile eEffect) {
        this.effect = eEffect;
        this.paneViewContainer = new Pane();
        if (effect instanceof ZoneSpell) {
            this.image = new ImageView(spriteFireBall);
        }
        else if (effect instanceof Arrow) {
            this.image = new ImageView(spriteArrow);
        }
        this.paneViewContainer.setId("e" + effect.getId());
        this.id = effect.getId();
        this.image.layoutXProperty().bind(effect.getColProperty().multiply(16).add(-16/2));
        this.image.layoutYProperty().bind(effect.getLinProperty().multiply(16).add(-16/2));
        this.image.rotateProperty().bind(effect.angleProperty().add(0));
        this.paneViewContainer.getChildren().add(this.image);
    }

    public ImageView getImage() {
        return image;
    }

    public void blow() {
        if (effect instanceof ZoneSpell) {
            this.image.setImage(spriteFireBallBlowing);
            this.image.toBack();
            this.image.setFitHeight((effect.getRange()/2)*2*16);
            this.image.setFitWidth((effect.getRange()/2)*2*16);
            double x = this.image.getLayoutX();
            double y = this.image.getLayoutY();
            this.image.layoutXProperty().unbind();
            this.image.layoutYProperty().unbind();
            this.image.setLayoutX(x - (((effect.getRange()/2)*2*16)/2));
            this.image.setLayoutY(y - (((effect.getRange()/2)*2*16)/2));

            FadeTransition fade = new FadeTransition(Duration.seconds(0.5), image);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished(event -> {
                if (paneViewContainer.getParent() != null) {
                    ((javafx.scene.layout.Pane) paneViewContainer.getParent()).getChildren().remove(paneViewContainer);
                }
            });
            fade.play();
        }
    }

    public int getId() {
        return id;
    }
    public Pane getPaneViewContainer() {
        return paneViewContainer;
    }
}
