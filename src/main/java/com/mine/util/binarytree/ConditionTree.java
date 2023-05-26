package com.mine.util.binarytree;

import java.util.ArrayList;
import java.util.List;

public class ConditionTree {

    private ConditionTree() {}

    public abstract static class AbsTree {

        public AbsTree[] removeNullValue(AbsTree[] arr) {
            List<AbsTree> list = new ArrayList<>();
            for(AbsTree s : arr) {
                if(s != null) {
                    list.add(s);
                }
            }
            return list.toArray(new AbsTree[list.size()]);
        }

        private AbsTree() {}

    }

    public static class Or extends AbsTree {
        private final AbsTree[] node;

        public Or(AbsTree... node) {
            this.node = this.removeNullValue(node);
        }

        public AbsTree[] getNode() {
            return node;
        }
    }

    public static class And extends AbsTree {
        private final AbsTree[] node;
        public And(AbsTree... node) {
            this.node = this.removeNullValue(node);
        }

        public AbsTree[] getNode() {
            return node;
        }
    }

    public static class Condition extends AbsTree {
        private final Object field;
        private final String operation;
        private final Object value;

        public Condition(Object field, String operation, Object value) {
            this.field = field;
            this.operation = operation;
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public Object getField() {
            return field;
        }

        public String getOperation() {
            return operation;
        }
    }

    public static void main(String[] args) {

        Condition c = new Condition("1","and","1");
        Condition c1 = new Condition("2","and","2");
        AbsTree[] and = new AbsTree[2];
        and[0] = c;
        and[1] = c1;
        And a = new And(and);

        Condition c2 = new Condition("3","and","3");
        Condition c3 = new Condition("4","and","4");
        AbsTree[] or = new AbsTree[2];
        or[0] = c2;
        or[1] = c3;
        Or o = new Or(or);

        AbsTree[] x = new AbsTree[2];
        x[0] = a;
        x[1] = o;
        And a1 = new And(x);


    }

}
