<html>
<head>
<link rel="stylesheet" href="/css/main.css">

</head>

<body>
	<!-- multistep form -->

	<form id="msform" action="/personaldetails" method="get">
		<!-- progressbar -->
		<ul id="progressbar">
			<li class="active">Account Setup</li>
			<li>Social Profiles</li>
			<li>Personal Details</li>
		</ul>

		<fieldset>
			<h2 class="fs-title">Personal Details</h2>
			<h3 class="fs-subtitle">We will never sell it</h3>
			<input type="text" name="fname" placeholder="First Name" /> <input
				type="text" name="lname" placeholder="Last Name" /> <input
				type="text" name="phone" placeholder="Phone" />
			<textarea name="address" placeholder="Address"></textarea>
			<input type="button" name="previous" class="previous action-button"
				value="Previous" /> <input type="submit" name="submit"
				class="submit action-button" value="Submit" />
		</fieldset>

	</form>

</body>
</html>