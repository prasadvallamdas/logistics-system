<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>

<title>Register</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">

</head>

<body>

<div class="login-container">

<div class="login-box">

<h1>Create Account</h1>

<form>

<input type="text" placeholder="Full Name" required>

<input type="email" placeholder="Email" required>

<input type="password" placeholder="Password" required>

<input type="password" placeholder="Confirm Password" required>

<button> Register </button>

</form>

<a href="/">Back To Login</a>

</div>

</div>

</body>
</html>