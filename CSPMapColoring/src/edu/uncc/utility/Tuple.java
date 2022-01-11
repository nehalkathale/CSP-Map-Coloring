package edu.uncc.utility;

public class Tuple<T1, T2> {

	private final T1 tuple1;

    private final T2 tuple2;

    public Tuple(T1 tuple1, T2 tuple2) {
        this.tuple1 = tuple1;
        this.tuple2 = tuple2;
    }

    //	getFirst
    public T1 fetchT1Element() {
        return tuple1;
    }

    //	getSecond
    public T2 fetchT2Element() {
        return tuple2;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Tuple<?, ?>) {
        	Tuple<?, ?> p = (Tuple<?, ?>) o;
            return tuple1.equals(p.tuple1) && tuple2.equals(p.tuple2);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return tuple1.hashCode() + 31 * tuple2.hashCode();
    }

    @Override
    public String toString() {
        return "< " + fetchT1Element().toString() + " , " + fetchT2Element().toString() + " > ";
    }
	
}