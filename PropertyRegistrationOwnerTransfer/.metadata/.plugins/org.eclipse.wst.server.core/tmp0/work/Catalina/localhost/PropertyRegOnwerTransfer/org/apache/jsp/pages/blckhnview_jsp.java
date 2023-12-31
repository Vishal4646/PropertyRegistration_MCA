/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.32
 * Generated at: 2022-07-23 07:14:02 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.HashMap;
import com.ipfsblockchain.BlockChainIPFS;
import com.model.DocumentModel;
import java.util.List;
import com.database.ConnectionManager;

public final class blckhnview_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes.add("com.model.DocumentModel");
    _jspx_imports_classes.add("com.ipfsblockchain.BlockChainIPFS");
    _jspx_imports_classes.add("java.util.HashMap");
    _jspx_imports_classes.add("com.database.ConnectionManager");
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=utf-8 />\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../tiles/inc.jsp", out, false);
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<style>\r\n");
      out.write("\t.hash{\r\n");
      out.write("\tborder: 1px solid #4fe14f;\r\n");
      out.write("\tbackground-color: #F9F9FB;\r\n");
      out.write("\tborder-radius: 4px;\r\n");
      out.write("\tpadding: 1px 5px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".hoverr: hover {\r\n");
      out.write("\tbox-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);\r\n");
      out.write("\tbackground-color: red;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<body class=\"container py-5\" style=\"background-color: #F9F9FB\">\r\n");
      out.write("\r\n");
      out.write("\r\n");

String pid = (String)request.getParameter("pid");
	System.out.println("pid: " + pid);

      out.write("\r\n");
      out.write("\r\n");
      out.write("<h3 class=\"text-center m-auto\">Blockchain View of Property</h3>\r\n");
if(pid == null || pid.equalsIgnoreCase("")){ 
      out.write("\r\n");
      out.write("\t<h4 class=\"text-center text-danger m-auto\">Please select a valid property to view details</h4>\r\n");
}else{
	List list = ConnectionManager.getSingleProperty(pid);
	if(list.size() == 0){

      out.write("\r\n");
      out.write("\t<h4 class=\"text-center text-danger m-auto\">No valid property selected</h4>\r\n");
      out.write("\t");
}else{
		DocumentModel dm = (DocumentModel) list.get(0);
	
      out.write("\r\n");
      out.write("\r\n");
      out.write("<p class=\"text-secondary text-center m-auto\">Default ID: ");
      out.print(pid );
      out.write(" | Property ID: ");
      out.print(dm.getPropid() );
      out.write(" |  Survey No.: ");
      out.print(dm.getSurveyno() );
      out.write(" |  Property type: ");
      out.print(dm.getProptype() );
      out.write(" |  Address : ");
      out.print(dm.getAddr() );
      out.write(" |  Verification Status: ");
      out.print(dm.getStatus() );
      out.write(" </p>\r\n");
      out.write("<br>\r\n");
      out.write("<div class=\"row d-flex justify-content-center\">\r\n");
 BlockChainIPFS br = new BlockChainIPFS();
	List blocks = br.getBlockHistoryById(pid);
	
	if(blocks.size() > 0){
		for(int i=0; i<blocks.size(); i++){
			String data = (String) blocks.get(i);
			//System.out.println("got data: " + data);
			HashMap<String, String> map = new HashMap<String, String>();
			String arr[] = data.split(",");
			//System.out.println("length: " + arr.length);
			//for(int k=0; k<arr.length; k++){
				//System.out.println("splitted: " + k + " : " + arr[k]);
			//}
			//System.out.println("");
			//System.out.println("");
			
			for(int j=0; j<arr.length; j++){
				map.put(arr[j].split("=")[0], arr[j].split("=")[1]);
			}
			//System.out.println(map);

      out.write("\r\n");
      out.write("\t<div class=\"card hoverr\" style=\"width: 65%;\">\r\n");
      out.write("\t\t<div class=\"card-body p-4\">\r\n");
      out.write("\t\t\t");
if(i==0){ 
      out.write("\r\n");
      out.write("\t\t\t\t<h5 class=\"card-title\" style=\"font-size: 24px!important;\">GENESIS BLOCK</h5>\r\n");
      out.write("\t\t\t");
}else{ 
      out.write("\r\n");
      out.write("\t\t\t\t<h5 class=\"card-title\" style=\"font-size: 24px!important;\">BLOCK #");
      out.print(i);
      out.write("</h5>\r\n");
      out.write("\t\t\t");
} 
      out.write("\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\t\t\t<!-- <h6 class=\"card-subtitle mb-2 text-muted\">Card subtitle</h6> -->\r\n");
      out.write("\t\t\t<div style = \"text-transform:uppercase;\">Previous Hash : &nbsp;&nbsp;<span class=\"text-success\">");
      out.print(map.get("prevHash") );
      out.write("</span></div>\r\n");
      out.write("\t\t\t<div style = \"text-transform:uppercase;\">Hash : &nbsp;&nbsp;<span class=\"text-success hash\">");
      out.print(map.get("Hash") );
      out.write("</span></div>\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\t\t\t<div style = \"text-transform:uppercase;\">Previous Owner : &nbsp;&nbsp;");
      out.print(ConnectionManager.getOnwerDetForBlock((String)map.get("OldOwnerId")) );
      out.write("</div>\r\n");
      out.write("\t\t\t<div style = \"text-transform:uppercase;\">Current Owner : &nbsp;&nbsp;");
      out.print(ConnectionManager.getOnwerDetForBlock((String)map.get("NewOwnerId")) );
      out.write("</div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<small>Created on : &nbsp;&nbsp;");
      out.print(ConnectionManager.millToDatetime((String)map.get("time")) );
      out.write("</small>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t");
}
		}else{ 
      out.write("\r\n");
      out.write("\t<h4 class=\"text-center text-info m-auto\">No history records available for this property yet</h4>\r\n");
      out.write("\t");
}}
	} 
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("</body>\r\n");
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
