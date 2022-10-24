import io.restassured.http.ContentType;
import io.restassured.response.Response;

import models.CreateUserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import services.DataGenerator;
import services.GoRestService;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateUserTests {

    static Integer userID;
    final CreateUserModel createUserModel = new CreateUserModel(
            DataGenerator.generateName("female"), "female", DataGenerator.generateEmail(15), "Active"
    );

    @Test
    @Order(0)
     //validate creation of a resource
    public void Users_CreateUsers_Success(){
        Response response = GoRestService.createUser(createUserModel);
        response.then()
                .statusCode(SC_CREATED)
                .body("data.id", notNullValue())
                .body("data.name", equalTo(createUserModel.getName()));
        Assertions.assertEquals(SC_CREATED, response.statusCode());
        System.out.println("POST\n"+response.asPrettyString());
        // Extract id from response
        userID = response.body().jsonPath().getInt("data.id");
    }

    @Test
    @Order(1)
    //validate Retrieval of  a resource
    public void Get_User_Details(){
        Response response = GoRestService.getUserDetails(createUserModel, userID);
        response.then()
                .contentType(ContentType.JSON)
                .statusCode(SC_OK);
        Assertions.assertEquals(SC_OK, response.statusCode());
        Assertions.assertNotNull(response.body());
        System.out.println("GET\n"+response.asPrettyString());
    }

    @Test
    @Order(2)
     //validate modification/edition of partial resource
    public void PATCH_Update_User_Details(){
        createUserModel.setName("Alexandra Mary");
        createUserModel.setGender("Female");
        Response response = GoRestService.UpdateUserDetails("PATCH", createUserModel, userID);
        response.then()
                .statusCode(SC_OK);
        Assertions.assertEquals(SC_OK,response.statusCode());
    }
    @Test
    @Order(3)
   // validate edit of entire resource
    public void PUT_Update_User_Details(){
        createUserModel.setName("Allan Turing");
        createUserModel.setGender("Female");
        createUserModel.setEmail(DataGenerator.generateEmail(15));
        createUserModel.setStatus("inactive");
        Response response = GoRestService.UpdateUserDetails("PUT", createUserModel, userID);
        response.then()
                .statusCode(SC_OK);
        Assertions.assertEquals(SC_OK, response.statusCode());
        System.out.println("PUT\n"+response.asPrettyString());
    }
    @Test
    @Order(4)
     //validate delete of a resource
    public void Delete_User(){
        Response response = GoRestService.deleteUser(userID);
        response.then()
                .statusCode(SC_NO_CONTENT);
        Assertions.assertEquals(SC_NO_CONTENT,response.statusCode());
        System.out.println("DELETE\n"+response.asPrettyString());
    }
}
