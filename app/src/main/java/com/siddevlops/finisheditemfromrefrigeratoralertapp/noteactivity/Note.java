package com.siddevlops.finisheditemfromrefrigeratoralertapp.noteactivity;

public class Note {

    private String title;
    private String content;


    private int expat;


    public Note(){} //  need a empty constructor //



    public Note(String title, String content,int expdate) {
        this.title = title;
        this.content = content;
        this.expat = expdate;

    }

    public int getExpat() { return expat; }

    public void setExpat(int expat) { this.expat = expat; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }






}
