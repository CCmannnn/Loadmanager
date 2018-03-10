package com.pool.test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Scanner;

/**
 * @author Administrator
 */
public class TokenType {
    public static void main(String[] args)
    {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine se = sem.getEngineByName("js");
        Scanner scan = new Scanner(System.in);
        while(true)
        {
            System.out.println("输入式子：");
            String jsonstr = scan.nextLine().trim();
            try
            {
                double doubleOut = Double.parseDouble(se.eval("(" + jsonstr + ")").toString());
                System.out.printf("%s=%.1f%n", jsonstr, doubleOut);
            }
            catch(ScriptException e)
            {
                System.err.println("输入的式子不合法，退出程序");
                scan.close();
                break;
            }
        }
    }
}
