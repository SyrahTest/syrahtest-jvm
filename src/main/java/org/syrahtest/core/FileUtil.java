package org.syrahtest.core;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;

/**
 * Created by nate on 10/13/15.
 */
public class FileUtil {

  /**
   * Load a file from the classpath
   * @param path No leading '/'
   * @return
   */
  public static String getClasspathFileAsString(String path) {

    String fileContents = "";
    try {
      Path p = Paths.get(FileUtil.class.getClassLoader().getResource(path).toURI());
      fileContents = new String(readAllBytes(p));
    } catch (IOException | URISyntaxException e) {

    }
    return fileContents;
  }
}