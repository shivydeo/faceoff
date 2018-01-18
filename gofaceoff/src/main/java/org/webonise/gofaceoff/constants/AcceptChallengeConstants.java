package org.webonise.gofaceoff.constants;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AcceptChallengeConstants {

		@FindBy(xpath="//div[@class='challengesList']")
		public static List<WebElement> openChallengeList;
		
		
}
