public class Location
{
    public int xCoord;
    public int yCoord;
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }
    public Location()
    {
        this(0, 0);
    }
    @Override
    public boolean equals(Object obj) { //сравниваем поля xCoord и yCoord
        if (this == obj)
            return true;
        else if (obj != null && obj instanceof Location) {
            Location location = (Location) obj;
            return location.xCoord == xCoord && location.yCoord == yCoord;
        }
        return  false;
    }
    @Override
    public int hashCode(){
        return 31*xCoord + yCoord;
    }
}