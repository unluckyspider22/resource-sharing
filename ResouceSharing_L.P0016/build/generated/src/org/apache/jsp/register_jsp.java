package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

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
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>Register Page</title>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <div class=\"container\">\r\n");
      out.write("            <div class=\"row pt-5\">\r\n");
      out.write("                <aside class=\"col-sm-4\">\r\n");
      out.write("                </aside>\r\n");
      out.write("                <aside class=\"col-sm-4\">\r\n");
      out.write("                    <div class=\"card\">\r\n");
      out.write("                        <article class=\"card-body\">\r\n");
      out.write("                            <a href=\"login.jsp\" class=\"float-right btn btn-outline-primary\">Login</a>\r\n");
      out.write("                            <h4 class=\"card-title mb-4 mt-1\">Sign Up</h4>\r\n");
      out.write("                            <form action=\"MainController\" method=\"POST\">\r\n");
      out.write("                                <div class=\"form-group\">\r\n");
      out.write("                                    <label>Your name</label>\r\n");
      out.write("                                    <input name=\"txtName\" class=\"form-control\" placeholder=\"Name\" type=\"text\" required=\"true\">\r\n");
      out.write("                                </div> <!-- form-group// -->\r\n");
      out.write("\r\n");
      out.write("                                <div class=\"form-group\">\r\n");
      out.write("                                    <label>Your email</label>\r\n");
      out.write("                                    <input name=\"txtEmail\" class=\"form-control\" placeholder=\"Email\" type=\"email\" required=\"true\">\r\n");
      out.write("                                    <font color=\"red\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.REGISTERERROR.emailError}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</font>\r\n");
      out.write("                                </div> <!-- form-group// -->\r\n");
      out.write("                                <div class=\"form-group\">\r\n");
      out.write("                                    <label>Password</label>\r\n");
      out.write("                                    <input name=\"txtPassword\" id=\"txtPassword\" class=\"form-control\" placeholder=\"******\" type=\"password\" required=\"true\" min=\"8\" max=\"36\">\r\n");
      out.write("                                </div> <!-- form-group// --> \r\n");
      out.write("\r\n");
      out.write("                                <div class=\"form-group\">\r\n");
      out.write("                                    <label>Confirm password</label>\r\n");
      out.write("                                    <input name=\"txtConfirmPassword\" id=\"txtConfirmPassword\" class=\"form-control\" placeholder=\"******\" type=\"password\" required=\"true\">\r\n");
      out.write("                                </div> <!-- form-group// --> \r\n");
      out.write("                                <div class=\"form-group\">\r\n");
      out.write("                                    <button type=\"submit\" class=\"btn btn-primary btn-block\" name=\"action\" value=\"Register\" onclick=\"return ValidatePassword()\"> Register </button>\r\n");
      out.write("                                </div> <!-- form-group// -->                               \r\n");
      out.write("                            </form>\r\n");
      out.write("                        </article>\r\n");
      out.write("                    </div> <!-- card.// -->\r\n");
      out.write("                </aside> <!-- col.// -->\r\n");
      out.write("\r\n");
      out.write("                <aside class=\"col-sm-4\">\r\n");
      out.write("                </aside>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <script type=\"text/javascript\">\r\n");
      out.write("            function ValidatePassword() {\r\n");
      out.write("                var password = document.getElementById(\"txtPassword\").value;\r\n");
      out.write("                var confirmPassword = document.getElementById(\"txtConfirmPassword\").value;\r\n");
      out.write("                if (password != confirmPassword) {\r\n");
      out.write("                    alert(\"Passwords do not match!\");\r\n");
      out.write("                    return false;\r\n");
      out.write("                }\r\n");
      out.write("                return true;\r\n");
      out.write("            }\r\n");
      out.write("        </script>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
