package com.wreulicke.application;

import javax.inject.Inject;

import com.wreulicke.application.handler.DragDrop;
import com.wreulicke.application.handler.DragOver;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;

public class DecompilerApplication {

  @Inject
  @DragOver
  EventHandler<DragEvent> dragHandler;

  @Inject
  @DragDrop
  EventHandler<DragEvent> dropHandler;

  public void start(Stage stage) {
    Group root = new Group();
    Scene scene = new Scene(root, 551, 400);
    scene.setOnDragOver(dragHandler);
    scene.setOnDragDropped(dropHandler);

    stage.setScene(scene);
    stage.show();
  }

}
