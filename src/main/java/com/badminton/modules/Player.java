package com.badminton.modules;

import java.sql.*;

public class Player {


    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String team;
    private String image;
    private String phone;
    private String type; //Captain, Offense, Defense, Beginner, Marquee.
    private String shirtSize;
    private String empId;
    private String age;
    private String matchesPlayed;
    private String position;
    private String bidStatus; // 1->Sold 0->NotSold -1->did not face bidding, Set to -1 in database as default, Change to 0 when player faces bidding
    private String password;
    private String points;

    private String role;

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Player(){}




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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShirtSize() {
        return shirtSize;
    }

    public void setShirtSize(String shirtSize) {
        this.shirtSize = shirtSize;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(String matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(String bidStatus) {
        this.bidStatus = bidStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public String toString() {
        return  ("{  \"firstName\" : \" "+ firstName +
                "\", \"lastName\" : \"" + lastName +
                "\", \"email\" : \"" + email +
                "\", \"gender\" : \"" + gender +
                "\", \"team\" : \"" + team +
                "\", \"image\" : \""+image+
                "\" ,\"phone\" : \""+phone+
                "\", \"type\" : \""+type+
                "\",\"shirtSize\" : \""+shirtSize+
                "\", \"empid\" : \""+empId+
                "\", \"age\" : \""+age+
                "\", \"matchesPlayed\" : \""+matchesPlayed+
                "\", \"position\" : \""+position+

                "\", \"role\" : \""+ role+
                "\", \"points\" : \""+ points+
                "\", \"bidStatus\" : \""+bidStatus+"\" }")
                ;
    }

    public Player createPlayer(ResultSet res){
        Player player = new Player();
        System.out.println("in create player ");
        try {
            player.setFirstName(res.getString("firstName"));
            player.setLastName(res.getString("lastName"));
            player.setEmail(res.getString("email"));
            player.setGender(res.getString("gender"));
            player.setTeam(res.getString("team"));
            player.setImage(res.getString("image"));
            player.setPhone(res.getString("phone"));
            player.setType(res.getString("type"));
            player.setShirtSize(res.getString("shirtSize"));
            player.setEmpId(res.getString("empId"));
            player.setAge(res.getString("age"));
            player.setMatchesPlayed(res.getString("matchesPlayed"));
            player.setPosition(res.getString("position"));
            player.setBidStatus(res.getString("bidStatus"));
            player.setPassword(res.getString("password"));
            player.setPoints(res.getString("Points"));
            player.setRole(res.getString("role"));
        }
        catch (Exception e){
            e.printStackTrace();
            //player = null;
        }
        return player;
    }
}