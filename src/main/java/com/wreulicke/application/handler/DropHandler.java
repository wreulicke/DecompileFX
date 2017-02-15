package com.wreulicke.application.handler;

import java.io.File;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.wreulicke.decompiler.Decompiler;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

@ApplicationScoped
@DragDrop
public class DropHandler implements EventHandler<DragEvent> {
  @Inject
  Decompiler decompiler;

  @Override
  public void handle(DragEvent event) {
    Dragboard db = event.getDragboard();
    if (db.hasFiles()) {
      decompiler.decompile(db.getFiles()
        .stream()
        .map(File::toPath));
    }
    event.setDropCompleted(true);
    event.consume();
  }
}
