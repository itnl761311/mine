package com.mine.util.elasticsearch;

import com.mine.util.binarytree.ConditionTree;
import org.springframework.data.elasticsearch.core.query.Criteria;

public class VisitNode {
    public Criteria visit(ConditionTree.AbsTree abs){
        if(abs instanceof ConditionTree.And){
            return visit((ConditionTree.And)abs);
        }
        if(abs instanceof ConditionTree.Condition){
            return visit((ConditionTree.Condition)abs);
        }
        return null;
    }
    public Criteria visit(ConditionTree.And and){
        return visitAnd(and.getNode(),and.getNode().length-1);
    }
//    public Criteria visit(ConditionTree.Or or){
//
//    }

    public Criteria visit(ConditionTree.Condition condition){
        return new Criteria((String)condition.getField()).is(condition.getValue());
    }

    public Criteria visitAnd(ConditionTree.AbsTree[] arr, int index){
        if(index == 0){
            return visit(arr[index]);
        }
        return visit(arr[index]).and(visit(arr[index-1]));
    }

}
