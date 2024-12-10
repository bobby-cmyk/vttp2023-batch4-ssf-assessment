package vttp.ssf.assessment.eventmanagement.models;

public class Event {
    private Integer eventId;
    private String eventName;
    private Integer eventSize;
    private Long eventDate;
    private Integer participants;

    public Integer getEventId() {
        return eventId;
    }
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getEventSize() {
        return eventSize;
    }
    public void setEventSize(Integer eventSize) {
        this.eventSize = eventSize;
    }

    public Long getEventDate() {
        return eventDate;
    }
    public void setEventDate(Long eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getParticipants() {
        return participants;
    }
    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "Event [eventId=" + eventId + ", eventName=" + eventName + ", eventSize=" + eventSize + ", eventDate="
                + eventDate + ", participants=" + participants + "]";
    }

}
