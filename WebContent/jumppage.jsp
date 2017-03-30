<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jump Page</title>
<link rel="stylesheet" type="text/css" href="CSS/sweet-alert.css">
</head>
 <%String mentioncontent = (String) session.getAttribute("mentioncontent");%>
<body onload="checkmention()">
<script src="js/sweet-alert.min.js">
	</script> 
	<script src="js/jquery.countdown.min.js">
	</script> 
	
	<div id="getting-started"></div>
<script type="text/javascript">
  $("#getting-started")
  .countdown("2017/01/01", function(event) {
    $(this).text(
      event.strftime('%D days %H:%M:%S')
    );
  });
</script>

<script type="text/javascript">
/* read the mention information(mostly failure information) to decide which information hint should be shown on screen */
function checkmention(){
	var mc="<%=mentioncontent%>";
	/* session doesn't have login information */
		if(mc=="outoftime"){
			swal({title:"Out of time!",
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "ReLogin"
			}, function(){   
				window.location="login"; 
			}
			);
		}
	/* not quailified to pp fight mode */
		if(mc=="notinpp"){
			swal({title:"You are not allowed for the game!",
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "Back"
			}, function(){   
				window.location="loginsuccess.html"; 
			}
			);
		}
	/* username or password is wrong */
		if(mc=="wrongname"||mc=="wrongpwd"){
			swal({title:"Either your username or password is wrong!",
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "ReLogin"
			}, function(){   
				window.location="login"; 
			}
			);
		}
	/* username used to register has been registered by other person */
		if(mc=="repeatregister"){
			swal({title:"Sorry!\nThis username has been used.\nPlease change your username.",
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "ReRegister"
			}, function(){   
				window.location="login#toregister"; 
			}
			);
		}
	/* username or password exceeds length limit which is 12 chars */
		if(mc=="pwdorusnametoolong"){
			swal({title:"Sorry!\nYour username or password\nexceeds length limit.\nPlease make sure its length won't exceed 12.",
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "ReRegister"
			}, function(){   
				window.location="login#toregister"; 
			}
			);
		}
	/* password contains invalid char  */
		if(mc=="invalidchar"){
			swal({title:"Sorry!\nYour password is invalid.\nPlease make sure your password only consists of 0~9,a~Z,!,_,~",
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "ReRegister"
			}, function(){   
				window.location="login#toregister"; 
			}
			);
		}
	/* password and confirm-password is not equivalent */
		if(mc=="twopwddifferent"){
			swal({title:"Sorry!\nYour password and \nthe confirmed one\nare different.",
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "ReRegister"
			}, function(){   
				window.location="login#toregister"; 
			}
			);
		}
	/* age is not valid */
		if(mc=="invalidage"){
			swal({title:"Sorry!\nYour age is invalid!",
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "ReRegister"
			}, function(){   
				window.location="login#toregister"; 
			}
			);
		}
	/* register success */
		if(mc=="successregister"){
			swal({title:"Register success! ",
				confirmButtonColor: "#007FFF",
				confirmButtonText: "Login here"
			}, function(){   
				window.location="login"; 
			}
			);
		}
}
</script>
</body>
</html>