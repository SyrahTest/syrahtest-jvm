package org.syrahtest.core.data;

import org.syrahtest.core.data.annotation.DataSource;

import java.lang.reflect.Method;

/**
 * Created by nate on 11/14/15.
 */
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
   * @return
   */
  public DataSourceKey getDataSourceForThread(){
    StackTraceElement[] stack = Thread.currentThread().getStackTrace();
    DataSourceKey key = findDataSource(stack, 1);
    return key;
  }

  private DataSourceKey findDataSource(StackTraceElement[] stack, int index) {

    DataSourceKey key = null;
    StackTraceElement e = stack[index];

    try {
      Class c = Class.forName(e.getClassName());
      Method m = c.getDeclaredMethod(e.getMethodName());
      DataSource a = m.getDeclaredAnnotation(DataSource.class);
      a = a == null ? (DataSource)c.getDeclaredAnnotation(DataSource.class) : a;
      if( a != null )
      {
        if( "".equals(a.data()) ){
          key = DataSourceKey.createCacheableDataSourceKey(a.dialect(), c.getName() + "#" + m.getName());
        }else{
          key = DataSourceKey.createNonCacheableDataSourceKey(a.dialect(), a.data());
        }
      }
    } catch (ClassNotFoundException | NoSuchMethodException ex) {
      //TODO: Add a logger
      ex.printStackTrace();
    }

    if( key == null && e.getClassName().startsWith(CLASS_MATCH_PREFIX) ){
      key = findDataSource(stack, index+1);
    }

    return key;
  }
}
