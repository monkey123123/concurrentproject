package me.monkey.demo.string2Operator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.UUID;

public class Demo {
    public static void main(String[] args) throws ScriptException {
        String ss = "<";
        String str = "(a >= 0 && a <= 5)";
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        engine.put("a",4);
        Object result = engine.eval(str);

        System.out.println(UUID.randomUUID());
        System.out.println("结果类型:" + result.getClass().getName() + ",计算结果:" + result);
        System.out.println(UUID.fromString("1eb19bc938057e0914cbfd08631f464"));

    }
}
