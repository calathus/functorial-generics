package com.ncalathus.sample.ring.rep;

import com.ncalathus.sample.ring.api.IPolynomialRing;
import java.util.HashMap;

public class NumericPolynomialRing<N extends Number> extends PolynomialRing<NumericRing<N>.INST, NumericRing<N>.CLASS> {
    protected NumericPolynomialRing(final NumericRing<N>.CLASS coeffCls, final String variable) {
        super(coeffCls, variable);
    }
    public class CLASS extends PolynomialRing<NumericRing<N>.INST, NumericRing<N>.CLASS>.CLASS implements IPolynomialRing.CLASS<NumericRing<N>.INST, NumericRing<N>.CLASS, INST> {
        public final INST create(final N... coeffs) {
            return create(new HashMap<Integer, NumericRing<N>.INST>() {{
                int degree = coeffs.length-1;
                for (int i = 0; i <= degree; i++) {
                    final NumericRing<N>.INST c = _coeffCls.create(coeffs[i]);
                    if (!c.equals(_coeffCls.zero())) {
                        put(degree-i, c);
                    }
                }
            }});
        }
    }
    @Override
    protected void initCLASS() {
        this._class = new CLASS();
    }
    @Override
    public CLASS getCLASS() {
        return (CLASS)_class;
    }

    public static <N extends Number> NumericPolynomialRing<N>.CLASS CLASS(final NumericRing<N>.CLASS nCls, final String variable) {
        return new NumericPolynomialRing(nCls, variable).getCLASS();
    }

    //
    //
    //
    static void test_int_1() {
        System.out.println("----------- test_int_1 -----------");
        final NumericPolynomialRing<Integer>.CLASS NIntPolynomialRing = NumericPolynomialRing.CLASS(NumericRing.CLASS(Integer.class), "X");
        final NumericRing<Integer>.CLASS coeffCls = NIntPolynomialRing.coefficientRing();
        {
            final NumericPolynomialRing<Integer>.INST nr1 = NIntPolynomialRing.create(7, coeffCls.unit());
            final NumericPolynomialRing<Integer>.INST nr2 = NIntPolynomialRing.create(5, coeffCls.unit());
            final NumericPolynomialRing<Integer>.INST nr3 = nr1.mult(nr2);
            System.out.println(">> nr1: "+nr1);
            System.out.println(">> nr2: "+nr2);
            System.out.println(">> nr3: "+nr3);
        }
        // this looks strange, but it is OK, since subclass deosn ot define CLASS/INST, and they use teh  same inner class in byte code level.
        {
            final IntPolyRing.INST nr1 = NIntPolynomialRing.create(7, coeffCls.unit());
            final IntPolyRing.INST nr2 = NIntPolynomialRing.create(5, coeffCls.unit());
            final IntPolyRing.INST nr3 = nr1.mult(nr2);
            System.out.println(">> nr1: "+nr1);
            System.out.println(">> nr2: "+nr2);
            System.out.println(">>> nr3: "+nr3);
        }
    }
    
    static void test_int_2() {
        System.out.println("----------- test_int_2 -----------");
        final NumericPolynomialRing<Integer>.CLASS NIntPolynomialRing = NumericPolynomialRing.CLASS(NumericRing.CLASS(Integer.class), "X");
        final NumericRing<Integer>.CLASS coeffCls = NIntPolynomialRing.coefficientRing();
        {
            /*
            final NumericPolynomialRing<Integer> npr = new NumericPolynomialRing(NumericRing.CLASS(Integer.class));
            final NumericPolynomialRing<Integer>.INST nr1 = npr.new INST() {{
                final Random rnd = new Random(10);
                for (int i = 0; i < 10; i++) {
                    //int degree =
                    //add();
                }
            }};
            */
            final NumericPolynomialRing<Integer>.INST nr1 = NIntPolynomialRing.create(2, 3, 4, 6, 58, 3);
            final NumericPolynomialRing<Integer>.INST nr2 = NIntPolynomialRing.create(12, 31, 14, 16, 8, 13);
            final NumericPolynomialRing<Integer>.INST nr3 = nr1.mult(nr2);
            System.out.println(">> nr1: "+nr1);
            System.out.println(">> nr2: "+nr2);
            System.out.println(">> nr3: "+nr3);
        }
    }
    // (compiler) error case
    /*
    static void test_int_1a() {
        final NumericPolynomialRing<Integer>.CLASS NIntPolynomialRing = NumericPolynomialRing.CLASS(NumericRing.CLASS(Integer.class));
        final NumericRing<Float>.CLASS coeffCls = NIntPolynomialRing.coefficientRing();
        {
            final NumericPolynomialRing<Integer>.INST nr1 = NIntPolynomialRing.create(7, coeffCls.unit());
            final NumericPolynomialRing<Integer>.INST nr2 = NIntPolynomialRing.create(5, coeffCls.unit());
            final NumericPolynomialRing<Integer>.INST nr3 = nr1.mult(nr2);
            System.out.println(">> nr3: "+nr3);
        }
        // strange..
        {
            final IntPolyRing.INST nr1 = NIntPolynomialRing.create(7, coeffCls.unit());
            final IntPolyRing.INST nr2 = NIntPolynomialRing.create(5, coeffCls.unit());
            final IntPolyRing.INST nr3 = nr1.mult(nr2);
            System.out.println(">>> nr3: "+nr3);
        }
    }
    */

