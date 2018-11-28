<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<%@taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<body>

<s:form action="index" method="post">
	<s:textfield name="name" label="Name" size="20" />
	<s:submit name="submit" label="Submit" align="center" />
</s:form>


</body>
</html>
	