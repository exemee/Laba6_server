package BasicClasses;

import java.io.Serializable;

public class Organization implements Comparable<Organization>, Serializable {
    private String fullName; //Поле может быть null
    private Float annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    private Integer employeesCount; //Поле может быть null, Значение поля должно быть больше 0
    private Address postalAddress; //Поле не может быть null

    public Organization(String fullNameOrganization, Float annualTurnover, Integer employeesCount, Address postalAddress){
        this.fullName = fullNameOrganization;
        this.annualTurnover = annualTurnover;
        this.employeesCount = employeesCount;
        this.postalAddress = postalAddress;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }

    public Float getAnnualTurnover() {
        return annualTurnover;
    }

    public Integer getEmployeesCount() {
        return employeesCount;
    }

    public String getFullName() {
        return fullName;
    }

    public void setAnnualTurnover(Float annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public void setEmployeesCount(Integer employeesCount) {
        this.employeesCount = employeesCount;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }

    @Override
    public int compareTo(Organization o) {
        return this.employeesCount - o.employeesCount;
    }
}