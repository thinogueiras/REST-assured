package br.qa.thinogueiras.suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import br.qa.thinogueiras.tests.AccountTest;
import br.qa.thinogueiras.tests.AuthTest;
import br.qa.thinogueiras.tests.BalanceTest;
import br.qa.thinogueiras.tests.MovementTest;

@Suite
@SuiteDisplayName("API Tests - Barriga REST")
@SelectClasses({ 
	AuthTest.class,
	AccountTest.class, 
	BalanceTest.class,
	MovementTest.class }) 
public class SuiteTest {	
	
}
