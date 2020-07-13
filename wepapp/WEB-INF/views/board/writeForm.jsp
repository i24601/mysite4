<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

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

		<c:import url="/WEB-INF/views/include/aSideUser.jsp">
		</c:import>

		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>게시판</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>게시판</li>
						<li class="last">일반게시판</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
				<div id="writeForm">
					<form action="${pageContext.request.contextPath}/board/write" method="get">
						<!-- 제목 -->
						<div class="form-group">
							<label class="form-text" for="txt-title">제목</label>
							<input type="text" id="txt-title" name="title" value="" placeholder="제목을 입력해 주세요">
						</div>
					
						<!-- 내용 -->
						<div class="form-group">
							<textarea id="txt-content" name="content"></textarea>
						</div>
						
						<a id="btn_cancel" href="${pageContext.request.contextPath}/board/list?page=${param.page}&str=${param.str}&no=${param.no}">취소</a>
						<button id="btn_add" type="submit" >등록</button>
						<input type="hidden" name="page" value="1">
						<c:if test="${param.no ne null}">
						<input type="hidden" name="order_no" value="${param.order_no}">
						</c:if>
						<!-- no없으면 setter가 동작을 안해서 400에러 남.. -->
						<c:if test="${param.no ne null}">
							<input type="text" name="no" value="${param.no}">							
						</c:if>
						<c:if test="${param.depth ne null}">
							<input type="text" name="depth" value="${param.depth}">							
						</c:if>
						
						<c:if test="${param.depth ne null}">						
							<input type="text" name="group_no" value="${param.group_no}">							
						</c:if>
							
					</form>
	                <!-- //form -->
				</div>
				<!-- //writeForm -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<!-- 푸터푼리 -->
		<c:import url="/WEB-INF/views/include/footer.jsp">
		</c:import>
		<!-- 푸터푼리 -->
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>
