package org.keycloak.quickstart.storage.user.entity;

import org.keycloak.quickstart.storage.user.dto.manzana.ManzanaUserDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "users")
public class ManzanaUser {

    public ManzanaUser() {
    }

    public ManzanaUser(Integer id, String lastName, String login, String password, String firstName, String middleName,
                       String fullName, String genderCode, LocalDateTime birthDate, String familyStatusCode,
                       String hasChildrenCode, String emailAddress, String mobilePhone, String allowedEmail,
                       String allowSms, String allowPhone, String balance, String activeBalance, String debet,
                       String credit, String summ, String summDiscounted, String discountSumm, String quantity,
                       LocalDateTime registrationDate, String partnerId, String partnerName, String orgUnitId,
                       String orgUnitName, String preferredOrgUnitId, String preferredOrgUnitName, String mobilePhoneVerified,
                       String agreeToTerms, String emailVerified, String communicationMethod, String allowNotification,
                       String defaultCardId, String defaultCardNumber, String codeWord, String stateId, String stateName,
                       String cityId, String cityName, String street, String building, String requestedForUpdateEmailAddress,
                       LocalDateTime lastUpdateDateTime) {
        this.id = id;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.fullName = fullName;
        this.genderCode = genderCode;
        this.birthDate = birthDate;
        this.familyStatusCode = familyStatusCode;
        this.hasChildrenCode = hasChildrenCode;
        this.emailAddress = emailAddress;
        this.mobilePhone = mobilePhone;
        this.allowedEmail = allowedEmail;
        this.allowSms = allowSms;
        this.allowPhone = allowPhone;
        this.balance = balance;
        this.activeBalance = activeBalance;
        this.debet = debet;
        this.credit = credit;
        this.summ = summ;
        this.summDiscounted = summDiscounted;
        this.discountSumm = discountSumm;
        this.quantity = quantity;
        this.registrationDate = registrationDate;
        this.partnerId = partnerId;
        this.partnerName = partnerName;
        this.orgUnitId = orgUnitId;
        this.orgUnitName = orgUnitName;
        this.preferredOrgUnitId = preferredOrgUnitId;
        this.preferredOrgUnitName = preferredOrgUnitName;
        this.mobilePhoneVerified = mobilePhoneVerified;
        this.agreeToTerms = agreeToTerms;
        this.emailVerified = emailVerified;
        this.communicationMethod = communicationMethod;
        this.allowNotification = allowNotification;
        this.defaultCardId = defaultCardId;
        this.defaultCardNumber = defaultCardNumber;
        this.codeWord = codeWord;
        this.stateId = stateId;
        this.stateName = stateName;
        this.cityId = cityId;
        this.cityName = cityName;
        this.street = street;
        this.building = building;
        this.requestedForUpdateEmailAddress = requestedForUpdateEmailAddress;
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    public ManzanaUser(ManzanaUserDTO manzanaUserDTO) {
        this.id = null;
        this.lastName = manzanaUserDTO.getLastName();
        this.login = manzanaUserDTO.getLogin();
        this.password = null;
        this.firstName = manzanaUserDTO.getFirstName();
        this.middleName = manzanaUserDTO.getMiddleName();
        this.fullName = manzanaUserDTO.getFullName();
        this.genderCode = manzanaUserDTO.getGenderCode();
        this.birthDate = manzanaUserDTO.getBirthDate();
        this.familyStatusCode = manzanaUserDTO.getFamilyStatusCode();
        this.hasChildrenCode = manzanaUserDTO.getHasChildrenCode();
        this.emailAddress = manzanaUserDTO.getEmailAddress();
        this.mobilePhone = manzanaUserDTO.getMobilePhone();
        this.allowedEmail = manzanaUserDTO.getAllowedEmail();
        this.allowSms = manzanaUserDTO.getAllowSms();
        this.allowPhone = manzanaUserDTO.getAllowPhone();
        this.balance = manzanaUserDTO.getBalance();
        this.activeBalance = manzanaUserDTO.getActiveBalance();
        this.debet = manzanaUserDTO.getDebet();
        this.credit = manzanaUserDTO.getCredit();
        this.summ = manzanaUserDTO.getSumm();
        this.summDiscounted = manzanaUserDTO.getSummDiscounted();
        this.discountSumm = manzanaUserDTO.getDiscountSumm();
        this.quantity = manzanaUserDTO.getQuantity();
        this.registrationDate = manzanaUserDTO.getRegistrationDate();
        this.partnerId = manzanaUserDTO.getPartnerId();
        this.partnerName = manzanaUserDTO.getPartnerName();
        this.orgUnitId = manzanaUserDTO.getOrgUnitId();
        this.orgUnitName = manzanaUserDTO.getOrgUnitName();
        this.preferredOrgUnitId = manzanaUserDTO.getPreferredOrgUnitId();
        this.preferredOrgUnitName = manzanaUserDTO.getPreferredOrgUnitName();
        this.mobilePhoneVerified = manzanaUserDTO.getMobilePhoneVerified();
        this.agreeToTerms = manzanaUserDTO.getAgreeToTerms();
        this.emailVerified = manzanaUserDTO.getEmailVerified();
        this.communicationMethod = manzanaUserDTO.getCommunicationMethod();
        this.allowNotification = manzanaUserDTO.getAllowNotification();
        this.defaultCardId = manzanaUserDTO.getDefaultCardId();
        this.defaultCardNumber = manzanaUserDTO.getDefaultCardNumber();
        this.codeWord = manzanaUserDTO.getCodeWord();
        this.stateId = manzanaUserDTO.getStateId();
        this.stateName = manzanaUserDTO.getStateName();
        this.cityId = manzanaUserDTO.getCityId();
        this.cityName = manzanaUserDTO.getCityName();
        this.street = manzanaUserDTO.getStreet();
        this.building = manzanaUserDTO.getBuilding();
        this.requestedForUpdateEmailAddress = manzanaUserDTO.getRequestedForUpdateEmailAddress();
        this.lastUpdateDateTime = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Integer id;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String login;

    private String password;

    private String firstName;
    private String middleName;
    private String fullName;
    private String genderCode;
    private LocalDateTime birthDate;
    private String familyStatusCode;
    private String hasChildrenCode;
    private String emailAddress;

    @Column(unique = true, nullable = false)
    private String mobilePhone;

    private String allowedEmail;
    private String allowSms;
    private String allowPhone;
    private String balance;
    private String activeBalance;
    private String debet;
    private String credit;
    private String summ;
    private String summDiscounted;
    private String discountSumm;
    private String quantity;
    private LocalDateTime registrationDate;
    private String partnerId;
    private String partnerName;
    private String orgUnitId;
    private String orgUnitName;
    private String preferredOrgUnitId;
    private String preferredOrgUnitName;
    private String mobilePhoneVerified;
    private String agreeToTerms;
    private String emailVerified;
    private String communicationMethod;
    private String allowNotification;
    private String defaultCardId;
    private String defaultCardNumber;
    private String codeWord;
    private String stateId;
    private String stateName;
    private String cityId;
    private String cityName;
    private String street;
    private String building;
    private String requestedForUpdateEmailAddress;
    private LocalDateTime lastUpdateDateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getFamilyStatusCode() {
        return familyStatusCode;
    }

    public void setFamilyStatusCode(String familyStatusCode) {
        this.familyStatusCode = familyStatusCode;
    }

    public String getHasChildrenCode() {
        return hasChildrenCode;
    }

    public void setHasChildrenCode(String hasChildrenCode) {
        this.hasChildrenCode = hasChildrenCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getAllowedEmail() {
        return allowedEmail;
    }

    public void setAllowedEmail(String allowedEmail) {
        this.allowedEmail = allowedEmail;
    }

    public String getAllowSms() {
        return allowSms;
    }

    public void setAllowSms(String allowSms) {
        this.allowSms = allowSms;
    }

    public String getAllowPhone() {
        return allowPhone;
    }

    public void setAllowPhone(String allowPhone) {
        this.allowPhone = allowPhone;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getActiveBalance() {
        return activeBalance;
    }

    public void setActiveBalance(String activeBalance) {
        this.activeBalance = activeBalance;
    }

    public String getDebet() {
        return debet;
    }

    public void setDebet(String debet) {
        this.debet = debet;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getSumm() {
        return summ;
    }

    public void setSumm(String summ) {
        this.summ = summ;
    }

    public String getSummDiscounted() {
        return summDiscounted;
    }

    public void setSummDiscounted(String summDiscounted) {
        this.summDiscounted = summDiscounted;
    }

    public String getDiscountSumm() {
        return discountSumm;
    }

    public void setDiscountSumm(String discountSumm) {
        this.discountSumm = discountSumm;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getOrgUnitId() {
        return orgUnitId;
    }

    public void setOrgUnitId(String orgUnitId) {
        this.orgUnitId = orgUnitId;
    }

    public String getOrgUnitName() {
        return orgUnitName;
    }

    public void setOrgUnitName(String orgUnitName) {
        this.orgUnitName = orgUnitName;
    }

    public String getPreferredOrgUnitId() {
        return preferredOrgUnitId;
    }

    public void setPreferredOrgUnitId(String preferredOrgUnitId) {
        this.preferredOrgUnitId = preferredOrgUnitId;
    }

    public String getPreferredOrgUnitName() {
        return preferredOrgUnitName;
    }

    public void setPreferredOrgUnitName(String preferredOrgUnitName) {
        this.preferredOrgUnitName = preferredOrgUnitName;
    }

    public String getMobilePhoneVerified() {
        return mobilePhoneVerified;
    }

    public void setMobilePhoneVerified(String mobilePhoneVerified) {
        this.mobilePhoneVerified = mobilePhoneVerified;
    }

    public String getAgreeToTerms() {
        return agreeToTerms;
    }

    public void setAgreeToTerms(String agreeToTerms) {
        this.agreeToTerms = agreeToTerms;
    }

    public String getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getCommunicationMethod() {
        return communicationMethod;
    }

    public void setCommunicationMethod(String communicationMethod) {
        this.communicationMethod = communicationMethod;
    }

    public String getAllowNotification() {
        return allowNotification;
    }

    public void setAllowNotification(String allowNotification) {
        this.allowNotification = allowNotification;
    }

    public String getDefaultCardId() {
        return defaultCardId;
    }

    public void setDefaultCardId(String defaultCardId) {
        this.defaultCardId = defaultCardId;
    }

    public String getDefaultCardNumber() {
        return defaultCardNumber;
    }

    public void setDefaultCardNumber(String defaultCardNumber) {
        this.defaultCardNumber = defaultCardNumber;
    }

    public String getCodeWord() {
        return codeWord;
    }

    public void setCodeWord(String codeWord) {
        this.codeWord = codeWord;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRequestedForUpdateEmailAddress() {
        return requestedForUpdateEmailAddress;
    }

    public void setRequestedForUpdateEmailAddress(String requestedForUpdateEmailAddress) {
        this.requestedForUpdateEmailAddress = requestedForUpdateEmailAddress;
    }

    public LocalDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setLastUpdateDateTime(LocalDateTime lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    @Override
    public String toString() {
        return "ManzanaUser{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", genderCode='" + genderCode + '\'' +
                ", birthDate=" + birthDate +
                ", familyStatusCode='" + familyStatusCode + '\'' +
                ", hasChildrenCode='" + hasChildrenCode + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", allowedEmail='" + allowedEmail + '\'' +
                ", allowSms='" + allowSms + '\'' +
                ", allowPhone='" + allowPhone + '\'' +
                ", balance='" + balance + '\'' +
                ", activeBalance='" + activeBalance + '\'' +
                ", debet='" + debet + '\'' +
                ", credit='" + credit + '\'' +
                ", summ='" + summ + '\'' +
                ", summDiscounted='" + summDiscounted + '\'' +
                ", discountSumm='" + discountSumm + '\'' +
                ", quantity='" + quantity + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", partnerName='" + partnerName + '\'' +
                ", orgUnitId='" + orgUnitId + '\'' +
                ", orgUnitName='" + orgUnitName + '\'' +
                ", preferredOrgUnitId='" + preferredOrgUnitId + '\'' +
                ", preferredOrgUnitName='" + preferredOrgUnitName + '\'' +
                ", mobilePhoneVerified='" + mobilePhoneVerified + '\'' +
                ", agreeToTerms='" + agreeToTerms + '\'' +
                ", emailVerified='" + emailVerified + '\'' +
                ", communicationMethod='" + communicationMethod + '\'' +
                ", allowNotification='" + allowNotification + '\'' +
                ", defaultCardId='" + defaultCardId + '\'' +
                ", defaultCardNumber='" + defaultCardNumber + '\'' +
                ", codeWord='" + codeWord + '\'' +
                ", stateId='" + stateId + '\'' +
                ", stateName='" + stateName + '\'' +
                ", cityId='" + cityId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", requestedForUpdateEmailAddress='" + requestedForUpdateEmailAddress + '\'' +
                ", lastUpdateDateTime=" + lastUpdateDateTime +
                '}';
    }
}
