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
        if (cmdBits.length == 2 && !cmdBits[1].isBlank()) {
            params.put("id", cmdBits[1].trim());
        }
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

    public String getParam(String name, String defaultValue) {
        return params.getOrDefault(name, defaultValue);
    }
}