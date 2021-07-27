package org.keycloak.quickstart.storage.user.service;

import org.jboss.logging.Logger;
import org.keycloak.quickstart.storage.user.dto.ApiumResponseDTO;
import org.keycloak.quickstart.storage.user.dto.EmployeeFilterDTO;
import org.keycloak.quickstart.storage.user.dto.EmployeeZUPDTO;
import org.keycloak.quickstart.storage.user.dto.PaginationDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

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

    private ApiumResponseDTO loginToReports1C() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(BASIC_AUTH);

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", GRANT_TYPE);
        parameters.add("client_id", CLIENT_ID);
        parameters.add("username", USERNAME);
        parameters.add("password", PASSWORD);

        ResponseEntity<ApiumResponseDTO> responseEntity = new RestTemplate().exchange(OAUTH_URL, HttpMethod.POST,
                new HttpEntity<>(parameters, headers), ApiumResponseDTO.class);

        return responseEntity.getBody();
    }

    public <T> List<T> processResponse(ResponseEntity<PaginationDTO<T>> responseEntity) throws Exception {
        if (Objects.requireNonNull(responseEntity.getBody()).getStatus().equals("ok")
                && Objects.nonNull(responseEntity.getBody().getValue())) {
            return responseEntity.getBody().getValue();
        } else if (responseEntity.getBody().getStatus().equals("error")) {
            throw new Exception(responseEntity.getBody().getCauseMessage());
        } else if (responseEntity.getBody().getStatus().equals("fail")) {
            throw new Exception(responseEntity.getBody().getCauseMessage());
        } else if (StringUtils.isEmpty(responseEntity.getBody().getError())) {
            throw new Exception(responseEntity.getBody().getCauseMessage());
        } else {
            throw new Exception();
        }
    }

    public List<EmployeeZUPDTO> getUserByPhone(String phone) throws Exception {
        EmployeeFilterDTO employeeFilterDTO = new EmployeeFilterDTO();
        employeeFilterDTO.setPhone(phone);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(loginToReports1C().getAccess_token());

        HttpEntity<EmployeeFilterDTO> httpEntity = new HttpEntity<>(employeeFilterDTO, headers);

        logger.info("Send request getUserByPhone: " + phone);

        return processResponse(new RestTemplate().exchange(GET_EMPLOYEE_WITH_FILTER_PROCEDURE_URL,
                HttpMethod.POST, httpEntity, new ParameterizedTypeReference<PaginationDTO<EmployeeZUPDTO>>() {}));
    }

    public List<EmployeeZUPDTO> getUserByEmployeeId(String employeeId) throws Exception {
        EmployeeFilterDTO employeeFilterDTO = new EmployeeFilterDTO();
        employeeFilterDTO.setEmployeeId(employeeId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(loginToReports1C().getAccess_token());

        HttpEntity<EmployeeFilterDTO> httpEntity = new HttpEntity<>(employeeFilterDTO, headers);

        logger.info("Send request getUserByEmployeeId: " + employeeId);

        return processResponse(new RestTemplate().exchange(GET_EMPLOYEE_WITH_FILTER_PROCEDURE_URL,
                HttpMethod.POST, httpEntity, new ParameterizedTypeReference<PaginationDTO<EmployeeZUPDTO>>() {}));
    }

    public List<EmployeeZUPDTO> getAllUsers() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(loginToReports1C().getAccess_token());

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        logger.info("Send request getAllUsers");

        ResponseEntity<PaginationDTO<EmployeeZUPDTO>> responseEntity = new RestTemplate().exchange(
                GET_EMPLOYEE_DTO_ONLY_WORKING_PROCEDURE_URL,
                HttpMethod.POST, httpEntity, new ParameterizedTypeReference<PaginationDTO<EmployeeZUPDTO>>() {});

        return processResponse(responseEntity);
    }

}
