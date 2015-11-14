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
public class DataSourceAnnotationTest {

  @DataSource( dialect= DataSource.Dialect.JSON, data="{ token: \"Value\"}" )
  @Test
  public void replaceTokenInTestMethodAnnotatedWithDataSource(){

    IDataSource dsc = DataStore.get().getDataSourceInContext();
    IDataSource ads = DataStore.get().getActiveDataSource();

    assertSame(dsc, ads);

    String result = new DefaultVariableResolver().resolveVariables("some {{token}}", ads);
    assertEquals("some Value", result);
  }
}
