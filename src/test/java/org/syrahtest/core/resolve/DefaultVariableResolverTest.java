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
@RunWith(JMockit.class)
public class DefaultVariableResolverTest {

  @Mocked private IDataSource IDataSource;
  @Tested private DefaultVariableResolver resolver;

  @Test
  public void replaceSingleTokenFromDataSource(){

    new Expectations(){
      {
        IDataSource.getValue("parameter"); result = "token";
      }};

    String newValue = resolver.resolveVariables("A string that has a {{parameter}} in the middle.", IDataSource);

    assertEquals("A string that has a token in the middle.", newValue);
  }

  @Test
  public void returnTokenizedStringWhenDataSourceDoesNotContainToken(){

    String newValue = resolver.resolveVariables("A string that has a {{parameter}} in the middle.", IDataSource);

    assertEquals("A string that has a {{parameter}} in the middle.", newValue);
  }
}