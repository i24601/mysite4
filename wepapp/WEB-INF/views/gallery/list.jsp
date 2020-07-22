<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<c:import url="/WEB-INF/views/include/nav.jsp"></c:import>
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/galleryAside.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">
			
						<c:if test="${sessionScope.authUser ne null}">						
							<button id="btnImgUpload">이미지올리기</button>
						</c:if>

						<div class="clear"></div>

			
			
			
					<ul id="viewArea">
						
						<c:forEach items="${gList}" var="list">
						<!-- 이미지반복영역 -->
							<li data-no="${list.no}">
								<div class="view">
									<img class="imgItem" src="${pageContext.request.contextPath }/gallery/${list.saveName}">
									<div class="imgWriter">작성자: <strong>${list.name}</strong></div>
								</div>
							</li>
						<!-- 이미지반복영역 -->
						</c:forEach>
						
					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	
		
	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				
				<form method="post" action="${pageContext.request.contextPath}/gallery/upload" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label>
							<input id="addModalContent" type="text" name="content" value="" >
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label>
							<input id="file" type="file" name="file" value="" >
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>
				
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	


	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">
					
					<div class="formgroup" >
						<img id="viewModelImg" src =""> <!-- ajax로 처리 : 이미지출력 위치-->
					</div>
					
					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>
					
				</div>
				<form method="" action="">
					<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-danger" id="btnDel" value="">삭제</button>
				</div>
				
					<%-- <input type="text" id="btnUno" value="${sessionScope.authUser.no}"> --%>
				
				
				
				</form>
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	


</body>

<script type="text/javascript">
$("#btnImgUpload").on("click", function(){
	$('#addModal').modal("show");
});

$("#viewArea>li").on("click", function() {
	event.preventDefault();
	
	$("#btnDel").hide();
	var user_no = $("#btnUno").val();
	var no = $(this).data("no");
	
	console.log("여기서");
	console.log(no);
	console.log(user_no);

    $.ajax({
		url : "${pageContext.request.contextPath }/api/gallery/read",
		type : "post",
		//contentType : "application/json",
		//파라미터로 보내는것
		data : {no : no},
		//json으로 보내는것
		/* data : JSON.stringify(no), */
		

		
		//받는것
		dataType : "json",
		//이름 안같아도 되긴함
		success : function(gVo) {
			/*성공시 처리해야될 코드 작성*/
			console.log("받는거성공");
			console.log(gVo);
			$("#viewModelImg").attr("src", gVo.saveName);
		    $("#viewModelContent").text(gVo.content);
		    $("[name='no']").val(no);
		    
		    //서버는 jsp에서 el jstl 우선시해서 문자로 바꿔서 클라이언트로 보낸다
		    //즉 js 내부에서도 el jstl 사용가능
		    //세션을 받는 jstl을 ""로 묶어주면 로그아웃상태에서도 null이 아닌 ""이 입력되기 때문에 에러가 나지않는다
		    if("${sessionScope.authUser.no}"==gVo.user_no){
		    	$("#btnDel").show();
		    	$("#btnDel").val(gVo.no);
		    	$("#btnUno").val
		    }
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	$('#viewModal').modal("show");
});

$("#btnDel").on("click", function(){
	var no = $("#btnDel").val();
	 $.ajax({
			url : "${pageContext.request.contextPath }/api/gallery/delete",
			type : "post",
			//contentType : "application/json",
			//파라미터로 보내는것
			data : {no : no},
			//json으로 보내는것
			/* data : JSON.stringify(no), */
			

			
			//받는것
			dataType : "json",
			//이름 안같아도 되긴함
			success : function(result) {
				$('#viewModal').modal("hide");
				/*성공시 처리해야될 코드 작성*/
				console.log("지우는거성공");
				console.log(result+"건 처리")
				$("#viewArea>li[data-no="+no+"]").remove();
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
});


</script>




</html>

