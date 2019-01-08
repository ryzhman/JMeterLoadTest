import java.io.File;
import java.io.IOException;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.junit.jupiter.api.Test;

/**
 * Jmeter test based on the test plan created from the GUI
 *
 * @author Alex Ryzhkov
 */
public class MainPageTest {

	//TODO: get rid of absolute paths
	@Test
	public void testMainPageFromGUICreatedPlan() throws IOException {
		// Engine
		StandardJMeterEngine jmeter = new StandardJMeterEngine();

		// Initialize Properties, logging, locale, etc.
		JMeterUtils.loadJMeterProperties("c:\\Users\\oryzhkov\\git\\apache-jmeter-5.0\\apache-jmeter-5.0\\bin\\jmeter.properties");
		JMeterUtils.setJMeterHome("c:\\Users\\oryzhkov\\git\\apache-jmeter-5.0\\apache-jmeter-5.0\\");
		JMeterUtils.initLocale();

		// Initialize JMeter SaveService
		SaveService.loadProperties();

		// Load existing .jmx Test Plan
		File in = new File("C:\\Users\\oryzhkov\\git\\jmeterdemo\\src\\test\\resources\\demo.jmx");
		HashTree testPlanTree = SaveService.loadTree(in);

		// Run JMeter Test
		jmeter.configure(testPlanTree);
		jmeter.run();
	}
}
