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
		                        		<p></p>
		                        </td>
		                    </tr>
		                    
		                
	 		                </c:forEach>
			            </tbody>
	       		   </table>
                    
                
            </div>
            <div class="col">
	          
	      
            	
            	<form action="/${question.id}/answer/create" method="post">
				<p>
					<span style="display:block">Answer:</span>
					<textarea name="answer" rows=6 cols=60 class="form-control" ></textarea>
				</p>
				
				<p>${error}</p>
				
				<button type="submit" class="btn btn-primary">Submit Answer</button>
			</form>
            	
            
            </div> 
            </div>   
            
          
            
            
            
                
                
            
    
    </div>
</body>
</html>