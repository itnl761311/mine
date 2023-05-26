package com.mine.util.elasticsearch;

import com.google.gson.Gson;
import com.mine.util.binarytree.ConditionTree;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.util.List;
import java.util.Map;

public class Convert {
    static String jsonString = "{\n" +
            "  \"and\": [\n" +
            "    {\n" +
            "      \"and\": [\n" +
            "        {\n" +
            "          \"field\": \"title\",\n" +
            "          \"operation\": \"must\",\n" +
            "          \"value\": \"gia tri1\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"field\": \"name\",\n" +
            "          \"operation\": \"should\",\n" +
            "          \"value\": \"gia tri2\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"field\": \"book\",\n" +
            "          \"operation\": \"should\",\n" +
            "          \"value\": \"gia tri5\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"and\": [\n" +
            "        {\n" +
            "          \"field\": \"address\",\n" +
            "          \"operation\": \"must\",\n" +
            "          \"value\": \"gia tri3\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"field\": \"age\",\n" +
            "          \"operation\": \"should\",\n" +
            "          \"value\": \"gia tri4\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    static Gson gson = new Gson();

    public static Map firstConvertJsonToMap(String json) {
        return convertJsonStringToMap(jsonString);
    }

    public static Map convertJsonStringToMap(String jsonString) {
        return gson.fromJson(jsonString, Map.class);
    }

    public static ConditionTree.AbsTree buildFilter(Map map){

        if(map.containsKey("field") && map.containsKey("operation") && map.containsKey("value")){
            return new ConditionTree.Condition(map.get("field"),String.valueOf(map.get("operation")), map.get("value"));
        }
        if(map.containsKey("and")){
            return buildAndFilter((List)map.get("and"));
        }
        if(map.containsKey("or")){
            return buildOrFilter((List)map.get("or"),((List<?>) map.get("or")).size()-1);
        }
        return null;
    }

    public static ConditionTree.AbsTree buildAndFilter(List<Map> listMap){
        ConditionTree.AbsTree[] abs = new ConditionTree.AbsTree[listMap.size()];
        for(int i = 0; i<listMap.size(); i++){
            abs[i] = buildFilter(listMap.get(i));
        }
        return new ConditionTree.And(abs);
    }

    public static ConditionTree.AbsTree buildOrFilter(List<Map> listMap, int index){
        if(index == 0){
            return buildFilter(listMap.get(index));
        }
        return new ConditionTree.And(buildOrFilter(listMap,index), buildOrFilter(listMap,index));
    }

    public static void main(String[] args) {
        Convert convert = new Convert();
        Map map = firstConvertJsonToMap(jsonString);
        ConditionTree.AbsTree absTree = buildFilter(map);
        VisitNode node = new VisitNode();
        Criteria visit = node.visit(absTree);
        String s = new CriteriaQuery(visit).toString();
        System.out.println(absTree);
    }
}
