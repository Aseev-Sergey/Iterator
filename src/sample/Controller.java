package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    public ImageCollection imgs;
    public Iterator iter_main;
    public Timeline timeline =new Timeline();
    public ImageView showBox;
    public Label currentDir;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        imgs = new ImageCollection("schemes","Slide");
        iter_main = imgs.getIterator();
    }

    public void clickPlay(ActionEvent actionEvent) {
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.getKeyFrames().add(new KeyFrame(new Duration(1000 ), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {

                if( iter_main.hasNext()) {
                    Image name = (Image) iter_main.next();
                    showBox.setImage(name);
                    currentDir.setText(name.getUrl());
                }
            }
        }));


        timeline.play();
    }

    public void clickStop(ActionEvent actionEvent) {
        timeline.stop();
    }


    public void clickNext(ActionEvent actionEvent) {
        timeline.stop();
        if (iter_main.hasNext()) {
            Image name = (Image) iter_main.next();
            showBox.setImage(name);
        }
    }

    public void clickPrev(ActionEvent actionEvent) {
        timeline.stop();
        if (iter_main.hasNext()) {
            Image name = (Image) iter_main.back();
            showBox.setImage(name);
        }
    }


    public void clickClose(ActionEvent actionEvent) {
        timeline.stop();
        Platform.exit();
    }
}
