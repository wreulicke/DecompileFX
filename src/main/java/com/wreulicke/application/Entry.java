package com.wreulicke.application;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javafx.application.Application;
import javafx.stage.Stage;

public class Entry extends Application {

  public static void main(String[] args) throws Exception {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    Weld weld = new Weld();;
    WeldContainer container = weld.initialize();
    try {
      DecompilerApplication application = container.instance()
        .select(DecompilerApplication.class)
        .get();
      application.start(stage);

    } finally {
      Runtime.getRuntime()
        .addShutdownHook(new Thread() {
          @Override
          public void run() {
            weld.shutdown();
          }
        });

    }
  }
}
