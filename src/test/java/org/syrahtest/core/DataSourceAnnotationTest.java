package org.syrahtest.core;

import org.junit.Test;
import org.syrahtest.core.data.DataStore;
import org.syrahtest.core.data.IDataSource;
import org.syrahtest.core.data.annotation.DataSource;
import org.syrahtest.core.resolve.DefaultVariableResolver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Created by nate on 11/14/15.
 */
@DataSource(dialect = DataSource.Dialect.JSON, data = "{ token: \"Class Value\"}")
public class DataSourceAnnotationTest {

  @Test
  public void getDataSourceInContextSetsActiveContext() {
    IDataSource dsc = DataStore.get().getDataSourceInContext();
    IDataSource ads = DataStore.get().getActiveDataSource();
    assertSame(dsc, ads);
  }

  @Test
  public void replaceTokenInTestMethodAnnotatedWithDataSourceFromClass() {
    String result = getDataSourceVariable("some {{token}}");
    assertEquals("some Class Value", result);
  }

  @DataSource(dialect = DataSource.Dialect.JSON, data = "{ token: \"Method Value\"}")
  @Test
  public void replaceTokenInTestMethodAnnotatedWithDataSource() {
    String result = getDataSourceVariable("some {{token}}");
    assertEquals("some Method Value", result);
  }

  @Test
  public void replaceTokenInTestMethodAnnotatedWithDataSourceFromClassUsingJSONExpression() {
    String result = getDataSourceVariable("some {{$.token}}");
    assertEquals("some Class Value", result);
  }

  private String getDataSourceVariable(String searchString) {
    IDataSource dsc = DataStore.get().getDataSourceInContext();
    return new DefaultVariableResolver().resolveVariables(searchString, dsc);
  }
}