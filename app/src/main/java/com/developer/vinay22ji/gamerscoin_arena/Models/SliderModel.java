package com.developer.vinay22ji.gamerscoin_arena.Models;

public class SliderModel {

    private String image,redirect,source;

    public SliderModel(String image, String redirect, String source) {
        this.image = image;
        this.redirect = redirect;
        this.source = source;
    }

    public SliderModel() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
