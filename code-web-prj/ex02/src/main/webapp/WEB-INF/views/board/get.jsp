<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
	<%@include file="../includes/header.jsp"  %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Read</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board Read
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
							
							
							<!-- form start -->
                            <form id='actionForm' action="/board/list" method='get'>
                            	<input type='hidden' name='pageNum' value='${cri.pageNum}'>
                            	<input type='hidden' name='amount' value='${cri.amount}'>
                            	<input type='hidden' name='bno' value='${board.bno}'>
                            	<input type='hidden' name='type' value='${cri.type}'>
                            	<input type='hidden' name='keyword' value='${cri.keyword}'>
                            </form>
                            <!-- form end -->
                            
							<button type="button" class="btn btn-default listBtn"><a href='/board/list'>List</a></button>
							<button type="button" class="btn btn-default modBtn"><a href='/board/modify?bno=<c:out value="${board.bno}"/>'>Modify</a></button>
							
							<!-- used form for board-detail to list  active start -->
							<script>
							
								var actionForm = $("#actionForm");
								
								$(".listBtn").click(function(e) {
									e.preventDefault();
									/* 해당 페이지로 이동할 때 bno는 제거함 */
									/* http://localhost:8080/board/list?pageNum=1&amount=10 */
									actionForm.find("input[name='bno']").remove();
									actionForm.submit();
								});
								
								$(".modBtn").click(function(e) {
									e.preventDefault();
									/* http://localhost:8080/board/modify?pageNum=1&amount=10&bno=37 */
									actionForm.attr("action", "/board/modify");
									actionForm.submit();
								});
							</script>
							<!-- used form for board-detail to list  active end -->
						</div>
				<!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
        <%@include file="../includes/footer.jsp"%>