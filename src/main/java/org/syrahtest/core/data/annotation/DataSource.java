package org.syrahtest.core.data.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by nate on 11/14/15.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

  /** The kind of data, default is JSON */
  Dialect dialect() default Dialect.JSON;

  /** Provide json data inline using this attribute */
  String data() default "";

  public enum Dialect{
    JSON, XML, XLS, XLSX, SQL;
  }

}
