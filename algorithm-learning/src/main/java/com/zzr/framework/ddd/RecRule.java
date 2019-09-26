package com.zzr.framework.ddd;


import java.util.List;

/**
 * @Auther: qiuyujiang
 * @Date: 2019/2/14.
 * @Description: 单个推荐流程推荐出来的规则集，待融合
 */
public class RecRule {

    /**
     * ID
     */
    private Integer id;
    private long time;

    /**
     * 规则集名称
     */
    private String code;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
