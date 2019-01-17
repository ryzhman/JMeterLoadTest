package com.paymentus.javaRunner;

import java.io.IOException;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.junit.jupiter.api.Test;

/**
 * @author Alex Ryzhkov
 */
public class LoadTestOfItemAppJavaBased {

	@Test
	public void testMainPageFromJavaCreatedPlan() throws IOException {
		//JMeter Engine
		StandardJMeterEngine jmeter = new StandardJMeterEngine();

		//JMeter initialization (properties, log levels, locale, etc)
		JMeterUtils.loadJMeterProperties(getClass().getResource("/config/jmeter.properties").getPath());
		JMeterUtils.setJMeterHome(getClass().getResource("/config/").getPath());
		JMeterUtils.initLocale();

		// Test Plan
		TestPlan testPlan = new TestPlan("Create JMeter Script From Java Code");

		// HTTP Sampler
		HTTPSampler httpSampler = new HTTPSampler();
		httpSampler.setProtocol("http");
		httpSampler.setDomain("localhost");
		httpSampler.setPort(8080);
		httpSampler.setPath("/item/1");
		httpSampler.setMethod("GET");

		LoopController loopController = new LoopController();
		loopController.setLoops(100);
		loopController.addTestElement(httpSampler);
		loopController.initialize();

		// Thread Group
		ThreadGroup threadGroup = new ThreadGroup();
		threadGroup.setNumThreads(10);
		threadGroup.setRampUp(1);
		threadGroup.setName("Test users");
		threadGroup.setSamplerController(loopController);
		threadGroup.setEnabled(true);

		// JMeter Test Plan, basic all u JOrphan HashTree
		HashTree testPlanTree = new HashTree();
		testPlanTree.add(testPlan);
		HashTree threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);
		threadGroupHashTree.add(httpSampler);
		threadGroupHashTree.add(loopController);

		// Run Test Plan
		jmeter.configure(testPlanTree);

		writeToFile(testPlanTree);

		jmeter.run();

	}

	public void writeToFile(HashTree testPlanTree) {
		Summariser summer = null;
		String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
		if (summariserName.length() > 0) {
			summer = new Summariser(summariserName);
		}

		String logFile = "src/test/results/javaGeneratedResults.csv";
		ResultCollector logger = new ResultCollector(summer);
		logger.setFilename(logFile);
		testPlanTree.add(testPlanTree.getArray()[0], logger);
	}
}
