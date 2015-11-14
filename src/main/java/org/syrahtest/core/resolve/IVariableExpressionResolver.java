package org.syrahtest.core.resolve;

/**
 * Takes an expression and determines its value.
 *
 * Created by nate on 11/14/15.
 */
public interface IVariableExpressionResolver<DataStoreType> {

  String resolve(String expression, DataStoreType dataStore);
}
