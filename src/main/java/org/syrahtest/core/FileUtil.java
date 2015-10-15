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

  public static String getClasspathFileAsString(String path) throws URISyntaxException, IOException {
    Path p = Paths.get(FileUtil.class.getClassLoader().getResource(path).toURI());
    return new String(readAllBytes(p));
  }
}