package com.siddevlops.finisheditemfromrefrigeratoralertapp.userauth;

public class ModelEatingHabit {

    String name;
    String image;

    public ModelEatingHabit(){

    }


    public ModelEatingHabit(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }








}
