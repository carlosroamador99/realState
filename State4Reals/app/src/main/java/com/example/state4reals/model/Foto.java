package com.example.state4reals.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Foto {

    @SerializedName("url")
    @Expose
    private String url;
    private String id_prperty;

    public Foto(){
    }

    public Foto(String url, String id_prperty) {
        this.url = url;
        this.id_prperty = id_prperty;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId_prperty() {
        return id_prperty;
    }

    public void setId_prperty(String id_prperty) {
        this.id_prperty = id_prperty;
    }

    public String getImgurUrl() {
        return url;
    }

    public void setImgurUrl(String imgurUrl) {
        this.url = imgurUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Foto foto = (Foto) o;

        if (url != null ? !url.equals(foto.url) : foto.url != null) return false;
        return id_prperty != null ? id_prperty.equals(foto.id_prperty) : foto.id_prperty == null;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (id_prperty != null ? id_prperty.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Foto{" +
                "url='" + url + '\'' +
                ", id_prperty='" + id_prperty + '\'' +
                '}';
    }
}
