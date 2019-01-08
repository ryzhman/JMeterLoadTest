import java.io.IOException;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.junit.jupiter.api.Test;

/**
 * @author Alex Ryzhkov
 */
public class MainPageTestFromJava {

	@Test
	public void testMainPageFromJavaCreatedPlan() throws IOException {
		//JMeter Engine
		StandardJMeterEngine jmeter = new StandardJMeterEngine();

		//JMeter initialization (properties, log levels, locale, etc)
		JMeterUtils.loadJMeterProperties(
				"c:\\Users\\oryzhkov\\git\\apache-jmeter-5.0\\apache-jmeter-5.0\\bin\\jmeter.properties");
		JMeterUtils.setJMeterHome("c:\\Users\\oryzhkov\\git\\apache-jmeter-5.0\\apache-jmeter-5.0\\");
		JMeterUtils.initLocale();

		// JMeter Test Plan, basic all u JOrphan HashTree
		HashTree testPlanTree = new HashTree();

		// HTTP Sampler
		HTTPSamplerProxy httpSampler = new HTTPSamplerProxy();
		httpSampler.setProtocol("https");
		httpSampler.setDomain("docker.paymentus.io");
		httpSampler.setPath("biller/stde/logon.do");
		httpSampler.setMethod("POST");

		HTTPSamplerProxy httpSamplerProxy = new HTTPSamplerProxy();
		Arguments args = new Arguments();
		args.addArgument("loginId", "paymentus");
		args.addArgument("password", "sysadmin");
		args.addArgument("client", "8067797768");
		httpSamplerProxy.setArguments(args);
		// Loop Controller
		LoopController loopController = new LoopController();
		loopController.setLoops(5);
		loopController.addTestElement(httpSampler);
		loopController.setFirst(true);
		loopController.initialize();

		// Thread Group
		ThreadGroup threadGroup = new ThreadGroup();
		threadGroup.setNumThreads(3);
		threadGroup.setRampUp(1);
		threadGroup.setSamplerController(loopController);

		// Test Plan
		TestPlan testPlan = new TestPlan("Create JMeter Script From Java Code");

		// Construct Test Plan from previously initialized elements
		testPlanTree.add("testPlan", testPlan);
		testPlanTree.add("httpSampler", httpSampler);
		testPlanTree.add("loopController", loopController);
		testPlanTree.add("threadGroup", threadGroup);
//		testPlanTree.add("httpSamplerProxy", httpSamplerProxy);

		// Run Test Plan
		jmeter.configure(testPlanTree);

		writeToFile(threadGroup, testPlanTree);

		jmeter.run();

	}

	public static void writeToFile(ThreadGroup threadGroup, HashTree testPlanTree) throws IOException {
		Summariser summer = null;
		String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");//$NON-NLS-1$
		if (summariserName.length() > 0) {
			summer = new Summariser(summariserName);
		}

		String logFile = "resultsFromJava.csv";
		ResultCollector logger = new ResultCollector(summer);
		logger.setFilename(logFile);
		testPlanTree.add(testPlanTree.getArray()[0], logger);
	}
}
