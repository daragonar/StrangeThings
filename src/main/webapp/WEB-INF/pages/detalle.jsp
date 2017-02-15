<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<h2 class="text-center">${pelicula.nombrePel}</h2>
			<div class="col-md-3">
				<img src="${pelicula.caratula}" alt="${pelicula.caratula}"
					class="pull-left" style="max-height: 180px">
			</div>
			<div class="col-md-6">
				<p class="text-center">${pelicula.descripcionPel}</p>
				<sec:authentication var="user" property="principal" />

				<a href='<c:url value="/peliculas/alq/${pelicula.idPel}-${user.idPer}"></c:url>'>
					Alquilar ${user.username} </a>
			</div>
			<div class="col-md-3">
				<h3>Generos</h3>
				<ul>
					<c:forEach items="${pelicula.generos}" var="gen">
						<li>${gen.nombreGen}</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<hr>
		<div class="row">
			<script>
				var id = youtube_parser("${pelicula.trailer}");

				function youtube_parser(url) {
					var regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=)([^#\&\?]*).*/;
					var match = url.match(regExp);
					if (match && match[2].length == 11) {
						return match[2];
					} else {
						//error
					}
				}
				var url = "https://www.youtube.com/embed/" + id;
				$(document).ready(function() {
					$("#youtube").attr('src', url);
				})
			</script>
			<div class="col-md-6">
				<iframe class="col-md-12" id="youtube" src=" " frameborder="0"
					allowfullscreen></iframe>
			</div>
			<sec:authorize access="hasRole('ADMIN')">
				<div class="col-md-6">
					<h4>Historico de alquileres</h4>
					<ul>
						<c:forEach items="${pelicula.personas}" var="persona">
							<li>${persona.username}</li>
						</c:forEach>
					</ul>
				</div>
			</sec:authorize>

		</div>
	</div>
</body>
</html>