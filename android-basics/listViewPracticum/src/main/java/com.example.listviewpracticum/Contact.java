package com.example.listviewpracticum;

import com.google.gson.annotations.SerializedName;

public record Contact(String name, String phoneNumber, AvatarType avatarType) {
    public enum AvatarType {
        @SerializedName("hero")
        HERO,
        @SerializedName("princess")
        PRINCESS,
        @SerializedName("robot")
        ROBOT,
        @SerializedName("alien")
        ALIEN,
    }

}
