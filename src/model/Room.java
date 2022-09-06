package model;

public class Room implements IRoom{

    private String roomNumber;
    private Double roomPrice;
    private RoomType enumeration;

    public Room(String roomNumber, Double roomPrice, RoomType enumeration) {
        super();
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return roomPrice;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        if (roomPrice == 0.0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        IRoom room = (IRoom) o;
        if (this.getRoomNumber().equals(room.getRoomNumber()) &&
                this.getRoomPrice().equals(room.getRoomPrice()) &&
                this.getRoomType().equals(room.getRoomType())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int res = 23;
        res = 31 * res + roomNumber.hashCode();
        res = 31 * res + roomPrice.hashCode();
        res = 31 * res + enumeration.hashCode();
        return res;
    }

    @Override
    public String toString() {
        return "RoomNumber:" + roomNumber + ";"
                + "Price:" + roomPrice + ";"
                + "Type:" + enumeration;
    }
}
