package com.wreulicke.application.handler;

import javax.enterprise.context.ApplicationScoped;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

@ApplicationScoped
@DragOver
public class OverHandler implements EventHandler<DragEvent> {
  @Override
  public void handle(DragEvent event) {
    Dragboard db = event.getDragboard();
    if (db.hasFiles()) {
      event.acceptTransferModes(TransferMode.COPY);
    }
    else {
      event.consume();
    }
  }
}
