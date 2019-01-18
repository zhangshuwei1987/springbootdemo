package com.framework.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ContextUtil {

    public static String getRealPath(HttpServletRequest request, String path){
        return  request.getSession().getServletContext().getRealPath(path);
    }

}
