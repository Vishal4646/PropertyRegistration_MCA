/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.32
 * Generated at: 2022-07-15 04:21:51 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.tiles;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.constant.ServerConstants;
import com.model.SignRequestModel;
import java.util.List;
import com.database.ConnectionManager;
import com.model.UserModel;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("com.model.SignRequestModel");
    _jspx_imports_classes.add("com.model.UserModel");
    _jspx_imports_classes.add("com.database.ConnectionManager");
    _jspx_imports_classes.add("com.constant.ServerConstants");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"main-header\">\r\n");
      out.write("\t<div class=\"logo-header\">\r\n");
      out.write("\t\t<a href=\"index.html\" class=\"logo\"> ");
      out.print(ServerConstants.PROJECT_NAME.split("&")[0] );
      out.write(" </a>\r\n");
      out.write("\t\t<button class=\"navbar-toggler sidenav-toggler ml-auto\" type=\"button\"\r\n");
      out.write("\t\t\tdata-toggle=\"collapse\" data-target=\"collapse\" aria-controls=\"sidebar\"\r\n");
      out.write("\t\t\taria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n");
      out.write("\t\t\t<span class=\"navbar-toggler-icon\"></span>\r\n");
      out.write("\t\t</button>\r\n");
      out.write("\t\t<button class=\"topbar-toggler more\">\r\n");
      out.write("\t\t\t<i class=\"la la-ellipsis-v\"></i>\r\n");
      out.write("\t\t</button>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<nav class=\"navbar navbar-expand-sm bg-primary navbar-project1\">\r\n");
      out.write("\t\t<div class=\"container-fluid\">\r\n");
      out.write("\t\t\t");

				UserModel um = (UserModel) session.getAttribute("USER_MODEL");
			
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t<ul class=\"navbar-nav topbar-nav ml-md-auto align-items-center\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t");

					List<SignRequestModel> list = ConnectionManager
							.getPendingSignInRequests(um.getUid());
				
      out.write("\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<li class=\"nav-item dropdown\"><a\r\n");
      out.write("\t\t\t\t\tclass=\"dropdown-toggle profile-pic\" data-toggle=\"dropdown\" href=\"#\"\r\n");
      out.write("\t\t\t\t\taria-expanded=\"false\"> <img\r\n");
      out.write("\t\t\t\t\t\tsrc=\"");
      out.print(request.getContextPath());
      out.write("/theme/assets/img/profile.png\"\r\n");
      out.write("\t\t\t\t\t\talt=\"user-img\" width=\"36\" class=\"img-circle\"><span>");
      out.print(um.getEmailid());
      out.write("</span>\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t<ul class=\"dropdown-menu dropdown-user\">\r\n");
      out.write("\t\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"user-box\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"u-img\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<img\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tsrc=\"");
      out.print(request.getContextPath());
      out.write("/theme/assets/img/profile.png\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\talt=\"user\">\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"u-text\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<h4>");
      out.print(um.getEmailid());
      out.write("</h4>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<a class=\"dropdown-item\" href=\"");
      out.print(request.getContextPath());
      out.write("/tiles/ajax.jsp?methodId=logout\"><i class=\"fa fa-power-off\"></i> Logout</a>\r\n");
      out.write("\t\t\t\t\t</ul> </li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</nav>\r\n");
      out.write("</div>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
