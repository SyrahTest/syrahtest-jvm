package org.syrahtest.core.data;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.syrahtest.core.data.annotation.DataSource;

import java.lang.reflect.Method;

/**
 * Created by nate on 11/14/15.
 */
@Slf4j
public class AnnotationProcessor {


  // TODO: Make this a config setting, or just let if we are using junit
  private static final String CLASS_MATCH_PREFIX = "org.syrahtest";

  /**
   * The default constructor will scan the call stack from where it was instantiated.
   */
  public AnnotationProcessor() {

  }

  /**
   * Using the current stack trace, look for a DataSource annotation on methods
   * or classes until the type no longer matches an expected type.
   *
   * @return
   */
  public DataSourceKey getDataSourceForThread() {
    StackTraceElement[] stack = Thread.currentThread().getStackTrace();
    DataSourceKey key = findDataSource(stack, 1);
    return key;
  }

  private DataSourceKey findDataSource(StackTraceElement[] stack, int index) {

    DataSourceKey key = null;
    StackTraceElement e = stack[index];

    //If a method of a type does not have an annotation look at all other methods in that class before using the class
    DataSource methodAnnotation;
    DataSource classAnnotation;

    try {
      Class c = Class.forName(e.getClassName());
      Method m = getDeclaredMethodOfClass(e.getMethodName(), c);
      methodAnnotation = m.getDeclaredAnnotation(DataSource.class);
      classAnnotation = (DataSource)c.getDeclaredAnnotation(DataSource.class);
      String locationKey = c.getName() + "#" + m.getName();
      key = createKeyFromAnnotation(methodAnnotation, locationKey);
      //Only use class if the next stack element is not this class
      if( key == null && !stack[index+1].getClassName().equals(e.getClassName()) ){
        key = createKeyFromAnnotation(classAnnotation, locationKey);
      }

    } catch (ClassNotFoundException | NoSuchMethodException ex) {
      log.error("Couldn't load DataSource annotation: {}", ex.toString());
    }

    if (key == null && e.getClassName().startsWith(CLASS_MATCH_PREFIX)) {
      key = findDataSource(stack, index + 1);
    }

    return key;
  }


  private DataSourceKey createKeyFromAnnotation(DataSource annotation, String locationKey) {
    DataSourceKey key = null;
    if (annotation != null) {
      if ("".equals(annotation.data())) {
        key = DataSourceKey.createCacheableDataSourceKey(annotation.dialect(), locationKey);
      } else {
        key = DataSourceKey.createNonCacheableDataSourceKey(annotation.dialect(), annotation.data());
      }
    }
    return key;
  }

  private Method getDeclaredMethodOfClass(String methodName, Class c) throws NoSuchMethodException {
    Method[] methods = c.getDeclaredMethods();
    for( Method m : methods ){
      if( m.getName().equals(methodName) )
        return m;
    }
    return null; //can't really happen
  }
}
