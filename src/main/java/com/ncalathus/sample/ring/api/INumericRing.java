package com.ncalathus.sample.ring.api;

public interface INumericRing {
    interface CLASS<N extends Number, REP extends INST<N, REP>> extends IAbstractRing.CLASS<REP> {
        REP create(N n);
    }

    interface INST<N extends Number, REP extends INST<N, REP>> extends IAbstractRing.INST<REP> {

        @Override
        CLASS<N, REP> CLASS();
    }
}
