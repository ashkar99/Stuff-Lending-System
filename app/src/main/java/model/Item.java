package model;

public class Item {
    private String name;
    private String description;
    private int cost;
    private int creationDate;
    private Boolean availabilty;
    private CategoryEnum category;

    
    public Item(String name, String description, int cost, int creationDate, Boolean availabilty, CategoryEnum category) {
        setName(name);
        setDescription(description);
        setCost(cost);
        setCreationDate(creationDate);
        setAvailabilty(availabilty);
        setCategory(category);
        
    }
    public String getName() {
        return name;
    }
    private void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    private void setDescription(String description) {
        this.description = description;
    }
    public int getCost() {
        return cost;
    }
    private void setCost(int cost) {
        this.cost = cost;
    }
    public int getCreationDate() {
        return creationDate;
    }
    private void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }
    public Boolean getAvailabilty() {
        return availabilty;
    }
    private void setAvailabilty(Boolean availabilty) {
        this.availabilty = availabilty;
    }
    public CategoryEnum getCategory() {
        return category;
    }
    private void setCategory(CategoryEnum category) {
        this.category = category;
    }

}
