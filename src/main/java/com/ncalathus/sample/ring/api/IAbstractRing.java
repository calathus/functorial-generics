package com.ncalathus.sample.ring.api;

public interface IAbstractRing {

    public interface CLASS<REP extends INST> {

        REP zero();

        REP unit();

        REP neg_unit();
   }

    public interface INST<REP extends INST> {

        CLASS<REP> CLASS();

        REP add(REP elem);

        REP subst(REP elem);

        REP mult(REP elem);
    }
}
