package com.example.assignment3v2;

public class CatPic {
    public Cat[] getBreeds() {
        return breeds;
    }

    public void setBreeds(Cat[] breeds) {
        this.breeds = breeds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private Cat[] breeds;
    private String id;
    private String url;

}

