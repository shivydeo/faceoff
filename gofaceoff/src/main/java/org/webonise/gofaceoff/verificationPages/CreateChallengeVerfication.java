package org.webonise.gofaceoff.verificationPages;

import org.webonise.automation.core.WeboAutomation;
import org.webonise.gofaceoff.constants.CreateChallengeConstants;

public class CreateChallengeVerfication extends WeboAutomation {
	
	public static void verifyMatchSearchResult(String searchUsername) {
		verify.assertion(searchUsername, CreateChallengeConstants.firstSearchResult.getText() );
	}
	
	public static void verifyErrorPopupIsNotPresent() {
		weboActions.chkVisible(CreateChallengeConstants.challengeCreatedPopup);
	}
	
	public static void verifyStakeZeroError() {
		verify.verifyMessage("YOUR STAKE SHOULD BE GREATER THAN 0", CreateChallengeConstants.stakeErrorMessage.getText());
	}
	
	public static void verifyOpenChallenge(String homeTeam, String awayTeam, String market, String status) {
		verify.assertion(globalMap.get(homeTeam), homeTeam);
		verify.assertion(globalMap.get(awayTeam), awayTeam);
		verify.assertion(globalMap.get(market), market);
		verify.assertion(globalMap.get(status), status);
	}
	
	
}
