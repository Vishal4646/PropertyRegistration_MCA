/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.32
 * Generated at: 2022-08-12 11:05:45 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.model.TransferRecords;
import com.model.DocumentModel;
import java.util.List;
import com.model.UserModel;
import com.database.ConnectionManager;

public final class tranferRecords_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/pages/uploadFileFragment.jsp", Long.valueOf(1571137102000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("com.model.DocumentModel");
    _jspx_imports_classes.add("com.model.UserModel");
    _jspx_imports_classes.add("com.database.ConnectionManager");
    _jspx_imports_classes.add("com.model.TransferRecords");
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
      response.setContentType("text/html; charset=ISO-8859-1");
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=utf-8 />\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../tiles/inc.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"wrapper\">\r\n");
      out.write("\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../tiles/header.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../tiles/menu.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t<div class=\"main-panel\">\r\n");
      out.write("\t\t\t<div class=\"content\">\r\n");
      out.write("\t\t\t\t<div class=\"container-fluid\">\r\n");
      out.write("\t\t\t\t\t<h4 class=\"page-title\">Transfered Properties Record</h4>\r\n");
      out.write("\t\t\t\t\t<div class=\"row\"></div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<div class=\"row row-card-no-pd\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"card card-tasks\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"card-body \">\r\n");
      out.write("\t\t\t\t\t\t\t\t");

								UserModel um = (UserModel) session.getAttribute("USER_MODEL");
												List list = ConnectionManager.getTransferedPropRecords();
													
													if(list.size()>0){
													
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"table-full-width\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<table class=\"table\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<thead>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<tr style=\"text-align: center\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<th>Sr.No.</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<th>Property</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<th>Property ID</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<th>Pre.OwnerId</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<th>Pre.OwnerName</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<th>New.OwnerId</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<th>New.OwnerName</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<th>Transfer Date</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<th>Address</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<th>Registrar</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</thead>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<tbody>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");

												int i = 0;
													for ( i = 0; i < list.size(); i++) {
														TransferRecords dm = (TransferRecords) list.get(i);
												
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<td>");
      out.print( i+1 );
      out.write(".</td>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<td> ");
      out.print(dm.getProp());
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<td>");
      out.print(dm.getPid());
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<td>");
      out.print(dm.getPreoid());
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<td>");
      out.print(dm.getPreoname());
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<td>");
      out.print(dm.getNewoid());
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<td>");
      out.print(dm.getNewoname());
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<td>");
      out.print(dm.getTradate());
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<td>");
      out.print(dm.getAddrs());
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<td>");
      out.print(dm.getReg());
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");

													}
												
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
}else{ 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<h2>No Records Found</h2>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
} 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t");
      out.write("\r\n");
      out.write("<div class=\"modal fade\" id=\"modalUpdate\" tabindex=\"-1\" role=\"dialog\"\r\n");
      out.write("\taria-labelledby=\"modalUpdatePro\" aria-hidden=\"true\">\r\n");
      out.write("\t<div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\r\n");
      out.write("\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t<div class=\"modal-header bg-primary\">\r\n");
      out.write("\t\t\t\t<h6 class=\"modal-title\">\r\n");
      out.write("\t\t\t\t\t<i class=\"la la-frown-o\"></i> Upload File\r\n");
      out.write("\t\t\t\t</h6>\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\"\r\n");
      out.write("\t\t\t\t\taria-label=\"Close\">\r\n");
      out.write("\t\t\t\t\t<span aria-hidden=\"true\">&times;</span>\r\n");
      out.write("\t\t\t\t</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"modal-body text-center\">\r\n");
      out.write("\t\t\t\t<form class=\"navbar-left navbar-form nav-search mr-md-3\"\r\n");
      out.write("\t\t\t\t\tmethod=\"post\" enctype=\"multipart/form-data\"\r\n");
      out.write("\t\t\t\t\taction=\"");
      out.print(request.getContextPath());
      out.write("/tiles/ajax.jsp?methodId=uploadFile\">\r\n");
      out.write("\t\t\t\t\t<div class=\"card-header\">\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" name=\"propid\" placeholder=\"Property ID\" required=\"required\" class=\"form-control\"\r\n");
      out.write("\t\t\t\t\t style=\"text-align:center; background-color:#6F8996; color:#ffffff; margin-bottom:15px\">\r\n");
      out.write("\t\t\t\t\t \r\n");
      out.write("\t\t\t\t\t\t<input class=\"btn btn-default\" accept=\"application/pdf\"\r\n");
      out.write("\t\t\t\t\t\t\trequired=\"required\" type=\"file\" name=\"file\" class=\"form-control\"><br>\r\n");
      out.write("\t\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<button type=\"submit\" class=\"btn btn-default\">\r\n");
      out.write("\t\t\t\t\t\t\t<i class=\"la la-cloud-upload\" style=\"font-size: 20px\"></i> Upload\r\n");
      out.write("\t\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<!-- Modal Property ID -->\r\n");
      out.write("\r\n");
      out.write("<div class=\"modal fade\" id=\"modalProperty\" tabindex=\"-1\" role=\"dialog\"\r\n");
      out.write("\taria-labelledby=\"modalUpdatePro\" aria-hidden=\"true\">\r\n");
      out.write("\t<div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\r\n");
      out.write("\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t<div class=\"modal-header bg-primary\">\r\n");
      out.write("\t\t\t\t<h6 class=\"modal-title\">\r\n");
      out.write("\t\t\t\t\t<i class=\"la la-frown-o\"></i> User Name\r\n");
      out.write("\t\t\t\t</h6>\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\"\r\n");
      out.write("\t\t\t\t\taria-label=\"Close\">\r\n");
      out.write("\t\t\t\t\t<span aria-hidden=\"true\">&times;</span>\r\n");
      out.write("\t\t\t\t</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"modal-body text-center\">\r\n");
      out.write("\t\t\t\t<form class=\"navbar-left navbar-form nav-search mr-md-3\"\r\n");
      out.write("\t\t\t\t\tmethod=\"post\"\r\n");
      out.write("\t\t\t\t\taction=\"");
      out.print(request.getContextPath());
      out.write("/pages/propertyverification.jsp\"\">\r\n");
      out.write("\t\t\t\t\t<div class=\"card-header\">\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" name=\"propid\" placeholder=\"User Name\" required=\"required\" class=\"form-control\"\r\n");
      out.write("\t\t\t\t\t style=\"text-align:center; background-color:#6F8996; color:#ffffff; margin-bottom:15px\">\r\n");
      out.write("\t\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t\t<button type=\"submit\" class=\"btn btn-default\">\r\n");
      out.write("\t\t\t\t\t\t\t<i class=\"la la-search\" style=\"font-size: 20px\"></i> Search\r\n");
      out.write("\t\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"modal fade\" id=\"modalUpdate1\" tabindex=\"-1\" role=\"dialog\"\r\n");
      out.write("\taria-labelledby=\"modalUpdatePro\" aria-hidden=\"true\">\r\n");
      out.write("\t<div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\r\n");
      out.write("\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t<div class=\"modal-header bg-primary\">\r\n");
      out.write("\t\t\t\t<h6 class=\"modal-title\">\r\n");
      out.write("\t\t\t\t\t<i class=\"la la-frown-o\"></i> Request to Sign\r\n");
      out.write("\t\t\t\t</h6>\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\"\r\n");
      out.write("\t\t\t\t\taria-label=\"Close\">\r\n");
      out.write("\t\t\t\t\t<span aria-hidden=\"true\">&times;</span>\r\n");
      out.write("\t\t\t\t</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"modal-body\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<form class=\"navbar-left navbar-form nav-search mr-md-3\"\r\n");
      out.write("\t\t\t\t\tid=\"requestsign\" action=\"javascript:fnRequestSign();\">\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t<div class=\"card-header \" id=\"docname\">Document Name :</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"card-header \">\r\n");
      out.write("\t\t\t\t\t\t<br> Need to Sign before requesting?\r\n");
      out.write("\t\t\t\t\t\t<p align=\"right\">\r\n");
      out.write("\t\t\t\t\t\t\t<br> <i class=\"la la-frown-o\"></i>All sign by<input\r\n");
      out.write("\t\t\t\t\t\t\t\ttype=\"date\" name=\"cdate\" required=\"required\">\r\n");
      out.write("\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" id=\"docid\" name=\"docid\">\r\n");
      out.write("\t\t\t\t\t<div class=\"card-header \">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" id=\"successInput\" name=\"uname\"\r\n");
      out.write("\t\t\t\t\t\t\trequired=\"required\" placeholder=\"Email ID or Mobile Number\">\r\n");
      out.write("\t\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"card-header \">\r\n");
      out.write("\t\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t\t<textarea id=\"successInput\" name=\"msg\" required=\"required\"\r\n");
      out.write("\t\t\t\t\t\t\tplaceholder=\"Add a message\" style=\"width: 100%\"></textarea>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"card-header \" align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t<br> <input type=\"submit\" class=\"btn btn-success\"\r\n");
      out.write("\t\t\t\t\t\t\tvalue=\"Submit\" style=\"width: 50%\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t<!-- \t\t\t\t\t<p>Currently the pro version of the <b>Ready Dashboard</b> Bootstrap is in progress development</p> -->\r\n");
      out.write("\t\t\t\t<!-- \t\t\t\t\t<p> -->\r\n");
      out.write("\t\t\t\t<!-- \t\t\t\t\t\t<b>We'll let you know when it's done</b></p> -->\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"modal fade\" id=\"modalShareDoc\" tabindex=\"-1\" role=\"dialog\"\r\n");
      out.write("\taria-labelledby=\"modalUpdatePro\" aria-hidden=\"true\">\r\n");
      out.write("\t<div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\r\n");
      out.write("\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t<div class=\"modal-header bg-primary\">\r\n");
      out.write("\t\t\t\t<h6 class=\"modal-title\">\r\n");
      out.write("\t\t\t\t\t<i class=\"la la-frown-o\"></i> Share With Others\r\n");
      out.write("\t\t\t\t</h6>\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\"\r\n");
      out.write("\t\t\t\t\taria-label=\"Close\">\r\n");
      out.write("\t\t\t\t\t<span aria-hidden=\"true\">&times;</span>\r\n");
      out.write("\t\t\t\t</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"modal-body\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<form class=\"navbar-left navbar-form nav-search mr-md-3\"\r\n");
      out.write("\t\t\t\t\tid=\"requestShare\" action=\"javascript:fnShareDocument()\">\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t<div class=\"card-header \" id=\"sharedocname\">Document Name :</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"card-header \">\r\n");
      out.write("\t\t\t\t\t\t<br> Need to Choose Share User Mail?\r\n");
      out.write("\t\t\t\t\t\t<p align=\"right\">\r\n");
      out.write("\t\t\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" id=\"sharedocid\" name=\"sharedocid\">\r\n");
      out.write("\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t\t<br> <br>\r\n");
      out.write("\t\t\t\t\t<div class=\"card-header \">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" id=\"successInput\" name=\"username\"\r\n");
      out.write("\t\t\t\t\t\t\trequired=\"required\" placeholder=\"Email ID or Mobile Number\">\r\n");
      out.write("\t\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<div class=\"card-header \" align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t<br> <input type=\"submit\" class=\"btn btn-success\"\r\n");
      out.write("\t\t\t\t\t\t\tvalue=\"Share Document\" style=\"width: 50%\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"modal fade\" id=\"modalShareOnEmailDoc\" tabindex=\"-1\"\r\n");
      out.write("\trole=\"dialog\" aria-labelledby=\"modalUpdatePro\" aria-hidden=\"true\">\r\n");
      out.write("\t<div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\r\n");
      out.write("\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t<div class=\"modal-header bg-primary\">\r\n");
      out.write("\t\t\t\t<h6 class=\"modal-title\">\r\n");
      out.write("\t\t\t\t\t<i class=\"la la-frown-o\"></i> Email A Copy\r\n");
      out.write("\t\t\t\t</h6>\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\"\r\n");
      out.write("\t\t\t\t\taria-label=\"Close\">\r\n");
      out.write("\t\t\t\t\t<span aria-hidden=\"true\">&times;</span>\r\n");
      out.write("\t\t\t\t</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"modal-body\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<form class=\"navbar-left navbar-form nav-search mr-md-3\"\r\n");
      out.write("\t\t\t\t\tid=\"requestShareOnEmail\"\r\n");
      out.write("\t\t\t\t\taction=\"javascript:fnShareDocumentOnMail()\">\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t<div class=\"card-header \" id=\"shareonemaildocname\">Document\r\n");
      out.write("\t\t\t\t\t\tName :</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"card-header \">\r\n");
      out.write("\t\t\t\t\t\t<br> Need to Enter User Mail?\r\n");
      out.write("\t\t\t\t\t\t<p align=\"right\">\r\n");
      out.write("\t\t\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" id=\"shareonemaildocid\"\r\n");
      out.write("\t\t\t\t\t\tname=\"shareonemaildocid\">\r\n");
      out.write("\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t\t<br> <br>\r\n");
      out.write("\t\t\t\t\t<div class=\"card-header \">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" id=\"successInput\" name=\"emailid\"\r\n");
      out.write("\t\t\t\t\t\t\trequired=\"required\" placeholder=\"Email ID \"> <br>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<div class=\"card-header \" align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t<br> <input type=\"submit\" class=\"btn btn-success\"\r\n");
      out.write("\t\t\t\t\t\t\tvalue=\"Share Document With Other User\" style=\"width: 50%\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"modal fade\" id=\"modalUpdatesignin\" tabindex=\"-1\"\r\n");
      out.write("\trole=\"dialog\" aria-labelledby=\"modalUpdatePro\" aria-hidden=\"true\">\r\n");
      out.write("\t<div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\r\n");
      out.write("\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t<div class=\"modal-header bg-primary\">\r\n");
      out.write("\t\t\t\t<h6 class=\"modal-title\">\r\n");
      out.write("\t\t\t\t\t<i class=\"la la-frown-o\"></i> Request to Sign\r\n");
      out.write("\t\t\t\t</h6>\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\"\r\n");
      out.write("\t\t\t\t\taria-label=\"Close\">\r\n");
      out.write("\t\t\t\t\t<span aria-hidden=\"true\">&times;</span>\r\n");
      out.write("\t\t\t\t</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"modal-body\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<form class=\"navbar-left navbar-form nav-search mr-md-3\"\r\n");
      out.write("\t\t\t\t\tmethod=\"post\" enctype=\"multipart/form-data\"\r\n");
      out.write("\t\t\t\t\taction=\"");
      out.print(request.getContextPath());
      out.write("/tiles/ajax.jsp?methodId=signFile\">\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t<div class=\"card-header \" id=\"signdocname\">Document Name :</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"card-header \">\r\n");
      out.write("\t\t\t\t\t\t<br> Need to Sign before digital sign?\r\n");
      out.write("\t\t\t\t\t\t<p align=\"right\">\r\n");
      out.write("\t\t\t\t\t\t\t<br> <input type=\"text\" id=\"successInput\" name=\"password\"\r\n");
      out.write("\t\t\t\t\t\t\t\trequired=\"required\" placeholder=\"Email Certificate Password\">\r\n");
      out.write("\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" id=\"signdocid\" name=\"signdocid\"> <input\r\n");
      out.write("\t\t\t\t\t\tclass=\"btn btn-default\" accept=\"application/p12\" type=\"file\"\r\n");
      out.write("\t\t\t\t\t\trequired=\"required\" name=\"file\" class=\"form-control\"><br>\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<div class=\"card-header \" align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t<br> <input type=\"submit\" class=\"btn btn-success\"\r\n");
      out.write("\t\t\t\t\t\t\tvalue=\"Sign Document\" style=\"width: 50%\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("function fnShareDocumentOnMail(){\t\r\n");
      out.write("\tvar str = $(\"#requestShareOnEmail\" ).serialize();\r\n");
      out.write("\r\n");
      out.write("\t$.post(\"");
      out.print(request.getContextPath());
      out.write("/tiles/ajax.jsp?methodId=requestShareOnEmail\",\r\n");
      out.write("\t\t\tstr, function(data) {\r\n");
      out.write("\t\t\t\tdata = $.trim(data);\r\n");
      out.write("\t\t\t\talert(data);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t\twindow.location.href=\"");
      out.print(request.getContextPath());
      out.write("/pages/dashboard.jsp\";\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t$('#myform')[0].reset();\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("function fnRequestSign(){\t\r\n");
      out.write("\tvar str = $(\"#requestsign\" ).serialize();\r\n");
      out.write("\r\n");
      out.write("\t$.post(\"");
      out.print(request.getContextPath());
      out.write("/tiles/ajax.jsp?methodId=requestsign\",\r\n");
      out.write("\t\t\tstr, function(data) {\r\n");
      out.write("\t\t\t\tdata = $.trim(data);\r\n");
      out.write("\t\t\t\talert(data);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t\twindow.location.href=\"");
      out.print(request.getContextPath());
      out.write("/pages/dashboard.jsp\";\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t$('#myform')[0].reset();\r\n");
      out.write("\t\t\t\t\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function fnShareDocument(){\t\r\n");
      out.write("\tvar str = $(\"#requestShare\" ).serialize();\r\n");
      out.write("\r\n");
      out.write("\t$.post(\"");
      out.print(request.getContextPath());
      out.write("/tiles/ajax.jsp?methodId=requestShare\",\r\n");
      out.write("\t\t\tstr, function(data) {\r\n");
      out.write("\t\t\t\tdata = $.trim(data);\r\n");
      out.write("\t\t\t\talert(data);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t\twindow.location.href=\"");
      out.print(request.getContextPath());
      out.write("/pages/dashboard.jsp\";\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t$('#myform')[0].reset();\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("function fnDeleteDocument(did){\t\r\n");
      out.write("\r\n");
      out.write("if(confirm('Are you sure want to Delete?')){\r\n");
      out.write("\twindow.location.href='");
      out.print(request.getContextPath());
      out.write("/tiles/ajax.jsp?methodId=deleteFile&did='+did;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("function fnShareFileWith(a,b){\t\r\n");
      out.write("//\talert(b);\r\n");
      out.write("\tdocument.getElementById('sharedocid').value=a;\r\n");
      out.write("\t\r\n");
      out.write("\tdocument.getElementById('sharedocname').innerHTML='Document Name : '+b;\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#modalShareDoc\").modal(\"show\");\r\n");
      out.write("}\r\n");
      out.write("</script>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../tiles/footer.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- Modal -->\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../tiles/footerinc.jsp", out, false);
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function fnShowValue(a,b){\t\r\n");
      out.write("// \talert(b);\r\n");
      out.write("\tdocument.getElementById('docid').value=a;\r\n");
      out.write("\t\r\n");
      out.write("\tdocument.getElementById('docname').innerHTML='Document Name : '+b;\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#modalUpdate1\").modal(\"show\");\r\n");
      out.write("}\r\n");
      out.write("function fnSignFile(a,b){\t\r\n");
      out.write("// \talert(b);\r\n");
      out.write("\tdocument.getElementById('signdocid').value=a;\r\n");
      out.write("\t\r\n");
      out.write("\tdocument.getElementById('signdocname').innerHTML='Document Name : '+b;\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#modalUpdatesignin\").modal(\"show\");\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function fnEmailDoc(a,b){\t\r\n");
      out.write("\talert(b);\r\n");
      out.write("\tdocument.getElementById('shareonemaildocid').value=a;\r\n");
      out.write("\t\r\n");
      out.write("\tdocument.getElementById('shareonemaildocname').innerHTML='Document Name : '+b;\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#modalShareOnEmailDoc\").modal(\"show\");\r\n");
      out.write("}\r\n");
      out.write("function fnViewDoc(did){\t\r\n");
      out.write("// \talert(b);\r\n");
      out.write("// \tdocument.getElementById('docid').value=a;\r\n");
      out.write("\t\r\n");
      out.write("// \tdocument.getElementById('docname').innerHTML='Document Name : '+b;\r\n");
      out.write("\t//$(\"#\"+a).modal(\"show\");\r\n");
      out.write("\t\ts='';\r\n");
      out.write("params = 'scrollbars=no,resizable=no,status=no,location=no,toolbar=no,menubar=no,width=1000,height=700';\r\n");
      out.write("s2=\"");
      out.print(request.getContextPath());
      out.write("/tiles/ajax.jsp?methodId=viewFile2&fid=\"+did+\"&inline=1\";\r\n");
      out.write("\twindow.open(s2, 'My PDF', params);\r\n");
      out.write("\t\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function fnChangeSaleStat(stat,did,verif){\r\n");
      out.write("\tif(verif==\"verified\"){\r\n");
      out.write("\t$.post(\"");
      out.print(request.getContextPath());
      out.write("/tiles/ajax.jsp?methodId=changeSaleStat&stat=\"+stat+\"&did=\"+did,\r\n");
      out.write("\t\t\t function(data) {\r\n");
      out.write("\t\t\t\tif(data==\"0\"){\r\n");
      out.write("\t\t\t\t\talert(\"Some Problem Occurred!\");\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\talert(\"Status Changed Successfully!\");\r\n");
      out.write("\t\t\t\t\t//window.location.href=\"");
      out.print(request.getContextPath());
      out.write("/pages/dashboard.jsp\";\r\n");
      out.write("\t\t\t\t\tlocation.reload(); \r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\talert(\"Property is not Verifies yet. Please Verify Property first to Sale.\");\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</html>");
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
