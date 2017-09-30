package model;

/**
 *
 * @author Admin
 */
public class Genealogy {
    long id;
    String name;
    String description;

    public Genealogy(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Genealogy() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Genealogy{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }
    
}
