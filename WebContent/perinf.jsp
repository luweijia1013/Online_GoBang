<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
<head>
<title>My Information</title>
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="My Information" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //for-mobile-apps -->
<link href="CSS/perinfcss/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="CSS/perinfcss/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- js -->
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<!-- //js -->
<!-- fonts -->
<link href='//fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Acme' rel='stylesheet' type='text/css'><!-- //fonts -->

	<!-- start-smoth-scrolling -->
		<script type="text/javascript" src="js/move-top.js"></script>
		<script type="text/javascript" src="js/easing.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
				});
			});
		</script>
	<!-- start-smoth-scrolling -->

<!-- skills -->
<script src="js/pie-chart.js" type="text/javascript"></script>
 <script type="text/javascript">

        $(document).ready(function () {
            $('#demo-pie-1').pieChart({
                barColor: '#10A7AF',
                trackColor: '#fff',
                lineCap: 'round',
                lineWidth: 8,
                onStep: function (from, to, percent) {
                    $(this.element).find('.pie-value').text(Math.round(percent) + '%');
                }
            });

            $('#demo-pie-2').pieChart({
                barColor: '#10A7AF',
                trackColor: '#fff',
                lineCap: 'butt',
                lineWidth: 8,
                onStep: function (from, to, percent) {
                    $(this.element).find('.pie-value').text(Math.round(percent) + '%');
                }
            });

            $('#demo-pie-3').pieChart({
                barColor: '#10A7AF',
                trackColor: '#fff',
                lineCap: 'square',
                lineWidth: 8,
                onStep: function (from, to, percent) {
                    $(this.element).find('.pie-value').text(Math.round(percent) + '%');
                }
            });
			
			$('#demo-pie-4').pieChart({
                barColor: '#10A7AF',
                trackColor: '#fff',
                lineCap: 'square',
                lineWidth: 8,
                onStep: function (from, to, percent) {
                    $(this.element).find('.pie-value').text(Math.round(percent) + '%');
                }
            });
        });
    </script>
<!-- skills -->
<script type="text/javascript" src="js/numscroller-1.0.js"></script>

</head>
<%String myname = (String) session.getAttribute("myname");%>
<%Integer myID = (Integer) session.getAttribute("myID");%>
<%String myrole = (String) session.getAttribute("myroles");%>
<%Integer myage = (Integer) session.getAttribute("myage");%>
<body onload="checklogin()">
<!-- banner -->
<div class="header-top">
	<div class="container">
		<ul>
			<li><a href="loginsuccess.html"><span class="glyphicon glyphicon-cloud" aria-hidden="true"></span>Index Page</a></li>
			<li><a href="logout"></span>Log Out</a></li>
		</ul>
	</div>
</div>	
<div class="header">
	<div class="container">
		<div class="col-md-8 header-left">
			<div class="col-sm-5 pro-pic">
				<img class="img-responsive" src="IMAGE/head.jpg" alt=" "/>
			</div>
			<div class="col-sm-5 pro-text">
				<h1><%=myname%></h1>
				<p>Gobang Player</p>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="col-md-4 header-right ">
			<ul class="list-left">
				<li>ID:</li>
				<li>Age:</li>
				<li>Role:</li>
				<li>Score: </li>
			</ul>
			<ul class="list-right">
				<li><%=myID%></li>
				<li><%=myage%></li>
				<li><%=myrole%></li>
				<li> To be continued.</li>
			</ul>
			<div class="clearfix"></div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
<!-- //banner -->
<script type="text/javascript">
/* checklogin() is used to check whether a user is visiting page without login */
function checklogin(){
	$.post('checklogin',{"check":1},function(data,status){
		json = eval("(" + data + ")");
		if(json.login==0){
			window.location="fail";
		}
	}
	);
}
</script>
</body>
</html>
