<!DOCTYPE HTML>
<html>
	<head>
		<title>MyReferrer</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="resources/css/main.css" />
  
    
    <title>Myreferrer Service
    </title>
	<script>
	function urlPost(){
		var url = "/referrer-endpoint/webapi/referrer/url";
		var domain = document.getElementById("domain").value;
		var client = new XMLHttpRequest();
		if(domain==null || domain==""){
			
			alert("URL cannot be empty");
			return;
		}
		domain = "{".concat("\"domain\"").concat(":\"").concat(domain).concat("\"}");
		

		client.open("POST", url, false);

		client.setRequestHeader("Content-Type", "application/json");

		client.send(domain);

		if (client.status == 200){
			alert("Request successful!")
			document.getElementById("response").value = client.responseText;
			
		}
		    
		   
	}
	
	
	function topGet(){
		var url = "/referrer-endpoint/webapi/referrer/top";
		//var domain = document.getElementById("domain").value;
		//domain = "{".concat("\"domain\"").concat(":\"").concat(domain).concat("\"}");
		var client = new XMLHttpRequest();

		client.open("GET", url, false);

		client.setRequestHeader("Content-Type", "application/json");

		client.send(domain);

		if (client.status == 200){
			alert("Request successful!")
			document.getElementById("response").value = client.responseText;
			
		}
		    
		   
	}

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
          <a href="/webapi/"> My Referrer URL service</a>
    </div>   
</div>
		</header>


				<!-- Main -->
<section id="main">

  <form name="urlform"  accept-charset="UTF-8" action="javascript:void(0);" method="post">
      <input placeholder="Response" type="text" id="response" name="response" readonly/>
    <span>  <input placeholder="URL *" aria-required="true" id="domain" name="domain" size="30" type="text" /></span>
 

          <br><br>

        <button  type="submit" class="yj-btn"  onclick="urlPost()" ><span>Send</span></button>


</form>

  <form accept-charset="UTF-8" action="javascript:void(0);" method="get">
      

        <button  type="submit" class="yj-btn" onclick="topGet()" ><span>Get Trending URL</span></button>


</form>

</section>


</div>

			



	</body>
</html>