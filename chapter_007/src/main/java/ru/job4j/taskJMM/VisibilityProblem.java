package ru.job4j.taskJMM;

public class VisibilityProblem {
    private String a= "abc";
    private int numb = new Integer(1000000);
    private Item item = new Item();

    public String getA() {
        return a;
    }

    public int getNumb() {
        return numb;
    }

    public Item getItem() {
        return item;
    }

    public void setA(String a) {
        this.a = a;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    class Item {
        private int size = 0;
        private int length = 0;

        public int getSize() {
            return size;
        }

        public int getLength() {
            return length;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public void setLength(int length) {
            this.length = length;
        }
    }
}