    // this looks type checking is not working as expected..
    static void test_int_1b() {
        System.out.println("----------- test_int_1b -----------");
        final NumericPolynomialRing<Integer>.CLASS NIntPolynomialRing = NumericPolynomialRing.CLASS(NumericRing.CLASS(Integer.class), "X");
        final NIntegerRing.CLASS coeffCls = NIntPolynomialRing.coefficientRing(); // strange..
        {
            final NumericPolynomialRing<Integer>.INST nr1 = NIntPolynomialRing.create(7, coeffCls.unit());
            final NumericPolynomialRing<Integer>.INST nr2 = NIntPolynomialRing.create(5, coeffCls.unit());
            final NumericPolynomialRing<Integer>.INST nr3 = nr1.mult(nr2);
            System.out.println(">> nr1: "+nr1);
            System.out.println(">> nr2: "+nr2);
            System.out.println(">> nr3: "+nr3);
        }
        // strange..
        {
            final IntPolyRing.INST nr1 = NIntPolynomialRing.create(7, coeffCls.unit());
            final IntPolyRing.INST nr2 = NIntPolynomialRing.create(5, coeffCls.unit());
            final IntPolyRing.INST nr3 = nr1.mult(nr2);
            System.out.println(">> nr1: "+nr1);
            System.out.println(">> nr2: "+nr2);
            System.out.println(">>> nr3: "+nr3);
        }
    }
    
    static void test_float_1() {
        System.out.println("----------- test_float_1 -----------");
        final NumericPolynomialRing<Float>.CLASS NFloatPolynomialRing = NumericPolynomialRing.CLASS(NumericRing.CLASS(Float.class), "X");
        final NumericRing<Float>.CLASS coeffCls = NFloatPolynomialRing.coefficientRing();
        {
            final NumericPolynomialRing<Float>.INST nr1 = NFloatPolynomialRing.create(7, coeffCls.create(43.7f));
            final NumericPolynomialRing<Float>.INST nr2 = NFloatPolynomialRing.create(5, coeffCls.create(48.5f));
            final NumericPolynomialRing<Float>.INST nr3 = nr1.mult(nr2);
            System.out.println(">> nr1: "+nr1);
            System.out.println(">> nr2: "+nr2);
            System.out.println(">> nr3: "+nr3);
        }
    }

    static void test_float_2() {
        System.out.println("----------- test_float_2 -----------");
        final NumericRing<Float>.CLASS FloatNumeral = NumericRing.CLASS(Float.class);
        final NumericPolynomialRing<Float>.CLASS NFloatPolynomialRing = NumericPolynomialRing.CLASS(FloatNumeral, "X");
        {
            final NumericPolynomialRing<Float>.INST nr0 = NFloatPolynomialRing.create(FloatNumeral.unit(), FloatNumeral.unit(), FloatNumeral.unit());
            final NumericPolynomialRing<Float>.INST nr1 = NFloatPolynomialRing.create(2.1f, 3f, 4f, 6.5f, 58f, 3f);
            final NumericPolynomialRing<Float>.INST nr2 = NFloatPolynomialRing.create(12f, 31.9f, 14f, 16f, 8f, 13f);
            final NumericPolynomialRing<Float>.INST nr3 = NFloatPolynomialRing.create(14f, 16f, 8f, 13f);
            final NumericPolynomialRing<Float>.INST nr4 = NFloatPolynomialRing.create(8f, 13f);
            final NumericPolynomialRing<Float>.INST nr5 = NFloatPolynomialRing.create(14f, 0f, 8f, 13f);
            final NumericPolynomialRing<Float>.INST nr6 = nr1.mult(nr2);
            System.out.println(">> nr1: "+nr1);
            System.out.println(">> nr2: "+nr2);
            System.out.println(">> nr3: "+nr3);
            System.out.println(">> nr4: "+nr4);
            System.out.println(">> nr5: "+nr5);
            System.out.println(">> nr6: "+nr6);

            final PolynomialRing<NumericPolynomialRing<Float>.INST, NumericPolynomialRing<Float>.CLASS>.CLASS NFloatPolynomial2Ring = PolynomialRing.CLASS(NFloatPolynomialRing, "Y");

            //final PolynomialRing<NumericPolynomialRing<Float>.INST, NumericPolynomialRing<Float>.CLASS>.INST nr1a = NFloatPolynomial2Ring.create(nr1, nr2, nr3);
            //final PolynomialRing<NumericPolynomialRing<Float>.INST, NumericPolynomialRing<Float>.CLASS>.INST nr1a = NFloatPolynomial2Ring.create(nr1, NFloatPolynomialRing.unit());
            //final PolynomialRing<NumericPolynomialRing<Float>.INST, NumericPolynomialRing<Float>.CLASS>.INST nr1a = NFloatPolynomial2Ring.create(NFloatPolynomialRing.unit(), NFloatPolynomialRing.unit(), NFloatPolynomialRing.unit());

            final PolynomialRing<NumericPolynomialRing<Float>.INST, NumericPolynomialRing<Float>.CLASS>.INST nr1a = NFloatPolynomial2Ring.create(nr0, nr0, nr0);
            final PolynomialRing<NumericPolynomialRing<Float>.INST, NumericPolynomialRing<Float>.CLASS>.INST nr2a = NFloatPolynomial2Ring.create(nr4, nr5, nr6);
            final PolynomialRing<NumericPolynomialRing<Float>.INST, NumericPolynomialRing<Float>.CLASS>.INST nr3a = nr1a.mult(nr1a);
            System.out.println(">> nr1a: "+nr1a);
            System.out.println(">> nr2a: "+nr2a);
            System.out.println(">> nr3a: "+nr3a);
        }

    }

    public static void main(String[] args) {
        /*
        test_int_1();
        test_int_1b();
        test_float_1();
        */
        //test_int_2();
        test_float_2();    }
}
