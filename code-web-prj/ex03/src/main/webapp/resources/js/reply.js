console.log("Reply module");

let replyService = (function() {

  // 댓글 추가
  function add(reply, callback, error) {
	console.log("add reply");
	
	$.ajax({
	  type: 'post',        // HTTP 전송 방식
	  url: '/replies/new', // Request client -> server
	  data: JSON.stringify(reply), // Request + >> DATA <<
	  contentType: "application/json; charset=utf-8", 
	  // server -> client 보낼 때 statement(success/error)실행
	  success: function(result, status, xhr) {
	    if(callback) {
	      callback(result);
	    }
	  },
	  error: function(xhr, status, er) {
	    if(error) {
	      error(er);
	    }
	  }
	})
  }
	
  // 댓글 목록(특정 게시물)
  function getList(param, callback, error) {
    var bno = param.bno;
    var page = param.page || 1;
    
    $.getJSON("/replies/pages/" + bno + "/" + page + ".json", // 매개변수 1: 처리할 데이터
    			function(data) { // 매개변수 2: 데이터 처리 방법
    			  if(callback) {
    			    callback(data);
    			  }
    			}).fail(function(xhr, status, err) {
    			  if(error) {
    			    error();
    			  }
    			});
    }
    
    // 댓글 삭제
    function remove(rno, callback, error) {
      $.ajax({
        type: 'delete',
        url: '/replies/' + rno,
        success: function(deleteResult, status, xhr) {
          if(callback) {
            callback(deleteResult);
          }
        },
        error: function(xhr, status, er) {
	      if(error) {
	        error(er);
	      }
	    }
      });
    }
    
    // 댓글 수정
    function update(reply, callback, error) {
      console.log("RNO: " + reply.rno);
      
      $.ajax({
        type: 'put',
        url: '/replies/' + reply.rno,
        data: JSON.stringify(reply),
        contentType: "application/json; charset=utf-8", 
	    success: function(result, status, xhr) {
	      if(callback) {
	        callback(result);
	      }
	    },
	    error: function(xhr, status, er) {
	      if(error) {
	        error(er);
	      }
	    }
      });
    }
    
    // 댓글 조회 (특정 번호)
    function get(rno, callback, error) {
      $.get("/replies/" + rno + ".json", function(result) {
        if(callback) {
	      callback(result);
	    }
	  }).fail(function(xhr, status, err) {
	   if(error) {
	     error();
	   }
	  });
    }
    
    // 시간 처리
    function displayTime(timeValue) {
      let today = new Date();
      let gap = today.getTime() - timeValue;
      let dateObj = new Date(timeValue);
      let str = "";
      
      if(gap < (1000 * 60 * 60 * 24)) {
        let hh = dateObj.getHours();
        let mi = dateObj.getMinutes();
        let ss = dateObj.getSeconds();
        
        return [ (hh > 9 ? '' : '0') + hh, ':', 
        		 (mi > 9 ? '' : '0') + mi, ':',
        		 (ss > 9 ? '' : '0') + ss ].join('');
      } else {
      	let yy = dateObj.getFullYear();
      	let mm = dateObj.getMonth() + 1; // getMonth() is zero-based
      	let dd = dateObj.getDate();
      	
      	return [ yy, '/', 
      			(mm > 9 ? '' : '0') + mm, '/',
      			(dd > 9 ? '' : '0') + dd ].join('');
      }
    };
  
  return {
    add:add,
    getList: getList,
    remove: remove,
    update: update,
    get: get,
    displayTime: displayTime
  };
})();