/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-09-27 12:59:37 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!doctype html>\n");
      out.write("<html lang=\"zh\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <title>Oauth2 Server 端流程</title>\n");
      out.write("    <style type=\"text/css\" rel=\"stylesheet\">\n");
      out.write("        body{\n");
      out.write("            background-color: black;\n");
      out.write("            color: white;\n");
      out.write("            font-size: 18px;\n");
      out.write("            font-family: \"Consolas\";\n");
      out.write("            margin: 20px;\n");
      out.write("        }\n");
      out.write("        h1{\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("        a{\n");
      out.write("            color: white;\n");
      out.write("            font-size: 18px;\n");
      out.write("            cursor: pointer;\n");
      out.write("            text-decoration: none;\n");
      out.write("        }\n");
      out.write("        div{\n");
      out.write("            float: right;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<h1>Oauth2 Server 端流程</h1>\n");
      out.write("<div>\n");
      out.write("    <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/client\">应用管理</a>\n");
      out.write("    <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/user\">用户管理</a>\n");
      out.write("</div>\n");
      out.write("<pre>\n");
      out.write("\n");
      out.write("1. 先注册应用\n");
      out.write("\n");
      out.write("http://localhost:8080/zetark-oauth2-server/client\n");
      out.write("\n");
      out.write("会生成client_id和client_secret 这两个以后会用到\n");
      out.write("\n");
      out.write("client_id               c1ebe466-1cdc-4bd3-ab69-77c3561b9dee    应用id\n");
      out.write("client_secret           d8346ea2-6017-43ed-ad68-19c0f971738b    应用secret\n");
      out.write("\n");
      out.write("2. 请求授权码\n");
      out.write("\n");
      out.write("http://localhost:8080/zetark-oauth2-server/authorize?client_id=c1ebe466-1cdc-4bd3-ab69-77c3561b9dee&response_type=code&redirect_uri=http://aimeizi.net\n");
      out.write("\n");
      out.write("参数说明\n");
      out.write("\n");
      out.write("client_id               应用id\n");
      out.write("response_type           返回授权码的标识\n");
      out.write("redirect_uri            回调地址\n");
      out.write("\n");
      out.write("上面的网站会打开oauth server的用户登录页面。用户输入正确的用户名和密码以POST方式提交后会重定向到用户所填的回调地址并在地址后携带授权码.\n");
      out.write("\n");
      out.write("请求成功后会返回如下的页面:\n");
      out.write("\n");
      out.write("http://aimeizi.net/?code=63910432da9186b22b1ad888d55ae8ae\n");
      out.write("\n");
      out.write("这里code=63910432da9186b22b1ad888d55ae8ae 即授权码\n");
      out.write("\n");
      out.write("3. 换取accessToken (POST操作)\n");
      out.write("\n");
      out.write("首先GET方式请求http://localhost:8080/zetark-oauth2-server/access会打开一个表单在该表单中填入必填项，具体表单参数详见说明部分\n");
      out.write("\n");
      out.write("表单将会以POST方式提交到http://localhost:8080/zetark-oauth2-server/accessToken,最终返回accessToken\n");
      out.write("\n");
      out.write("需要以POST方式提交以下参数换取accessToken\n");
      out.write("\n");
      out.write("client_id       c1ebe466-1cdc-4bd3-ab69-77c3561b9dee            应用id\n");
      out.write("client_secret   d8346ea2-6017-43ed-ad68-19c0f971738b            应用secret\n");
      out.write("grant_type      authorization_code                              用于传递授权码的参数名authorization_code\n");
      out.write("code            63910432da9186b22b1ad888d55ae8ae                用户登录授权后的授权码\n");
      out.write("redirect_uri    http://aimeizi.net                              回调地址\n");
      out.write("\n");
      out.write("最终返回如下数据\n");
      out.write("\n");
      out.write("{\"expires_in\":3600,\"access_token\":\"223ae05dfbb0794396fb60a0960c197e\"}\n");
      out.write("\n");
      out.write("4. 测试accessToken\n");
      out.write("\n");
      out.write("http://localhost:8080/zetark-oauth2-server/v1/openapi/userInfo?access_token=223ae05dfbb0794396fb60a0960c197e\n");
      out.write("\n");
      out.write("测试ok的话返回用户名信息,access_token=223ae05dfbb0794396fb60a0960c197e为上一步获取的access_token\n");
      out.write("\n");
      out.write("注：其中的参数名不要随意更改，固定写法。\n");
      out.write("</pre>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}