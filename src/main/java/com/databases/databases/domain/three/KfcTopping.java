package com.databases.databases.domain.three;

import java.math.BigDecimal;
import java.util.Date;

public class KfcTopping {
    private Integer id;

    private String store_code;

    private Integer topping_id;

    private String topping_name;

    private String topping_short_name;

    private BigDecimal price;

    private String img_url;

    private Integer status;

    private Date create_time;

    private Date update_time;

    private String remark;

    private Integer machine_code;

    private Integer insale;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStore_code() {
        return store_code;
    }

    public void setStore_code(String store_code) {
        this.store_code = store_code == null ? null : store_code.trim();
    }

    public Integer getTopping_id() {
        return topping_id;
    }

    public void setTopping_id(Integer topping_id) {
        this.topping_id = topping_id;
    }

    public String getTopping_name() {
        return topping_name;
    }

    public void setTopping_name(String topping_name) {
        this.topping_name = topping_name == null ? null : topping_name.trim();
    }

    public String getTopping_short_name() {
        return topping_short_name;
    }

    public void setTopping_short_name(String topping_short_name) {
        this.topping_short_name = topping_short_name == null ? null : topping_short_name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url == null ? null : img_url.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getMachine_code() {
        return machine_code;
    }

    public void setMachine_code(Integer machine_code) {
        this.machine_code = machine_code;
    }

    public Integer getInsale() {
        return insale;
    }

    public void setInsale(Integer insale) {
        this.insale = insale;
    }
}