<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add question</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
    <script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>

<style>

	.container {
	margin-top: 25px;}
	
</style>

<body>
    <div class="container">
        <h1>What is your question?</h1>
        
        <div class="row">
            <div class="col">
                <form:form action="/question/create" method="post" modelAttribute="quest">
                    
                    <div class="form-group">
                        <label>Question:</label>
                        <form:textarea path="question" class="form-control" />
                        <form:errors path="question" class="text-danger" />
                    </div>
                    
                    <div class="form-group">
                        <label>Tags (separate with space, not comma):</label>
                        <form:input path="tags" class="form-control" list="taglist" />
                        <form:errors path="tags" class="text-danger" />
                    
		                    <datalist id="taglist">
		                        <c:forEach items="${tags}" var="t">
		                            <option>${t.subject}</option>
		                        </c:forEach>
		                    </datalist>

                     </div>
                    <input type="submit" class="btn btn-primary" value="Create" />
                </form:form> 
            </div>
            </div>   
            
          
            
            
            
                
                
            
    
    </div>
</body>
</html>