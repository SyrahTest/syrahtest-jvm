package org.syrahtest.core.data;

import org.syrahtest.core.data.annotation.DataSource;

/**
 * Created by nate on 11/14/15.
 */
public class DataSourceKey {

  private DataSource.Dialect dialect;
  private String sourceLocation;
  private boolean cachable = false;
  private String data;

  public static DataSourceKey createCacheableDataSourceKey(DataSource.Dialect dialect, String sourceLocation){
    DataSourceKey dsk = new DataSourceKey();
    dsk.dialect = dialect;
    dsk.sourceLocation = sourceLocation;
    dsk.cachable = true;
    return dsk;
  }

  public static DataSourceKey createNonCacheableDataSourceKey(DataSource.Dialect dialect, String data){
    DataSourceKey dsk = new DataSourceKey();
    dsk.dialect = dialect;
    dsk.data = data;
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
}
