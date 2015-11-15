package org.syrahtest.core.resolve;

import org.syrahtest.core.data.IDataSource;

/**
 * Created by nate on 11/14/15.
 */
public class DefaultVariableResolver implements IVariableResolver {


  public static final String VARIABLE_START_STRING = "{{";
  public static final String VARIABLE_STOP_STRING = "}}";

  @Override
  public String resolveVariables(String searchString, IDataSource dataSource) {

    StringBuilder newString = new StringBuilder();
    int cursor = 0;
    int tokenStart = searchString.indexOf(VARIABLE_START_STRING);

    while( tokenStart > -1 ){
      int tokenEnd = searchString.indexOf(VARIABLE_STOP_STRING, tokenStart);
      String variableName = searchString.substring(tokenStart+2, tokenEnd);
      String variableValue = dataSource.getValue(variableName);

      newString.append(searchString.substring(cursor, tokenStart));

      if( variableValue != null ){
        newString.append(variableValue);
      }
      else {
        newString.append(VARIABLE_START_STRING).append(variableName).append(VARIABLE_STOP_STRING);
      }

      cursor = tokenEnd + 2;
      tokenStart = searchString.indexOf(VARIABLE_START_STRING, cursor);
    }
    return newString.length() > 0 ? newString.append(searchString.substring(cursor)).toString() : searchString;
  }

}
