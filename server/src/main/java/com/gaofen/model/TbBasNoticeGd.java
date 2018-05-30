package com.gaofen.model;

public class TbBasNoticeGd {
    private String id;

    private String title;

    private String content;

    private String createdate;

    private String quickimg;

    private Short state;

    private String csource;

    private String aother;

    private String publisher;

    private String levelone;

    private String leveltwo;

    private String draft;

    public TbBasNoticeGd(String id, String title, String content, String createdate, String quickimg, Short state, String csource, String aother, String publisher, String levelone, String leveltwo, String draft) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdate = createdate;
        this.quickimg = quickimg;
        this.state = state;
        this.csource = csource;
        this.aother = aother;
        this.publisher = publisher;
        this.levelone = levelone;
        this.leveltwo = leveltwo;
        this.draft = draft;
    }

    public TbBasNoticeGd() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate == null ? null : createdate.trim();
    }

    public String getQuickimg() {
        return quickimg;
    }

    public void setQuickimg(String quickimg) {
        this.quickimg = quickimg == null ? null : quickimg.trim();
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getCsource() {
        return csource;
    }

    public void setCsource(String csource) {
        this.csource = csource == null ? null : csource.trim();
    }

    public String getAother() {
        return aother;
    }

    public void setAother(String aother) {
        this.aother = aother == null ? null : aother.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public String getLevelone() {
        return levelone;
    }

    public void setLevelone(String levelone) {
        this.levelone = levelone == null ? null : levelone.trim();
    }

    public String getLeveltwo() {
        return leveltwo;
    }

    public void setLeveltwo(String leveltwo) {
        this.leveltwo = leveltwo == null ? null : leveltwo.trim();
    }

    public String getDraft() {
        return draft;
    }

    public void setDraft(String draft) {
        this.draft = draft == null ? null : draft.trim();
    }
}