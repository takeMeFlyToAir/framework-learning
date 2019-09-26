package com.zzr.framework.groovy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Auther: qiuyujiang
 * @Date: 2019/2/12.
 * @Description: 推荐物品
 */
public class RecItem {

    /**
     * 物品id
     */
    private String id;

    /**
     * 埋点信息
     */
    private String tracking;




    public RecItem(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id+"  ";
    }
}
