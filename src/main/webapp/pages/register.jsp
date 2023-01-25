
<html>

<head>

</head>

<body bgcolor="sandybrown">

${message}

<form enctype="multipart/form-data" style="text-align: center;">
		
		Username - <input type="text" name="username" placeholder="enter user name"><br><br>
		Password - <input type="password" name="password" placeholder="enter password"><br><br>
		Email - <input type="email" name="email" placeholder="enter emailid"><br><br>
		<label>Select Photo	</label>
		<input type="file" name="images"><br><br>
		
		<input type=submit value="register" formmethod="post" formaction="savedata">
		
</form>

</body>


</html>