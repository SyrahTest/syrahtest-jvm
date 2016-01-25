package org.syrahtest.core.resolve;

import org.syrahtest.core.data.IDataSource;

/**
 * Created by nate on 11/14/15.
 */
public interface Interpolator<T> {

  /**
   * Find and replace variables in {@code searchString}, if a variable can not be replaced it will be left intact.
   *
   * @param target
   * @param IDataSource
   * @return
   */
  String resolveVariables(T target, IDataSource IDataSource);

}
