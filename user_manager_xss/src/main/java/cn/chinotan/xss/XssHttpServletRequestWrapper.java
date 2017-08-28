package cn.chinotan.xss;

import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        String htmlEscape = HtmlUtils.htmlEscape(value);

        return htmlEscape;
    }

    @Override
    public String getParameter(String name) {
        String parameter = super.getParameter(name);
        String htmlEscape = HtmlUtils.htmlEscape(parameter);

        return htmlEscape;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameterValues = super.getParameterValues(name);
        
        String[] newParameterValues = new String[parameterValues.length];
        
        for (int i = 0; i < parameterValues.length; i++) {
            newParameterValues[i] = HtmlUtils.htmlEscape(parameterValues[i]);
        }

        return newParameterValues;
    }
}
