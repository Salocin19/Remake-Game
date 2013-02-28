//contains HitBox data for all sprites

import java.util.*;
public class HitBoxesMap
{
    public static Map<String, HitBox> hitBoxesMap;
    
    
    public static void initialize()
    {
        hitBoxesMap = new HashMap<String,HitBox>();
        hitBoxesMap.put("Salocin.png", new HitBox(0,0,19,48,29,79));
    }
    
    public static HitBox getHitBox(String filename)
    {
        return HitBoxesMap.hitBoxesMap.get(filename);
    }
    
    public static void clear()
    {
        hitBoxesMap.clear();
    }
}
