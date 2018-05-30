package com.gaofen.model.weixin;

public class ItemMsg {
    private String itemId;

    private String description;

    private String url;

    private String title;

    private String imageUrl;

    private long needLogin;

    private String baseUrl;
    private String mBaseLoginUrl;

    public ItemMsg(String itemId, String description, String url, String title, String imageUrl, Long needLogin) {
        this.itemId = itemId;
        this.description = description;
        this.url = url;
        this.title = title;
        this.imageUrl = imageUrl;
        this.needLogin = needLogin;
    }

    public ItemMsg() {
        super();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUrl() {
        if (baseUrl != null && mBaseLoginUrl != null && !url.startsWith(mBaseLoginUrl)) {
            this.url = baseUrl + url;
            if (needLogin == 1) {
                this.url = mBaseLoginUrl + url;
            }
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImageUrl() {
        if (baseUrl != null && baseUrl != null && !imageUrl.startsWith(baseUrl)) {
            this.imageUrl = baseUrl + imageUrl;
        }
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Long getNeedLogin() {
        return needLogin;
    }

    public void setNeedLogin(Long needLogin) {
        this.needLogin = needLogin;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        this.mBaseLoginUrl = baseUrl + "user/getUser?url=";
    }

    @Override
    public String toString() {
        return "ItemMsg{" +
                "itemId='" + itemId + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", needLogin=" + needLogin +
                ", baseUrl='" + baseUrl + '\'' +
                '}';
    }
}