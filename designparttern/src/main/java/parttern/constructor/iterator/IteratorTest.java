package parttern.constructor.iterator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author whp 18-4-17
 */
public class IteratorTest {
    public static void main(String[] args){
        NameRepository nameRepository=new NameRepository();
        for(Iterator iterator=nameRepository.getIterator();iterator.hasNext();){
            System.out.println((String)iterator.next());
        }

        Map<String,String> map=new HashMap<String, String>();
        Set<String> set=new HashSet<String>();
        java.util.Iterator iterator=set.iterator();
    }
}
