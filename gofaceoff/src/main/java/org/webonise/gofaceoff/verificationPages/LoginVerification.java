package org.webonise.gofaceoff.verificationPages;

import java.util.HashMap;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.webonise.automation.core.WeboAutomation;
import org.webonise.gofaceoff.constants.LoginConstants;
import com.relevantcodes.extentreports.LogStatus;

public class LoginVerification extends WeboAutomation{
	
	public static void verifyLoginAppearance(){
			String inputId = LoginConstants.firstInputBox.getAttribute("id");
			verify.assertion("login", inputId);
	}
	
	public static void verifyInvalidErrorMessage() {
		weboActions.isPresent(LoginConstants.invalidLoginError, "INVALID EMAIL OR PASSWORD!");	
	}
	
	public static void verifyPasswordMasked() {
		String type = LoginConstants.inputPassword.getAttribute("type");
		verify.assertion("password", type);
	}
}
