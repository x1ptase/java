/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.model;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class UniversityDto {
    //-----            your code here   --------------------------------
    private String id;
    private String name;
    private String shortName;
    private String description;
    private int foundedYear;
    private String address;
    private String city;
    private String region;
    private String type;
    private int totalStudents;
    private int totalFaculties;
    private int isDraft;

    public UniversityDto() {
    }

    public UniversityDto(String id, String name, String shortName, String description, int foundedYear, String address, String city, String region, String type, int totalStudents, int totalFaculties, int isDraft) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.foundedYear = foundedYear;
        this.address = address;
        this.city = city;
        this.region = region;
        this.type = type;
        this.totalStudents = totalStudents;
        this.totalFaculties = totalFaculties;
        this.isDraft = isDraft;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(int foundedYear) {
        this.foundedYear = foundedYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public int getTotalFaculties() {
        return totalFaculties;
    }

    public void setTotalFaculties(int totalFaculties) {
        this.totalFaculties = totalFaculties;
    }

    public int getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(int isDraft) {
        this.isDraft = isDraft;
    }
    
    
}
