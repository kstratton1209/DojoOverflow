<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Question details</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
    <script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>

<style>

	.container {
	margin-top: 25px;}
	
	.tags {
	display: flex;}
	
</style>

<body>
    <div class="container">
        <h1>${question.question}</h1>
        <div class="tags">
        
	        <h3>Tags:</h3>
	        	<c:forEach items="${question.tags}" var="tags">             
			      
			       <button type="button" class="btn btn-md btn-primary ml-3" disabled> ${tags.subject} </button>
			       
			    </c:forEach>
        
        </div>
        
        <div class="row">
            <div class="col">
                    
                    <table class="table table-striped mt-3">
		            <tbody>
		                <tr>
		                    <th>Answers</th>
		                </tr>
		                <p></p>
		                <c:if test="${question.getAnswers().size() == 0}"><p>There are no answers yet</p></c:if>
		                
 		                <c:forEach items="${question.getAnswers()}" var="ans">
		            		<tr>
		                        <td>
		                         
		        						${ans.answer}
		                        		
		                        </td>
		                    </tr>
		                    
		                
	 		                </c:forEach>
			            </tbody>
	       		   </table>
                    
                
            </div>
            <div class="col">
	         			
				
				 <form:form action="/${question.id}/answer/create" method="post" modelAttribute="answ">
                    <input type="hidden" name = "question" value = "${question.id}">
                    <div class="form-group">
                        <label>Answer:</label>
                        <form:textarea path="answer" class="form-control" />
                        <form:errors path="answer" class="text-danger" />
                    </div>
                    
                    
                    <input type="submit" class="btn btn-primary" value="Answer" />
                </form:form> 
				
				
				
				
				
				
			
            	
            
            </div> 
            </div>   
            
          
            
            
            
                
                
            
    
    </div>
</body>
</html>