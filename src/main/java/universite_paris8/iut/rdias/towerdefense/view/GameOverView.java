package universite_paris8.iut.rdias.towerdefense.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameOverView {
    private final Pane actorsArea;

    public GameOverView(Pane actorsArea) {
        this.actorsArea = actorsArea;
    }

    public void show(int wave) {
        String message = getGameOverMessage(wave);

        VBox gameOverPane = new VBox(20);
        gameOverPane.setAlignment(Pos.CENTER);
        gameOverPane.setStyle("-fx-background-color: rgba(0,0,0,0.7);");

        gameOverPane.prefWidthProperty().bind(actorsArea.widthProperty());
        gameOverPane.prefHeightProperty().bind(actorsArea.heightProperty());

        Label gameOverLabel = new Label("GAME OVER");
        gameOverLabel.getStyleClass().add("gameOver");

        Label waveReached = new Label("Vague atteinte : " + wave);
        waveReached.getStyleClass().add("gameOverWave");

        Label messageLabel = new Label(message);
        messageLabel.getStyleClass().add("gameOverMessage");

        gameOverPane.getChildren().addAll(gameOverLabel, waveReached, messageLabel);
        actorsArea.getChildren().add(gameOverPane);
    }

    private String getGameOverMessage(int wave) {
        if (wave <= 2) {
            return "Tu fais honte au roi. Au bûcher !!!";
        } else if (wave <= 5) {
            return "Pff. T'es mauvais ! ";
        } else if (wave <= 10) {
            return "Mmmmmh. Va lire l'art de la Guerre pour t'améliorer.";
        } else if (wave <= 15) {
            return "Bien ! Mais tu dois améliorer ta tactique.";
        } else {
            return "Tu as été promu en tant que tacticien de l'armée du Roi. Chapeau l'artiste.";
        }
    }
}