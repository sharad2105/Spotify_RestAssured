package com.spotifyapi;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ApiAutomation {
   private static String tokenValue ="";
   private static String id="";
   private static String playlist_id = "";
  
    @BeforeMethod
    public void setUp() {
        tokenValue = "Bearer BQAuSAly_fKmA9Zy0vytiJ6_NIYbi8u86lVuMYK4p-8tymDmK6kS6EYvIzgzj5wzAOOzOliVHejAiUQJTTT1_-FCxFJB8ZisDywXmoLy37Sv6RRBj8V4kSHJ0upv_1PP6lFbyR0lL17n6h7Y2R0TmhgamT5NHg3fvwHsO3bszDXEJWzp1Lsi4s9NzEWn5Sqc1c_-F7fgkZv0YllVNMpGGGFSkRLoISDS4wrmdIu6eRziC1LG8Bp5J2Miq9QUry-iTdRIoOOVfYCYmpMAXvlMv0aleLHvaNp1PX2lWyKR";
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
    			.get("https://api.spotify.com/v1/users/ulwt9ws4u0srlb1cm9bfe811o/");
    	response.then().assertThat().statusCode(200);
    			response.prettyPrint();
    }
   
    @Test
    public void post_playlist () {
    	Response response = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .body("{\"name\": \"Sanu Kumar\",\"description\": \"old love song\",\"public\": true}")
                .when()
                .post("https://api.spotify.com/v1/users/ulwt9ws4u0srlb1cm9bfe811o/playlists");
 
        response.then().assertThat().statusCode(201);
        response.prettyPrint();
        playlist_id = response.path("id");
        System.out.println("playlist  ID "+playlist_id);
    }
    
    @Test
    public void put_playlist () {
    	Response response = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", tokenValue)
                .body("{\"name\": \"Asha bhosle\",\"description\": \"old romantic song\",\"public\": true}")
                .when()
                .post("https://api.spotify.com/v1/playlists/4jAskm3sOmfNNlHhLHq2kB");
        response.prettyPrint();
    }
    
    @Test
    public void delete() {
    	Response response = given()
    			.header("Authorization", tokenValue)
                .when()
                .delete("https://api.spotify.com/v1/playlists/4jAskm3sOmfNNlHhLHq2kB/tracks");
        response.prettyPrint();
    }
}
  