package com.ncalathus.sample.ring.rep;

import com.ncalathus.sample.ring.api.*;

// this class allows the all elements of NumericRing to have the same class which follow from generic type N.
// Meta class must be used to share the same generic type for static and instance classes.
// Also these static/instance has recuresive references, so outer classes must be used.
// (see NumricRing in the INumericRing_Static<N, NumericRing> for NumericRingStatic)
public class NumericRing<N extends Number> implements IAbstractRing {

    protected final Class<N> _nCls;
    protected final CLASS _class;

    protected NumericRing(final Class<N> nCls) {
        this._nCls = nCls;
        this._class = new CLASS();
    }

    // this is an entry point.
    // all instance of NumericRing are cretated from NumericRingStatic
    // this gurantee, those elements has the same element type.
    public CLASS getCLASS() {
        return _class;
    }
    
    public static <N extends Number> NumericRing<N>.CLASS CLASS(Class<N> cls) {
        return new NumericRing<N>(cls).getCLASS();
    }

    //
    public class CLASS implements INumericRing.CLASS<N, INST> {
        private final INST _zero;
        private final INST _unit;
        private final INST _neg_unit;

        CLASS() {
            this._zero = new INST(create_zero(_nCls));
            this._unit = new INST(create_unit(_nCls));
            this._neg_unit = new INST(create_neg_unit(_nCls));
        }

        public INST zero() { return _zero; }
        public INST unit() { return _unit; }
        public INST neg_unit() { return _neg_unit; }

        public INST create(N n) {
            return new INST(n);
        }

        final N create_zero(Class<N> nCls) {
            if (nCls.equals(Short.class)) {
                return (N)new Short((short)0);
            } else if (nCls.equals(Integer.class)) {
                return (N)new Integer(0);
            } else if (nCls.equals(Long.class)) {
                return (N)new Long(0);
            } else if (nCls.equals(Float.class)) {
                return (N)new Float(0);
            } else if (nCls.equals(Double.class)) {
                return (N)new Double(0);
            } else {
                throw new RuntimeException("bug");
            }
        }
        private N create_unit(Class<N> nCls) {
            if (nCls.equals(Short.class)) {
                return (N)new Short((short)1);
            } else if (nCls.equals(Integer.class)) {
                return (N)new Integer(1);
            } else if (nCls.equals(Long.class)) {
                return (N)new Long(1);
            } else if (nCls.equals(Float.class)) {
                return (N)new Float(1);
            } else if (nCls.equals(Double.class)) {
                return (N)new Double(1);
            } else {
                throw new RuntimeException("bug");
            }
        }
        private N create_neg_unit(Class<N> nCls) {
            if (nCls.equals(Short.class)) {
                return (N)new Short((short)(-1));
            } else if (nCls.equals(Integer.class)) {
                return (N)new Integer(-1);
            } else if (nCls.equals(Long.class)) {
                return (N)new Long(-1);
            } else if (nCls.equals(Float.class)) {
                return (N)new Float(-1);
            } else if (nCls.equals(Double.class)) {
                return (N)new Double(-1);
            } else {
                throw new RuntimeException("bug");
            }
        }
    }

    public class INST implements INumericRing.INST<N, INST> {
        // this is to enforce the interface requirement.
        @Override
        public CLASS CLASS() {
            return _class;
        }

        private final N i;

        INST(final N i) {
            this.i = i;
        }

        @Override
        public INST add(final INST elem) {
            return new INST(add(i, elem.i));
        }
        @Override
        public INST subst(final INST elem) {
            return new INST(subst(i, elem.i));
        }
        @Override
        public INST mult(final INST elem) {
            return new INST(mult(i, elem.i));
        }

        @Override
        public String toString() {
            return ""+i;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof NumericRing.INST) {
                INST inst = (INST)obj;
                return i.equals(inst.i);
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return i.hashCode();
        }

