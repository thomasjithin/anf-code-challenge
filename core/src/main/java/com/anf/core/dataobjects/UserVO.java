package com.anf.core.dataobjects;

public class UserVO {

    private String firstName;
    private String lastName;
    private int age;
    private String country;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNodeName() {
        return firstName + "-" + lastName + "-" + age + "-" + country;
    }

    @Override
    public String toString() {
        return "UserVO [age=" + age + ", country=" + country + ", errorMessage=" + errorMessage + ", firstName="
                + firstName + ", lastName=" + lastName + "]";
    }

}
