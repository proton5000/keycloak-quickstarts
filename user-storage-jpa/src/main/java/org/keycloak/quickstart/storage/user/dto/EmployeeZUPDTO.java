package org.keycloak.quickstart.storage.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeZUPDTO {

    public EmployeeZUPDTO() {
    }

    public EmployeeZUPDTO(String id, String fio, String name, String surname, String patronymic, String email,
                          LocalDateTime dateBirth, String phone, String inn, String personalNumber, String shopId,
                          LocalDateTime dateOfReceipt, LocalDateTime dateOfDismissal, Double tariff, Double rate,
                          String state, String addressJur, String addressFact, String managerId, String regDirectorId,
                          String employmentType, String subunitId, String subunitName, String subunitCode,
                          String headSubunitId, String headSubunitName, String headSubunitCode, String positionId,
                          String positionName, String positionFullName, String positionCode, String passportNumber,
                          String passportSeries, String passportIssuedBy, LocalDateTime passportIssuedAt,
                          String passportType, String positionSubgroup, LocalDateTime dateOfReceiptOfCompany,
                          ShopPersonalType shopPersonalType) {
        this.id = id;
        this.fio = fio;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.dateBirth = dateBirth;
        this.phone = phone;
        this.inn = inn;
        this.personalNumber = personalNumber;
        this.shopId = shopId;
        this.dateOfReceipt = dateOfReceipt;
        this.dateOfDismissal = dateOfDismissal;
        this.tariff = tariff;
        this.rate = rate;
        this.state = state;
        this.addressJur = addressJur;
        this.addressFact = addressFact;
        this.managerId = managerId;
        this.regDirectorId = regDirectorId;
        this.employmentType = employmentType;
        this.subunitId = subunitId;
        this.subunitName = subunitName;
        this.subunitCode = subunitCode;
        this.headSubunitId = headSubunitId;
        this.headSubunitName = headSubunitName;
        this.headSubunitCode = headSubunitCode;
        this.positionId = positionId;
        this.positionName = positionName;
        this.positionFullName = positionFullName;
        this.positionCode = positionCode;
        this.passportNumber = passportNumber;
        this.passportSeries = passportSeries;
        this.passportIssuedBy = passportIssuedBy;
        this.passportIssuedAt = passportIssuedAt;
        this.passportType = passportType;
        this.positionSubgroup = positionSubgroup;
        this.dateOfReceiptOfCompany = dateOfReceiptOfCompany;
        this.shopPersonalType = shopPersonalType;
    }

    @JsonProperty("employeeId")
    private String id;

    private String fio;

    private String name;

    private String surname;

    private String patronymic;

    private String email;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime dateBirth;

    private String phone;

    private String inn;

    @JsonProperty("perosnalNumber")
    private String personalNumber;

    private String shopId;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime dateOfReceipt;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime dateOfDismissal;

    private Double tariff;

    private Double rate;

    @JsonProperty("stateEmployee")
    private String state;

    private String addressJur;

    private String addressFact;

    private String managerId;

    private String regDirectorId;

    private String employmentType;

    private String subunitId;
    private String subunitName;
    private String subunitCode;
    private String headSubunitId;
    private String headSubunitName;
    private String headSubunitCode;
    private String positionId;
    private String positionName;
    private String positionFullName;
    private String positionCode;
    private String passportNumber;
    private String passportSeries;
    private String passportIssuedBy;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime passportIssuedAt;
    private String passportType;

    //Топ-признак: "3-я линейка (specialist)", "2-я линейка (middle management)", "1-я линейка (top management)"
    private String positionSubgroup;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    //принят в компанию
    private LocalDateTime dateOfReceiptOfCompany;

    private ShopPersonalType shopPersonalType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDateTime dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public LocalDateTime getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(LocalDateTime dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    public LocalDateTime getDateOfDismissal() {
        return dateOfDismissal;
    }

    public void setDateOfDismissal(LocalDateTime dateOfDismissal) {
        this.dateOfDismissal = dateOfDismissal;
    }

    public Double getTariff() {
        return tariff;
    }

    public void setTariff(Double tariff) {
        this.tariff = tariff;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddressJur() {
        return addressJur;
    }

    public void setAddressJur(String addressJur) {
        this.addressJur = addressJur;
    }

    public String getAddressFact() {
        return addressFact;
    }

    public void setAddressFact(String addressFact) {
        this.addressFact = addressFact;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getRegDirectorId() {
        return regDirectorId;
    }

    public void setRegDirectorId(String regDirectorId) {
        this.regDirectorId = regDirectorId;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getSubunitId() {
        return subunitId;
    }

    public void setSubunitId(String subunitId) {
        this.subunitId = subunitId;
    }

    public String getSubunitName() {
        return subunitName;
    }

    public void setSubunitName(String subunitName) {
        this.subunitName = subunitName;
    }

    public String getSubunitCode() {
        return subunitCode;
    }

    public void setSubunitCode(String subunitCode) {
        this.subunitCode = subunitCode;
    }

    public String getHeadSubunitId() {
        return headSubunitId;
    }

    public void setHeadSubunitId(String headSubunitId) {
        this.headSubunitId = headSubunitId;
    }

    public String getHeadSubunitName() {
        return headSubunitName;
    }

    public void setHeadSubunitName(String headSubunitName) {
        this.headSubunitName = headSubunitName;
    }

    public String getHeadSubunitCode() {
        return headSubunitCode;
    }

    public void setHeadSubunitCode(String headSubunitCode) {
        this.headSubunitCode = headSubunitCode;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionFullName() {
        return positionFullName;
    }

    public void setPositionFullName(String positionFullName) {
        this.positionFullName = positionFullName;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportIssuedBy() {
        return passportIssuedBy;
    }

    public void setPassportIssuedBy(String passportIssuedBy) {
        this.passportIssuedBy = passportIssuedBy;
    }

    public LocalDateTime getPassportIssuedAt() {
        return passportIssuedAt;
    }

    public void setPassportIssuedAt(LocalDateTime passportIssuedAt) {
        this.passportIssuedAt = passportIssuedAt;
    }

    public String getPassportType() {
        return passportType;
    }

    public void setPassportType(String passportType) {
        this.passportType = passportType;
    }

    public String getPositionSubgroup() {
        return positionSubgroup;
    }

    public void setPositionSubgroup(String positionSubgroup) {
        this.positionSubgroup = positionSubgroup;
    }

    public LocalDateTime getDateOfReceiptOfCompany() {
        return dateOfReceiptOfCompany;
    }

    public void setDateOfReceiptOfCompany(LocalDateTime dateOfReceiptOfCompany) {
        this.dateOfReceiptOfCompany = dateOfReceiptOfCompany;
    }

    public ShopPersonalType getShopPersonalType() {
        return shopPersonalType;
    }

    public void setShopPersonalType(ShopPersonalType shopPersonalType) {
        this.shopPersonalType = shopPersonalType;
    }

    public enum ShopPersonalType {
        ADMINISTRATIVE_STAFF("Админ. состав"),
        SALES_STAFF("Торговый персонал"),
        UNDEFINED("Не определено");

        private final String shopPersonalType;

        ShopPersonalType(String shopPersonalType) {
            this.shopPersonalType = shopPersonalType;
        }

        public String getShopPersonalType() { return shopPersonalType; }
    }

    @Override
    public String toString() {
        return "EmployeeZUPDTO{" +
                "id='" + id + '\'' +
                ", fio='" + fio + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", email='" + email + '\'' +
                ", dateBirth=" + dateBirth +
                ", phone='" + phone + '\'' +
                ", inn='" + inn + '\'' +
                ", personalNumber='" + personalNumber + '\'' +
                ", shopId='" + shopId + '\'' +
                ", dateOfReceipt=" + dateOfReceipt +
                ", dateOfDismissal=" + dateOfDismissal +
                ", tariff=" + tariff +
                ", rate=" + rate +
                ", state='" + state + '\'' +
                ", addressJur='" + addressJur + '\'' +
                ", addressFact='" + addressFact + '\'' +
                ", managerId='" + managerId + '\'' +
                ", regDirectorId='" + regDirectorId + '\'' +
                ", employmentType='" + employmentType + '\'' +
                ", subunitId='" + subunitId + '\'' +
                ", subunitName='" + subunitName + '\'' +
                ", subunitCode='" + subunitCode + '\'' +
                ", headSubunitId='" + headSubunitId + '\'' +
                ", headSubunitName='" + headSubunitName + '\'' +
                ", headSubunitCode='" + headSubunitCode + '\'' +
                ", positionId='" + positionId + '\'' +
                ", positionName='" + positionName + '\'' +
                ", positionFullName='" + positionFullName + '\'' +
                ", positionCode='" + positionCode + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", passportSeries='" + passportSeries + '\'' +
                ", passportIssuedBy='" + passportIssuedBy + '\'' +
                ", passportIssuedAt=" + passportIssuedAt +
                ", passportType='" + passportType + '\'' +
                ", positionSubgroup='" + positionSubgroup + '\'' +
                ", dateOfReceiptOfCompany=" + dateOfReceiptOfCompany +
                ", shopPersonalType=" + shopPersonalType +
                '}';
    }
}
