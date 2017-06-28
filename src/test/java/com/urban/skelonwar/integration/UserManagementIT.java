package com.urban.skelonwar.integration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.urban.skelonwar.SkelonwarApp;
import com.urban.skelonwar.config.ConfigForTest;
import com.urban.skelonwar.constants.GlobalConstants;

/**
 * Created by hurban on 23/05/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SkelonwarApp.class, ConfigForTest.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserManagementIT {

    @Value("${local.server.port}")
    private int port;

    @Value("#{servletContext.contextPath}")
    private String servletContextPath;

    @Autowired
    DataSource ds;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = servletContextPath;
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(context.getResource
                ("classpath:/insert-data.sql"));
        DatabasePopulatorUtils.execute(populator, ds);
    }

    @Test
    public void getAccountTest() {
        String token = given()
                .accept(ContentType.JSON)
                .log().all()
                .body("{\"username\":\"hurban\",\"password\":\"test123\"}")
                .when()
                .post(GlobalConstants.LOGIN_URI)
                .getHeader("Authorization");





        given()
                .accept(ContentType.JSON)
                .log().all()
        .when()
                .header("Authorization", token)
               .get(GlobalConstants.ACCOUNT_URI + "/2")
        .then()
               .log().all()
               .statusCode(200)
               .body("username", equalTo("hurban"));
    }

}
