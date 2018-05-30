package com.gaofen.model;

public class TbBasDataGfArea {
    private String id;

    private Short dataid;

    private String code;

    private String name;

    public TbBasDataGfArea(String id, Short dataid, String code, String name) {
        this.id = id;
        this.dataid = dataid;
        this.code = code;
        this.name = name;
    }

    public TbBasDataGfArea() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Short getDataid() {
        return dataid;
    }

    public void setDataid(Short dataid) {
        this.dataid = dataid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}