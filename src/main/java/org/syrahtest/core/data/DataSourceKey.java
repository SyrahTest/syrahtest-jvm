package org.syrahtest.core.data;

import org.syrahtest.core.data.annotation.DataSource;

/**
 * Used to lookup a data source.
 *
 * Created by nate on 11/14/15.
 */
public class DataSourceKey {

  private DataSource annotation;
  private DataSource.Dialect dialect;
  private String sourceLocation;
  private boolean cachable = false;
  private String data;

  public static DataSourceKey createCacheableDataSourceKey(DataSource annotation, String annotationSource){
    DataSourceKey dsk = new DataSourceKey();
    dsk.annotation = annotation;
    dsk.dialect = annotation.dialect();
    dsk.sourceLocation = annotationSource;
    dsk.cachable = true;
    return dsk;
  }

  public static DataSourceKey createNonCacheableDataSourceKey(DataSource annotation){
    DataSourceKey dsk = new DataSourceKey();
    dsk.annotation = annotation;
    dsk.dialect = annotation.dialect();
    dsk.data = annotation.data();
    return dsk;
  }

  private DataSourceKey() {
  }

  public DataSource.Dialect getDialect() {
    return dialect;
  }

  public String getSourceLocation() {
    return sourceLocation;
  }

  public boolean isCachable() {
    return cachable;
  }

  public String getData() {
    return data;
  }

  public String getFileLocation() { return annotation.file(); };
}
