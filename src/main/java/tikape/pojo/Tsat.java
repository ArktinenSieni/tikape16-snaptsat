package tikape.pojo;

public class Tsat {

    private Integer id;
    private String text;
    private String username;

    public Tsat(Integer id, String text, String username) {
        this.id = id;
        this.text = text;
        this.username = username;
    }

    public Tsat(String text, String username) {
        this(null, text, username);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
