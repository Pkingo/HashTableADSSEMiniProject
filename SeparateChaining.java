public class SeparateChaining<Key, Value> {
    private int m;
    private SequentialSearchST<Key, Value>[] st;
    public SeparateChaining() {
        this(997);
    }

    public SeparateChaining(int m) {
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; ++i)
            st[i] = new SequentialSearchST();
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        st[hash(key)].put(key, value);
    }
}