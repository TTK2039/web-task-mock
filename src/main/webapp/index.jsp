<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body class="login_body">
	<div class="header">
		<h1 class="site_logo">商品管理システム</h1>
	</div>

	<div class="login_form">
		<img src="./images/logo.png" class="login_logo">
		<p class="error">${msg }</p>

		<form action="login" method="post">
			<fieldset>
				<div class="cp_iptxt">
					<input class="base_input" type="text" name="loginId"
						placeholder="ID"> <i class="fa fa-user fa-lg fa-fw"
						aria-hidden="true"></i>
					<div class="error">${requestScope.idError}</div>
				</div>

				<div>
					<input class="base_input" type="password" name="pass"
						placeholder="PASS">
					<div class="error">${requestScope.passError}</div>
				</div>
			</fieldset>
			<button class="logout_btn" type="submit" name="btn" value="login">ログイン</button>
		</form>
	</div>
</body>
</html>
