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
							
							
							
						</div>
				<!-- /.panel-body -->
						
                    </div>
                    <!-- /.panel -->
                    
                    <!-- 댓글처리 시작 -->
                    <div class ='row'>
						<div class="col-lg-12">
							<!-- /.panel -->
							<div class="panel panel-default">
								<div class="panel-heading">
									<li class="fa fa-comments fa-fw"></li>Reply
									<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New Reply</button>
								</div>
								
								<!-- ./panel-heading -->
								<div class="panel-body">
									<ul class="chat">
										<!-- start reply -->
										<li class="left clearfix" data-rno='12'>
											<div>
												<div class="header">
													<strong class="primary-font">user00</strong>
													<small class="pull-right text-muted">2021-12-19 07:47</small>
												</div>
												<p>Good job!</p>
											</div>
										</li>
										<!-- end reply -->	
									</ul>
									<!-- ./ end ul -->
								</div>
								<!-- /.panel .chat-panel -->
							</div>
						</div>
						<!-- ./ end row -->
					</div>
					<!-- 댓글처리 끝 -->
							
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            
            
            
            <!-- Modal -->
             <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                 <div class="modal-dialog">
                     <div class="modal-content">
                         <div class="modal-header">
                             <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                             <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
                         </div>
                         <div class="modal-body">
                         	<div class="form-group">
                         		<label>Reply</label>
                         		<input class="form-control" name='reply' value='New Reply!'>
                         	</div>
                         	<div class="form-group">
                         		<label>Replyer</label>
                         		<input class="form-control" name='replyer' value='replyer'>
                         	</div>
                         	<div class="form-group">
                         		<label>Reply Date</label>
                         		<input class="form-control" name='replyDate' value=''>
                         	</div>
                         </div>
                         <div class="modal-footer">
                             <button type="button" class="btn btn-warning" id='modalModBtn'>Modify</button>
                             <button type="button" class="btn btn-danger" id='modalRemoveBtn'>Remove</button>
                             <button type="button" class="btn btn-primary" id='modalRegisterBtn'>Register</button>
                             <button type="button" class="btn btn-default" id='modalCloseBtn' data-dismiss="modal">Close</button>
                         </div>
                     </div>
                     <!-- /.modal-content -->
                 </div>
                 <!-- /.modal-dialog -->
             </div>
             <!-- /.modal -->
            
            
            
            
            <!-- used form for board-detail to list  active start -->
			<script type="text/javascript" src="/resources/js/reply.js"></script>
			
			<!-- reply.js <-> get.jsp Connection Test  -->
			<!-- <script type="text/javascript">
				$(document).ready(function() {
					console.log(replyService);
				});
			</script> -->
			
			<!-- 댓글 등록/목록조회/삭제 -->
			<script>
				console.log("============");
				console.log("JS TEST");
				
				let bnoValue = '<c:out value="${board.bno}"/>'; // 게시물 번호
				
				
				// **********************************
				// reply.js 댓글 등록 시작
				/* replyService.add(
				  { 
				  reply:"JS Test",
				  replyer:"tester",
				  bno: bnoValue
				  }
				  ,
				  function(result) {
					alert("RESULT : " + result);
				  }
				); */
				// reply.js 댓글 등록 끝
				// **********************************
				
				// **********************************
				// reply.js 댓글 목록 조회 시작
				/* replyService.getList({bno:bnoValue, page:1}, function(list) {
					 for(let i = 0, len = list.length || 0; i < len; i++) {
						 console.log(list[i]);
					 } 
				  }); */
				// reply.js 댓글 목록 조회 끝
				// **********************************
				  
				// **********************************
				// reply.js 댓글 삭제 시작
				/* replyService.remove(18, function(count) {
				  console.log(count);
				  
				  if(count === "success") {
					  alert("REMOVED");
				  }
				}, function(err) {
				  alert('ERROR');	
				}); */
				// reply.js 댓글 삭제 끝
				// **********************************
				
				// **********************************
				// reply.js 댓글 수정 시작
				/* replyService.update({
				  rno: 16,
				  bno: bnoValue,
				  reply: "Modified Reply (reply.js)"
				}, function(result) {
					alert("수정 완료");
				}); */								
				// reply.js 댓글 수정 끝
				// **********************************
				
				
				// **********************************
				// reply.js 댓글 조회 시작
				/* replyService.get(6, function(data) {
					console.log(data);
				}); */
				// reply.js 댓글 조회 끝
				// **********************************
			</script>
			
			
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
			
			<script>
				$(document).ready(function() {
					let bnoValue = '<c:out value="${board.bno}"/>';
					let replyUL = $(".chat");
					
					showList(1);
					
					function showList(page) {
						replyService.getList({bno:bnoValue, page:1}, function(list) {
							let str = "";
							
							if(list == null || list.length == 0) {
								replyUL.html("");
								
								return ;
							}
							for(let i = 0, len = list.length || 0; i < len; i++) {
							/* 
							<li class="left clearfix" data-rno='12'>
							  <div><div class="header"><strong class="primary-font">user00</strong>
								<small class="pull-right text-muted">2021-12-19 07:47</small></div>
								  <p>Good job!</p></div></li> 
							*/
								str += "<li class='left clearfix' data-rno='" + list[i].rno + "'>";
								str += "  <div><div class='header'><strong class='primary-font'>" + list[i].replyer + "</strong>";
								str += "    <small class='pull-right text-muted'>" + replyService.displayTime(list[i].replyDate) + "</small></div>";
								str += "    <p>" + list[i].reply + "</p></div></li>";
							}
							replyUL.html(str);
						}); // end replyService.getList
					} // end showList
					
					/* --------------------------- */
					/* --------- Modal ----------- */
					/* --------------------------- */
					const modal = $(".modal");
					let modalInputReply = modal.find("input[name='reply']");
					let modalInputReplyer = modal.find("input[name='replyer']");
					let modalInputReplyDate = modal.find("input[name='replyDate']");
					
					const modalModBtn = $("#modalModBtn");
					const modalRemoveBtn = $("#modalRemoveBtn");
					const modalRegisterBtn = $("#modalRegisterBtn");
					
					// [New Reply] 클릭 이벤트
					$("#addReplyBtn").on("click", function(e) {
						modal.find("input").val("");
						modalInputReplyDate.closest("div").hide();
						modal.find("button[id != 'modalCloseBtn']").hide();
						
						modalRegisterBtn.show();
						
						$(".modal").modal("show");
					});
					
					// 댓글 등록 및 목록 갱신
					modalRegisterBtn.on("click", function(e) {
						let reply = {
								reply: modalInputReply.val(),
								replyer: modalInputReplyer.val(),
								bno: bnoValue
						};
						replyService.add(reply, function(result) {
							alert(result);
							
							modal.find("input").val("");
							modal.modal("hide");
							
							// page refresh
							showList(1);
						});
					});
					
					// 댓글 클릭 시 이벤트					
					$(".chat").on("click", "li", function(e) {
						let rno = $(this).data("rno");
						// 댓글 클릭시 콘솔에 rno출력
						console.log(rno);
						
						// 댓글 정보 가져오기
						replyService.get(rno, function(reply) {
							modalInputReply.val(reply.reply);
							modalInputReplyer.val(reply.replyer);
							modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly", "readonly");
							
							modal.data("rno", reply.rno);
							
							modal.find("button[id != 'modalCloseBtn']").hide();
							modalModBtn.show();
							modalRemoveBtn.show();
							
							$(".modal").modal("show");
						});
						
						// 댓글 수정 버튼 클릭 시 이벤트
						modalModBtn.on("click", function(e) {
							let reply = {rno: modal.data("rno"), reply: modalInputReply.val()};
							
							// 댓글 수정
							replyService.update(reply, function(result) {
								alert(result);
								modal.modal("hide");
								showList(1);
							});
						});
						
						// 댓글 삭제 버튼 클릭 시 이벤트
						modalRemoveBtn.on("click", function(e) {
							let rno = modal.data("rno");
							
							//댓글 삭제
							replyService.remove(rno, function(result) {
								alert(result);
								modal.modal("hide");
								showList(1);
							});
						});
						
					});
				});
			</script>
            
        <%@include file="../includes/footer.jsp"%>