<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기상청 육상 중기 예보(Weather.jsp)</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main.css">

<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>
<body>

<!-- 
stnId=108	전국 
stnId=109	서울, 경기
stnId=105	강원
stnId=131	충북
stnId=133	충남
stnId=146	전북
stnId=156	전남
stnId=143	경북
stnId=159	경남
stnId=184	제주특별자치도
-->



<div class="container">

	<h2>
		기상 정보 <small>중기 예보</small>
	</h2>
	
	<div class="panel-group" role="group">
	
		<div class="panel panel-default" role="group">
			<div class="panel-heading">지역 선택</div>
			<div class="panel-body">
				<form action="" method="get" role="form">
					<input type="radio" name="stnId" value="108"> 전국     
					<input type="radio" name="stnId" value="109"> 서울, 경기 
					<input type="radio" name="stnId" value="105"> 강원     
					<input type="radio" name="stnId" value="131"> 충북     
					<input type="radio" name="stnId" value="133"> 충남     
					<input type="radio" name="stnId" value="146"> 전북     
					<input type="radio" name="stnId" value="156"> 전남     
					<input type="radio" name="stnId" value="143"> 경북     
					<input type="radio" name="stnId" value="159"> 경남     
					<input type="radio" name="stnId" value="184"> 제주특별자치도

					<button type="button" class="btn btn-default">Submit</button>
				</form>
			</div><!-- .panel-body -->
		</div><!-- .panel.panel-default -->
		<br>
		
		<div class="panel panel-default" role="group">
		
			<div class="panel-heading">기상 정보 출력</div>
			<div class="panel-body">
				<p>
					<b>서울,경기도 육상 중기예보 - 2025년 03월 19일 (수)요일 06:00 발표</b>
				</p>
				<p>
					○ (하늘상태) 이번 예보기간에는 구름많거나 흐린 날이 많겠습니다.<br />
					○ (기온) 이번 예보기간 아침 기온은 1~9도, 낮 기온은 11~21도로 평년(최저기온 -1~4도, 최고기온 11~15도)과 비슷하거나 높겠습니다.<br />
					○ (해상) 서해중부해상의 물결은 25일(화) 1.0~3.0m로 높게 일겠고, 그 밖의 날은 0.5~2.0m로 일겠습니다.<br />
					○ (주말전망) 22일(토)~23일(일)은 대체로 맑겠습니다. 아침 기온은 2~7도, 낮 기온은 13~21도가 되겠습니다.
				</p>
				
				<h3>서울</h3>
				<table class="table">
					<thead>
						<tr>
							<th>날짜</th>
							<th>날씨</th>
							<th>최저/최고 기운</th>
							<th>강수확률</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>2025-03-23 00:00</td>
							<td>맑음</td>
							<td>6 ~ 19</td>
							<td>10</td>
						</tr>
						<tr>
							<td>2025-03-23 12:00</td>
							<td>맑음</td>
							<td>6 ~ 19</td>
							<td>10</td>
						</tr>
						<tr>
							<td>2025-03-24 00:00</td>
							<td>구름많음</td>
							<td>8 ~ 18</td>
							<td>20</td>
						</tr>
						<tr>
							<td>2025-03-24 12:00</td>
							<td>맑음</td>
							<td>8 ~ 18</td>
							<td>10</td>
						</tr>
					</tbody>
				</table>
				
				<h3>인천</h3>
				<table class="table">
					<thead>
						<tr>
							<th>날짜</th>
							<th>날씨</th>
							<th>최저/최고 기운</th>
							<th>강수확률</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>2025-03-23 00:00</td>
							<td>맑음</td>
							<td>6 ~ 19</td>
							<td>10</td>
						</tr>
						<tr>
							<td>2025-03-23 12:00</td>
							<td>맑음</td>
							<td>6 ~ 19</td>
							<td>10</td>
						</tr>
						<tr>
							<td>2025-03-24 00:00</td>
							<td>구름많음</td>
							<td>8 ~ 18</td>
							<td>20</td>
						</tr>
						<tr>
							<td>2025-03-24 12:00</td>
							<td>맑음</td>
							<td>8 ~ 18</td>
							<td>10</td>
						</tr>
					</tbody>
				</table>
				
			</div>
		</div><!-- .panel.panel-default -->
		
	</div><!-- .panel-group -->

</div><!-- .container -->

</body>
</html>