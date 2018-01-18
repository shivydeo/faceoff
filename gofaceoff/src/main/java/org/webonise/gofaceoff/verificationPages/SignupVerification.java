package org.webonise.gofaceoff.verificationPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.webonise.automation.core.WeboAutomation;
import org.webonise.gofaceoff.constants.SignupConstants;

public class SignupVerification extends WeboAutomation{
	
	public static void verifyStateNames(List<String> illegalStates, List<WebElement> legalStates) {
		for(int i=0;i<illegalStates.size();i++) {
			for(int j=1;j<legalStates.size();j++) {
				Assert.assertFalse(illegalStates.get(i).equals(legalStates.get(j).getText()),"Error. Illegal state is present in dropdown list.");
			}
		}
	}
	
	public static void verifyMandatoryFields() {
		verify.verifyMessage("CAN'T BE BLANK", SignupConstants.firstNameMandatory.getText());
		verify.verifyMessage("CAN'T BE BLANK", SignupConstants.lastNameMandatory.getText());
		verify.verifyMessage("IS TOO SHORT (MINIMUM IS 3 CHARACTERS)", SignupConstants.userNameMandatory.getText());
		verify.verifyMessage("CAN'T BE BLANK", SignupConstants.emailMandatory.getText());
		verify.verifyMessage("CAN'T BE BLANK; IS TOO SHORT (MINIMUM IS 6 CHARACTERS)", SignupConstants.passwordMandatory.getText());
		verify.verifyMessage("YOU MUST BE 18 YEARS OF AGE OR OLDER TO REGISTER", SignupConstants.ageAbove18Mandatory.getText());
		verify.verifyMessage("PLEASE ACCEPT TERMS AND CONDITIONS TO SIGN UP", SignupConstants.acceptTCMandatory.getText());
	}
	
	public static void verifyIntitialChipsAmount() {
		verify.assertion("100", SignupConstants.chipsBalance.getText());
	}
	
	public static void verifyActivityBoxMessage() {
		verify.verifyMessage( SignupConstants.joinedFaceoffMessage.getText(), globalMap.get("userName")+" joined FaceOff.");
	}
}
