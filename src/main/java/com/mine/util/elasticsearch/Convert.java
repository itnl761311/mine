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
            "      \"_and\": [\n" +
            "        {\n" +
            "          \"field\": \"title\",\n" +
            "          \"operation\": \"and\",\n" +
            "          \"value\": \"demo of one\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"field\": \"content\",\n" +
            "          \"operation\": \"and\",\n" +
            "          \"value\": \"this is\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"field\": \"isPublish\",\n" +
            "          \"operation\": \"and\",\n" +
            "          \"value\": \"1\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"and\": [\n" +
            "        {\n" +
            "          \"field\": \"title\",\n" +
            "          \"operation\": \"and\",\n" +
            "          \"value\": \"demo of two\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"field\": \"content\",\n" +
            "          \"operation\": \"and\",\n" +
            "          \"value\": \"this\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    static Gson gson = new Gson();

    public static Map firstConvertJsonToMap(String json) {
        return convertJsonStringToMap(json);
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
        if(map.containsKey("_and")){
            return _buildAndFilter((List)map.get("_and"));
        }
        if(map.containsKey("or")){
            return buildOrFilter((List)map.get("or"));
        }
        if(map.containsKey("_or")){
            return _buildOrFilter((List)map.get("_or"));
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

    public static ConditionTree.AbsTree _buildAndFilter(List<Map> listMap){
        ConditionTree.AbsTree[] abs = new ConditionTree.AbsTree[listMap.size()];
        for(int i = 0; i<listMap.size(); i++){
            abs[i] = buildFilter(listMap.get(i));
        }
        return new ConditionTree._And(abs);
    }


    public static ConditionTree.AbsTree buildOrFilter(List<Map> listMap){
        ConditionTree.AbsTree[] abs = new ConditionTree.AbsTree[listMap.size()];
        for(int i = 0; i<listMap.size(); i++){
            abs[i] = buildFilter(listMap.get(i));
        }
        return new ConditionTree.Or(abs);
    }

    public static ConditionTree.AbsTree _buildOrFilter(List<Map> listMap){
        ConditionTree.AbsTree[] abs = new ConditionTree.AbsTree[listMap.size()];
        for(int i = 0; i<listMap.size(); i++){
            abs[i] = buildFilter(listMap.get(i));
        }
        return new ConditionTree._Or(abs);
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
