package com.developer.vinay22ji.gamerscoin_arena.Models;

import java.util.Map;

public class GameModel
{
    private String icon,link,name;

    public GameModel(String icon, String link,String name) {
        this.icon = icon;
        this.link = link;
        this.name = name;
    }

    public GameModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
