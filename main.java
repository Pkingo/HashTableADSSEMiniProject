
class main {
    public static void main(String[] args) {
        SeparateChaining<String, Integer> SC = new SeparateChaining();
        String[] keys = {"Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit", "Vivamus", "venenatis"};
        Integer[] values = {5, 1, 6, 3, 7, 2, 8, 2, 8, 10};
        for (int i = 0; i < keys.length; ++i)
            SC.put(keys[i], values[i]);
        for(int i = 0; i < keys.length; ++i)
            System.out.println(SC.get(keys[i]));
    }
}