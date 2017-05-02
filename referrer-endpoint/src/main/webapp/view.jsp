<!DOCTYPE HTML>
<html>
	<head>
		<title>YouConnect</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="resources/css/main.css" />

<!------------ Including jQuery Date UI with CSS --------------> 
<script src="http://code.jquery.com/jquery-1.10.2.js"></script> 
 <script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script> 
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css"> 
 <!-- jQuery Code executes on Date Format option -----> 
 <script src="js/script.js"></script>  
  
    
    <title>YouConnect
      : Login
    </title>
	<script>
    history.forward();
</script>
  
   <style>
  	.demo1 { 
  	font-size: 100px;
  	font-family: "Century Gothic", "Helvetica", sans-serif;
    font-weight: bold;
    font-weight: italic;
    text-align: center;
    color: #333;
    background-color: #666;
    text-shadow: 0px 1px 0px rgba(255,255,255,.5); /* 50% white from bottom */
}
   </style>
    </head>
<body>
		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
					<header id="header">
				
  <div class="container">
    <div class="demo1">
          <a href="/YouConnect-SocialNetworking/"> You Connect</a>
    </div>   
</div>
		</header>


				<!-- Main -->
<section id="main">

  <form accept-charset="UTF-8" action="/YouConnect-SocialNetworking/submitLogin" method="post">

<h2><font color="red"> ${headerMessage} </font></h2>
      
    <span>  <input placeholder="Email Id*" aria-required="true" id="LoginId" name="LoginId" size="30" type="text" /></span>
 

          <br><br>
      <span>    <input placeholder="Password*" aria-describedby="pw-str pw-rules" aria-required="true" autocomplete="off" class="yj-validate password wider" id="passWord" name="passWord" size="30" type="password" /><br></span>


<br>
        <button  type="submit" class="yj-btn"  ><span>Login</span></button>


</form>

</section>


</div>

			



	</body>
</html>