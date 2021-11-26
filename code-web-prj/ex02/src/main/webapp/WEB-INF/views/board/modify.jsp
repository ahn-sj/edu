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
							<form>
								<!-- <h1>Board Register</h1> -->
				
								<!-- bno  -->
								<div class="form-group">
									<label>BNO</label> 
									<input class="form-control" name="bno" readonly="readonly" value='<c:out value="${board.bno}"/>'>
								</div>
								
								<!-- title  -->
								<div class="form-group">
									<label>Title</label> 
									<input class="form-control" name="title" value='<c:out value="${board.title}"/>'>
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
							</form>
						</div>
						<!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>

<script>
	$(document).ready(function() {
		
		var formObj = $("form");
		
		$('.btn').click(function(e) {
			
			e.preventDefault();
			
			var operation = $(this).data("oper");	
			console.log(operation);
			
			/* btn list action */
			if(operation === 'list') self.location = "/board/list";
			/* btn remove action */
			/* 아래 문장 입력 시 remove를 클릭하면 form태그의 action과 method가 변경됨 */
			else if(operation === 'remove') {
				formObj.attr("action", "/board/remove")
				.attr("method", "post");
				formObj.submit();
			}
			/* btn modify action */
			else if(operation === 'modify') {
				formObj.attr("action", "/board/modify")
				.attr("method", "post");
				formObj.submit();
			}
		})
	});
</script>
            
        <%@include file="../includes/footer.jsp"%>