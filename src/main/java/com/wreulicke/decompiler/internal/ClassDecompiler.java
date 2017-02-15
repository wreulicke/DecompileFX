package com.wreulicke.decompiler.internal;

import java.nio.file.Path;

import org.jboss.windup.decompiler.procyon.ProcyonDecompiler;

import com.google.common.io.Files;
import com.wreulicke.decompiler.DecompilationException;
import com.wreulicke.decompiler.FileDecompiler;

public class ClassDecompiler implements FileDecompiler {

  @Override
  public boolean isSupportedFile(Path path) {
    Path refPath = path.getFileName();
    if (refPath == null) {
      return false;
    }
    String fileName = refPath.toString();
    String extension = Files.getFileExtension(fileName);
    switch (extension) {
      case "class":
        return true;
      default:
        return false;
    }
  }

  @Override
  public void decompile(Path path) throws DecompilationException {
    ProcyonDecompiler decompiler = new ProcyonDecompiler();
    Path parent = path.getParent();
    if (parent == null) {
      throw new DecompilationException(String.format("cannot find parent directory:%s", path.toString()));
    }
    Path outputDir = parent.resolve("decompiled");
    decompiler.decompileClassFile(parent, path, outputDir);
  }


}
