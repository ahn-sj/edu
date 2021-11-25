<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
	<%@include file="../includes/header.jsp"  %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Modify/Delete</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board Modify/Delete
                        </div>
	                        <!-- /.panel-heading -->
						<div class="panel-body">
			
							<!-- <h1>Board Register</h1> -->
			
							<!-- bno  -->
							<div class="form-group">
								<label>BNO</label> 
								<input class="form-control" name="bno" readonly="readonly" value='<c:out value="${board.bno}"/>'>
							</div>
							
							<!-- title  -->
							<div class="form-group">
								<label>Title</label> 
								<input class="form-control" name="title" readonly="readonly" value='<c:out value="${board.title}"/>'>
							</div>
			
							<!-- content -->
							<div class="form-group">
								<label>Content</label>
								<textarea class="form-control" name="content" rows="5" cols="50"><c:out value="${board.content}"/></textarea>
							</div>
			
							<!-- writer  -->
							<div class="form-group">
								<label>Writer</label> 
								<input class="form-control" name="writer" value='<c:out value="${board.writer}"/>'>
							</div>
			
							<button type="button" class="btn btn-default" data-oper='modify'>Modify</button>
							<button type="button" class="btn btn-danger" data-oper='remove'>Remove</button>
							<button type="button" class="btn btn-info" data-oper='list'>List</button>
						</div>
				<!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>

<script>
	$(document).ready(function(e) {
		
		$('.')
		
		e.preventDefault();
		
		var operation=$(this).data("oper");
	});
</script>
            
        <%@include file="../includes/footer.jsp"%>