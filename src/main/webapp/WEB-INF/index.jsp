<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Question dashboard</title>
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
        <h1>Questions Dashboard</h1>
        
        <div class="row">
            
            <div class="col">
               
               <table class="table table-striped mt-3">
		            <tbody>
		                <tr>
		                    <th>Question</th>
		                    <th>Tags</th>
		                </tr>
 		                <c:forEach items="${questions}" var="q">
		            		<tr>
		                        <td><a href="/question/${q.id}">${q.question}</a></td>
		                        <td>
		                           <c:forEach items="${q.tags}" var="tags" varStatus="loop">
		                        
		        						${tags.subject}<c:if test="${loop.index+1<q.getTags().size()}">,</c:if>
		                           </c:forEach>
		                        		
		                        </td>
		                    </tr>
		                    
		                
 		                </c:forEach>
		            </tbody>
       		   </table>
       		  <a href="/newquestion" class="btn btn-primary btn-lg" role="button" >New question</a>
       		        
          </div>   
</div>   
            
          
            
            
            
                
                
            
    
</div>
</body>
</html>