package services;

import io.restassured.response.Response;
import models.CreateUserModel;


public class GoRestService extends BaseService {

    public static Response createUser(final CreateUserModel createUserModel){
        return defaultRequestSpecification()
                .body(createUserModel)
                .when()
                .post("/public/v1/users");
    }
    public static Response getUserDetails(final CreateUserModel createUserModel, Integer id){
        return defaultRequestSpecification()
                .when()
                .get("/public/v1/users/"+id);
    }

    public static Response UpdateUserDetails(final String method, final CreateUserModel createUserModel, Integer userID){
        if(method.equalsIgnoreCase("PATCH")) {
            return defaultRequestSpecification()
                    .given()
                    .body(createUserModel)
                    .when()
                    .patch("/public/v1/users/"+userID);
        }else if(method.equalsIgnoreCase("PUT")){
            return defaultRequestSpecification()
                    .body(createUserModel)
                    .when()
                    .put("/public/v1/users/"+userID);
        }
        return null;
    }

    public static Response deleteUser(Integer id){
        return defaultRequestSpecification()
                .when()
                .delete("/public/v1/users/"+id);
    }
}
