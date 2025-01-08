package CENG112_HW2;

public class Room {
    private int roomNumber;
    private String roomType;
    private boolean availability;

    public Room(int roomNumber, String roomType, boolean availability) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.availability = availability;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}