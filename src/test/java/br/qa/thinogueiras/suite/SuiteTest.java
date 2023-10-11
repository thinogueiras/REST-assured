package br.qa.thinogueiras.suite;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("API Tests - Barriga REST")
@SelectPackages("br.qa.thinogueiras.tests")
public class SuiteTest {	

}
