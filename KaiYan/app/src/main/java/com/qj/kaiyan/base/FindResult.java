package com.qj.kaiyan.base;

public class FindResult {

    /**
     * bgColor :
     * bgPicture : http://img.kaiyanapp.com/22192a40de238fe853b992ed57f1f098.jpeg
     * defaultAuthorId : 2160
     * description : 优雅地行走在潮流尖端
     * headerImage : http://img.kaiyanapp.com/c9b19c2f0a2a40f4c45564dd8ea766d3.png
     * id : 24
     * name : 时尚
     */

    private String bgColor;
    private String bgPicture;
    private int defaultAuthorId;
    private String description;
    private String headerImage;
    private int id;
    private String name;

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getBgPicture() {
        return bgPicture;
    }

    public void setBgPicture(String bgPicture) {
        this.bgPicture = bgPicture;
    }

    public int getDefaultAuthorId() {
        return defaultAuthorId;
    }

    public void setDefaultAuthorId(int defaultAuthorId) {
        this.defaultAuthorId = defaultAuthorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
