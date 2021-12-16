package nashorn;

import java.util.Arrays;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestNashorn2 {

    public static void main(String[] args) {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");
          
        String jsCode = 
            "importPackage(java.util);"
            +"importClass(java.util.List);"
            +"var list = Arrays.asList(\"a12aa\",\"bb12b\",\"c12cc\",\"cab\",\"cba\");";
        
        try {
            nashorn.eval("load(\"nashorn:mozilla_compat.js\");");
            nashorn.eval(jsCode);
            List<String> list = (List<String>) nashorn.get("list");
            for (String ls : list) {
                System.out.println(ls);
            }
        }catch(ScriptException e){
           System.out.println("执行脚本错误: "+ e.getMessage());
        }
        
    }
}
