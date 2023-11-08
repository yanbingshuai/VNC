package com.qnkj.clouds.modules.utils;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * create by 徐雁
 */
@Slf4j
public class WebViews {
    public static final String VIEW_PREFIX = "views/";

    public static String view(String viewName) {
        if(viewName.startsWith("/views/") || viewName.startsWith("/error/")) {
            return viewName;
        }
        if(viewName.startsWith("views/")) {
            return "/" + viewName;
        }
        if(viewName.startsWith("/")) {
            return VIEW_PREFIX + viewName.substring(1);
        }else {
            return VIEW_PREFIX + viewName;
        }
    }

    public static Object body(HttpServletResponse response, WebResponse message) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().print(message);
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
        return null;
    }
}