        //
        private N add(final N n1, final N n2) {
            if (n1 == null || n2 == null) {
                throw new RuntimeException("add for null is not supported");
            }
            if (n1 instanceof Short) {
                final short i1 = (Short)n1;
                final short i2 = (Short)n2;
                return (N)new Short((short)(i1+i2));
            } else if (n1 instanceof Integer) {
                final int i1 = (Integer)n1;
                final int i2 = (Integer)n2;
                return (N)new Integer(i1+i2);
            } else if (n1 instanceof Long) {
                final long i1 = (Long)n1;
                final long i2 = (Long)n2;
                return (N)new Long(i1+i2);
            } else if (n1 instanceof Long) {
                final long i1 = (Long)n1;
                final long i2 = (Long)n2;
                return (N)new Long(i1+i2);
            } else if (n1 instanceof Float) {
                final float i1 = (Float)n1;
                final float i2 = (Float)n2;
                return (N)new Float(i1+i2);
            } else if (n1 instanceof Double) {
                final double i1 = (Double)n1;
                final double i2 = (Double)n2;
                return (N)new Double(i1+i2);
            } else {
                throw new RuntimeException("bug");
            }
        }
        private N subst(final N n1, final N n2) {
            if (n1 == null || n2 == null) {
                throw new RuntimeException("add for null is not supported");
            }
            if (n1 instanceof Short) {
                final short i1 = (Short)n1;
                final short i2 = (Short)n2;
                return (N)new Short((short)(i1-i2));
            } else if (n1 instanceof Integer) {
                final int i1 = (Integer)n1;
                final int i2 = (Integer)n2;
                return (N)new Integer(i1-i2);
            } else if (n1 instanceof Long) {
                final long i1 = (Long)n1;
                final long i2 = (Long)n2;
                return (N)new Long(i1-i2);
            } else if (n1 instanceof Long) {
                final long i1 = (Long)n1;
                final long i2 = (Long)n2;
                return (N)new Long(i1-i2);
            } else if (n1 instanceof Float) {
                final float i1 = (Float)n1;
                final float i2 = (Float)n2;
                return (N)new Float(i1-i2);
            } else if (n1 instanceof Double) {
                final double i1 = (Double)n1;
                final double i2 = (Double)n2;
                return (N)new Double(i1-i2);
            } else {
                throw new RuntimeException("bug");
            }
        }
        private N mult(final N n1, final N n2) {
            if (n1 == null || n2 == null) {
                throw new RuntimeException("add for null is not supported");
            }
            if (n1 instanceof Short) {
                final short i1 = (Short)n1;
                final short i2 = (Short)n2;
                return (N)new Short((short)(i1*i2));
            } else if (n1 instanceof Integer) {
                final int i1 = (Integer)n1;
                final int i2 = (Integer)n2;
                return (N)new Integer(i1*i2);
            } else if (n1 instanceof Long) {
                final long i1 = (Long)n1;
                final long i2 = (Long)n2;
                return (N)new Long(i1*i2);
            } else if (n1 instanceof Long) {
                final long i1 = (Long)n1;
                final long i2 = (Long)n2;
                return (N)new Long(i1*i2);
            } else if (n1 instanceof Float) {
                final float i1 = (Float)n1;
                final float i2 = (Float)n2;
                return (N)new Float(i1*i2);
            } else if (n1 instanceof Double) {
                final double i1 = (Double)n1;
                final double i2 = (Double)n2;
                return (N)new Double(i1*i2);
            } else {
                throw new RuntimeException("bug");
            }
        }
    }

    public static void main(String[] args) {
        //NumericRing<Integer> integerRing = new NumericRing<Integer>(Integer.class);
        //final NumericRing<Integer>.CLASS IntegerRing = new NumericRing<Integer>(Integer.class).create();
        final NumericRing<Integer>.CLASS IntegerRing = NumericRing.CLASS(Integer.class);
        final NumericRing<Integer>.INST nr1 = IntegerRing.create(7);
        final NumericRing<Integer>.INST nr2 = IntegerRing.create(9);
        final NumericRing<Integer>.INST nr3 = nr1.mult(nr2);
        System.out.println(">> nr3: "+nr3);
    }
}
