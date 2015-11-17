/*
 *     This file is part of SyrahTest
 *
 *     SyrahTest is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     SyrahTest is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with SyrahTest.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.syrahtest.core.data;

import org.apache.commons.lang3.StringUtils;
import org.syrahtest.core.FileUtil;

/**
 * Responsible for creating the right kind of datasource given a DataSourceKey
 * Created by Nate on 11/16/2015.
 */
public class DataSourceFactory {


  /**
   * Use this method when a datasource needs to be loaded.
   *
   * @param dsKey
   * @return
   */
  public static IDataSource get(DataSourceKey dsKey) {
    IDataSource dataSource = null;
    String sourceLocation = dsKey.getFileLocation();

    // File Based
    if (StringUtils.isNotEmpty(sourceLocation)) {
      //TODO: Read extension if no Dialect is provided
      dataSource = new JSONDataSource(FileUtil.getClasspathFileAsString(sourceLocation));
    }

    // Inline Data
    else if (StringUtils.isNotEmpty(dsKey.getData())) {
      dataSource = new JSONDataSource(dsKey.getData());
    }
    return dataSource;
  }
}
