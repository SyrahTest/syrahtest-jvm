package org.syrahtest.core.resolve;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.syrahtest.core.data.IDataSource;

import static org.junit.Assert.assertEquals;

/**
 * Created by nate on 11/14/15.
 */
public class StringInterpolatorTest {

  @Mocked private IDataSource IDataSource;
  @Tested private StringInterpolator resolver;

  @Test
  public void resolveVariables_replaceSingleTokenFromDataSource(){

    new Expectations(){
      {
        IDataSource.getValue("parameter"); result = "token";
      }};

    String newValue = resolver.resolveVariables("A string that has a {{parameter}} in the middle.", IDataSource);

    assertEquals("A string that has a token in the middle.", newValue);
  }

  @Test
  public void resolveVariables_returnTokenizedStringWhenDataSourceDoesNotContainToken(){

    String newValue = resolver.resolveVariables("A string that has a {{parameter}} in the middle.", IDataSource);

    assertEquals("A string that has a {{parameter}} in the middle.", newValue);
  }
}