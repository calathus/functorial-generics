package com.ncalathus.sample.ring.rep;

import com.ncalathus.sample.ring.api.IAbstractRing;
import com.ncalathus.sample.ring.api.IPolynomialRing;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PolynomialRing<C_INST extends IAbstractRing.INST<C_INST>, C_CLASS extends IAbstractRing.CLASS<C_INST>> {

    protected final C_CLASS _coeffCls;
    protected CLASS _class;
    protected final String variable;

    protected PolynomialRing(final C_CLASS coeffCls, final String variable) {
        this._coeffCls = coeffCls;
        this.variable = variable;
        initCLASS();
    }

    // this should be overl;oaded by subclass if CLASS is redefined thee.
    protected void initCLASS() {
        this._class = new CLASS();
    }
    
    // this is an entry point.
    // all instance of NumericRing are cretated from NumericRingStatic
    // this gurantee, those elements has the same element type.
    //
    // this should be overl;oaded by subclass if CLASS is redefined thee.
    public CLASS getCLASS() {
        return _class;
    }

    public static <C_INST extends IAbstractRing.INST<C_INST>, C_CLASS extends IAbstractRing.CLASS<C_INST>> PolynomialRing<C_INST, C_CLASS>.CLASS CLASS(C_CLASS cls, final String variable) {
        return new PolynomialRing<C_INST, C_CLASS>(cls, variable).getCLASS();
    }

    public class Term {
        final int degree;
        final C_INST c;
        Term(final int degree, final C_INST c) {
            this.degree = degree;
            this.c = c;
        }

        Term r_mult(final C_INST c1) {
            final C_INST c2 = c.mult(c1);
            return new Term(degree, c2);
        }
        Term mult(final Term term) {
            return new Term(degree+term.degree, c.mult(term.c));
        }
        @Override
        public String toString() {
            return "("+c+")"+termString();
        }
        public String termString() {
            if (degree == 0) {
                return "";
            } else if (degree == 1) {
                return variable;
            } else {
                return variable+"^"+degree;
            }
        }
        public String toString(final String vars) {
            return "("+c+")"+termString(vars);
        }
        public String termString(final String vars) {
            if (degree == 0) {
                return vars;
            } else {
                return termString()+((vars.isEmpty())?"":("*"+vars));
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof PolynomialRing.Term) {
                Term term = (Term)obj;
                return degree == term.degree && c == term.c;
            } else {
                return false;
            }
        }
        @Override
        public int hashCode() {
            return degree+137*c.hashCode();
        }
    }

    //
    public class CLASS implements IPolynomialRing.CLASS<C_INST, C_CLASS, INST> {
        private final INST _zero;
        private final INST _unit;
        private final INST _neg_unit;

        CLASS() {
            this._zero = create(0, _coeffCls.zero());
            this._unit = create(0, _coeffCls.unit());
            this._neg_unit = create(0, _coeffCls.neg_unit());
        }

        public INST zero() { return _zero; }
        public INST unit() { return _unit; }
        public INST neg_unit() { return _neg_unit; }

        @Override
        public C_CLASS coefficientRing() {
            return _coeffCls;
        }

        @Override
        public INST variable(int degree) {
            return new INST(degree, _coeffCls.unit());
        }

        @Override
        public final INST create(int degree, C_INST c) {
            return new INST(degree, c);
        }

        @Override
        public final INST create(final Map<Integer, C_INST> terms) {
            return new INST(new ArrayList<Term>() {{
                for (final Map.Entry<Integer, C_INST> term: terms.entrySet()) {
                    add(new Term(term.getKey(), term.getValue()));
                }
            }});
        }
        
        @Override
        public final INST create(final C_INST... coeffs) {
            return new INST(new ArrayList<Term>() {{
                int degree = coeffs.length-1;
                for (int i = degree; i >= 0; i--) {
                    final C_INST c = coeffs[i];
                    if (!c.equals(_coeffCls.zero())) {
                        add(new Term(degree-i, c));
                    }
                }
            }});
        }
    }

    public class INST implements IPolynomialRing.INST<C_INST, C_CLASS, INST> {
        // this is to enforce the interface requirement.
        @Override
        public CLASS CLASS() {
            return _class;
        }

        private final List<Term> terms;

        protected INST(final int degree, final C_INST c) {
            this.terms = new ArrayList<Term>();
            terms.add(new Term(degree, c));
        }
        protected INST(final List<Term> terms) {
            this.terms = normalize(terms);
        }
        protected INST() {
            this.terms = new ArrayList<Term>();
        }

        private List<Term> normalize(List<Term> terms) {
            final Set<Integer> degrees = new HashSet<Integer>();
            for (final Term term: terms) {
                degrees.add(term.degree);
            }
            final List<Integer> sorted_degrees = new ArrayList<Integer>(degrees);
            Collections.sort(sorted_degrees, Collections.reverseOrder());
            final List<Term> norm = new ArrayList<Term>();
            for (final Integer degree: sorted_degrees) {
               final C_INST c0 = sumCoeff(degree, terms);
               if (!c0.equals(_coeffCls.zero())) {
                   norm.add(new Term(degree, c0));
               }
            }
            return norm;
        }
        private C_INST sumCoeff(int degree, List<Term> terms) {
            C_INST c = _coeffCls.zero();
            for (final Term term: terms) {
                if (term.degree == degree) {
                    c = c.add(term.c);
                }
            }
            return c;
        }

        @Override
        public int degree() {
            if (terms.isEmpty()) {
                return 0;
            }
            return terms.get(0).degree;
        }
        @Override
        public C_INST coefficient(int degree) {
            for (final Term term: terms) {
                if (term.degree == degree) {
                    return term.c;
                }
            }
            return _coeffCls.zero();
        }

        @Override
        public INST add(final INST elem) {
            return new INST(new ArrayList<Term>(){{
                addAll(INST.this.terms);
                addAll(elem.terms);
            }});
        }
        @Override
        public INST subst(final INST elem) {
            return add(elem.r_mult(_coeffCls.neg_unit()));
        }
        @Override
        public INST mult(final INST elem) {
            return new INST(new ArrayList<Term>(){{
                for (final Term term1: terms) {
                    for (final Term term2: elem.terms) {
                        add(term1.mult(term2));
                    }
                }
            }});
        }

        @Override
        public INST r_mult(final C_INST c) {
            return new INST(new ArrayList<Term>(){{
                for (final Term term: terms) {
                    add(term.r_mult(c));
                }
            }});
       }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            return toString(sb, "");
            /*
            boolean is_first = true;
            for (final Term term: terms) {
                if (is_first) {
                    is_first = false;
                } else {
                    sb.append("+");
                }
                sb.append(term.toString());
            }
            return sb.toString();
            */
        }

        public String toString(final StringBuilder sb, final String vars) {
            //final StringBuilder sb = new StringBuilder();
            boolean is_first = true;
            for (final Term term: terms) {
                if (is_first) {
                    is_first = false;
                } else {
                    sb.append("+");
                }

                if (term.c instanceof PolynomialRing.INST) {
                    final INST inst = (PolynomialRing.INST)term.c;
                    final String term0 = term.termString();
                    final String vars0 = (vars.isEmpty())?term0:(vars+"*"+term0);
                    inst.toString(sb, vars0);
                } else {
                    sb.append(term.toString(vars));
                }
            }
            return sb.toString();
        }


        @Override
        public boolean equals(Object obj) {
            if (obj instanceof PolynomialRing.INST) {
                INST inst = (INST)obj;
                return equalTerms(inst.terms);
            } else {
                return false;
            }
        }
        @Override
        public int hashCode() {
            int i = 0;
            for (Term term: terms) {
                i += term.hashCode();
            }
            return i;
        }
        private boolean equalTerms(List<Term> terms0) {
            final int size = terms.size();
            if (terms0.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!terms.get(i).equals(terms0.get(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        {
            //final PolynomialRing<NIntegerRing.INST, NIntegerRing.CLASS>.CLASS IntPolynomialRing1 = new PolynomialRing<NIntegerRing.INST, NIntegerRing.CLASS>(NIntegerRing.CLASS()).getCLASS();
            final PolynomialRing<NIntegerRing.INST, NIntegerRing.CLASS>.CLASS IntPolynomialRing = PolynomialRing.CLASS(NIntegerRing.CLASS(), "X");
            final NIntegerRing.CLASS coeffCls = IntPolynomialRing.coefficientRing();
            final PolynomialRing<NIntegerRing.INST, NIntegerRing.CLASS>.INST nr1 = IntPolynomialRing.create(7, coeffCls.unit());
            final PolynomialRing<NIntegerRing.INST, NIntegerRing.CLASS>.INST nr2 = IntPolynomialRing.create(5, coeffCls.unit());
            final PolynomialRing<NIntegerRing.INST, NIntegerRing.CLASS>.INST nr3 = nr1.mult(nr2);
            System.out.println(">> nr3: "+nr3);
        }
    }
}
