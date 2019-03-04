package com.example.state4reals.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Casa {

    private String id;
    private Usuario user;
    private String[] photos;
    private String title;
    private String description;
    private int price;
    private int room;
    private int size;
    private String address;
    private String zipcode;
    private String city;
    private String province;
    private String loc;
    private boolean isFav;

    public Casa(){}

    public Casa(String id, Usuario user, String title, String description, int price, int room, int size, String address, String zipcode, String city, String province, String loc, boolean isFav) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.price = price;
        this.room = room;
        this.size = size;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.province = province;
        this.loc = loc;
        this.isFav = isFav;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Casa casa = (Casa) o;

        if (price != casa.price) return false;
        if (room != casa.room) return false;
        if (size != casa.size) return false;
        if (isFav != casa.isFav) return false;
        if (id != null ? !id.equals(casa.id) : casa.id != null) return false;
        if (user != null ? !user.equals(casa.user) : casa.user != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(photos, casa.photos)) return false;
        if (title != null ? !title.equals(casa.title) : casa.title != null) return false;
        if (description != null ? !description.equals(casa.description) : casa.description != null)
            return false;
        if (address != null ? !address.equals(casa.address) : casa.address != null) return false;
        if (zipcode != null ? !zipcode.equals(casa.zipcode) : casa.zipcode != null) return false;
        if (city != null ? !city.equals(casa.city) : casa.city != null) return false;
        if (province != null ? !province.equals(casa.province) : casa.province != null)
            return false;
        return loc != null ? loc.equals(casa.loc) : casa.loc == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(photos);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + room;
        result = 31 * result + size;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (zipcode != null ? zipcode.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (loc != null ? loc.hashCode() : 0);
        result = 31 * result + (isFav ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Casa{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", photos=" + Arrays.toString(photos) +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", room=" + room +
                ", size=" + size +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", loc='" + loc + '\'' +
                ", isFav=" + isFav +
                '}';
    }
}
