package org.webonise.automation.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;

public class Configuration {

    public static String browser, instance, path, testAutomationURL = null, superAdminTestURL = null, superAdminStageURL = null, superAdminPreprodURL = null, superAdminProdURL = null, adminTestURL = null, adminStageURL = null, adminPreprodURL = null, adminProdURL = null, apiTestURL = null, apiStageURL = null, apiPreprodURL = null, apiProdURL = null, pdf_converter_dir, testUrl = null, stageUrl = null, preprodUrl = null, prodUrl = null, url = null, superAdminUrl = null, adminUrl = null, apiUrl = null, configPath = "/config.properties";
    public static String nodePath = null, mobileAutomation = null, androidApkFileName = null, appiumJavaScriptPath = null, fullReset = null;
    public static int defaultGUIWaitPeriod;
    static InputStream input = null;
    ;

    @SuppressWarnings("resource")
    public static void initializeSettings() {
        try {
            String line = null;
            String[] keyValue = null;
            path = new File(".").getCanonicalPath().replace("\\", "/");
            FileReader fr = new FileReader(new File(path + configPath).getAbsoluteFile());
            ;
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                keyValue = line.split("=");
                switch (keyValue[0].toString()) {
                    case "browser":
                        browser = keyValue[1];
                        System.out.println("browser:" + browser);
                        if (System.getenv("browser") != null)
                            browser = System.getenv("browser");
                        browser = browser.toLowerCase();
                        break;
                    case "defaultguiwaitperiod":
                        defaultGUIWaitPeriod = Integer.parseInt(keyValue[1]);
                        System.out.println("defaultGUIWaitPeriod" + defaultGUIWaitPeriod);
                        break;
                    case "instance":
                        instance = keyValue[1].toString().toLowerCase();
                        System.out.println("instance:" + instance);
                        if (System.getenv("instance") != null)
                            instance = System.getenv("instance");
                        instance = instance.toLowerCase();
                        break;
                    case "testurl":
                        testUrl = keyValue[1];
                        System.out.println("testUrl" + testUrl);
                        break;
                    case "stageurl":
                        stageUrl = keyValue[1];
                        System.out.println("stageUrl" + stageUrl);
                        break;
                    case "preprodurl":
                        preprodUrl = keyValue[1];
                        System.out.println("preprodUrl" + preprodUrl);
                        break;
                    case "produrl":
                        prodUrl = keyValue[1];
                        System.out.println("prodUrl" + prodUrl);
                        break;
                    case "superadmintesturl":
                        superAdminTestURL = keyValue[1];
                        System.out.println("superAdminTestURL" + superAdminTestURL);
                        break;
                    case "superadminstageurl":
                        superAdminStageURL = keyValue[1];
                        System.out.println("superAdminStageURL" + superAdminStageURL);
                        break;
                    case "superadminpreprodurl":
                        superAdminPreprodURL = keyValue[1];
                        System.out.println("superAdminPreprodURL" + superAdminPreprodURL);
                        break;
                    case "superadminprodurl":
                        superAdminProdURL = keyValue[1];
                        System.out.println("superAdminProdURL" + superAdminProdURL);
                        break;
                    case "admintesturl":
                        adminTestURL = keyValue[1];
                        System.out.println("adminTestURL" + adminTestURL);
                        break;
                    case "adminstageurl":
                        adminStageURL = keyValue[1];
                        System.out.println("adminStageURL" + adminStageURL);
                        break;
                    case "adminpreprodurl":
                        adminPreprodURL = keyValue[1];
                        System.out.println("adminPreprodURL" + adminPreprodURL);
                        break;
                    case "adminprodurl":
                        adminProdURL = keyValue[1];
                        System.out.println("adminProdURL" + adminProdURL);
                        break;
                    case "apitesturl":
                        apiTestURL = keyValue[1];
                        System.out.println("apiTestURL" + apiTestURL);
                        break;
                    case "apistageurl":
                        apiStageURL = keyValue[1];
                        System.out.println("apiStageURL" + apiStageURL);
                        break;
                    case "apipreprodurl":
                        apiPreprodURL = keyValue[1];
                        System.out.println("apiPreprodURL" + apiPreprodURL);
                        break;
                    case "apiprodurl":
                        apiProdURL = keyValue[1];
                        System.out.println("apiProdURL" + apiProdURL);
                        break;
                    case "mobile_automation":
                        mobileAutomation = keyValue[1];
                        System.out.println("mobileAutomation:" + mobileAutomation);
                        break;
                    case "nodePath":
                        nodePath = keyValue[1];
                        System.out.println("nodePath:" + nodePath);
                        break;
                    case "appiumJavaScriptPath":
                        appiumJavaScriptPath = keyValue[1];
                        System.out.println("appiumJavaScriptPath:" + appiumJavaScriptPath);
                        break;
                    case "androidApkFileName":
                        androidApkFileName = keyValue[1];
                        System.out.println("androidApkFileName:" + androidApkFileName);
                        break;
                    case "full-reset":
                        fullReset = keyValue[1];
                        System.out.println("full-reset: " + fullReset);
                        break;
                }
            }
            br.close();
            switch (instance) {
                case "test":
                    url = testUrl;
                    superAdminUrl = superAdminTestURL;
                    adminUrl = adminTestURL;
                    apiUrl = apiTestURL;
                    break;
                case "stage":
                    url = stageUrl;
                    superAdminUrl = superAdminStageURL;
                    adminUrl = adminStageURL;
                    apiUrl = apiStageURL;
                    break;
                case "preprod":
                    url = preprodUrl;
                    superAdminUrl = superAdminPreprodURL;
                    adminUrl = adminPreprodURL;
                    apiUrl = apiPreprodURL;
                    break;
                case "prod":
                    url = prodUrl;
                    superAdminUrl = superAdminProdURL;
                    adminUrl = adminProdURL;
                    apiUrl = apiProdURL;
                    break;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}