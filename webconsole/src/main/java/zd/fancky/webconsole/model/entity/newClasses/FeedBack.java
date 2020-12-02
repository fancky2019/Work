package zd.fancky.webconsole.model.entity.newClasses;

public class FeedBack {
    private Integer id;

    private String suggestion;

    private String imagepath;

    private String phone;

    public FeedBack(Integer id, String suggestion, String imagepath, String phone) {
        this.id = id;
        this.suggestion = suggestion;
        this.imagepath = imagepath;
        this.phone = phone;
    }

    public FeedBack() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion == null ? null : suggestion.trim();
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath == null ? null : imagepath.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}