package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class mapList {
    public static void main(String[] args) {
        
        List<Map> dataList = new ArrayList<Map>();
        Map<String,String> map = new HashMap<>();
        map.put("ciType", "Port");
        map.put("ciTypeEn", "Port");
        map.put("count", "1");
        dataList.add(0,map);
        Map<String,String> map2 = new HashMap<>();
        map2.put("ciType", "Url");
        map2.put("ciTypeEn", "Url");
        map2.put("count", "2");
        dataList.add(1,map2);
        Map<String,String> map3 = new HashMap<>();
        map3.put("ciType", "Process");
        map3.put("ciTypeEn", "Process");
        map3.put("count", "3");
        dataList.add(2,map3);

        List<Map> dataListNew = new ArrayList<Map>();
        System.out.println("dataList:"+dataList.size());
        for(Map item:dataList){
            System.out.println(item.get("ciType"));
            System.out.println(item.get("ciTypeEn"));
            System.out.println(item.get("count"));
            dataListNew.add(item);
        }

        System.out.println("dataListNew:"+dataListNew.size());
        for(Map item:dataListNew){
            System.out.println(item.get("ciType"));
            System.out.println(item.get("ciTypeEn"));
            System.out.println(item.get("count"));
        }


    }
}
