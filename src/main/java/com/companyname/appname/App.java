package com.companyname.appname;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        // System.out.println( "Hello World!" );

        // App fx = new App();
        // System.out.println(fx.fx(16) + fx.fx(7));
        // System.out.println(fx.newFx(16) + fx.newFx(7));
        // System.out.println(fx.fx(0) + fx.fx(2)+ fx.fx(1));
        // System.out.println(fx.newFx(0) + fx.newFx(2) + fx.newFx(1));

        String jsonString = "{\"id\": 1, \"info\": {\"emails\": [\"haechan@gmail.com\", \"jaemin@gmail.com\"], \"phones\": {\"home\": \"12345\", \"mobile\": \"67890\"}}}";

        System.out.println("Input: "+jsonString);
        System.out.println();
        System.out.println("Output: ");
        Map<String, Object> flatMap = flattenJson(jsonString);

        for (Map.Entry<String, Object> entry : flatMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static Map<String, Object> flattenJson(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        Map<String, Object> flatMap = new HashMap<>();
        flatten(jsonObject, "", flatMap);
        Map<String, Object> treeMap = new TreeMap<String, Object>(flatMap);
        return treeMap;
    }


    private static void flatten(JSONObject jsonObject, String prefix, Map<String, Object> flatMap) {
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = jsonObject.get(key);
            String newKey = prefix.isEmpty() ? key : prefix + "." + key;

            if (value instanceof JSONObject) {
                flatten((JSONObject) value, newKey, flatMap);
            } else if (value instanceof JSONArray) {
                flattenArray((JSONArray) value, newKey, flatMap);
            } else {
                flatMap.put(newKey, value);
            }
        }
    }

    private static void flattenArray(JSONArray jsonArray, String prefix, Map<String, Object> flatMap) {
        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            String newKey = prefix + "." + i;
            if (value instanceof JSONObject) {
                flatten((JSONObject) value, newKey, flatMap);
            } else if (value instanceof JSONArray) {
                flattenArray((JSONArray) value, newKey, flatMap);
            } else {
                flatMap.put(newKey, value);
            }
        }
    }

    public int fx(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fx(n - 1) + fx(n - 2);
    }

    public int newFx(int n) {
        // Your current function fx(int n) is a recursive implementation of the Fibonacci sequence. It calculates the nth Fibonacci number using recursion. While this is a straightforward approach, it can be inefficient for larger values of n due to redundant calculations. Here's the simplified version using an iterative approach which avoids redundant calculations:

        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
    
        int a = 0, b = 1, c;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;

        // In this version:
        // 1. We initialize a and b to 0 and 1 respectively, representing the first two Fibonacci numbers.
        // 2. We use a loop to iteratively calculate the Fibonacci numbers up to n.
        // 3. This avoids the overhead and potential stack overflow issues associated with deep recursion.
        // This iterative approach is more efficient for larger values of n compared to the recursive approach, which recalculates Fibonacci numbers multiple times for the same values.
    }
}
