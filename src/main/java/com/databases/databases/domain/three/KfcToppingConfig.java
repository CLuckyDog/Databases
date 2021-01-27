package com.databases.databases.domain.three;

public class KfcToppingConfig {
    private Integer id;

    private Integer topping_id;

    private String topping_name;

    private String topping_abbname;

    private String img_url;

    private String img_weburl;

    private String topping_dynamic;

    private String store_code;

    private Integer machine_code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTopping_abbname() {
        return topping_abbname;
    }

    public void setTopping_abbname(String topping_abbname) {
        this.topping_abbname = topping_abbname == null ? null : topping_abbname.trim();
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url == null ? null : img_url.trim();
    }

    public String getImg_weburl() {
        return img_weburl;
    }

    public void setImg_weburl(String img_weburl) {
        this.img_weburl = img_weburl == null ? null : img_weburl.trim();
    }

    public String getTopping_dynamic() {
        return topping_dynamic;
    }

    public void setTopping_dynamic(String topping_dynamic) {
        this.topping_dynamic = topping_dynamic == null ? null : topping_dynamic.trim();
    }

    public String getStore_code() {
        return store_code;
    }

    public void setStore_code(String store_code) {
        this.store_code = store_code == null ? null : store_code.trim();
    }

    public Integer getMachine_code() {
        return machine_code;
    }

    public void setMachine_code(Integer machine_code) {
        this.machine_code = machine_code;
    }
}