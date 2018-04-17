package parttern.constructor.iterator;

/**
 * @author whp 18-4-17
 */
public class NameRepository implements Container {

    public String names[]={"whp","wwr","lwz"};

    public Iterator getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements Iterator{
        int index;
        public boolean hasNext() {
            return index<names.length;
        }

        public Object next() {
            if(this.hasNext()){
                return names[index++];
            }
            return null;
        }
    }
}
