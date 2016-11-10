package me.itxuye.gankdbinding.model;

import java.util.List;
import me.itxuye.gankdbinding.model.entity.Gank;

public class FunnyData extends BaseData{
    public List<Gank> results;

    @Override
    public String toString() {
        return "FunnyData{" +
                "results=" + results +
                '}';
    }
}
