import java.util.HashMap;
import java.util.Iterator;
public class AStarState {
    private Map2D map;
    public HashMap<Location, Waypoint> openWaypoints;
    public HashMap<Location, Waypoint> closeWaypoints;
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");
        openWaypoints = new HashMap<Location, Waypoint>();
        closeWaypoints = new HashMap<Location, Waypoint>();
        this.map = map;
    }
    public Map2D getMap()
    {
        return map;
    }

    public Waypoint getMinOpenWaypoint() //2 поиск вершины с наименьшей общей стоимостью
    {
        if (openWaypoints.size()==0){
            return null;
        }
        Iterator it = openWaypoints.values().iterator();
        Waypoint result = (Waypoint)it.next();
        while(it.hasNext()) {
            Waypoint waypoint = (Waypoint)it.next();
            if(result.getTotalCost()>waypoint.getTotalCost()) {
                result = waypoint;
            }
        }
        return result;
    }
    public boolean addOpenWaypoint(Waypoint newWP) {
    if(!openWaypoints.containsValue(newWP) || openWaypoints.get(newWP.getLocation()).getPreviousCost()>newWP.getPreviousCost()){
            openWaypoints.put(newWP.getLocation(), newWP);
            return true;
        }
        return false;
    }
    public int numOpenWaypoints(){
        return openWaypoints.size();
    }
    public void closeWaypoint(Location loc)
    {
        if(!openWaypoints.containsKey(loc))
            return;
        closeWaypoints.put(loc, openWaypoints.get(loc));
        openWaypoints.remove(loc);
    }
    public boolean isLocationClosed(Location loc) //4 встречается ли указанное местоположение в наборе закрытых вершин
    {
        return closeWaypoints.containsKey(loc);
    }
}
