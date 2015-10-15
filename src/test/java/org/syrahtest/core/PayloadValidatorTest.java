package org.syrahtest.core;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

/**
 * Created by nate on 10/13/15.
 */
public class PayloadValidatorTest {

  /**
   * Validate a JSON Payload against an expected data set by using a mapping definition.
   *
   * @throws Exception
   */
  @Test
  public void jsonAttributes() throws Exception {

    //https://github.com/jacobono/gradle-jaxb-plugin

    //Arrange
    String responseText = FileUtil.getClasspathFileAsString("/org/syrahtest/core/transform/sample-response.json");
    MappingDefinition mappingDefinition = new MappingDefinition("/org/syrahtest/core/transform/mapping.xml");
    DataSource dataSource = ExcelDataSourceFactory.get("/org/syrahtest/data/wineData.xlsx");

    //Act
    PayloadValidator pv = new PayloadValidator(mappingDefinition, dataSource);
    ValidationResult vr = pv.validate(responseText);

    //Assert
    assertTrue(vr.isSuccessful());
  }
}