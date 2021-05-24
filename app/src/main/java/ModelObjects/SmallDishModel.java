package ModelObjects;

public class SmallDishModel {

    /*
    This is a minimal model strictly for display purposes in linear menu
    like this:
    1. [photo] NAME; description
    2. ...
    3. ...
    4. ...
    .
    .
    .
     */

    private String name;                    // just the name
    private String description;             // just the description
    private int position;                   // position in line
    private String addedAt;                 // time imported from History

    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPosition() {
        return position;
    }
    public String getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(String addedAt) {
        this.addedAt = addedAt;
    }
}
