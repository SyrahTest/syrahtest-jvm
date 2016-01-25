package org.syrahtest.core.resolve;

import org.syrahtest.core.data.IDataSource;

/**
 * Interpolates (or replaces variables in) strings.
 *
 * double brace '{{' syntax is used to not conflict with other language's built in interpolation features (Groovy, C#, etc)
 *
 * @author Nate Good
 */
public class StringInterpolator implements Interpolator<String> {


  public static final String VARIABLE_START_STRING = "{{";
  public static final String VARIABLE_STOP_STRING = "}}";

  /**
   *
   * @param searchString A string that contains variables, for example: "Replace the {{token}} value".
   * @param dataSource
   * @return
   */
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
