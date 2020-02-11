import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SpotifyCRUDTest {
    public static String tokenValue = "Bearer BQCNo9_hN1Cs6jNJJyF1odFUm8MZp3sd-8UfhsdtvkE2ADDwxBVxJJcWuWKc3QV7FilRT1OWncHJ83hAErxeH2MCBk97h0nFaU9bWZsARvXTTFg21y5XawP6MGAqsoF8vC15H6Q4R2qvfJ77FbvfrJoPLuzpeTDypklzcpgnogSDCwfwUUKceIjcLpmTW0KCWLaid0Wb87qEgG5angdPtETiIVrZq3kJrtS59y7mxNjfgdMBGudlzi_IJjaSnhrarn4msJirhf2QB-tqhrwmH6dEGKF6gg";
    String userId = "";
    String playlistId = "";

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://api.spotify.com/";
    }

    @Test
    public void givenApiUnderExecution_WhenPassedTokenAndOtherDetails_ShouldPerformCRUDOperations() {
        Response responseOfCurrentUser = RestAssured.given()
                .accept("application/json")
                .contentType(ContentType.JSON)
                .header("Authorization", tokenValue)
                .when()
                .get("v1/me");
        userId = responseOfCurrentUser.path("id");
        responseOfCurrentUser.then().assertThat().statusCode(200);
        System.out.println("checking userId of current user = " + userId);

        Response playlistRecordsResponse = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .when()
                .get("v1/me/playlists");
        playlistRecordsResponse.toString();
        int totalPlaylist = playlistRecordsResponse.path("total");
        playlistRecordsResponse.then().assertThat().statusCode(200);
        System.out.println("existing playlist count is = " + totalPlaylist);

        Response newPlaylistResponse = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .body("{\"name\":\"New1 Playlist111\",\"description\":\"New1 playlist descriptionnmn\",\"public\":false}")
                .pathParam("user_id", userId)
                .when()
                .post("v1/users/{user_id}/playlists");
        playlistId = newPlaylistResponse.path("id");
        newPlaylistResponse.then().assertThat().statusCode(201);
        System.out.println("make new playlist and retrieve playlistId = " + playlistId);

        Response updatedPlaylistResponse = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .when()
                .get("v1/me/playlists");
        updatedPlaylistResponse.toString();
        int expectedUpdatedTotal = updatedPlaylistResponse.path("total");
        int actualUpdatedTotal = (totalPlaylist + 1);
        Assert.assertEquals(actualUpdatedTotal, expectedUpdatedTotal);
        updatedPlaylistResponse.then().assertThat().statusCode(200);
        System.out.println("updated playlist count is = " + actualUpdatedTotal);

        Response updateExistingPlaylist = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .body("{\"name\":\"Updated Playlist\",\"description\":\"Updated playlist description\",\"public\":false}")
                .pathParam("playlist_id", playlistId)
                .when()
                .put("v1/playlists/{playlist_id}");
        updateExistingPlaylist.then().assertThat().statusCode(200);
        System.out.println("updated details of playlistId = " + playlistId);
    }
}

