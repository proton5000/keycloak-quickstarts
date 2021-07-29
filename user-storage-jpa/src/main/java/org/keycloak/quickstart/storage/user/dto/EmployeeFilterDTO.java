package org.keycloak.quickstart.storage.user.dto;

import java.time.LocalDate;

public class EmployeeFilterDTO {

    public EmployeeFilterDTO() {
    }

    public EmployeeFilterDTO(String positionId, String employmentType, String phone, String inn, String positionCode,
                             LocalDate dateOfReceipt, String employeeId, String shopId, String fio, String name,
                             String surname, String patronymic, String subunitId, String headSubunitId) {
        this.positionId = positionId;
        this.employmentType = employmentType;
        this.phone = phone;
        this.inn = inn;
        this.positionCode = positionCode;
        this.dateOfReceipt = dateOfReceipt;
        this.employeeId = employeeId;
        this.shopId = shopId;
        this.fio = fio;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.subunitId = subunitId;
        this.headSubunitId = headSubunitId;
    }

    String positionId;
    String employmentType;
    String phone;
    String inn;
    String positionCode;

    LocalDate dateOfReceipt;
    String employeeId;
    String shopId;
    String fio;
    String name;
    String surname;
    String patronymic;
    String subunitId;
    String headSubunitId;

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
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

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public LocalDate getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(LocalDate dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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

    public String getSubunitId() {
        return subunitId;
    }

    public void setSubunitId(String subunitId) {
        this.subunitId = subunitId;
    }

    public String getHeadSubunitId() {
        return headSubunitId;
    }

    public void setHeadSubunitId(String headSubunitId) {
        this.headSubunitId = headSubunitId;
    }

    @Override
    public String toString() {
        return "EmployeeFilterDTO{" +
                "positionId='" + positionId + '\'' +
                ", employmentType='" + employmentType + '\'' +
                ", phone='" + phone + '\'' +
                ", inn='" + inn + '\'' +
                ", positionCode='" + positionCode + '\'' +
                ", dateOfReceipt=" + dateOfReceipt +
                ", employeeId='" + employeeId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", fio='" + fio + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", subunitId='" + subunitId + '\'' +
                ", headSubunitId='" + headSubunitId + '\'' +
                '}';
    }
}
