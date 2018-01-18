package org.webonise.automation.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import com.relevantcodes.extentreports.ExtentReports;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.webonise.automation.core.verification.Verification;
import org.webonise.automation.rest.RESTTest;
import org.webonise.automation.rest.RESTVerification;


public class WeboAutomation {

    public static WebDriver driver;
    public static String report_name;
    static String dir;
    public static TestData testdata = new TestData();
    public static Configuration config = new Configuration();
    public static CommonUtility utils = new CommonUtility();
    public static WeboActions weboActions;
    public static Verification verify;
    public static ExtentReports extent = ExtentReports.get(WeboAutomation.class);
    public static String methodName;
    static String filename;
    static String scriptName;
    public static Date date = new Date();
    public static String reportName = "";
    public static HashMap<String, String> globalMap;
    public static RESTVerification rest_verification;
    public static RESTTest rest;
    public static String reportDir;
    public static String scriptVideosDir;


    @BeforeTest
    public static void testStart() {
        Reporter.log("TestCase Started", true);
        try {
            Configuration.initializeSettings();
            reportDir = Configuration.path + "/Report/";
            File reportFile = new File(reportDir);
            FileUtils.deleteDirectory(reportFile);
            reportFile.mkdirs();
            if (Configuration.mobileAutomation.equalsIgnoreCase("yes")) {
                scriptVideosDir = Configuration.path + "/ScriptVideos/";
                File scriptVideoFile = new File(scriptVideosDir);
                FileUtils.deleteDirectory(scriptVideoFile);
                scriptVideoFile.mkdirs();
            }
            dir = Configuration.path;
            reportName = reportDir + "Weboniselab-QA-Execution-Report.html";
            if (!dir.isEmpty()) {
                extent.init(reportName, true);
                extent.config().documentTitle("Webonise Automation Report");
                extent.config().reportHeadline("Weboniselab Automation Report");
                extent.config().displayCallerClass(false);
            } else {
                System.err.println("Failed to initialize report");
            }

            weboActions = new WeboActions(utils, dir);
            verify = new Verification(weboActions);
            globalMap = new HashMap<String, String>();

            if (Configuration.mobileAutomation.equalsIgnoreCase("no")) { //selenium execution
                initializeLocalBrowser(Configuration.browser);
                weboActions.setDriver(driver);
            } else { //mobile execution
                // Create ScriptVideos Directory
                scriptVideosDir = Configuration.path + "/ScriptVideos/";
                File scriptVideoFile = new File(scriptVideosDir);
                FileUtils.deleteDirectory(scriptVideoFile);
                scriptVideoFile.mkdirs();
                //Stop appium server first
                MobileActions.stopAppium();
                //Start appium server first
                MobileActions.startAppium();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    protected static void initializeLocalBrowser(String type) throws Exception {
        DesiredCapabilities capability;
        try {
            switch (type) {
                case "firefox":
                    capability = DesiredCapabilities.firefox();
                    capability.setCapability("marionette", true);
                    FirefoxProfile profile = new FirefoxProfile();
                    profile.setPreference("dom.max_script_run_time", "999");
                    profile.setPreference("browser.download.folderList", 2);
                    profile.setPreference("browser.helperApps.neverAsk.openFile", "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
                    profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
                    profile.setPreference("browser.download.manager.showWhenStarting", false);
                    profile.setPreference("browser.download,manager.focusWhenStarting", false);
                    profile.setPreference("browser.helperApps.alwaysAsk.force", false);
                    profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
                    profile.setPreference("browser.download.manager.closeWhenDone", false);
                    profile.setPreference("browser.download.manager.showAlertOnComplete", false);
                    profile.setPreference("browser.download.manager.useWindow", false);
                    profile.setPreference("browser.download.manager.showWhenStarting", false);
                    profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
                    profile.setPreference("pdfjs.disabled", true);
                    profile.setAcceptUntrustedCertificates(true);
                    profile.setPreference("security.OCSP.enabled", 0);
                    profile.setPreference("network.http.use-cache", false);
                    profile.setPreference("gfx.direct2d.disabled", true);
                    profile.setPreference("layers.acceleration.disabled", true);
                    capability.setJavascriptEnabled(true);
                    capability.setCapability(FirefoxDriver.PROFILE, profile);
                    System.setProperty("webdriver.gecko.driver", Configuration.path + "/resources/geckodriver.exe");
                    driver = new FirefoxDriver(capability);
                    break;
                case "chrome":
                    capability = DesiredCapabilities.chrome();
                    capability.setBrowserName("chrome");
                    capability.setJavascriptEnabled(true);
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("chrome.switches", "--disable-extensions");
                    options.addArguments("disable-infobars");
                    options.addArguments("test-type");
                    options.addArguments("--js-flags=--expose-gc");
                    options.addArguments("--enable-precise-memory-info");
                    options.addArguments("--disable-popup-blocking");
                    options.addArguments("--disable-default-apps");
                    options.addArguments("--start-maximized");
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("password_manager_enabled", false);
                    options.setExperimentalOption("prefs", prefs);
                    capability.setCapability(ChromeOptions.CAPABILITY, options);
                    System.setProperty("webdriver.chrome.driver", Configuration.path + "/resources/chromedriver.exe");
                    driver = new ChromeDriver(options);
                    Thread.sleep(8000);
                    System.out.println("Driver initialized");
                    break;
                case "ie":
                    DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                    capabilities.setCapability("ignoreProtectedModeSettings", true);
                    System.setProperty("webdriver.ie.driver", Configuration.path + "/resources/IEDriverServer.exe");
                    driver = new InternetExplorerDriver(capabilities);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception in initializing driver" + e.getMessage());
        }
    }

    public AndroidDriver initializeMobileDrivers() throws MalformedURLException, ATUTestRecorderException {
        AndroidDriver androidDriver = null;
        try {
            Thread.sleep(2000);
            File app = new File(System.getProperty("user.dir") + "\\App\\Android\\" + Configuration.androidApkFileName);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.0.2");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "200");
            if (Configuration.fullReset.equalsIgnoreCase("false")) {
                capabilities.setCapability("noReset", "true");
            }
            androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            MobileActions.startRecording();
        } catch (Exception e) {
            System.out.println("Problem is initializing mobile driver:" + e.getMessage());
        }
        return androidDriver;
    }

   

    @AfterTest
    public static void testEnd() throws InterruptedException, ATUTestRecorderException {
        testdata.hmap.clear();
        String css = ".exec-info { display: block; }";
      
        Reporter.log("TestCase completed", true);
        extent.config().addCustomStyles(css);
        extent.endTest();
      
        if (Configuration.mobileAutomation.equalsIgnoreCase("yes")) {
            MobileActions.stopRecording();
        } else {
            Thread.sleep(3000);
            //driver.quit();
        }
    }


    public static void updateTCData(Integer iteration) {
        testdata.updateTCData(iteration);
        extent.startTest(methodName);
    }

    public String getValue(String varName) {
        return testdata.getValue(varName);
    }

    public static HashMap<String, String> getTestData() {
        return testdata.getHashMap();
    }

    @DataProvider(name = "xml")
    public static Object[][] getTestData(Method method, ITestContext context) {
        try {
            filename = context.getSuite().getXmlSuite().getFileName();
            scriptName = context.getSuite().getName();
            methodName = method.getName();

            try {
                if (driver != null) {
                    testdata.initialize(filename);
                } else if (Configuration.mobileAutomation.equalsIgnoreCase("yes")) {
                    testdata.initialize(filename);
                } else {
                    System.out.println("Driver not initialized");
                }
                weboActions.setTCNameToFile(method.getName());
                testdata.setTCNode(method.getName());
            } catch (NullPointerException e) {
                System.err.println("Driver not defined.Please define Driver in config file");
            }
        } catch (Exception e) {
            System.err.println("setUp() failed");
            e.printStackTrace();
        }
        System.out.println("in WeboAutomation.GetTCData");
        try {
            return testdata.getTCData();
        } catch (NullPointerException e) {
            System.err.println("getTCData() Failed");
            return null;
        }
    }


    @AfterSuite
    public static void generateZIPFile() {
        try {
        	 
            File directoryToZip = new File(Configuration.path + "\\Report");
            List<File> fileList = new ArrayList<File>();
            getAllFiles(directoryToZip, fileList);
            writeZipFile(directoryToZip, fileList);
            System.out.println("---Zip file created.");
        } catch (Exception e) {
            System.out.println("Exception e:" + e);
        }
    }

    public static void getAllFiles(File dir, List<File> fileList) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                fileList.add(file);
                if (file.isDirectory()) {
                    System.out.println("directory:" + file.getCanonicalPath());
                    getAllFiles(file, fileList);
                } else {
                    System.out.println("file:" + file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeZipFile(File directoryToZip, List<File> fileList) {

        try {
            FileOutputStream fos = new FileOutputStream(Configuration.path + "//" + directoryToZip.getName() + ".zip");
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (File file : fileList) {
                if (!file.isDirectory()) {
                    addToZip(directoryToZip, file, zos);
                }
            }
            zos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException, IOException {

        FileInputStream fis = new FileInputStream(file);
        String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1, file.getCanonicalPath().length());
        ZipEntry zipEntry = new ZipEntry(zipFilePath);
        zos.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }
        zos.closeEntry();
        fis.close();
    }
}