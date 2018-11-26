package dev.berserk.firststeps.databaseandrecyclerview.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class DogShop extends RealmObject {

    @PrimaryKey
    public String dogShopID;

    public String name;

    public String address;

    public String image;

    public DogShop(String dogShopID, String name, String address, String image) {
        this.dogShopID = dogShopID;
        this.name = name;
        this.address = address;
        this.image = image;
    }

    public DogShop() {

    }

    @Override
    public String toString() {
        return "DogShop{" +
                "dogShopID='" + dogShopID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
