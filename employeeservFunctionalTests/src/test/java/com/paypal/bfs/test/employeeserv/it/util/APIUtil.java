package com.paypal.bfs.test.employeeserv.it.util;

import java.util.Map;
import net.sf.json.JSONObject;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.with;

public class APIUtil {
    public Response executePOST(String baseURL, String path, String requestBody) {
        String url = createURL(baseURL, path);

        JSONObject body = createRequestBody(requestBody);
        Response response = with().contentType(ContentType.JSON).body(body).when().post(url).then().extract().response();

        return response;
    }

    public Response executeGET(String baseURL, String path) {
        String url = createURL(baseURL, path);

        Response response = with().expect().when().get(url).then().extract().response();
        return response;
    }

    public String createURL(String baseURL, String pathVariable) {
        String url = baseURL;
        if (pathVariable != null && !"null".equalsIgnoreCase(pathVariable))
            url += "/" + pathVariable;

        return url;
    }

    public JSONObject createRequestBody(String body) {

        JSONObject json = null;
        if (body != null)
            json = JSONObject.fromObject(body);

        return json;
    }
}
