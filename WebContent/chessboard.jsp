<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chessboard</title>
<link href="CSS/chessboardcss/chessboard.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="CSS/sweet-alert.css">
<link type="text/css" rel="stylesheet" href="CSS/chessboardcss/countdown.css" />
</head>
 <%String mycolor = (String) session.getAttribute("mycolor");%>
<body onload="checklogin()">	
	<img hidden="hidden" id="bg" src="IMAGE/chessboard.jpg"
		alt="chessboard" />
	<div id="canvasbg">
		<div id="rightside">
		    <button id="getback"   onclick="backtomainpage()" >Return </button>
		    <button id="giveup" onclick="confirmgiveup()" >Give Up! </button>
		    <div id="colorshow">
		    <p>&nbsp&nbspYour Color:</p>	   
		    <p id="colorword">&nbsp <%=mycolor%></p>
		    </div>
		</div>
		<div class="game_time" id="countdown">
			<div class="hold">
				<div class="pie pie1"></div>
			</div>
			<div class="hold">
				<div class="pie pie2"></div>
			</div>
			<div class="bg"> </div>
			<div class="time"></div>
		</div>
		<div id="canvasdiv" >
			<canvas id="myCanvas" width="1000" height="1000" align="center"
		 		onclick="chessclick(event)">
			</canvas>
		</div>
		
	</div>
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-1.6.min.js">
	</script>
	<script type="text/javascript"
		src="http://www.jeasyui.net/Public/js/easyui/jquery.easyui.min.js">
	</script>
	<script src="js/sweet-alert.min.js">
	</script> 
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/countdown.js"></script>


	
	<script type="text/javascript">
	 	function getbackdisplay(){
	 		var count=document.getElementById("countdown");
			count.style.display="none";
			var getback=document.getElementById("getback");
			getback.style.display="block";
			var giveup=document.getElementById("giveup");
			giveup.style.display="none";
		}
	</script>
	<script type="text/javascript">
	var c=document.getElementById("myCanvas");
	var cxt=c.getContext("2d");
	var img=document.getElementById("bg");
	cxt.drawImage(img,300,250);
	</script>
	<script type="text/javascript">
	var boardheight=250;
	var boardwidth=300;
	var color;/* black=1 & white=2 */
	var iswin=0;/* whether there is someone has won */
	var ismyturn;/* a boolean value whether I can put piece now */
	var waitnum;/* the number of piece now waited */
	var secondlimit=60;/* one piece should be done in 60s */
	var isclick=0;
	var clickcount=1;
	var totalcount=1;
	var turns=0;
	var lastx=-1;
	var lasty=-1;
	var array=[]; 
	for(var i=0;i<15;i++){
		array[i]=[];
	for(var j=0;j<15;j++){
		array[i][j]=0;
		}
	}
	</script>
	<script type="text/javascript">
		/* initiaion of the game */
		function startgame(){
			var co="<%=mycolor%>";
			/* Some friendly mention GUI should be here to replace alert() */
			if (co == "black") {
				color = 1;
				ismyturn = 1;
				waitnum = 2;
				countDown();
				setInterval("checklimit()",1000);
			} else {
				color = 2;
				ismyturn = 0;
				waitnum = 1;
				countDown();
				rivalclick(1);
			}
		}
	
		/* logic of the piece i make */
		function chessclick(e) {
			if (ismyturn == 0) {
				return;
			}	
			x = e.offsetX;
			y = e.offsetY;
			i = Math.floor((x - boardwidth-2.5) / 35);     
			j = Math.floor((y - boardheight+2.5) / 35);
			if (i<=14&&i> - 1 && j<=14&&j> - 1) {
				if (array[i][j] == 0) {
					isclick=1;
					clickcount++;
					totalcount++;
					drawchesspieces(i, j, color);
					drawmentiondot(lastx,lasty,3-color);
					drawmentiondot(i,j,0);
					array[i][j] = color;
					lastx=i;
					lasty=j;
					turns++;
					ismyturn = 0;
					countDown();
					$.post('storemypiece', {"x_cordinate":i,"y_cordinate":j},function(data, status) {/* countDown(); */rivalclick(0);});
				}

			}
		}
		
		/* get the piece rival click */
		function rivalclick(Isfirst) {
			var win;
			if(Isfirst==0){
				win=judge(i, j, color);
				if(win!=0){
					swal("You win!");
					return;
				}/* End here if I win */
			}
			$.post('waitrivalclick',{"wait_num":waitnum},function(data,status){
				if(iswin!=0) return;
				json = eval("(" + data + ")");
				if(json.x==-1){   //overtime or give up
						swal("You win! Rival overtime or give up!");
						iswin=color;
					getbackdisplay();
					$.post('setwinner', {"winner_num" : iswin});
					return;
				}
				secondlimit=60*clickcount;/* one piece should be done in 60s, clickcount is used to deal with repeatedly operation of setInterval()*/
				isclick=0;
				totalcount++;
				drawchesspieces(json.x, json.y, 3-color);
				drawmentiondot(lastx,lasty,color);
				drawmentiondot(json.x,json.y,0);
				countDown();
				array[json.x][json.y] = 3-color;
				win=judge(json.x, json.y, 3-color);
				if(win!=0){
					swal("You Lose!");
					return;
				}/* End here if enemy win */
				waitnum += 2;
				lastx=json.x;
				lasty=json.y;
				turns++;
				ismyturn = 1;
				setInterval("checklimit()",1000);
			});	
		}
		
		/* check whether user didn't make a piece in time limit(60s) */
		function checklimit(){
			if(isclick==1) return;
			secondlimit=secondlimit-1;
			if(secondlimit==0){
				swal({title:"You Lose!",
					text:"You didn't make a piece in 1 minute!",
					confirmButtonColor: "#DD6B55",
					confirmButtonText: "Ok"
				}
				);
				giveup();
			}
		}
		
		/* confirm whether user really wants to give up */
		function confirmgiveup(){
			if(iswin!=0) return;
			swal({
				title: "Give up?",
				text: "You will lose the game if giving up!",   
				 imageUrl: "IMAGE/warning.jpg",  
				showCancelButton: true,   
				confirmButtonColor: "#DD6B55",   
				confirmButtonText: "Confirm!" }, 
				function(){giveup();});
		}
		
		/* end logic after confirming giving up */
		function giveup(){
			ismyturn=0;
			iswin=3-color;
			$.post('storemypiece', {"x_cordinate":-1,"y_cordinate":-1});
			getbackdisplay();
		}
		
		/* function:drawchesspiece(i,j,color) */
		/* usage:draw the chess piece */
		/* parameter: */
		/* i: x cordinate of the point */
		/* j: y cordinate of the point */
		/* color: 1 means draw black chess, 2 means draw white chess */
		function drawchesspieces(i, j, color) {
			if (color == 1)//black
				cxt.fillStyle = "#000000";
			else
				cxt.fillStyle = "#ffffff";
			cxt.beginPath();
			cxt.arc(boardwidth+20 + 35 * i, boardheight+15 + 35 * j, 12, 0, Math.PI * 2, true);
			cxt.closePath();
			cxt.fill();
		}
		
		/* function:drawmentiondot(i,j,type) */
		/* usage:draw the red point and erase the last red one to highlight the latest chess piece */
		/* parameter: */
		/* i: x cordinate of the point */
		/* j: y cordinate of the point */
		/* type: 0 means draw red point, not 0 means the color used to erase */
		function drawmentiondot(i,j,type){
			if(i<0) return; //means it's the first piece
			if(type==0){
				cxt.fillStyle= "#ff0000";
				cxt.beginPath();
				cxt.arc(boardwidth+20 + 35 * i, boardheight+15 + 35 * j, 2.5, 0, Math.PI * 2, true);
			}	
			else if(type==1){
				cxt.fillStyle= "#000000";
				cxt.beginPath();
				cxt.arc(boardwidth+20 + 35 * i, boardheight+15 + 35 * j, 3, 0, Math.PI * 2, true);
			}	
			else if(type==2){
				cxt.fillStyle = "#ffffff";
				cxt.beginPath();
				cxt.arc(boardwidth+20 + 35 * i, boardheight+15 + 35 * j, 3, 0, Math.PI * 2, true);
			}
		
			cxt.closePath();
			cxt.fill();
		}
		
		
		/* judge whether "color" one has won the game */
		/* parameter: */
		/* i:x_cordinate of the now piece */
		/* j:y_cordinate of the now piece */
		/* color:color of the now piece */
		function judge(i, j, color) {
			var judgecolor = 0;
			if (color ==1) {
				judgecolor = 1;
			} else
				judgecolor = 2;
			var left = 0;
			var right = 0;
			var up = 0;
			var down = 0;
			var leftup = 0;
			var leftdown = 0;
			var rightup = 0;
			var rightdown = 0;
			for (var m = i; m > 0; m--) {
				if (array[m - 1][j] == judgecolor)
					left++;
				else
					break;
			}
			for (var m = i; m < 14; m++) {
				if (array[m + 1][j] == judgecolor)
					right++;
				else
					break;
			}
			for (var m = j; m > 0; m--) {
				if (array[i][m - 1] == judgecolor)
					up++;
				else
					break;
			}
			for (var m = j; m < 14; m++) {
				if (array[i][m + 1] == judgecolor)
					down++;
				else
					break;
			}
			for (var m = 1; m < Math.min(i, j) + 1; m++) {
				if (array[i - m][j - m] == judgecolor)
					leftup++;
				else
					break;
			}
			for (var m = 1; m < Math.min(14 - i, j) + 1; m++) {
				if (array[i + m][j - m] == judgecolor)
					rightup++;
				else
					break;
			}
			for (var m = 1; m < Math.min(i, 14 - j) + 1; m++) {
				if (array[i - m][j + m] == judgecolor)
					leftdown++;
				else
					break;
			}
			for (var m = 1; m < Math.min(14 - i, 14 - j) + 1; m++) {
				if (array[i + m][j + m] == judgecolor)
					rightdown++;
				else
					break;
			}
			
			if ((left + right) == 4 || (leftup + rightdown) == 4
					|| (leftdown + rightup) == 4 || (up + down) == 4) {
				iswin=judgecolor;
				getbackdisplay();
				$.post('setwinner', {"winner_num" : iswin});
			}
			return iswin;
		}

		
		/* return index page */
		function backtomainpage(){
			if(iswin!=0){
				window.location="loginsuccess.html";
			}
		}

		/* checklogin() is used to check whether a user is visiting page without login or not through "vs computer" button */
		function checklogin(){
			$.post('checklogin',{"check":1},function(data,status){
				json = eval("(" + data + ")");
				if(json.login==0){
					window.location="fail";
					return;
				}
				$.post('checkcolor',{"check":2},function(data,status){
					json = eval("(" + data + ")");
					if(json.color==0){
						window.location="fail";
					}
					startgame();
				}
				);
			}
			);
		}
</script>
</body>
</html>