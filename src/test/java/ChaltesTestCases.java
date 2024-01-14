import helper.DateHelper;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class ChaltesTestCases {
GetLookups getLookups = new GetLookups();
SearchChalets searchChalets = new SearchChalets();
DateHelper dateHelper = new DateHelper();
JSONObject Body = new JSONObject();
    @Test
    public void verifyGetCitiesLockup(){
     Response response= getLookups.getLookups();
     getLookups.verifyLookupAPIResponse(response);
    }

    @Test
    public void verifySearchChaletsPositiveCase() throws JSONException {
        JSONArray lookupInfo = new JSONArray();
        JSONObject lookupObject = new JSONObject();
        String[] lookupId = {"24"};
        lookupObject.put("lookupTypeId", 2);
        lookupObject.put("lookupId", lookupId);
        lookupInfo.put(lookupObject);
        Body.put("checkIn",dateHelper.getDateAfterDays(10));
        Body.put("checkOut",dateHelper.getDateAfterDays(12));
        Body.put("searchCriteria",lookupInfo);
        Response response = searchChalets.chaletsSearchCall(Body.toString());
        searchChalets.verifySearchChaletsAPIResponse(response,200,"HTTP/1.1 200 OK");

    }

    @Test
    public void verifySearchChaletsNegativeCaseDates() throws JSONException {
        JSONArray lookupInfo = new JSONArray();
        JSONObject lookupObject = new JSONObject();
        String[] lookupId = {"24"};
        lookupObject.put("lookupTypeId", 2);
        lookupObject.put("lookupId", lookupId);
        lookupInfo.put(lookupObject);
        Body.put("checkIn","2023-01-16");
        Body.put("checkOut","2023-01-17");
        Body.put("searchCriteria",lookupInfo);
        Response response = searchChalets.chaletsSearchCall(Body.toString());
        searchChalets.verifySearchChaletsAPIResponse(response,400,"HTTP/1.1 400 Bad Request");

    }
   @Test
    public void verifySearchChaletsNegativeCaseLookups() throws JSONException {
        JSONArray lookupInfo = new JSONArray();
        JSONObject lookupObject = new JSONObject();
        String[] lookupId = {"24"};
        lookupObject.put("lookupTypeId", 20000);
        lookupObject.put("lookupId", lookupId);
        lookupInfo.put(lookupObject);
        Body.put("checkIn","2024-01-16");
        Body.put("checkOut","2024-01-17");
        Body.put("searchCriteria",lookupInfo);
       Response response = searchChalets.chaletsSearchCall(Body.toString());
       searchChalets.verifySearchChaletsAPIResponse(response,400,"HTTP/1.1 400 Bad Request");

    }



}
