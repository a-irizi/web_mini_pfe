<jsp:include page="header_disconnected.jsp"></jsp:include>

<div class="container mt-5">
	<div class="row clo-12">

		<form method="post" action="Connection">
			<div class="col-8 offset-2">

				<div class="form-group">
					<div class="mb-3">
						<label for="loginInput" class="form-label"> Login</label> <input
							type="text" class="form-control" id="loginInput" name="login"
							aria-describedby="emailHelp">
					</div>
				</div>
			</div>
			<div class="col-8 offset-2">

				<div class="form-group">
					<label for="exampleInputPassword1" class="form-label">Password</label>
					<input type="password" class="form-control" name="password"
						id="exampleInputPassword1">
				</div>
			</div>
			<div class="col-8 offset-2">

				<button type="submit" class="btn btn-primary mt-3">Submit</button>
			</div>
		</form>

		<div class="col-8 offset-2 mt-3">
			Voud n'avez pas de compte? <b><a href="register.jsp"
				class="link-primary">s'authentifier</a></b>
		</div>
	</div>

</div>



</body>
</html>