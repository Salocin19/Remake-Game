package Remake;
import java.util.*;

public class NQueue
{
  int max_size;
  int current_size;
  LinkedList<String> list;

  public NQueue (int size)
  {
    max_size = size;
    list = new LinkedList<String>();
  }

  public void add(String s)
  {
    if (current_size != max_size)
    {
      list.add(s);
      current_size++;
    }
    else
    {
      list.poll();
      list.add(s);
    }
  }

  public List<String> list()
  {
    return list;
  }
}
