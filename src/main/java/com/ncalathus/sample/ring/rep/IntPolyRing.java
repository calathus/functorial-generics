package com.ncalathus.sample.ring.rep;

public class IntPolyRing extends PolynomialRing<NIntegerRing.INST, NIntegerRing.CLASS> {

    protected IntPolyRing(final NIntegerRing.CLASS coeffCls) {
        super(coeffCls, "X");
    }

    public static IntPolyRing.CLASS CLASS() {
        return new IntPolyRing(NIntegerRing.CLASS()).getCLASS();
    }

    public static void main(String[] args) {
        {
            final IntPolyRing.CLASS IntPolynomialRing = IntPolyRing.CLASS();
            final NIntegerRing.CLASS coeffCls = IntPolynomialRing.coefficientRing();
            final IntPolyRing.INST nr1 = IntPolynomialRing.create(7, coeffCls.unit());
            final IntPolyRing.INST nr2 = IntPolynomialRing.create(5, coeffCls.unit());
            final IntPolyRing.INST nr3 = nr1.mult(nr2);
            System.out.println(">> nr3: "+nr3);
        }
    }
}
