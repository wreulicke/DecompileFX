package com.wreulicke.decompiler;

import java.nio.file.Path;

public interface FileDecompiler {
  public boolean isSupportedFile(Path path);

  public void decompile(Path path);
}
