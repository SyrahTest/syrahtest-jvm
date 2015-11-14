package org.syrahtest.core.resolve;

import org.syrahtest.core.data.IDataSource;

/**
 * Created by nate on 11/14/15.
 */
public interface IVariableResolver {

  /**
   * Find and replace variables in {@code searchString}
   * @param searchString
   * @param IDataSource
   * @return
   */
  String resolveVariables(String searchString, IDataSource IDataSource);

}
