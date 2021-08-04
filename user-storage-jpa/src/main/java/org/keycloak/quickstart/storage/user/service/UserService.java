package org.keycloak.quickstart.storage.user.service;

import org.jboss.logging.Logger;
import org.keycloak.component.ComponentModel;
import org.keycloak.quickstart.storage.user.dto.manzana.ManzanaUserDTO;
import org.keycloak.quickstart.storage.user.entity.ManzanaUser;
import org.keycloak.quickstart.storage.user.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.keycloak.quickstart.storage.user.service.RestService.GET_EMPLOYEE_DTO_ONLY_WORKING_PROCEDURE_URL;

public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class);
    private static final RestService restService = new RestService();
    private static final long UPDATE_HOURS = 3;

    public ManzanaUser getUserByOption(String option, ComponentModel model) throws Exception {

        logger.info("getUserByOption: " + option);

        try (Connection c = DbUtil.getConnection(model)) {

            PreparedStatement prepareStatementSelect = c.prepareStatement("select * from users where mobilePhone = ? or emailAddress = ?");
            prepareStatementSelect.setString(1, option);
            prepareStatementSelect.setString(2, option);
            prepareStatementSelect.execute();

            ResultSet rs = prepareStatementSelect.getResultSet();
            boolean isResultSet = rs.next();

            if (isResultSet && rs.getTimestamp("lastUpdateDateTime").toLocalDateTime().plusHours(UPDATE_HOURS)
                    .isAfter(LocalDateTime.now())) {
                return matToUserEntity(rs);
            }

            List<ManzanaUserDTO> result = restService.getUserByPhone(option);

            if (Objects.nonNull(result) && result.isEmpty()) {
                logger.info("could not find by option: " + option);
                return null;
            } if (Objects.isNull(result)) {
                logger.info("The find response by user with option: " + option + " is null");
                return null;
            }

            ManzanaUserDTO manzanaUserDTO = result.get(0);

            ManzanaUser userEntity = new ManzanaUser(manzanaUserDTO);

            if (isResultSet && rs.getTimestamp("lastUpdateDateTime").toLocalDateTime().plusHours(UPDATE_HOURS)
                    .isBefore(LocalDateTime.now())) {
                userEntity.setId(rs.getInt("id"));
                return updateUser(userEntity, model);
            } else {
                return createUser(userEntity, model);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Database error:" + ex.getMessage(), ex);
        }
    }

    public ManzanaUser getUserById(String id, ComponentModel model) {
        try (Connection c = DbUtil.getConnection(model)) {
            PreparedStatement prepareStatementSelect = c.prepareStatement("select * from users where id = ?");
            prepareStatementSelect.setInt(1, Integer.parseInt(id));
            prepareStatementSelect.execute();

            ResultSet rs = prepareStatementSelect.getResultSet();
            return (rs.next()) ? matToUserEntity(rs) : null;
        } catch (SQLException ex) {
            throw new RuntimeException("Database error:" + ex.getMessage(), ex);
        }
    }

    private List<ManzanaUser> getAllCachedUsers(ComponentModel model) {

        List<ManzanaUser> userEntityList = new ArrayList<>();

        try (Connection c = DbUtil.getConnection(model)) {

            PreparedStatement prepareStatementSelect = c.prepareStatement("select * from users");
            prepareStatementSelect.execute();

            ResultSet rs = prepareStatementSelect.getResultSet();

            while (rs.next()) {
                userEntityList.add(matToUserEntity(rs));
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Database error:" + ex.getMessage(), ex);
        }
        return userEntityList;
    }

    public List<ManzanaUser> getAllUsers(ComponentModel model) {

        List<ManzanaUserDTO> result = null;
        //TODO temporary return only cached users because no the manzana request to get all users
//        try {
//            result = restService.getAllUsers();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        if (Objects.nonNull(result) && result.isEmpty()) {
            logger.info("Could not find all users by procedure: " + GET_EMPLOYEE_DTO_ONLY_WORKING_PROCEDURE_URL);
            logger.info("Return cached users");
            return getAllCachedUsers(model);
        } if (Objects.isNull(result)) {
            logger.info("The all user request: " + GET_EMPLOYEE_DTO_ONLY_WORKING_PROCEDURE_URL + " is null");
            logger.info("Return cached users");
            return getAllCachedUsers(model);
        }

        return result.parallelStream().map(manzanaUserDTO -> {

            try (Connection c = DbUtil.getConnection(model)) {

                PreparedStatement prepareStatementSelect = c.prepareStatement("select * from users where mobilePhone = ?");
                prepareStatementSelect.setString(1, manzanaUserDTO.getMobilePhone());
                prepareStatementSelect.execute();

                ResultSet rs = prepareStatementSelect.getResultSet();
                boolean isResultSet = rs.next();

                if (isResultSet && rs.getTimestamp("lastUpdateDateTime").toLocalDateTime().plusHours(UPDATE_HOURS)
                        .isAfter(LocalDateTime.now())) {
                    return matToUserEntity(rs);
                }

                ManzanaUser userEntity = new ManzanaUser(manzanaUserDTO);

                if (isResultSet && rs.getTimestamp("lastUpdateDateTime").toLocalDateTime().plusHours(UPDATE_HOURS)
                        .isBefore(LocalDateTime.now())) {
                    userEntity.setId(rs.getInt("id"));
                    return updateUser(userEntity, model);
                } else {
                    return createUser(userEntity, model);
                }

            } catch (Exception ex) {
                throw new RuntimeException("Database error:" + ex.getMessage(), ex);
            }

        }).collect(Collectors.toList());
    }

    private ManzanaUser matToUserEntity(ResultSet rs) throws SQLException {
        logger.info("matToUserEntity the user with login: " + rs.getString("login"));

        return new ManzanaUser(
                rs.getInt("id"),
                rs.getString("lastName"),
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("firstName"),
                rs.getString("middleName"),
                rs.getString("fullName"),
                rs.getString("genderCode"),
                Objects.nonNull(rs.getTimestamp("birthDate")) ? rs.getTimestamp("birthDate").toLocalDateTime() : null,
                rs.getString("familyStatusCode"),
                rs.getString("hasChildrenCode"),
                rs.getString("emailAddress"),
                rs.getString("mobilePhone"),
                rs.getString("allowedEmail"),
                rs.getString("allowSms"),
                rs.getString("allowPhone"),
                rs.getString("balance"),
                rs.getString("activeBalance"),
                rs.getString("debet"),
                rs.getString("credit"),
                rs.getString("summ"),
                rs.getString("summDiscounted"),
                rs.getString("discountSumm"),
                rs.getString("quantity"),
                Objects.nonNull(rs.getTimestamp("registrationDate")) ? rs.getTimestamp("registrationDate").toLocalDateTime() : null,
                rs.getString("partnerId"),
                rs.getString("partnerName"),
                rs.getString("orgUnitId"),
                rs.getString("orgUnitName"),
                rs.getString("preferredOrgUnitId"),
                rs.getString("preferredOrgUnitName"),
                rs.getString("mobilePhoneVerified"),
                rs.getString("agreeToTerms"),
                rs.getString("emailVerified"),
                rs.getString("communicationMethod"),
                rs.getString("allowNotification"),
                rs.getString("defaultCardId"),
                rs.getString("defaultCardNumber"),
                rs.getString("codeWord"),
                rs.getString("stateId"),
                rs.getString("stateName"),
                rs.getString("cityId"),
                rs.getString("cityName"),
                rs.getString("street"),
                rs.getString("building"),
                rs.getString("requestedForUpdateEmailAddress"),
                rs.getTimestamp("lastUpdateDateTime").toLocalDateTime()
        );
    }

    public ManzanaUser createUser(ManzanaUser userEntity, ComponentModel model) throws Exception {
        logger.info("createUser: " + userEntity);
        try (Connection c = DbUtil.getConnection(model)) {

            PreparedStatement prepareStatementInsert = c.prepareStatement("insert into users " +
                    "(lastName, login, firstName, middleName, fullName, genderCode, birthDate, familyStatusCode, hasChildrenCode, " +
                    "emailAddress, mobilePhone, allowedEmail, allowSms, allowPhone, balance, activeBalance, debet, credit, " +
                    "summ, summDiscounted, discountSumm, quantity, registrationDate, partnerId, partnerName, orgUnitId, " +
                    "orgUnitName, preferredOrgUnitId, preferredOrgUnitName, mobilePhoneVerified, agreeToTerms, " +
                    "emailVerified, communicationMethod, allowNotification, defaultCardId, defaultCardNumber, codeWord, " +
                    "stateId, stateName, cityId, cityName, street, building, requestedForUpdateEmailAddress, lastUpdateDateTime) " +
                    "values (" +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?)");


            prepareStatementInsert.setString(1, userEntity.getLastName());
            prepareStatementInsert.setString(2, userEntity.getMobilePhone());
            prepareStatementInsert.setString(3, userEntity.getFirstName());
            prepareStatementInsert.setString(4, userEntity.getMiddleName());
            prepareStatementInsert.setString(5, userEntity.getFullName());
            prepareStatementInsert.setString(6, userEntity.getGenderCode());
            prepareStatementInsert.setObject(7, userEntity.getBirthDate());
            prepareStatementInsert.setString(8, userEntity.getFamilyStatusCode());
            prepareStatementInsert.setString(9, userEntity.getHasChildrenCode());
            prepareStatementInsert.setString(10, userEntity.getEmailAddress());
            prepareStatementInsert.setString(11, userEntity.getMobilePhone());
            prepareStatementInsert.setString(12, userEntity.getAllowedEmail());
            prepareStatementInsert.setString(13, userEntity.getAllowSms());
            prepareStatementInsert.setString(14, userEntity.getAllowPhone());
            prepareStatementInsert.setString(15, userEntity.getBalance());
            prepareStatementInsert.setString(16, userEntity.getActiveBalance());
            prepareStatementInsert.setString(17, userEntity.getDebet());
            prepareStatementInsert.setString(18, userEntity.getCredit());
            prepareStatementInsert.setString(19, userEntity.getSumm());
            prepareStatementInsert.setString(20, userEntity.getSummDiscounted());
            prepareStatementInsert.setString(21, userEntity.getDiscountSumm());
            prepareStatementInsert.setString(22, userEntity.getQuantity());
            prepareStatementInsert.setObject(23, userEntity.getRegistrationDate());
            prepareStatementInsert.setString(24, userEntity.getPartnerId());
            prepareStatementInsert.setString(25, userEntity.getPartnerName());
            prepareStatementInsert.setString(26, userEntity.getOrgUnitId());
            prepareStatementInsert.setString(27, userEntity.getOrgUnitName());
            prepareStatementInsert.setString(28, userEntity.getPreferredOrgUnitId());
            prepareStatementInsert.setString(29, userEntity.getPreferredOrgUnitName());
            prepareStatementInsert.setString(30, userEntity.getMobilePhoneVerified());
            prepareStatementInsert.setString(31, userEntity.getAgreeToTerms());
            prepareStatementInsert.setString(32, userEntity.getEmailVerified());
            prepareStatementInsert.setString(33, userEntity.getCommunicationMethod());
            prepareStatementInsert.setString(34, userEntity.getAllowNotification());
            prepareStatementInsert.setString(35, userEntity.getDefaultCardId());
            prepareStatementInsert.setString(36, userEntity.getDefaultCardNumber());
            prepareStatementInsert.setString(37, userEntity.getCodeWord());
            prepareStatementInsert.setString(38, userEntity.getStateId());
            prepareStatementInsert.setString(39, userEntity.getStateName());
            prepareStatementInsert.setString(40, userEntity.getCityId());
            prepareStatementInsert.setString(41, userEntity.getCityName());
            prepareStatementInsert.setString(42, userEntity.getStreet());
            prepareStatementInsert.setString(43, userEntity.getBuilding());
            prepareStatementInsert.setString(44, userEntity.getRequestedForUpdateEmailAddress());
            prepareStatementInsert.setObject(45, LocalDateTime.now());

            prepareStatementInsert.execute();

            return getUserByOption(userEntity.getMobilePhone(), model);

        } catch (SQLException ex) {
            throw new RuntimeException("Database error:" + ex.getMessage(), ex);
        }
    }

    public void updateUserPassword(String password, String id, ComponentModel model) {
        logger.info("updateUserPassword, user id: " + id);
        try (Connection c = DbUtil.getConnection(model)) {
            PreparedStatement prepareStatementUpdate =
                    c.prepareStatement("update users set password = ? where id = ?");
            prepareStatementUpdate.setString(1, password);
            prepareStatementUpdate.setInt(2, Integer.parseInt(id));
            prepareStatementUpdate.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Database error:" + ex.getMessage(), ex);
        }
    }

    public ManzanaUser updateUser(ManzanaUser userEntity, ComponentModel model) throws Exception {
        logger.info("updateUser: " + userEntity);
        try (Connection c = DbUtil.getConnection(model)) {

            PreparedStatement prepareStatementUpdate =
                    c.prepareStatement("update users " +
                            "set lastName = ?, login = ?, firstName = ?, middleName = ?, fullName = ?, genderCode = ?, " +
                            "birthDate = ?, familyStatusCode = ?, hasChildrenCode = ?, emailAddress = ?, mobilePhone = ?, " +
                            "allowedEmail = ?, allowSms = ?, allowPhone = ?, balance = ?, activeBalance = ?, debet = ?, " +
                            "credit = ?, summ = ?, summDiscounted = ?, discountSumm = ?, quantity = ?, registrationDate = ?, " +
                            "partnerId = ?, partnerName = ?, orgUnitId = ?, orgUnitName = ?, preferredOrgUnitId = ?, " +
                            "preferredOrgUnitName = ?, mobilePhoneVerified = ?, agreeToTerms = ?, emailVerified = ?, " +
                            "communicationMethod = ?, allowNotification = ?, defaultCardId = ?, defaultCardNumber = ?, " +
                            "codeWord = ?, stateId = ?, stateName = ?, cityId = ?, cityName = ?, street = ?, building = ?, " +
                            "requestedForUpdateEmailAddress = ?, lastUpdateDateTime = ? where id = ?");

            prepareStatementUpdate.setString(1, userEntity.getLastName());
            prepareStatementUpdate.setString(2, userEntity.getMobilePhone());
            prepareStatementUpdate.setString(3, userEntity.getFirstName());
            prepareStatementUpdate.setString(4, userEntity.getMiddleName());
            prepareStatementUpdate.setString(5, userEntity.getFullName());
            prepareStatementUpdate.setString(6, userEntity.getGenderCode());
            prepareStatementUpdate.setObject(7, userEntity.getBirthDate());
            prepareStatementUpdate.setString(8, userEntity.getFamilyStatusCode());
            prepareStatementUpdate.setString(9, userEntity.getHasChildrenCode());
            prepareStatementUpdate.setString(10, userEntity.getEmailAddress());
            prepareStatementUpdate.setString(11, userEntity.getMobilePhone());
            prepareStatementUpdate.setString(12, userEntity.getAllowedEmail());
            prepareStatementUpdate.setString(13, userEntity.getAllowSms());
            prepareStatementUpdate.setString(14, userEntity.getAllowPhone());
            prepareStatementUpdate.setString(15, userEntity.getBalance());
            prepareStatementUpdate.setString(16, userEntity.getActiveBalance());
            prepareStatementUpdate.setString(17, userEntity.getDebet());
            prepareStatementUpdate.setString(18, userEntity.getCredit());
            prepareStatementUpdate.setString(19, userEntity.getSumm());
            prepareStatementUpdate.setString(20, userEntity.getSummDiscounted());
            prepareStatementUpdate.setString(21, userEntity.getDiscountSumm());
            prepareStatementUpdate.setString(22, userEntity.getQuantity());
            prepareStatementUpdate.setObject(23, userEntity.getRegistrationDate());
            prepareStatementUpdate.setString(24, userEntity.getPartnerId());
            prepareStatementUpdate.setString(25, userEntity.getPartnerName());
            prepareStatementUpdate.setString(26, userEntity.getOrgUnitId());
            prepareStatementUpdate.setString(27, userEntity.getOrgUnitName());
            prepareStatementUpdate.setString(28, userEntity.getPreferredOrgUnitId());
            prepareStatementUpdate.setString(29, userEntity.getPreferredOrgUnitName());
            prepareStatementUpdate.setString(30, userEntity.getMobilePhoneVerified());
            prepareStatementUpdate.setString(31, userEntity.getAgreeToTerms());
            prepareStatementUpdate.setString(32, userEntity.getEmailVerified());
            prepareStatementUpdate.setString(33, userEntity.getCommunicationMethod());
            prepareStatementUpdate.setString(34, userEntity.getAllowNotification());
            prepareStatementUpdate.setString(35, userEntity.getDefaultCardId());
            prepareStatementUpdate.setString(36, userEntity.getDefaultCardNumber());
            prepareStatementUpdate.setString(37, userEntity.getCodeWord());
            prepareStatementUpdate.setString(38, userEntity.getStateId());
            prepareStatementUpdate.setString(39, userEntity.getStateName());
            prepareStatementUpdate.setString(40, userEntity.getCityId());
            prepareStatementUpdate.setString(41, userEntity.getCityName());
            prepareStatementUpdate.setString(42, userEntity.getStreet());
            prepareStatementUpdate.setString(43, userEntity.getBuilding());
            prepareStatementUpdate.setString(44, userEntity.getRequestedForUpdateEmailAddress());
            prepareStatementUpdate.setObject(45, LocalDateTime.now());
            prepareStatementUpdate.setInt(46, userEntity.getId());

            prepareStatementUpdate.execute();

            return getUserByOption(userEntity.getMobilePhone(), model);

        } catch (SQLException ex) {
            throw new RuntimeException("Database error:" + ex.getMessage(), ex);
        }
    }

    public void removeUser(String id, ComponentModel model) {
        logger.info("removeUser id: " + id);
        try (Connection c = DbUtil.getConnection(model)) {
            PreparedStatement prepareStatementDelete = c.prepareStatement("delete from users where id = ?");
            prepareStatementDelete.setInt(1, Integer.parseInt(id));
            prepareStatementDelete.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Database error:" + ex.getMessage(), ex);
        }
    }
}
