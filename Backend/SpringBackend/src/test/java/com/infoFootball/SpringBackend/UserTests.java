package com.infoFootball.SpringBackend;

import com.infoFootball.SpringBackend.Register.RegisterController;
import com.infoFootball.SpringBackend.User.User;
import com.infoFootball.SpringBackend.User.UserRepository;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import io.restassured.RestAssured;
import org.json.JSONObject;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UserTests {

    @LocalServerPort
    int port;

    @Autowired
    UserRepository userRepository;

    //This will run before every test
    @Before
    public void Setup(){
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";

    }

    private String username = "testUser";
    private String password = "testPassword";
    private String email = "testEmail@email";
    private String phoneNum = "123456789";
    private String permLv = "Admin";

    @Test
    public void testCreateUser() throws Exception {
        //Creating user JSON object
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("username", username);
        jsonUser.put("password",password);
        jsonUser.put("email",email);
        jsonUser.put("phoneNum",phoneNum);
        jsonUser.put("permLv",permLv);

        //Create a post request
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonUser.toString()) //Send in json USER
                .when().request("POST", "/login/register"); //The url and request type

        //Checking status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        //Check that it returns a success
        String returnString = response.getBody().asString();
        assertEquals("Success", returnString);
    }

    @Test
    public void testGetUser() throws Exception {
        //Get request
        Response response = RestAssured.given()
                .when()
                .request("GET", "/users/testUser");

        //Checking status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        //Compare to values of user that was created
        assertEquals(username, response.jsonPath().get("username"));
        assertEquals(password, response.jsonPath().get("password"));
        assertEquals(email, response.jsonPath().get("email"));
        assertEquals(phoneNum, response.jsonPath().get("phoneNum"));
        assertEquals(permLv, response.jsonPath().get("permLv"));
    }

    @Test
    public void updateUser() throws Exception {
        //Value for the updated password
        String newPassword = "superStrongPassword!";

        //Creating user JSON object to update user
        JSONObject updatedJsonUser = new JSONObject();
        updatedJsonUser.put("username", username);
        updatedJsonUser.put("password",newPassword);
        updatedJsonUser.put("email",email);
        updatedJsonUser.put("phoneNum",phoneNum);
        updatedJsonUser.put("permLv",permLv);

        //Create a put request
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(updatedJsonUser.toString()) //Send in json USER
                .when().request("PUT", "/users/" + username); //The url and request type

        //Checking status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        //Compare to values of user that was created
        assertEquals(newPassword, response.jsonPath().get("password"));
    }

    @Test
    public void loginTest() {
        String password = this.password;

        //Create a get request
        Response response = RestAssured.given()
                .when().request("GET", "/login/" + username); //The url and request type

        //Checking status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        assertEquals(username, response.jsonPath().get("username"));
        assertEquals(password, response.jsonPath().get("password"));
    }


//      This case doesnt work
//    @Test
//    public void deleteUser() {
//        Response response = RestAssured.given()
//                .delete("/users/testUser");
//                //.when().request("DELETE", "/users/testUser"); //The url and request type
//
//        assertEquals("Success", response.getBody().asString());
//    }

}
