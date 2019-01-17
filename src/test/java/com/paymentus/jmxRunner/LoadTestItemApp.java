package com.paymentus.jmxRunner;

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
public class LoadTestItemApp {

	/**
	 * Method runs the JMeter test plan from the Java code
	 *
	 * @throws IOException
	 */
	@Test
	public void testGetMethod() throws IOException {
		// Engine
		StandardJMeterEngine jmeter = new StandardJMeterEngine();

		// Initialize Properties, logging, locale, etc.
		JMeterUtils.loadJMeterProperties(getClass().getResource("/config/jmeter.properties").getPath());
		JMeterUtils.setJMeterHome(getClass().getResource("/config/").getPath());
		JMeterUtils.initLocale();

		// Initialize JMeter SaveService
		SaveService.loadProperties();

		// Load existing .jmx Test Plan
		File in = new File(getClass().getResource("/testPlans/Item_app_sample_test_plan.jmx").getPath());
		HashTree testPlanTree = SaveService.loadTree(in);

		// Run JMeter Test
		jmeter.configure(testPlanTree);
		jmeter.run();
	}
}
