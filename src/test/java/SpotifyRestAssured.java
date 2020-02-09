import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SpotifyRestAssured {
    public static String tokenValue = "Bearer BQASUG1x3LilY3JS48Tmbd0gosbscrrM4XjBvhf1Q4Qdru1yreh-5KQsrFEoZKBWf4KzleTMLE_Fj8D6m3sdlZFdW-7vPmxAJzOoSU61iFZSfXrx24koSe0hjc_Y9G2CCiYmBqn6enq__7TQaleVQhpNJc6-y5s5wy2WN2wgpO_fncTldp64_mKFJ--noYFH536g8a3gLoL7FAJI5SBHfwakOP62Iq5p8f5WRzqMWwNUp61n2RozrQTU-OJ9Ww1AlBP6rxWntJs3sv_wbgZzD5pW0jN5aw";
    String userId = "";
    String playlistId = "";

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://api.spotify.com/";
    }

    @Test
    public void givenApiUnderExecution_WhenPassedTokenToApi_ShouldGiveTheCurrentUsersProfile() throws ParseException {
        Response responseOfUser = RestAssured.given()
                .accept("application/json")
                .contentType(ContentType.JSON)
                .header("Authorization", tokenValue)
                .when()
                .get("v1/me");
        userId = responseOfUser.path("id");
        responseOfUser.then().assertThat().statusCode(200);
        System.out.println("checking userId----------------"+userId);

        Response userResponse = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .pathParam("user_id", userId)
                .when()
                .get("v1/users/{user_id}");
        userResponse.then().assertThat().statusCode(200);

        Response playlistRecordsResponse = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .when()
                .get("v1/me/playlists");
        playlistRecordsResponse.toString();
        int totalPlaylist = playlistRecordsResponse.path("total");
        Assert.assertEquals(51, totalPlaylist);
        playlistRecordsResponse.then().assertThat().statusCode(200);
        System.out.println("new playlist with---------------------------------"+ totalPlaylist);

        Response newPlaylistResponse = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .body("{\"name\":\"New1 Playlist11\",\"description\":\"New1 playlist descriptionnmn\",\"public\":false}")
                .pathParam("user_id", userId)
                .when()
                .post("v1/users/{user_id}/playlists");
        playlistId = newPlaylistResponse.path("id");
        newPlaylistResponse.then().assertThat().statusCode(201);
        System.out.println(playlistId + "retrieve playlist by id--------------------------------------"+ playlistId);

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
        System.out.println("updated playlist count-----------------------------------"+ actualUpdatedTotal);

        Response updateExistingPlaylist = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .body("{\"name\":\"Updated Playlist\",\"description\":\"Updated playlist description\",\"public\":false}")
                .pathParam("playlist_id", playlistId)
                .when()
                .put("v1/playlists/{playlist_id}");
        updateExistingPlaylist.then().assertThat().statusCode(200);
        System.out.println("updating playlist of id-----------------------------------"+ playlistId);
    }
}

