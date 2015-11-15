package org.syrahtest.core.data;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

/**
 * Created by nate on 11/14/15.
 */
public class JSONDataSource implements IDataSource {

  Object jsonDoc;

  public JSONDataSource(String jsonString) {
    this.jsonDoc = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
  }

  @Override
  public String getValue(String parameter) {
    String jsonExpression = parameter.startsWith("$") ? parameter : "$." + parameter;
    return JsonPath.read(jsonDoc, jsonExpression);
  }

}
