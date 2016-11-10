package me.itxuye.gankdbinding.model;

import java.util.List;
import me.itxuye.gankdbinding.model.entity.Meizi;

/**
 * 福利数据模型
 * 数据格式见：http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1
 */
public class MeiziData extends BaseData {
    public List<Meizi> results;

    @Override
    public String toString() {
        return "MeiziData{" +
                "results=" + results +
                '}';
    }
}
