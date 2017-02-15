package com.wreulicke.decompiler.internal;

import java.nio.file.Path;
import java.util.List;

import org.jboss.windup.decompiler.api.DecompilationListener;
import org.jboss.windup.decompiler.procyon.ProcyonDecompiler;

import com.google.common.io.Files;
import com.wreulicke.decompiler.FileDecompiler;

public class ArchiveDecompiler implements FileDecompiler {

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

  @Override
  public boolean isSupportedFile(Path path) {
    String extension = Files.getFileExtension(path.getFileName()
      .toString());
    switch (extension) {
      case "jar":
        return true;
      default:
        return false;
    }
  }

  @Override
  public void decompile(Path path) {
    ProcyonDecompiler decompiler = new ProcyonDecompiler();
    decompiler.decompileArchive(path, path.getParent()
      .resolve("decompiled"), new InfomationEmitListener());
  }

}
