package com.paypal.bfs.test.employeeserv.it.function;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.paypal.bfs.test.employeeserv.EmployeeservApplication;
import com.paypal.bfs.test.employeeserv.it.EmployeeServiceTestCase;
import net.sf.json.test.JSONAssert;
import org.json.JSONException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = EmployeeservApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "management.server.port=0" })
@ActiveProfiles("test")
public class EmployeeServiceIntegrationTest extends EmployeeServiceTestCase {

    private final String employeePostRequest = readFile("src/test/resources/json/employeePostRequest");
    private final String expectedEmployeeResponse = readFile("src/test/resources/json/employeeResponse.json");
    @Rule
    public WireMockRule contactsServiceMock = new WireMockRule(options().port(8777));

    public EmployeeServiceIntegrationTest() throws IOException, JSONException {
    }

    public static String readFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return content;
    }

    @Test
    public void shouldSaveEmployeeDetailAndRetrieve() throws Exception {
        saveEmployee(employeePostRequest);
        String actualEmployeeResponse =getEmployeeById(1);
        JSONAssert.assertEquals(expectedEmployeeResponse, actualEmployeeResponse);
    }

}
