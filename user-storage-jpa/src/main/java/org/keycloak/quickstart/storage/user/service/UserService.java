package org.keycloak.quickstart.storage.user.service;

import org.jboss.logging.Logger;
import org.keycloak.component.ComponentModel;
import org.keycloak.quickstart.storage.user.UserEntity;
import org.keycloak.quickstart.storage.user.dto.EmployeeZUPDTO;
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

    public UserEntity getUserByOption(String option, ComponentModel model) throws Exception {

        logger.info("getUserByOption: " + option);

        try (Connection c = DbUtil.getConnection(model)) {

            PreparedStatement prepareStatementSelect = c.prepareStatement("select * from users where username = ? or email = ? or phone = ?");
            prepareStatementSelect.setString(1, option);
            prepareStatementSelect.setString(2, option);
            prepareStatementSelect.setString(3, option);
            prepareStatementSelect.execute();

            ResultSet rs = prepareStatementSelect.getResultSet();
            boolean isResultSet = rs.next();

            if (isResultSet && rs.getTimestamp("lastUpdateDateTime").toLocalDateTime().plusHours(UPDATE_HOURS)
                    .isAfter(LocalDateTime.now())) {
                return matToUserEntity(rs);
            }

            List<EmployeeZUPDTO> result = restService.getUserByPhone(option);

            if (Objects.nonNull(result) && result.isEmpty()) {
                logger.info("could not find by option: " + option);
                return null;
            } if (Objects.isNull(result)) {
                logger.info("The find response by user with option: " + option + " is null");
                return null;
            }

            EmployeeZUPDTO employeeZUPDTOOne = result.get(0);

            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(employeeZUPDTOOne.getPhone());
            userEntity.setEmail(employeeZUPDTOOne.getEmail());
            userEntity.setPhone(employeeZUPDTOOne.getPhone());
            userEntity.setFirstName(employeeZUPDTOOne.getName());
            userEntity.setLastName(employeeZUPDTOOne.getSurname());

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

    public UserEntity getUserById(String id, ComponentModel model) throws Exception {
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

    private List<UserEntity> getAllCachedUsers(ComponentModel model) {

        List<UserEntity> userEntityList = new ArrayList<>();

        try (Connection c = DbUtil.getConnection(model)) {

            PreparedStatement prepareStatementSelect = c.prepareStatement("select * from users");
            prepareStatementSelect.execute();

            ResultSet rs = prepareStatementSelect.getResultSet();

            while (rs.next()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setId(rs.getInt("id"));
                userEntity.setUsername(rs.getString("username"));
                userEntity.setEmail(rs.getString("email"));
                userEntity.setPhone(rs.getString("phone"));
                userEntity.setFirstName(rs.getString("firstName"));
                userEntity.setLastName(rs.getString("lastName"));
                userEntity.setLastUpdateDateTime(rs.getTimestamp("lastUpdateDateTime").toLocalDateTime());
                userEntityList.add(userEntity);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Database error:" + ex.getMessage(), ex);
        }
        return userEntityList;
    }

    public List<UserEntity> getAllUsers(ComponentModel model) {

        List<EmployeeZUPDTO> result = null;
        try {
            result = restService.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Objects.nonNull(result) && result.isEmpty()) {
            logger.info("Could not find all users by procedure: " + GET_EMPLOYEE_DTO_ONLY_WORKING_PROCEDURE_URL);
            logger.info("Return cached users");
            return getAllCachedUsers(model);
        } if (Objects.isNull(result)) {
            logger.info("The all user request: " + GET_EMPLOYEE_DTO_ONLY_WORKING_PROCEDURE_URL + " is null");
            logger.info("Return cached users");
            return getAllCachedUsers(model);
        }

        return result.parallelStream().map(userZup -> {

            try (Connection c = DbUtil.getConnection(model)) {

                PreparedStatement prepareStatementSelect = c.prepareStatement("select * from users where username = ?");
                prepareStatementSelect.setString(1, userZup.getPhone());
                prepareStatementSelect.execute();

                ResultSet rs = prepareStatementSelect.getResultSet();
                boolean isResultSet = rs.next();

                if (isResultSet && rs.getTimestamp("lastUpdateDateTime").toLocalDateTime().plusHours(UPDATE_HOURS)
                        .isAfter(LocalDateTime.now())) {
                    return matToUserEntity(rs);
                }

                UserEntity userEntity = new UserEntity();
                userEntity.setUsername(userZup.getPhone());
                userEntity.setEmail(userZup.getEmail());
                userEntity.setPhone(userZup.getPhone());
                userEntity.setFirstName(userZup.getName());
                userEntity.setLastName(userZup.getSurname());

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

    private UserEntity matToUserEntity(ResultSet rs) throws SQLException {
        logger.info("matToUserEntity the user with username: " + rs.getString("username"));
        UserEntity userEntity = new UserEntity();
        userEntity.setId(rs.getInt("id"));
        userEntity.setUsername(rs.getString("username"));
        userEntity.setEmail(rs.getString("email"));
        userEntity.setPhone(rs.getString("phone"));
        userEntity.setFirstName(rs.getString("firstName"));
        userEntity.setLastName(rs.getString("lastName"));
        userEntity.setLastUpdateDateTime(rs.getTimestamp("lastUpdateDateTime").toLocalDateTime());
        return userEntity;
    }

    public UserEntity createUser(UserEntity userEntity, ComponentModel model) throws Exception {
        logger.info("createUser: " + userEntity);
        try (Connection c = DbUtil.getConnection(model)) {

            PreparedStatement prepareStatementInsert =
                    c.prepareStatement("insert into users " +
                            "(username, email, phone, firstName, lastName, lastUpdateDateTime)" +
                            " values (?, ?, ?, ?, ?, ?)");
            prepareStatementInsert.setString(1, userEntity.getUsername());
            prepareStatementInsert.setString(2, userEntity.getEmail());
            prepareStatementInsert.setString(3, userEntity.getPhone());
            prepareStatementInsert.setString(4, userEntity.getFirstName());
            prepareStatementInsert.setString(5, userEntity.getLastName());
            prepareStatementInsert.setObject(6, LocalDateTime.now());
            prepareStatementInsert.execute();

            return getUserByOption(userEntity.getUsername(), model);

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

    public UserEntity updateUser(UserEntity userEntity, ComponentModel model) throws Exception {
        logger.info("updateUser: " + userEntity);
        try (Connection c = DbUtil.getConnection(model)) {
            PreparedStatement prepareStatementUpdate =
                    c.prepareStatement("update users " +
                            "set username = ?, email = ?, phone = ?, firstName = ?, lastName = ?, " +
                            "lastUpdateDateTime = ? where id = ?");

            prepareStatementUpdate.setString(1, userEntity.getUsername());
            prepareStatementUpdate.setString(2, userEntity.getEmail());
            prepareStatementUpdate.setString(3, userEntity.getPhone());
            prepareStatementUpdate.setString(4, userEntity.getFirstName());
            prepareStatementUpdate.setString(5, userEntity.getLastName());
            prepareStatementUpdate.setObject(6, LocalDateTime.now());
            prepareStatementUpdate.setInt(7, userEntity.getId());
            prepareStatementUpdate.execute();

            return getUserByOption(userEntity.getUsername(), model);

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
