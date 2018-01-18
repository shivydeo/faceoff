package org.webonise.automation.core.verification;


import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import org.webonise.automation.core.WeboActions;
import org.webonise.automation.core.WeboAutomation;

public class Verification {
    org.webonise.automation.core.WeboActions actions;

    public Verification(WeboActions actions) {
        this.actions = actions;
    }

    public boolean checkTitle(String expected) {
        System.out.println("Title is        : " + actions.getTitle());
        System.out.println("Title should be : " + expected);
        if (!expected.equals(actions.getTitle())) {
            actions.takeScreenShot();
            WeboAutomation.extent.log(LogStatus.FAIL, "<br> Actual Title:" + actions.getTitle() + "<br> Expected Title:" + expected);
            Assert.assertTrue(false);
            return false;
        }
        return true;
    }

    public boolean assertion(String expectedString, String actualString) {
        System.out.println("Expected:" + expectedString);
        System.out.println("Actual:" + actualString);
        try {
            if (!expectedString.equals(actualString)) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
        return true;

    }

    public boolean verifyHeading(String expected, String actualText) {
        System.out.println("Heading is        : " + actualText);
        System.out.println("Heading should be : " + expected);
        if (!expected.equalsIgnoreCase(actualText)) {
            actions.takeScreenShot();
            WeboAutomation.extent.log(LogStatus.FAIL, "<br> Actual Heading:" + actualText + "<br> Expected Heading:" + expected);
            Assert.fail();
            return false;
        }
        return true;
    }

    public boolean verifyMessage(String expected, String actualText) {
        System.out.println("Message is        : " + actualText);
        System.out.println("Message should be : " + expected);
        if (!expected.equalsIgnoreCase(actualText)) {

            actions.takeScreenShot();
            WeboAutomation.extent.log(LogStatus.FAIL, "<br> Actual Message:" + actualText + "<br> Expected Message:" + expected);
            Assert.assertTrue(false);
            return false;
        }
        WeboAutomation.extent.log(LogStatus.PASS, "<br> Actual Message:" + actualText + "<br> Expected Message:" + expected);
        return true;
    }


    public boolean verifyTextLength(String text, int length) {

        if (text.length() != length) {
            actions.takeScreenShot();
            Assert.assertTrue(false);
            return false;
        }
        return true;
    }

    public boolean verifyAssertion(Object expected, Object actual, String messageToDisplay) throws Exception {
        if (expected.equals(actual)) {
            WeboAutomation.extent.log(LogStatus.INFO, "Verified texts :: Expected text as per requirement=" + expected + "and Actual text from UI=" + actual);
        } else {
            WeboAutomation.extent.log(LogStatus.FAIL, "Assertion failure found.Please find method details for::" + messageToDisplay);
            WeboAutomation.extent.log(LogStatus.FAIL, "Failed to verify texts :: <br> Actual Message:" + actual + "<br> Expected Message:" + expected);
            actions.takeScreenShot();
            Assert.assertTrue(false);
            return false;
        }
        return true;
    }

}
