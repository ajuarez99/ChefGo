package com.example.chefgo.DomainObjects;

import android.os.Parcel;
import android.os.Parcelable;

public class UsersDomain implements Parcelable{
    private String username;
    private String email;
    private String fName;
    private String lname;
    private String password;
    private Integer userType;
    private Double rating;
    private String address;
    private String state;
    private Integer zip;

    public UsersDomain(){

    }
    public UsersDomain(String username, String email, String f, String l, String pass, String user, Double rating,
                 Integer type, String address, String state, Integer zip) {
        this.username = username;
        this.email = email;
        this.fName= f;
        this.lname = l;
        this.password = pass;
        this.userType = type;
        this.rating = rating;
        this.address = address;
        this.state = state;
        this.zip = zip;

    }
    protected UsersDomain(Parcel in){
        this.username = in.readString();
        this.email = in.readString();
        this.fName= in.readString();
        this.lname = in.readString();
        this.password = in.readString();
        this.userType = in.readInt();
        this.rating = in.readDouble();
        this.address = in.readString();
        this.state = in.readString();
        this.zip = in.readInt();
    }
    public static final Parcelable.Creator<UsersDomain> CREATOR = new Parcelable.Creator<UsersDomain>() {
        @Override
        public UsersDomain createFromParcel(Parcel parcel) {
            return new UsersDomain(parcel);
        }

        @Override
        public UsersDomain[] newArray(int i) {
            return new UsersDomain[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel in, int i) {
        in.writeString(this.username) ;
        in.writeString(this.email);
        in.writeString(this.fName);
        in.writeString(this.lname);
        in.writeString(this.password);
        in.writeInt(this.userType);
        in.writeDouble(this.rating);
        in.writeString(this.address);
        in.writeString(this.state);
        in.writeInt(this.zip);
    }

    public Integer getUserType() {
        return this.userType;
    }
    public void setUserType(Integer user) {
        this.userType = user;
    }

    public String getfName() {
        return this.fName;
    }
    public void setfName(String name) {
        this.fName = name;
    }
    public String getlName() {
        return this.lname;
    }
    public void setLname(String name) {
        this.lname= name;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String name) {
        this.username = name;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String pass) {
        this.password = pass;
    }
    public Double getRating() {
        return this.rating;
    }
    public void setRating(Double r) {
        this.rating = r;
    }

    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setZip(Integer zip) {
        this.zip = zip;
    }
    public Integer getZip() {
        return this.zip;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }


}

