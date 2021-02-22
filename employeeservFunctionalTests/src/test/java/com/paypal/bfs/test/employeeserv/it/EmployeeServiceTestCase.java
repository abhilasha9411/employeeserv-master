package com.paypal.bfs.test.employeeserv.it;

import com.jayway.restassured.http.ContentType;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationListener;

import javax.validation.constraints.NotNull;

import static com.jayway.restassured.RestAssured.given;

public class EmployeeServiceTestCase implements ApplicationListener<WebServerInitializedEvent> {
    @LocalServerPort
    private int employeeServicePort;

    public String getEmployeeById(int userId) {
        return given().port(employeeServicePort)
                .when().get("v1/bfs/employees/" + userId)
                .then().statusCode(200).contentType(ContentType.JSON).extract().asString();
    }

    public void saveEmployee(String body) {
        given().port(employeeServicePort).body(body).contentType(ContentType.JSON)
                .when().post("/v1/bfs/employees/")
                .then().statusCode(200);
    }

    @Override
    public void onApplicationEvent(@NotNull WebServerInitializedEvent webServerInitializedEvent) {
        this.employeeServicePort = webServerInitializedEvent.getWebServer().getPort();
    }
}
