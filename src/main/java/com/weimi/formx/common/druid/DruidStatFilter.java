package com.weimi.formx.common.druid;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Created by yangsh on 2017-08-27
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = {"/*"},
    initParams = {
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*,/swagger*,/v2/api-docs") // 忽略资源
    }
)
public class DruidStatFilter extends WebStatFilter {
    
}
