package nashorn;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestNashorn2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
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
        
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             
            // cachedThreadPool.execute(new Runnable() {
            //     @Override
            //     public void run() {
            //         System.out.println(index);
            //     }
            // });
            Future f = cachedThreadPool.submit(() -> {
                System.out.println(index);
                return true;
            });
            System.out.println(f.get(5,TimeUnit.MILLISECONDS));
        }
        cachedThreadPool.shutdown();
    }
}
