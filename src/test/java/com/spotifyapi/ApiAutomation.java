package com.spotifyapi;

import io.restassured.response.Response;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiAutomation {
   private static String tokenValue ="";
   public String id="";
  

    @Before
    public void setUp() {
        tokenValue = "Bearer BQA5q07_C5z6_T8geFQojrEcu_qZTucw_vJl7db5v5qZ2ncaR0GJOBmXT1T6u1oVnESBEWqUtPDqqWc-tRoA2J18tcS7q__UUOT4G5b02SyB2dvx3ngANXe8qvR-YHvqVAdB5Ab7hWSlg8rwebCDM4SMHHoJbre5HPim1Qp_MYxpMlB1hdfPrqCTCHb-wOUxf5tjG94fpIH_yDQ2Bd3GfysvbXAcvUzeYDgeqm3yP4RUYDKdfwKVEb1eiki3Ay4J9FnJwj_Y4d4k54IQ5jGi9b1pgFWMqo8AMnHNb4bx";
    }

    @Test
    public void spotify_RestAssured_Automation() {
    
        Response response = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .when()
                .get("https://api.spotify.com/v1/me");
        response.then().assertThat().statusCode(200);
        id = response.path("id");
        System.out.println("User ID "+id);
        response.prettyPrint();       
    }
    
    @Test
    public void User_Profile () {
    	Response response = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .when()
    			.get("https://api.spotify.com/v1/users/"+ id+"/");
    	response.then().assertThat().statusCode(200);
    			response.prettyPrint();
    }
    
    @Test
    public void post_playlist () {
    	Response response = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .body("{\\\"name\\\": \\\"Party_Mix_New\\\",\\\"description\\\": \\\"New playlist description\\\",\\\"public\\\": true}")
                .when()
                .post("https://api.spotify.com/v1/users/"+ id +"playlists");
    	String name = response.path("owner.display_name");
        System.out.println("Name Of Owner: " + name);
        response.then().assertThat().statusCode(201);
        response.prettyPrint();
    }
    
    
}