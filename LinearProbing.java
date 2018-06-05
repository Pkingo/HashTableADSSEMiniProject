public class LinearProbing<Key, Value> {
    private int n;
    private int m;
    private Key[] keys;
    private Value[] values;

    public LinearProbing() {
        this(17);
    }

    public LinearProbing(int m) {
        this.m = m;
        keys    = (Key[])   new Object[m];
        values  = (Value[]) new Object[m];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int cap) {
        LinearProbing<Key, Value> t = new LinearProbing(cap);
        for (int  i = 0; i < m; ++i) 
            if (keys[i] != null)
                t.put(keys[i], values[i]);
        keys    = t.keys;
        values  = t.values;
        m       = t.m;
    }

    private Boolean contains(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i+1) % m)
            if (keys[i].equals(key)) return true;
        return false;
    }

    public void put(Key key, Value value) {
        if (n >= m/2) resize(2*m);
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % m)
            if (keys[i].equals(key)) { values[i] = value; return; }
        keys[i]     = key;
        values[i]   = value;
        n++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i+1) % m)
            if (keys[i].equals(key))
                return values[i];
        return null;
    }

    public void delete(Key key) {
        if (!contains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % m;
        keys[i]     = null;
        values[i]   = null;
        i = (i + 1) % m;
        while (keys[i] != null) {
            Key     keyToRedo   = keys[i];
            Value   valueToRedo = values[i];
            keys[i]     = null;
            values[i]   = null;
            n--;
            put(keyToRedo, valueToRedo);
            i = (i + 1) % m;
        }
        n--;
        if (n > 0 && n <= m/8)
            resize(m/2);
    }
}