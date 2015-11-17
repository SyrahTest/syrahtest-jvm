package org.syrahtest.core.data;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.syrahtest.core.data.annotation.DataSource;

/**
 * A Data Store is responsible for loading, caching, and unloading all data sources.
 * Multiple different types of data sources (i.e. JSON, XLS, etc) can be loaded into a single store.
 *
 * Created by nate on 11/14/15.
 */
public class DataStore {

  private static ThreadLocal<DataStore> defaultStore = new ThreadLocal<DataStore>();

  public static void initialize(){
    defaultStore.set(createDataStore());
  }


  /**
   * Gets the data store as set by the currently running thread.
   * @return
   */
  public static synchronized DataStore get(){

    DataStore dataStore = defaultStore.get();
    if (dataStore == null ){
      initialize();
    }
    return defaultStore.get();
  }

  private IDataSource activeDataSource = null;

  /**
   * The datastore can only have one active data source at a time, this method
   * returns the last datasource that was set to active or retrieved using #getDataSourceInContext
   */
  public IDataSource getActiveDataSource() {
    return activeDataSource;
  }

  /**
   * Examine the current stack looking for methods and/or types that have a
   * data source defined.  Once a data source is found it is set to active and returned.
   */
  public IDataSource getDataSourceInContext() {
    AnnotationProcessor ap = new AnnotationProcessor();
    DataSourceKey dsKey = ap.getDataSourceForThread();
    activeDataSource = DataSourceFactory.get(dsKey);
    return activeDataSource;
  }

  private static DataStore createDataStore() {
    return new DataStore();
  }
}
