package model;

public class FreeRoom extends Room {
    final Double roomPrice = 0.0;
    public FreeRoom(String roomNumber, Double roomPrice, RoomType enumeration) {
        super(roomNumber, roomPrice, enumeration);
    }

    @Override
    public String toString() {
        return "RoomNumber:" + this.getRoomNumber() + ";"
                + "Price:" + roomPrice + "(Free room);"
                + "Type:" + this.getRoomType();
    }
}
