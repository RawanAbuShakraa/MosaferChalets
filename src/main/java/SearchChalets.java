import helper.PropertiesReader;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class SearchChalets {

    public Response chaletsSearchCall(String body) {

        return given().when().log().all()
                .header("Content-Type", "application/json")
                .body(body)
                .baseUri(PropertiesReader.getProperty("url"))
                .relaxedHTTPSValidation()
                .post(PropertiesReader.getProperty("chalets-search-path"));

    }
    public void verifySearchChaletsAPIResponse(Response response,int statusCode,String status){
        Assert.assertNotNull(response);
        response.then().log().all().assertThat().statusCode(statusCode);
        response.then().log().all().assertThat().statusLine(status);
    }


}
