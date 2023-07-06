package org.lessons.springilmiofotoalbum.model;

import java.util.List;

public class Photo {
    private String title;
    private String description;
    private String url;
    private boolean visible;
    private List<String> categories;

    public Photo(String title, String description, String url, boolean visible, List<String> categories) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.visible = visible;
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
