package com.mine.util.elasticsearch;

import com.mine.util.binarytree.ConditionTree;
import org.springframework.data.elasticsearch.core.query.Criteria;

public class VisitNode {
    public Criteria visit(ConditionTree.AbsTree abs){
        if(abs instanceof ConditionTree.And){
            return visit((ConditionTree.And)abs);
        }
        if(abs instanceof ConditionTree._And){
            return visit((ConditionTree._And)abs);
        }
        if(abs instanceof ConditionTree.Or){
            return visit((ConditionTree.Or)abs);
        }
        if(abs instanceof ConditionTree._Or){
            return visit((ConditionTree._Or)abs);
        }
        if(abs instanceof ConditionTree.Condition){
            return visit((ConditionTree.Condition)abs);
        }
        return null;
    }
    public Criteria visit(ConditionTree.And and){
        return visitAnd(and.getNode(),and.getNode().length-1);
    }

    public Criteria visit(ConditionTree._And _and){
        return new Criteria().subCriteria(visitAnd(_and.getNode(),_and.getNode().length-1));
    }

    public Criteria visit(ConditionTree.Or or){
        return visitOr(or.getNode(),or.getNode().length-1);
    }

    public Criteria visit(ConditionTree._Or _or){
        return visitOr(_or.getNode(),_or.getNode().length-1);
    }

    public Criteria visit(ConditionTree.Condition condition){
        if(condition.getOperation().equals("match")){
            return new Criteria((String)condition.getField()).is(condition.getValue());
        }
        return null;
    }

    public Criteria visitAnd(ConditionTree.AbsTree[] arr, int index){
        if(index == 0){
            return visit(arr[index]);
        }
        return visit(arr[index]).and(visit(arr[index-1]));
    }

    public Criteria visitOr(ConditionTree.AbsTree[] arr, int index){
        if(index == 0){
            return visit(arr[index]);
        }
        return visit(arr[index]).or(visit(arr[index-1]));
    }

}
