package me.itxuye.gankdbinding.model;

import java.io.Serializable;


public class BaseData implements Serializable {
    public boolean error;

    @Override
    public String toString() {
        return "BaseData{" +
                "error=" + error +
                '}';
    }
}
