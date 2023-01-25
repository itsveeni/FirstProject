<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>

   span
   {
      font-size:30px;
      color : black;
   }

</style>
</head>
<body bgcolor="CDEE83">


	<form name="login" style="text-align: center;" >
		<fieldset>
			<legend>Login Details</legend>
			Username - <input type="text" name="username"> <br> <br>
			Password - <input type="password" name="password"> <br>
			<br> <input type="submit" value="login" formmethod="post" formaction="validate"> 
				<input type="submit" value="register" formmethod="post" formaction="showregister">
		</fieldset>

		<span>${message}</span>
	</form>

	



</body>
</html>