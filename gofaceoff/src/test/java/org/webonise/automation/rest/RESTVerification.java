package org.webonise.automation.rest;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;

import org.webonise.automation.core.CommonUtility;


public class RESTVerification {
    JSONObject jsonResponse, jsonObject = null;
    JSONArray jsonArray = new JSONArray();

    public RESTVerification() {
    }

    /*
     @Method = retrieve_AuthToken
     @description = Method to retrieve authentication token from the response body
     @param	= 1. resonse - Response generated after invoking URL
     @return = accessToken for further execution
    */
    public String retrieveAuthToken(String response) throws JSONException {
        jsonResponse = new JSONObject(response);
        String accessToken = jsonResponse.getString("authToken");
        return accessToken;

    }

    public String retrievefinalPScore(String response) throws JSONException {
        try {
            jsonResponse = new JSONObject(response);
            JSONObject finalPScoreObject = jsonResponse.getJSONObject("algoOutput");
            JSONArray finalPScore = finalPScoreObject.getJSONArray("finalPScore");
            return finalPScore.toString();
        } catch (Exception ex) {
            System.out.println("eorrr " + ex);
            return "";
        }

    }

    public String retrievePeriodAvgScore(String response) throws JSONException {
        try {
            jsonResponse = new JSONObject(response);
            JSONObject periodAvgScoreObject = jsonResponse.getJSONObject("algoOutput");
            JSONArray periodAvgScore = periodAvgScoreObject.getJSONArray("periodAvgScore");
            return periodAvgScore.toString();
        } catch (Exception ex) {
            System.out.println("eorrr " + ex);
            return "";
        }

    }

    /*
     @Method = updateURL
     @description = Method to update URL which is to be invoked
     @param	= 1. urlToInvoke - Basic url which is to be invoked
               2. commonHashMap - Values for newUrl which has to be replaced
     @return = newUrlToInvoke - Updated url for invocation
    */
    public String updateURL(String urlToInvoke, HashMap<String, String> commonHashMap) {
        String newUrlToInvoke = urlToInvoke;
        if (urlToInvoke.contains("#")) {
            newUrlToInvoke = newUrlToInvoke.replace("#", "&");
        }
        if (urlToInvoke.contains("{userId}")) {
            newUrlToInvoke = newUrlToInvoke.replace("{userId}", commonHashMap.get("userId"));
        }
        if (urlToInvoke.contains("{username}")) {
            newUrlToInvoke = newUrlToInvoke.replace("{username}", commonHashMap.get("username"));
        }
        if (urlToInvoke.contains("{organizationId}")) {
            newUrlToInvoke = newUrlToInvoke.replace("{organizationId}", commonHashMap.get("organizationId"));
        }
        if (urlToInvoke.contains("{roleId}")) {
            newUrlToInvoke = newUrlToInvoke.replace("{roleId}", commonHashMap.get("roleId"));
        }
        if (urlToInvoke.contains("{geofenceId}")) {
            newUrlToInvoke = newUrlToInvoke.replace("{geofenceId}", commonHashMap.get("geofenceId"));
        }
        if (urlToInvoke.contains("{userToken}")) {
            newUrlToInvoke = newUrlToInvoke.replace("{userToken}", commonHashMap.get("userToken"));
        }
        System.out.println("" + newUrlToInvoke);
        return newUrlToInvoke;

    }

    /*
     @Method = updatePOSTRequestBody
     @description = Method to update request body
     @param	= 1. requestBody - Request body which is to be updated
     @return = requestBody - Updated request body for execution
    */
    public String updatePOSTRequestBody(String requestBody) throws JSONException {
        String newRequestBody = requestBody;
        if (requestBody.contains("#phoneNumber")) {
            newRequestBody = requestBody.replace("#phoneNumber", CommonUtility.dateFormat("phoneNumber"));
        }
        return newRequestBody;

    }

    public int getUserId(String responseBody, String expectedUserName) throws JSONException {
        String userNameFromResponseBody = null;
        int userID = 0;
        jsonResponse = new JSONObject(responseBody);
        jsonArray = jsonResponse.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            userNameFromResponseBody = jsonObject.getString("username");
            if (userNameFromResponseBody.equalsIgnoreCase(expectedUserName)) {
                userID = jsonObject.getInt("id");
                break;
            }
        }
        return userID;

    }

    public int getAttributeValues(String responseBody, String expectedUserName, String[] attributeValueToRetrieve) throws JSONException {
        int userID = 0;
        String res[];
        jsonResponse = new JSONObject(responseBody);
        res = JSONObject.getNames(jsonResponse);
        for (int loopCounter = 0; loopCounter < res.length; loopCounter++) {
            if (jsonResponse.get(res[loopCounter]).toString().equals(expectedUserName)) {
                for (int i = 0; i < attributeValueToRetrieve.length; i++) {

                }
            }
        }
        return userID;

    }

    /**
     * verifyJSONResponse           This method verifies the JSON response
     *
     * @param ActualResponse  Generated response after executing the method
     * @param ExpectedStatus  Expected status code for verification
     * @param ExpectedMessage Expected message for verification
     * @return boolean                true - If status and message are equal
     * false - If status and message are not equal
     * @throws Exception
     */
    public boolean verifyJSONResponse(String ActualResponse, String ExpectedStatus, String ExpectedMessage) {
        boolean isVerified = false;
        try {
            JSONObject actualResponseObject = new JSONObject(ActualResponse);
            JSONObject obj = actualResponseObject.getJSONObject("response");
            String messageOutput = obj.getString("message");
            String expectedStatusOutput = obj.getInt("status") + "";
            if (messageOutput.equalsIgnoreCase(ExpectedMessage.trim()) && ExpectedStatus.equalsIgnoreCase(expectedStatusOutput)) {
                isVerified = true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return isVerified;
    }

    /**
     * verifyJSONResponse_LATAS     This method verifies the JSON response for latas
     *
     * @param ActualResponse  Generated response after executing the method
     * @param ExpectedStatus  Expected status code for verification
     * @param ExpectedMessage Expected message for verification
     * @return boolean                true - If status and message are equal
     * false - If status and message are not equal
     * @throws Exception
     */
    public boolean verifyJSONResponse_LATAS(String ActualResponse, String ExpectedValueForAttribute) {
        boolean isVerified = false;
        try {
            JSONObject actualResponseObject = new JSONObject(ActualResponse);
            String expectedStatusOutput = actualResponseObject.getBoolean("status") + "";
            if (ExpectedValueForAttribute.equalsIgnoreCase(expectedStatusOutput)) {
                isVerified = true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return isVerified;
    }

    /**
     * **********************************************************************************************************************************
     * Verification of jsonArray->JsonObjects
     * [
     * {
     * color: "red",
     * value: "#f00"
     * },
     * {
     * color: "green",
     * value: "#0f0"
     * }
     * ]
     * ************************************************************************************************************************************
     */
    public boolean verifyJSONArrayWithJSONObjects(String ActualResponse, String ExpectedStatus, String ExpectedMessage) throws JSONException {
        boolean isVerified = false;
        JSONArray actualResponseObject = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        String valueToFetch;
        String[] valuesForVerification = ExpectedStatus.split(",");
        for (int i = 0; i < actualResponseObject.length(); i++) {
            jsonObject = actualResponseObject.getJSONObject(i);
            valueToFetch = jsonObject.getString("color");
            Assert.assertEquals(valuesForVerification[i], valueToFetch);
        }
        return isVerified;
    }
}
