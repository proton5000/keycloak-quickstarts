package org.keycloak.quickstart.storage.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;
import org.keycloak.quickstart.storage.user.dto.ApiumResponseDTO;
import org.keycloak.quickstart.storage.user.dto.EmployeeFilterDTO;
import org.keycloak.quickstart.storage.user.dto.EmployeeZUPDTO;

import java.io.IOException;
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
    public static final String GET_EMPLOYEE_WITH_FILTER_PROCEDURE_URL =
            "https://apium.varus.ua/procedure/call/1310845557362655232/GET_EMPLOEE_WITH_FILTER";
    public static final String GET_EMPLOYEE_DTO_ONLY_WORKING_PROCEDURE_URL =
            "https://apium.varus.ua/procedure/call/1310845557362655232/GET_EMPLOYEE_DTO_ONLY_WORKING";

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

    public List<EmployeeZUPDTO> getUserByPhone(String phone) throws IOException {

        HttpPost post = new HttpPost(GET_EMPLOYEE_WITH_FILTER_PROCEDURE_URL);

        post.addHeader("content-type", "application/json");
        post.addHeader("Authorization", "Bearer " + loginToReports1C().getAccess_token());

        EmployeeFilterDTO employeeFilterDTOn = new EmployeeFilterDTO();
        employeeFilterDTOn.setPhone(phone);

        post.setEntity(new StringEntity(objectMapper.writeValueAsString(employeeFilterDTOn)));

        logger.info("Send request getUserByPhone: " + phone);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            return Arrays.asList(objectMapper.treeToValue(
                    objectMapper.readTree(EntityUtils.toString(response.getEntity())).get("data"),
                    EmployeeZUPDTO[].class));
        }
    }

    public List<EmployeeZUPDTO> getAllUsers() throws Exception {

        HttpPost post = new HttpPost(GET_EMPLOYEE_DTO_ONLY_WORKING_PROCEDURE_URL);

        post.addHeader("content-type", "application/json");
        post.addHeader("Authorization", "Bearer " + loginToReports1C().getAccess_token());

        logger.info("Send request getAllUsers");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            return Arrays.asList(objectMapper.treeToValue(
                    objectMapper.readTree(EntityUtils.toString(response.getEntity())).get("data"),
                    EmployeeZUPDTO[].class));
        }
    }

}
