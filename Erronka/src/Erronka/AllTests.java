package Erronka;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Layout eta DBOperazioak klaseen metodoak testeatzeko klasea.
 * @author Unax Azpirotz
 * @author Cristian Mulleras
 */
@Suite
@SelectClasses({ DBOperazioakTest.class, LayoutTest.class })
public class AllTests {

}
