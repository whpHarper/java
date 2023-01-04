package parttern.iterator;

import java.util.Arrays;
import java.util.List;

public class PepoleContainer implements Container{
    private List<String> names= Arrays.asList("whp","wsp","wwr");

    @Override
    public Iterator iterator() {
        return new ContainerIterator();
    }

    private class ContainerIterator implements Iterator{
        int index=0;
        @Override
        public boolean hasNext() {
            return index<names.size();
        }

        @Override
        public Object next() {
            return names.get(index++);
        }
    }
}
