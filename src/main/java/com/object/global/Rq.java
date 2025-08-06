package com.object.global;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
@Getter

public class Rq {
    private final String actionName;
    private final Map<String, String> params;

    public Rq(String cmd) {
        String[] cmdBits = cmd.split(" ", 2);
        actionName = cmdBits[0].trim();
        params = new HashMap<>();

        // 🔥 개선된 부분
        if (cmdBits.length == 2 && !cmdBits[1].isBlank()) {
            String[] paramBits = cmdBits[1].trim().split(" ");
            if (paramBits.length == 1) {
                // 기본적으로 sort 명령인 경우 → sortType 으로 저장
                params.put("sortType", paramBits[0]);
            } else {
                // 여러 파라미터를 넣고 싶을 경우 (key=value 형태)
                for (String paramBit : paramBits) {
                    String[] keyValue = paramBit.split("=", 2);
                    if (keyValue.length == 2) {
                        params.put(keyValue[0], keyValue[1]);
                    }
                }
            }
        }
    }

    public String getParam(String name, String defaultValue) {
        return params.getOrDefault(name, defaultValue);
    }

    public int getParamsAsInt(String name, int defaultValue) {
        String value = getParam(name, "");
        if (value == null) return defaultValue;
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

}
