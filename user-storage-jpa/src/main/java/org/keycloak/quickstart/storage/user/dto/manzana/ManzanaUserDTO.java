package org.keycloak.quickstart.storage.user.dto.manzana;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class ManzanaUserDTO {

    public ManzanaUserDTO() {
    }

    public ManzanaUserDTO(String id, String lastName, String login, String firstName, String middleName, String fullName,
                          String genderCode, LocalDateTime birthDate, String familyStatusCode, String hasChildrenCode,
                          String emailAddress, String mobilePhone, String allowedEmail, String allowSms, String allowPhone,
                          String balance, String activeBalance, String debet, String credit, String summ, String summDiscounted,
                          String discountSumm, String quantity, LocalDateTime registrationDate, String partnerId, String partnerName,
                          String orgUnitId, String orgUnitName, String preferredOrgUnitId, String preferredOrgUnitName,
                          String mobilePhoneVerified, String agreeToTerms, String emailVerified, String communicationMethod,
                          String allowNotification, String defaultCardId, String defaultCardNumber, String codeWord,
                          String stateId, String stateName, String cityId, String cityName, String street, String building,
                          String requestedForUpdateEmailAddress) {
        this.id = id;
        this.lastName = lastName;
        this.login = login;
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
    }

    @JsonProperty("Id")
    private String id;

    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("Login")
    private String login;

    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("MiddleName")
    private String middleName;

    @JsonProperty("FullName")
    private String fullName;

    @JsonProperty("GenderCode")
    private String genderCode;

    @JsonProperty("BirthDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime birthDate;

    @JsonProperty("FamilyStatusCode")
    private String familyStatusCode;

    @JsonProperty("HasChildrenCode")
    private String hasChildrenCode;

    @JsonProperty("EmailAddress")
    private String emailAddress;

    @JsonProperty("MobilePhone")
    private String mobilePhone;

    @JsonProperty("AllowEmail")
    private String allowedEmail;

    @JsonProperty("AllowSms")
    private String allowSms;

    @JsonProperty("AllowPhone")
    private String allowPhone;

    @JsonProperty("Balance")
    private String balance;

    @JsonProperty("ActiveBalance")
    private String activeBalance;

    @JsonProperty("Debet")
    private String debet;

    @JsonProperty("Credit")
    private String credit;

    @JsonProperty("Summ")
    private String summ;

    @JsonProperty("SummDiscounted")
    private String summDiscounted;

    @JsonProperty("DiscountSumm")
    private String discountSumm;

    @JsonProperty("Quantity")
    private String quantity;

    @JsonProperty("RegistrationDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime registrationDate;

    @JsonProperty("PartnerId")
    private String partnerId;

    @JsonProperty("PartnerName")
    private String partnerName;

    @JsonProperty("OrgUnitId")
    private String orgUnitId;

    @JsonProperty("OrgUnitName")
    private String orgUnitName;

    @JsonProperty("PreferredOrgUnitId")
    private String preferredOrgUnitId;

    @JsonProperty("PreferredOrgUnitName")
    private String preferredOrgUnitName;

    @JsonProperty("MobilePhoneVerified")
    private String mobilePhoneVerified;

    @JsonProperty("AgreeToTerms")
    private String agreeToTerms;

    @JsonProperty("EmailVerified")
    private String emailVerified;

    @JsonProperty("CommunicationMethod")
    private String communicationMethod;

    @JsonProperty("AllowNotification")
    private String allowNotification;

    @JsonProperty("DefaultCardId")
    private String defaultCardId;

    @JsonProperty("DefaultCardNumber")
    private String defaultCardNumber;

    @JsonProperty("CodeWord")
    private String codeWord;

    @JsonProperty("StateId")
    private String stateId;

    @JsonProperty("StateName")
    private String stateName;

    @JsonProperty("CityId")
    private String cityId;

    @JsonProperty("CityName")
    private String cityName;

    @JsonProperty("Street")
    private String street;

    @JsonProperty("Building")
    private String building;

    @JsonProperty("RequestedForUpdateEmailAddress")
    private String requestedForUpdateEmailAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public String toString() {
        return "ManzanaUserDTO{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
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
                '}';
    }
}
