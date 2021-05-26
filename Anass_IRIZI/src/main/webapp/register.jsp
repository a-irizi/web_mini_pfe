<jsp:include page="header_disconnected.jsp"></jsp:include>

<div class="container mt-5">
	<form method="post" action="Registration">
		<div class="row col-12  ">

			<div class="col-md-4 offset-2">
				<div class="form-group">
					<label for="nomInput" class="form-label">Nom</label> <input
						type="text" class="form-control" id="nomInput" name="nom">
				</div>
			</div>

			<div class="col-md-4">
				<div class=" form-group">
					<label for="prenomInput" class="form-label">Prenom</label> <input
						type="text" class="form-control" id="prenomInput" name="prenom">
				</div>
			</div>
		</div>

		<div class="row col-12">

			<div class="col-8 offset-2">


				<div class="form-group">
					<div class="mb-3">
						<label for="loginInput" class="form-label"> Login</label> <input
							type="text" class="form-control" id="loginInput"
							aria-describedby="emailHelp" name="login">
					</div>
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1" class="form-label">Password</label>
					<input type="password" class="form-control"
						id="exampleInputPassword1" name="password">
				</div>


			</div>
		</div>

		<div class="row col-12">

			<div class="col-8 offset-2 mt-3	">
				<div class="form-group">

					<label for="Filiere" class="form-label">Selectionner la
						Filere</label> <select class="form-select" id="Filiere"
						aria-label="Default select example" name="filiere">
						<option value="sma">SMA - Science Math Appliquer</option>
						<option value="smi">SMI - Science Math Informatique</option>
						<option value="smp">SMP - Science Math Physique</option>
					</select>
				</div>

			</div>

		</div>
		<div class="col-8 offset-2">
			<button type="submit" class="btn btn-primary mt-3">Submit</button>
		</div>
		<div class="col-8 offset-2 mt-3">
			Vous avez déjà un compte? <b><a href="connect.jsp"
				class="link-primary"> se connecter</a></b>
		</div>

	</form>
</div>




</body>
</html>