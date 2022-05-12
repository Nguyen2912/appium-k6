package Test;
import com.google.common.reflect.ClassPath;
import driver.MobileCapabilityTypeEx;
import driver.Platform;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
public class Main implements MobileCapabilityTypeEx{
    @SuppressWarnings("UnstableApiUsage")
    public static void main(String[] args) throws IOException {
        //Get all test class
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        List<Class<?>> testClasses = new ArrayList<>();

        for(ClassPath.ClassInfo info:ClassPath.from(loader).getTopLevelClasses()){
            String classInfoName = info.getName();

            boolean startWithTestDot = classInfoName.startsWith("Test.");
            boolean isBaseTestClass = classInfoName.startsWith("Test.BaseTest");
            boolean isMainClass = classInfoName.startsWith("Test.Main");
            if(startWithTestDot && !isBaseTestClass && !isMainClass){
                testClasses.add(info.load());

            }
        }
        //System.out.println(testClasses)->[class Test.authen.LoginTest, class Test.authen.LoginTest2, class Test.form.FormTest]
        //Get Platform
        //String platformName = System.getProperty("Platform");
        String platformName = "android";
        if(platformName == null){
            throw new RuntimeException("Please provide platform via -Dplatform");
        }
        try{
            Platform.valueOf(platformName);

        }catch (Exception e){
            throw new IllegalArgumentException("[ERR] We don't support" + platformName);
        }

        //Devices under Test
        List<String> iphoneDeviceList = Arrays.asList("iphone 12","iphone 13");
        List<String> androidDeviceList = Arrays.asList("R28M804D20E","emulator-5554");
        List<String> deviceList = platformName.equalsIgnoreCase("ios") ? iphoneDeviceList:androidDeviceList;
        //Assign Test classes into device list
        final int testNumEachDevice = testClasses.size()/deviceList.size();
        HashMap<String,List<Class<?>>> designCaps = new HashMap<>();
        for (int deviceIndex = 0; deviceIndex < deviceList.size(); deviceIndex++) {
            int startIndex = deviceIndex * testNumEachDevice;
            int endIndex = deviceIndex == deviceList.size() - 1 ? testClasses.size() : (startIndex + testNumEachDevice);
            List<Class<?>> subTestList = testClasses.subList(startIndex,endIndex);
            designCaps.put(deviceList.get(deviceIndex),subTestList);

        }
        // Build Dynamic test suite
        TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("Regression");
        List<XmlTest> allTests = new ArrayList<>();
        for(String deviceName:designCaps.keySet()){
            XmlTest test = new XmlTest(suite);
            test.setName(deviceName);
            List<XmlClass> xmlClasses = new ArrayList<>();
            List<Class<?>> dedicatedClasses = designCaps.get(deviceName);
            for(Class<?> dedicatedClass:dedicatedClasses){
                xmlClasses.add(new XmlClass(dedicatedClass.getName()));
            }
            test.setXmlClasses(xmlClasses);
            test.addParameter(UDID,deviceName);
            test.addParameter(PLATFORM_NAME,platformName);
            test.addParameter(PLATFORM_VERSION,"15.0");
            test.addParameter(SYSTEM_PORT,String.valueOf(new SecureRandom().nextInt(1000) + 8300));
            allTests.add(test);
        }
        suite.setTests(allTests);
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount(10);
        System.out.println(suite.toXml());

        // ADD testsuite into suite list
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        // Imvoke Run Method
        testNG.setXmlSuites(suites);
        //testNG.run();



    }
}
