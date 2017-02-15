package com.wreulicke.decompiler.internal;

import java.util.List;

import org.jboss.windup.decompiler.api.DecompilationListener;

public final class InfomationEmitListener implements DecompilationListener {
  @Override
  public void fileDecompiled(List<String> sourceClassPaths, String outputPath) {
    sourceClassPaths.forEach((path) -> {
      System.out.println(String.format("decompiled:%s output:%s", path, outputPath));
    });
  }

  @Override
  public void decompilationProcessComplete() {}

  @Override
  public void decompilationFailed(List<String> sourceClassPaths, String message) {
    sourceClassPaths.forEach((path) -> {
      System.out.println(String.format("failed decompile:%s message:%s", path, message));
    });

  }
}
