//contains HitBox data for all sprites
package Remake;

import java.util.*;
public class HitBoxesMap
{
    public static Map<String, HitBox> hitBoxesMap;
    
    
    public static void initialize()
    {
        hitBoxesMap = new HashMap<String,HitBox>();
        hitBoxesMap.put("Salostand", new HitBox(0,0,27,48,26,79));
        hitBoxesMap.put("Salorun", new HitBox(0,0,27,44,12,30));
        hitBoxesMap.put("Salojump", new HitBox(0,0,23,50,7,28));
        hitBoxesMap.put("Salofall", new HitBox(0,0,23,50,7,28));
        hitBoxesMap.put("Darkball", new HitBox(0,0,12,11,8,10));



        hitBoxesMap.put("Coolfool", new HitBox(0,0,24,49,10,27));
    }
    
    public static HitBox getHitBox(String filename)
    {
        return HitBoxesMap.hitBoxesMap.get(filename).clone();
    }
    
    public static void clear()
    {
        hitBoxesMap.clear();
    }
}
