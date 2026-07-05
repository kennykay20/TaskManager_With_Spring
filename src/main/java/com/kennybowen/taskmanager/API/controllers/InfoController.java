package com.kennybowen.taskmanager.API.controllers;

import com.kennybowen.taskmanager.API.configuration.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/info")
@RequiredArgsConstructor
public class InfoController {

    private final AppProperties appProperties;

    @GetMapping
    public Map<String, Object> getInfo() {
        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("appName", appProperties.getName());
        infoMap.put("appVersion", appProperties.getVersion());
        infoMap.put("maxTaskPerPage", appProperties.getMaxTasksPerPage());

        return infoMap;
    }
}
