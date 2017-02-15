package com.wreulicke.decompiler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@ApplicationScoped
public class Decompiler {
  @Inject
  Instance<FileDecompiler> decompilerInstances;

  public void decompile(Stream<Path> paths) {
    paths.forEach(this::decompile);
  }

  private void decompile(Path path) {
    boolean isDirecotory = Files.isDirectory(path);
    if (isDirecotory) {
      return;
    }
    Stream<FileDecompiler> decompilers = StreamSupport.stream(decompilerInstances.spliterator(), false);
    Optional<FileDecompiler> decompiler = decompilers.filter((d) -> d.isSupportedFile(path))
      .findFirst();
    decompiler.ifPresent((d) -> {
      d.decompile(path);
    });
    boolean notFound = !decompiler.isPresent();
    if (notFound) {
      System.out.println("This file is unsupported.");
    }

  }
}
