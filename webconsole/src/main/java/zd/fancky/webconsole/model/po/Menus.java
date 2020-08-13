package zd.fancky.webconsole.model.po;

public class Menus {
    private int id;
    private int parentID;
    /*
     绑定页面的ID用
     */
    private String menuName;
    private String url;
    private String icoName;
    /*
    显示用
     */
    private String displayName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcoName() {
        return icoName;
    }

    public void setIcoName(String icoName) {
        this.icoName = icoName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
