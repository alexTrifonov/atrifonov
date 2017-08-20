package ru.job4j.taskGeneric;


/**
 * Class for iterator for prime number in array.
 * @author atrifonov.
 * @since 19.08.2017.
 * @version 1.
 */

public class SimpleArray<T> {
    private Object objects[];
    private int index = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public void add(T value) {
        if(index == this.objects.length) {
            int newLength = objects.length + objects.length / 2;
            Object[] newObjects = new Object[newLength];
            System.arraycopy(this.objects, 0, newObjects, 0, this.objects.length);
            this.objects = newObjects;

        }
        this.objects[index++] = value;
    }

    public void update(T newValue, int position) {
        if(position < index) {
            this.objects[position] = newValue;
        }

    }

    public T delete(int position) {
        T deletedObject = (T) new Object();
        if (position < index) {
            Object[] newObjects = new Object[this.objects.length - 1];
            deletedObject = (T) this.objects[position];
            System.arraycopy(this.objects, 0, newObjects, 0, position);
            System.arraycopy(this.objects, position + 1, newObjects, position, this.objects.length - position - 1);
            this.objects = newObjects;
            index--;

        } else {
            deletedObject = null;
        }
        return deletedObject;
    }

    public int size(){
        return this.index;
    }

    public T get(int position) {
        T getObject;
        if (position < this.index) {
            getObject = (T) this.objects[position];
        } else {
            getObject = null;
        }
        return getObject;
    }
}

