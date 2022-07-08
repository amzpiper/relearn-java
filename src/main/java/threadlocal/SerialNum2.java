//import cn.hutool.http.HttpRequest;
//import java.math.BigDecimal;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.adtec.dubbo.util.DateUtil;
//import java.util.Date;
//import java.util.Calendar;
//
//public class SerialNum2 {
//
//    public static void main(String[] args) {
//
//        startTime = DateUtil.increase(new Date(), "hour" , -1).getTime();
//        endTime = (new Date()).getTime();
//        System.out.println(startTime);
//        System.out.println(endTime);
//
//        treePathList = db.query("select tree_path,name from ioc.app_t ", conn);
//
//        for(tree : treePathList) {
//            treePath = tree.get("tree_path");
//            name = tree.get("name");
//            paramBody =
//                "{" +
//                "    \"treePath\": "+ treePath+
//                "}";
//
//            //不分页调用方式
//            resStr = imoc.post("/api-gateway/aiops-openapi/v1/resource/queryCiCount", paramBody);
//            System.out.println("33333333333=" + resStr);
//
//            jsonObjectResult = JSONObject.parseObject(resStr);
//            dataList = jsonObjectResult.getJSONArray("data");
//            if (dataList != null && dataList.size() > 0) {
//                for (map : dataList) {
//
//                    tagsList = map.get("subType");
//                    if (tagsList != null && tagsList.size() > 0){
//                        for (tagMap : tagsList) {
//                            if (!map.containsKey(tagMap.get("ciType"))){
//                                map.put("ciType", tagMap.get("ciType"));
//                            }
//                            if (!map.containsKey(tagMap.get("ciTypeEn"))){
//                                map.put("ciTypeEn", tagMap.get("ciTypeEn"));
//                            }
//                            if (!map.containsKey(tagMap.get("count"))){
//                                map.put("count", tagMap.get("count"));
//                            }
//                        }
//                    }
//                    map.put("treePath",treePath);
//                    map.put("name",name);
//                }
//            }
//        }
//
//        mappingJson ="{" +
//                // 字段名 : 表名
//                // id
//                "\"id\":\"id\"," +
//                // 健康指数
//                "\"treePath\":\"tree_path\"," +
//                // 应用
//                "\"name\":\"name\"," +
//                // url拨测平均耗时
//                "\"source\":\"ci_type\"," +
//                // url拨测最大耗时
//                "\"sourceEn\":\"ci_type_en\"," +
//                // 产品树路径
//                "\"ciType\":\"ci_sub_type\"," +
//                // 紧急告警数量
//                "\"ciTypeEn\":\"ci_sub_type_en\"," +
//                // 严重告警数量
//                "\"count\":\"CI_sub_type_cnt\"" +
//            "}";
//        //添加自定义映射
//        db.execute("ioc.app_resou_ci_cnt_t", treePathList, mappingJson, conn);
//
//
//    }
//}
