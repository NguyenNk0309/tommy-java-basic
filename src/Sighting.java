import java.time.LocalDateTime;
import java.util.Date;

public class Sighting {
    private Bird bird;
    private String location;
    private Date time;

    public Sighting(Bird bird, String location, Date time) {
        this.bird = bird;
        this.location = location;
        this.time = time;
    }

    public Bird getBird() {
        return bird;
    }

    public String getLocation() {
        return location;
    }

    public Date getTime() {
        return time;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
