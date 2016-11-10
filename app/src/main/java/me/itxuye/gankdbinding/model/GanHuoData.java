package me.itxuye.gankdbinding.model;

import java.util.List;
import me.itxuye.gankdbinding.model.entity.Gank;

/**
 * 通用(Android ,ios,前端，拓展资源，休息视频)数据模型
 */
public class GanHuoData extends BaseData {
    public List<Gank> results;

    @Override
    public String toString() {
        return "GanHuoData{" +
                "results=" + results +
                '}';
    }
}
