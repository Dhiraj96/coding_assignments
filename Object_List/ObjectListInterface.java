
/**
 * Write a description of interface ObjectListInterface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface ObjectListInterface
{
  Object  getCurrent();
  Object  getFirst();
  Object  getNext();
  Object  getPrevious();
  Object  getLast();
  boolean append(Object newObject);
  boolean insert(Object newObject);
  Object  remove();
  boolean replace(Object newObject);
  boolean clear();
  int     getLength();
  int     getCurrentPosition();
}

