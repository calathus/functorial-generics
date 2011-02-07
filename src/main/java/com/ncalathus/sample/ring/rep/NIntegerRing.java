package com.ncalathus.sample.ring.rep;

public class NIntegerRing extends NumericRing<Integer> {
    public NIntegerRing() {
        super(Integer.class);
    }
    public static NIntegerRing.CLASS CLASS() {
        return new NIntegerRing().getCLASS();
    }

    public static void main(String[] args) {
        final NIntegerRing.CLASS IntegerRing = NIntegerRing.CLASS();
        final NIntegerRing.INST nr1 = IntegerRing.create(17);
        final NIntegerRing.INST nr2 = IntegerRing.create(19);
        final NIntegerRing.INST nr3 = nr1.mult(nr2);
        System.out.println(">> nr3: "+nr3);
    }

}
