package org.keycloak.quickstart.storage.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;
import org.keycloak.quickstart.storage.user.dto.ApiumResponseDTO;
import org.keycloak.quickstart.storage.user.dto.EmployeeFilterDTO;
import org.keycloak.quickstart.storage.user.dto.EmployeeZUPDTO;
import org.keycloak.quickstart.storage.user.dto.KeycloakAuthResponseDTO;
import org.keycloak.quickstart.storage.user.dto.manzana.ManzanaUserDTO;
import org.keycloak.quickstart.storage.user.dto.manzana.ManzanaUserResponseDTO;
import org.keycloak.quickstart.storage.user.entity.ManzanaUser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestService {

    private static final Logger logger = Logger.getLogger(RestService.class);

    public static final String BASIC_AUTH = "QVBJdW0xMzEwODQ1NTU3MzYyNjU1MjMyOic7bzkteiMtYDpBWG0vZW1iNytFc1c8RSVJNC46fGJDRmMlZUUkQ0VAdzQqKCJgNSdp";
    public static final String GRANT_TYPE = "password";
    public static final String CLIENT_ID = "APIum1310845557362655232";
    public static final String USERNAME = "KfRwxNYogK";
    public static final String PASSWORD = "2.JqSC_1kLGJi_cGEyIXKHISqxRR2g";

    public static final String OAUTH_URL = "https://apium.varus.ua/oauth/token";

    public static final String GET_MANZANA_USER_BY_PHONE_URL="http://manzana-api.varus.ua/manzana-api/users/by-phone";

    public static final String GET_EMPLOYEE_DTO_ONLY_WORKING_PROCEDURE_URL =
            "https://apium.varus.ua/procedure/call/1310845557362655232/GET_EMPLOYEE_DTO_ONLY_WORKING";

    public static final String MANZANA_OAUTH_URL = "http://uac.net.omega:8080/auth/realms/staff/protocol/openid-connect/token";
    public static final String MANZANA_GRANT_TYPE = "client_credentials";
    public static final String MANZANA_CLIENT_ID = "manzana-api";
    public static final String MANZANA_CLIENT_SECRET = "e0835a80-0d4e-4b29-a6b4-dd255f20fadc";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ApiumResponseDTO loginToReports1C() throws IOException {

        HttpPost post = new HttpPost(OAUTH_URL);
        post.addHeader("content-type", "application/x-www-form-urlencoded");
        post.addHeader("Authorization", "Basic " + BASIC_AUTH);

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("grant_type", GRANT_TYPE));
        urlParameters.add(new BasicNameValuePair("client_id", CLIENT_ID));
        urlParameters.add(new BasicNameValuePair("username", USERNAME));
        urlParameters.add(new BasicNameValuePair("password", PASSWORD));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            return objectMapper.treeToValue(objectMapper.readTree(EntityUtils.toString(response.getEntity())), ApiumResponseDTO.class);
        }
    }

    private KeycloakAuthResponseDTO loginToManzanaApi() throws IOException {

        HttpPost post = new HttpPost(MANZANA_OAUTH_URL);
        post.addHeader("content-type", "application/x-www-form-urlencoded");

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("grant_type", MANZANA_GRANT_TYPE));
        urlParameters.add(new BasicNameValuePair("client_id", MANZANA_CLIENT_ID));
        urlParameters.add(new BasicNameValuePair("client_secret", MANZANA_CLIENT_SECRET));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        logger.info("loginToManzanaApi");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            return objectMapper.treeToValue(objectMapper.readTree(EntityUtils.toString(response.getEntity())), KeycloakAuthResponseDTO.class);
        }
    }

    public List<ManzanaUserDTO> getUserByPhone(String phone) throws IOException, URISyntaxException {

        URIBuilder builder = new URIBuilder(GET_MANZANA_USER_BY_PHONE_URL);
        builder.addParameter("phone", phone.replaceAll("[+]", ""));

        HttpGet get = new HttpGet(builder.build());
        get.addHeader("Authorization", "Bearer " + loginToManzanaApi().getAccessToken());

        logger.info("Send request getUserByPhone: " + phone.replaceAll("[+]", ""));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(get)) {

            return Arrays.asList(objectMapper.treeToValue(
                    objectMapper.readTree(EntityUtils.toString(response.getEntity())).get("value"),
                    ManzanaUserDTO[].class));
        }
    }

    public List<ManzanaUserDTO> getAllUsers() throws Exception {

        HttpPost post = new HttpPost(GET_EMPLOYEE_DTO_ONLY_WORKING_PROCEDURE_URL);

        post.addHeader("content-type", "application/json");
        post.addHeader("Authorization", "Bearer " + loginToReports1C().getAccess_token());

        logger.info("Send request getAllUsers");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            return Arrays.asList(objectMapper.treeToValue(
                    objectMapper.readTree(EntityUtils.toString(response.getEntity())).get("value"),
                    ManzanaUserDTO[].class));
        }
    }

}
