package com.example.BarangayServices.models;

public class Resident {
    //Personal Info
    private String firstName;
    private String middleName;
    private String lastName;
    private String qualifier;
    private String gender;
    private String civilStatus;
    private String votersID;
    private String nationality;
    private String birthPlace;
    private String birthDate;
    private boolean isPWD;

    //Address
    private String houseBuildingStreet;
    private String barangay;

    //Contact
    private String emailAddress;
    private String mobileNumber;
    private String landline;

    //Other Info
    private String religion;
    private String educationalAttainment;
    private String occupation;
    private String socialClass;
    private String bloodType;
    private int height;
    private int weight;

    //Family Background
    private String fatherFirstName;
    private String fatherMiddleName;
    private String fatherLastName;
    private String fatherQualifier;
    private String fatherBirthPlace;

    private String motherFirstName;
    private String motherMiddleName;
    private String motherLastName;
    private String motherQualifier;
    private String motherBirthPlace;

    private String spouseFirstName;
    private String spouseMiddleName;
    private String spouseLastName;
    private String spouserQualifier;

    private String userRFID;

    public Resident() {
    }

    public Resident(String firstName,
                    String middleName,
                    String lastName,
                    String qualifier,
                    String gender,
                    String civilStatus,
                    String votersID,
                    String nationality,
                    String birthPlace,
                    String birthDate,
                    boolean isPWD,
                    String houseBuildingStreet,
                    String barangay,
                    String emailAddress,
                    String mobileNumber,
                    String landline,
                    String religion,
                    String educationalAttainment,
                    String occupation,
                    String socialClass,
                    String bloodType,
                    int height,
                    int weight,
                    String fatherFirstName,
                    String fatherMiddleName,
                    String fatherLastName,
                    String fatherQualifier,
                    String fatherBirthPlace,
                    String motherFirstName,
                    String motherMiddleName,
                    String motherLastName,
                    String motherQualifier,
                    String motherBirthPlace,
                    String spouseFirstName,
                    String spouseMiddleName,
                    String spouseLastName,
                    String spouserQualifier,
                    String userRFID) {

        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.qualifier = qualifier;
        this.gender = gender;
        this.civilStatus = civilStatus;
        this.votersID = votersID;
        this.nationality = nationality;
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
        this.isPWD = isPWD;
        this.houseBuildingStreet = houseBuildingStreet;
        this.barangay = barangay;
        this.emailAddress = emailAddress;
        this.mobileNumber = mobileNumber;
        this.landline = landline;
        this.religion = religion;
        this.educationalAttainment = educationalAttainment;
        this.occupation = occupation;
        this.socialClass = socialClass;
        this.bloodType = bloodType;
        this.height = height;
        this.weight = weight;
        this.fatherFirstName = fatherFirstName;
        this.fatherMiddleName = fatherMiddleName;
        this.fatherLastName = fatherLastName;
        this.fatherQualifier = fatherQualifier;
        this.fatherBirthPlace = fatherBirthPlace;
        this.motherFirstName = motherFirstName;
        this.motherMiddleName = motherMiddleName;
        this.motherLastName = motherLastName;
        this.motherQualifier = motherQualifier;
        this.motherBirthPlace = motherBirthPlace;
        this.spouseFirstName = spouseFirstName;
        this.spouseMiddleName = spouseMiddleName;
        this.spouseLastName = spouseLastName;
        this.spouserQualifier = spouserQualifier;
        this.userRFID = userRFID;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getVotersID() {
        return votersID;
    }

    public void setVotersID(String votersID) {
        this.votersID = votersID;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isPWD() {
        return isPWD;
    }

    public void setPWD(boolean PWD) {
        isPWD = PWD;
    }

    public String getHouseBuildingStreet() {
        return houseBuildingStreet;
    }

    public void setHouseBuildingStreet(String houseBuildingStreet) {
        this.houseBuildingStreet = houseBuildingStreet;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEducationalAttainment() {
        return educationalAttainment;
    }

    public void setEducationalAttainment(String educationalAttainment) {
        this.educationalAttainment = educationalAttainment;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getSocialClass() {
        return socialClass;
    }

    public void setSocialClass(String socialClass) {
        this.socialClass = socialClass;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getFatherFirstName() {
        return fatherFirstName;
    }

    public void setFatherFirstName(String fatherFirstName) {
        this.fatherFirstName = fatherFirstName;
    }

    public String getFatherMiddleName() {
        return fatherMiddleName;
    }

    public void setFatherMiddleName(String fatherMiddleName) {
        this.fatherMiddleName = fatherMiddleName;
    }

    public String getFatherLastName() {
        return fatherLastName;
    }

    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
    }

    public String getFatherQualifier() {
        return fatherQualifier;
    }

    public void setFatherQualifier(String fatherQualifier) {
        this.fatherQualifier = fatherQualifier;
    }

    public String getFatherBirthPlace() {
        return fatherBirthPlace;
    }

    public void setFatherBirthPlace(String fatherBirthPlace) {
        this.fatherBirthPlace = fatherBirthPlace;
    }

    public String getMotherFirstName() {
        return motherFirstName;
    }

    public void setMotherFirstName(String motherFirstName) {
        this.motherFirstName = motherFirstName;
    }

    public String getMotherMiddleName() {
        return motherMiddleName;
    }

    public void setMotherMiddleName(String motherMiddleName) {
        this.motherMiddleName = motherMiddleName;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public String getMotherQualifier() {
        return motherQualifier;
    }

    public void setMotherQualifier(String motherQualifier) {
        this.motherQualifier = motherQualifier;
    }

    public String getMotherBirthPlace() {
        return motherBirthPlace;
    }

    public void setMotherBirthPlace(String motherBirthPlace) {
        this.motherBirthPlace = motherBirthPlace;
    }

    public String getSpouseFirstName() {
        return spouseFirstName;
    }

    public void setSpouseFirstName(String spouseFirstName) {
        this.spouseFirstName = spouseFirstName;
    }

    public String getSpouseMiddleName() {
        return spouseMiddleName;
    }

    public void setSpouseMiddleName(String spouseMiddleName) {
        this.spouseMiddleName = spouseMiddleName;
    }

    public String getSpouseLastName() {
        return spouseLastName;
    }

    public void setSpouseLastName(String spouseLastName) {
        this.spouseLastName = spouseLastName;
    }

    public String getSpouserQualifier() {
        return spouserQualifier;
    }

    public void setSpouserQualifier(String spouserQualifier) {
        this.spouserQualifier = spouserQualifier;
    }

    public String getUserRFID() {
        return userRFID;
    }

    public void setUserRFID(String userRFID) {
        this.userRFID = userRFID;
    }
}
