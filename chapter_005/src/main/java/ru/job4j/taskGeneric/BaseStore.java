package ru.job4j.taskGeneric;


public  abstract class BaseStore<T extends Base> implements Store<T> {
    private SimpleArray<T> simpleArray = new SimpleArray<>(5);

    @Override
    public void add(T t) {
        if (t != null && indexRole(t) == -1) {
            simpleArray.add(t);
        }
    }

    @Override
    public void update(T newT) {
        int index = -1;
        if (newT != null) {
            index = indexRole(newT);
        }
        if (index != -1) {
            simpleArray.update(newT, index);
        }
    }

    @Override
    public T delete(T deletedT) {
        T t = null;
        int index = -1;
        if(deletedT != null) {
            index = indexRole(deletedT);
        }
        if (index != -1) {
            t = simpleArray.get(index);
            simpleArray.delete(index);
        }
        return t;
    }

    private int indexRole(T t) {
        T finedT;
        int index = -1;
        for (int i = 0; i < simpleArray.size(); i++) {
            finedT = simpleArray.get(i);
            if (finedT.getId().equals(t.getId())) {
                index = i;
                break;
            }
        }
        return index;
    }
}
