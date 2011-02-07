package com.ncalathus.sample.ring.api;

import java.util.Map;

public interface IPolynomialRing {
    interface CLASS<
            C_INST extends IAbstractRing.INST<C_INST>, C_CLASS extends IAbstractRing.CLASS<C_INST>,
            REP extends INST<C_INST, C_CLASS, REP>
        > extends IAbstractRing.CLASS<REP> {

        C_CLASS coefficientRing();

        REP variable(int degree);

        REP create(int degree, C_INST c);

        REP create(final Map<Integer, C_INST> terms);

        REP create(final C_INST... coeffs);

    }

    interface INST<
            C_INST extends IAbstractRing.INST<C_INST>, C_CLASS extends IAbstractRing.CLASS<C_INST>,
            REP extends INST<C_INST, C_CLASS, REP>
        > extends IAbstractRing.INST<REP> {

        @Override
        CLASS<C_INST, C_CLASS, REP> CLASS();

        int degree();
        
        C_INST coefficient(int degree);

        REP r_mult(C_INST coeff);
    }
 
}
