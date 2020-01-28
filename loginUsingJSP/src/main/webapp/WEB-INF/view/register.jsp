<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login page</title>
</head>
<body>
	<h2>register page</h2>

	<div class="container">

		<form method="get" action="/register" class="form-signin">
			<h2 class="form-heading">Log in</h2>

			<div class="form-group ">
				<span></span> <input name="newusername" type="text"
					class="form-control" placeholder="Username" autofocus="true"
					required />${exist} already exist<br> <input name="newuserpass"
					type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
					title="Six or more characters" class="form-control"
					placeholder="Password" required /><br> <span></span> <input
					name="newuserpassconfirm" type="password"
					pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
					title="Six or more characters" class="form-control"
					placeholder="Password" /> <span></span> <br>
				<input name="newusermail" type="email" class="form-control"
					placeholder="Username" autofocus="true" required /><br>
				<button class="btn btn-lg btn-primary btn-block" type="submit">register</button>
				
			</div>

		</form>

	</div>
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>