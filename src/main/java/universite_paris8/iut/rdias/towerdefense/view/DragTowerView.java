package universite_paris8.iut.rdias.towerdefense.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DragTowerView {
    private static final int TILE_SIZE = 16;

    private final Pane actorsArea;
    private ImageView ghostImage;
    private Circle rangeCircle;

    public DragTowerView(Pane actorsArea) {
        this.actorsArea = actorsArea;
    }

    public void show(Image sprite, double range) {
        hide();

        ghostImage = new ImageView(sprite);
        ghostImage.setFitWidth(32);
        ghostImage.setFitHeight(32);
        ghostImage.setOpacity(0.5);
        ghostImage.setMouseTransparent(true);

        rangeCircle = new Circle(range);
        rangeCircle.setFill(Color.TRANSPARENT);
        rangeCircle.setStroke(Color.BLACK);
        rangeCircle.setStrokeWidth(1.5);
        rangeCircle.setOpacity(0.6);
        rangeCircle.setMouseTransparent(true);

        actorsArea.getChildren().addAll(rangeCircle, ghostImage);
    }

    public void moveTo(int col, int line) {
        if (ghostImage == null) return;

        double cx = col * TILE_SIZE + TILE_SIZE / 2;
        double cy = line * TILE_SIZE + TILE_SIZE / 2;

        ghostImage.setLayoutX(cx - 16);
        ghostImage.setLayoutY(cy - 16);
        rangeCircle.setCenterX(cx);
        rangeCircle.setCenterY(cy);
    }

    public void hide() {
        if (ghostImage != null) {
            actorsArea.getChildren().remove(ghostImage);
            ghostImage = null;
        }
        if (rangeCircle != null) {
            actorsArea.getChildren().remove(rangeCircle);
            rangeCircle = null;
        }
    }
}
