package com.ncalathus.sample.ring.rep;

import com.ncalathus.sample.ring.api.IAbstractRing;
import com.ncalathus.sample.ring.api.IIntegerRing;

public class IntegerRing implements IIntegerRing.INST<IntegerRing> {
    private static final IntegerRing zero = new IntegerRing(0);
    private static final IntegerRing unit = new IntegerRing(1);
    private static final IntegerRing neg_unit = new IntegerRing(-1);

    private final static IIntegerRing.CLASS<IntegerRing> STATIC = new IIntegerRing.CLASS<IntegerRing>() {
        public IntegerRing zero() { return zero; }
        public IntegerRing unit() { return unit; }
        public IntegerRing neg_unit() { return neg_unit; }
        public IntegerRing create(int i) {
            return new IntegerRing(i);
        }
    };

    // this is to enforce the interface requirement.
    @Override
    public IIntegerRing.CLASS<IntegerRing> CLASS() {
        return STATIC;
    }

    private final int i;

    private IntegerRing(final int i) {
        this.i = i;
    }

    @Override
    public IntegerRing add(final IntegerRing elem) {
        return new IntegerRing(i+elem.i);
    }
    @Override
    public IntegerRing subst(final IntegerRing elem) {
        return new IntegerRing(i-elem.i);
    }
    @Override
    public IntegerRing mult(final IntegerRing elem) {
        return new IntegerRing(i*elem.i);
    }

    @Override
    public String toString() {
        return ""+i;
    }

    public static void main(String[] args) {
        IAbstractRing.INST two = STATIC.unit().add(STATIC.unit());
        IntegerRing two1 = unit.add(unit);
        IntegerRing i1 = new IntegerRing(3);
        IntegerRing i2 = new IntegerRing(33);
        IntegerRing i3 = i1.mult(i2);

        System.out.println("two: "+two);
        System.out.println("two1: "+two1);
        System.out.println("i1: "+i1);
        System.out.println("i2: "+i2);
        System.out.println("i3: "+i3);
    }
}
