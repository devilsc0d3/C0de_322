package start;

import java.util.ArrayList;
import java.util.List;

public class Event {
    List<Event> eventList = new ArrayList<>();
    String name;

    public List<Event> getevent(){
        return eventList;
    }
    public void setEventList(){
        this.eventList = eventList;
    }
}
