package com.wreulicke.decompiler.internal;

import java.nio.file.Path;

import org.jboss.windup.decompiler.procyon.ProcyonDecompiler;

import com.google.common.io.Files;
import com.wreulicke.decompiler.FileDecompiler;

public class ClassDecompiler implements FileDecompiler {

  @Override
  public boolean isSupportedFile(Path path) {
    String extension = Files.getFileExtension(path.getFileName()
      .toString());
    switch (extension) {
      case "class":
        return true;
      default:
        return false;
    }
  }

  @Override
  public void decompile(Path path) {
    ProcyonDecompiler decompiler = new ProcyonDecompiler();
    decompiler.decompileClassFile(path.getParent(), path, path.getParent()
      .resolve("decompiled"));
  }


}
