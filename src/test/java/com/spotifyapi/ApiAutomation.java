package com.spotifyapi;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ApiAutomation {
   private static String tokenValue ="";
   private static String id="";
   public String name = "";
  

    @BeforeMethod
    public void setUp() {
        tokenValue = "Bearer BQD7S_6EjIq1tMy_U3BJbnWa7oEC9QetpBmcE4R60ApL8omZ7-OZxoSALgIN176iVHYx-_UAbId63Mk4IOvaSO9Vd8R0lqyFHzFjAUcf9n4BbKmIypTp0jwXRVlTLK8nwXg1CKNM0fe4v7H3VUX5CqR752JsQfquVCRh7PA-gpHxdTQ7KdFJOjMFsssN3k3nKXrMC7P_HOzFVagey6k1piwL7FLAuPwy1RN-VJPh5REcX-1KMb68tUgsbJ0DT1qzVnzG6hjzeOOEqxhuXv3SmSQ5FC_xSRb20LbE58XN";
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
        
        ResponseBody body = response.getBody();
        System.out.println("Rsponse body is"+body.asString());
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
    }
}