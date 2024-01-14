import helper.PropertiesReader;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class GetLookups {

    public Response getLookups() {

        return given().when().log().all()
                .header("Content-Type", "application/json")
                .baseUri(PropertiesReader.getProperty("url"))
                .relaxedHTTPSValidation()
                .get(PropertiesReader.getProperty("lookups-path"));
    }

    public void verifyLookupAPIResponse(Response response){
        Assert.assertNotNull(response);
        response.then().log().all().assertThat().statusCode(200);
        response.then().log().all().assertThat().statusLine("HTTP/1.1 200 OK");
    }
}
