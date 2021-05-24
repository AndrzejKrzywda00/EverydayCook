package ModelObjects;

public class Tag {

    /*
    This is model class for tags - universal information plates
    that are used to filter data
    and to have more interactive application
     */

    private String content;                 // just the information it has
    private String color;                   // chosen color for this particular tag
    private int databaseId;                 // for storage purposes
    private boolean wasUsed;                // checking whether user used it or not

    public Tag(){}

    public Tag(String n, String c) {
        this.content = n;
        this.color = c;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setDatabaseId(int id) {
        this.databaseId = id;
    }
    public void setUsed() {
        this.wasUsed = true;
    }

    public String getContent() {
        return content;
    }
    public String getColor() {
        return color;
    }
    public String getFixedColor() {
        return "#" + color;
    }
    public int getDatabaseId() {
        return databaseId;
    }
    public boolean used() {
        return wasUsed;
    }
}
