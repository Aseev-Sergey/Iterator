package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
    public ComboBox<String> directory;
    public String imgDir;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        directory.getItems().clear();
        directory.getItems().addAll("nodes", "schemes");
        imgDir = directory.getItems().get(0);
        directory.setValue(imgDir);
        imgs = new ImageCollection(imgDir,"Slide");
        iter_main = imgs.getIterator();
    }

    public void clickPlay(ActionEvent actionEvent) {
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.getKeyFrames().add(new KeyFrame(new Duration(1000 ), new EventHandler<ActionEvent>() {  //Формула значения скорости прокрутки анимации в миллисекундах.
            @Override
            public void handle(ActionEvent t) {

                if( iter_main.hasNext()) {
                    Image name = (Image) iter_main.next();
                    showBox.setImage(name);
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


    public void chooseOption(ActionEvent actionEvent) {
        timeline.stop();
        imgDir = directory.getValue();
        imgs = new ImageCollection(imgDir,"Slide");
        iter_main = imgs.getIterator();
    }
}
