public class Bird {
    private String name;
    private String color;
    private Float weight;
    private Float height;

    public Bird(String name, String color, Float weight, Float height) {
        this.name = name;
        this.color = color;
        this.weight = weight;
        this.height = height;
    }

    public Bird() {
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Float getWeight() {
        return weight;
    }

    public Float getHeight() {
        return height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public void setHeight(Float height) {
        this.height = height;
    }
}
