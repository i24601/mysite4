<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
</head>
<body>

	<div id="wrap">
		<!-- 헤더분리 -->
		<c:import url="/WEB-INF/views/include/header.jsp">
		</c:import>
		<!-- 헤더분리 -->
		<!-- //header -->

		<c:import url="/WEB-INF/views/include/nav.jsp">
		</c:import>

		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/aSideGuestbook.jsp">
		</c:import>

		<!-- //aside -->




		<div id="content">
			<div id="content-head">
				<h3>ajax방명록</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>방명록</li>
						<li class="last">ajax방명록</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="guestbook">

				<table id="guestAdd">
					<colgroup>
						<col style="width: 70px;">
						<col>
						<col style="width: 70px;">
						<col>
					</colgroup>

					<tbody>

						<tr>
							<td><label class="form-text" for="input-uname">이름</label></td>

							<td><input id="input-uname" type="text" name="name">
							</td>

							<td><label class="form-text" for="input-pass">패스워드</label></td>

							<td><input id="input-pass" type="password" name="password">
							</td>

						</tr>
						<tr>
							<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
						</tr>
						<tr class="button-area">
							<td colspan="4"><button type="submit">등록</button></td>
						</tr>
					</tbody>
				</table>
				<!-- //guestWrite -->
				<button type="button" id="test">모달테스트</button>
				<div id="guestbooklistArea"></div>

			</div>
		</div>
		<div class="clear"></div>

		<!-- 푸터푼리 -->
		<c:import url="/WEB-INF/views/include/footer.jsp">
		</c:import>
		<!-- 푸터푼리 -->
	</div>
	<!-- //wrap -->

	<div class="modal fade" id="delModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">삭제</h4>
				</div>
				<div class="modal-body">
					<table id="guestDelete">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 25%;">
							<col style="width: 25%;">
						</colgroup>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" id="modalPassword"></td>
						</tr>
					</table>
					<input type='text' name="number" id="modalNo">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary" id="btnDel">확인</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

</body>

<script type="text/javascript">
	$(document).ready(function() {
		//전체 방명록 리스트 불러오기
		fetchList();
	});

	$("[type='submit']").on("click", write);
	
	$("#guestbooklistArea").on("click", "a", function() {
		console.log("a링크 작동");
		//a링크 기능 막기
		event.preventDefault();
		$("#modalPassword").val("");
		var $this = $(this);
		var no = $this.data("delno");
		console.log(no);
		$('#modalNo').val(no);
		$('#delModal').modal("show");
	});
	
	$("#btnDel").on("click", function(){
		//이벤트 체크
		console.log("삭제");
		//데이터 수집
		var no = $("#modalNo").val();
		$.ajax({
			url : "${pageContext.request.contextPath }/api/guestbook/delete",
			type : "post",
			//contentType : "application/json",
			data : {
				number : no,
				password : $("#modalPassword").val()
			},
			dataType : "json",
			//이름 안같아도 되긴함
			success : function(count) {
				/*성공시 처리해야될 코드 작성*/
				console.log(count);
				if(count==1){
					$("#t-"+no).remove();
					$("#delModal").modal("hide");
				} else {
					$("#delModal").modal("hide");
				}
				/* $("#guestbooklistArea") */
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
		
		//데이터 전송 --> 그리기 작업
	});
	
	function write() {
		console.log("write실행")
		//기존 기능 사용하지 않게 하는 이벤트
		//여기서는 폼태그를 사용하면서 폼기능을 막기위해 사용
		//event.preventDefault();
		//
		var guestbookVo = {
			name : $("[name=name]").val(),
			password : $("[name=password]").val(),
			content : $("[name=content]").val()
		}
		$.ajax({
			url : "${pageContext.request.contextPath }/api/guestbook/write",
			type : "post",
			
			
			contentType : "application/json",
			
			data : JSON.stringify(guestbookVo),
			
			
			dataType : "json",
			//이름 안같아도 되긴함
			success : function(guestbookVo) {
				/*성공시 처리해야될 코드 작성*/
				render(guestbookVo, "up");

				/* $("#guestbooklistArea") */
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

		$("[name=name]").val("");
		$("[name=password]").val("");
		$("[name=content]").val("");

	}

	function fetchList() {

		$.ajax({

			url : "${pageContext.request.contextPath }/api/guestbook/list",
			type : "post",
			//contentType : "application/json",
			//data : {name: "홍길동"},
			dataType : "json",
			success : function(list) {
				/*성공시 처리해야될 코드 작성*/
				/* $("#guestbooklistArea") */
				for (var i = 0; i < list.length; i++) {
					render(list[i], "down");
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}

	function render(guestbookVo, direction) {
		var str = "";
		str += "<table id = 't-"+guestbookVo.number+"' class='guestRead'>";
		str += "<colgroup>";
		str += '<col style="width: 10%;">';
		str += '<col style="width: 40%;">';
		str += '<col style="width: 40%;">';
		str += '<col style="width: 10%;">';
		str += '</colgroup>';
		str += '<tr>';
		str += '<td>' + guestbookVo.number + '</td>';
		str += '<td>' + guestbookVo.name + '</td>';
		str += '<td>' + guestbookVo.reg_date + '</td>';
		str += '<td><a href="" data-delno='+guestbookVo.number+'>삭제</a></td>';
		str += '</tr>';
		str += '<tr>';
		str += '<td colspan=4 class="text-left">' + guestbookVo.content
				+ '</td>';
		str += '</tr>';
		str += '</table>';
		console.log(str);
		if (direction == "down") {
			$("#guestbooklistArea").append(str);
		} else if (direction == "up") {
			$("#guestbooklistArea").prepend(str);
		}
	};
</script>

</html>