package com.wondersgroup.echartmodal;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName:yxjk_montior
 * @packageName:com.wonders.montior.modal
 * @authorName:wangjiaming
 * @createDate:2018-04-11
 * @editor:IntelliJ IDEA
 * @other:
 **/
public class LineSeries implements Serializable{

    public List<String> data;
    public String type;
    public String name;
    public boolean sommth;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSommth() {
        return sommth;
    }

    public void setSommth(boolean sommth) {
        this.sommth = sommth;
    }
}
