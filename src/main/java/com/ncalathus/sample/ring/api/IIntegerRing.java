package com.ncalathus.sample.ring.api;

public interface IIntegerRing {

    interface CLASS<REP extends INST> extends IAbstractRing.CLASS<REP> {

        REP create(int i);
    }

    interface INST<REP extends INST> extends IAbstractRing.INST<REP> {

        @Override
        IIntegerRing.CLASS<REP> CLASS();
    }
}
