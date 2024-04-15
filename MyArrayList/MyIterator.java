
/**
 * Defines a minimal iterator list for the lab.
 * 
 * @author Kenya Aridomi
 * @version October 14, 2019
 */
public interface MyIterator<E> extends java.util.Iterator<E>
{
    boolean hasNext();
    E next();
    void remove();
}
