package Domain.ADT;

public class MyPair<E1,E2> implements IMyPair<E1,E2> {

    private E1 e1;
    private E2 e2;

    public MyPair(E1 e1, E2 e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public E1 getFirst() {
        return e1;
    }

    @Override
    public E2 getSecond() {
        return e2;
    }

    @Override
    public String toString(){
        return "<"+e1.toString() + "," + e2.toString()+">";
    }
}
