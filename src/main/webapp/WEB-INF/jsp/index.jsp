<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  这是我的第一个jsp,在jsp下面
<c:form name="name1">
    <h1>这是传进来的name1：${name1}</h1>
  </c:form>
  <c:form  name="呵呵">
    <h1>这是传进来的userId1：${userId1}</h1>
    <h1>这是传进来的userName1：${userName1}</h1>
  </c:form>
  <c:form  name="list">
    <h1>这是传进来的userId2：${userId2}</h1>
    <h1>这是传进来的userName2：${userName2}</h1>
  </c:form>
</body>
</html>