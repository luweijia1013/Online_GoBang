<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chessboard</title>
<link href="CSS/AIchessboard.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="CSS/sweet-alert.css">
</head>

<body onload="checklogin()">	
	<img hidden="hidden" id="bg" src="IMAGE/chessboard.jpg"
		alt="chessboard" />
	<div id="canvasbg">
		<div id="rightside">
		    <button id="getback"   onclick="backtomainpage()" >Return </button>
		    <button id="startgame" onclick="startgame()" >Start Game! </button>
		    <button id="restart" onclick="restart()" >Restart! </button>
		    <button id="chooseblack" onclick="choosecolor(1)" >Black! </button>
		    <button id="choosewhite" onclick="choosecolor(2)" >White! </button>
		    <div id="colorshow">
		    <p id="selected" style="display:none">&nbsp&nbspYour Color:</p>
		    <p id="unselected" style="display:block">&nbsp&nbspSelect Color:</p>	   
		    <div id="colorwordblack">&nbsp black</div>
		    <div id="colorwordwhite">&nbsp white</div>
		    </div>
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
	<script type="text/javascript"
		src="js/ai.js">
	</script>
	<script src="js/sweet-alert.min.js">
	</script> 

	
	
	<script type="text/javascript">
	var c=document.getElementById("myCanvas");
	var cxt=c.getContext("2d");
	var cxtcopy=cxt;
	var img=document.getElementById("bg");
	cxt.drawImage(img,300,250);
	</script>
	<script type="text/javascript">
	var boardheight=250;
	var boardwidth=300;
	var color=0;/* black=1 & white=2 */
	var iswin=0;/* whether there is someone won */
	var ismyturn=0;/* a boolean value whether I can put piece now */
	var isstarted=0;
	var turns=0;
	var lastx=0;
	var lasty=0;
	var array=[]; 
	for(var i=0;i<15;i++){
		array[i]=[];
	for(var j=0;j<15;j++){
		array[i][j]=0;
		}
	}
	</script>
	<script type="text/javascript">
		/* mention to click two button before game */
		function mention(){
			if(color==0){
				swal("Please Choose the color!");
				return;
			}
			if(isstarted==0){
				swal("Please Press \"Start Game\"! ");
				return;
			}
			
		}
	
		/* css logic of color choose */
	 	function choosecolor(co){
	 		if(co==1) {
	 			color=1;
	 			var csb=document.getElementById("colorwordblack");
	 			csb.style.display="block";
	 		}
	 		if(co==2) {
	 			color=2;
	 			var csw=document.getElementById("colorwordwhite");
	 			csw.style.display="block";
	 		}
	 		var us=document.getElementById("unselected");
			us.style.display="none";
	 		var s=document.getElementById("selected");
			s.style.display="block";
			var cb=document.getElementById("chooseblack");
			cb.style.display="none";
			var cw=document.getElementById("choosewhite");
			cw.style.display="none";
			var st=document.getElementById("startgame");
			st.style.display="block";
		}
	
		/* initiation of the game */
		function startgame(){
			isstarted=1;
			if (color==1) {
				ismyturn = 1;
			} else {
				ismyturn = 0;
				AIclick(1);
			}
			var st=document.getElementById("startgame");
			st.style.display="none";
			var rst=document.getElementById("restart");
			rst.style.display="block";
		}

		/* the logic of I make the piece */
		function chessclick(e) {
			if(isstarted==0||color==0){
				mention();
				return;
			}
			if (ismyturn == 0) {
				return;
			}
			x = e.offsetX;
			y = e.offsetY;
			i = Math.floor((x - boardwidth-2.5) / 35);     
			j = Math.floor((y - boardheight+2.5) / 35);
			if (i<=14&&i> - 1 && j<=14&&j> - 1) {
				if (array[i][j] == 0) {
					drawchesspieces(i, j, color);
					if (turns!=0){
						drawmentiondot(lastx,lasty,3-color);
					}
					drawmentiondot(i,j,0);
					array[i][j]=color;
					lastx=i;
					lasty=j;
					turns++;
					ismyturn = 0;
					if((iswin=judge(i, j, color))!=0){
						return;
					}
					AIclick(0);
				}

			}
		}
		
		/* AI make the piece */
		function AIclick(IsFirst) {
			var AIx;
			var AIy;
			var AIcolor=3-color;
			var situationcode="";
			if(IsFirst==1){
				AIx=Math.floor(Math.random()*3+6);
				AIy=Math.floor(Math.random()*3+6);
				drawchesspieces(AIx, AIy, AIcolor);
				drawmentiondot(AIx,AIy,0);
				array[AIx][AIy] = AIcolor;
				lastx=AIx;
				lasty=AIy;
				turns++;
				ismyturn = 1;
				return;
			}
			for(var m=3;m<12;m++){
				for(var n=3;n<12;n++){
					situationcode+=array[n][m];
				}
			}
			$.post('waitAIclick',{"AIcolor":AIcolor,"situationcode":situationcode},function(data,status){
				json = eval("(" + data + ")");
				if(json.x!=0){
					AIx=json.x;
					AIy=json.y;
				}
				else{
					var result=AIalgo();
					AIx=result[0];
					AIy=result[1];
				}
				drawchesspieces(AIx, AIy, AIcolor);
				drawmentiondot(lastx,lasty,3-AIcolor);
				drawmentiondot(AIx,AIy,0);
				array[AIx][AIy] = AIcolor;
				iswin=judge(AIx, AIy, AIcolor);
				if(iswin!=0){
					/* end game logic here */
					return;
				}/* End here if enemy win */
				lastx=AIx;
				lasty=AIy;
				turns++; //should be put before judge?
				ismyturn = 1;
			});
		}

		
		
		
		/* function:drawchesspiece(i,j,color) */
		/* usage:draw the chess piece */
		/* parameter: */
		/* i: x cordinate of the point */
		/* j: y cordinate of the point */
		/* color: 1 means draw black chess, 2 means draw white chess */
		function drawchesspieces(i, j, color) {
			if (color == 1)
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
			
			if ((left + right) >= 4 || (leftup + rightdown) >= 4
					|| (leftdown + rightup) >= 4 || (up + down) >= 4) {
				if (judgecolor == 1) {
					swal("Black win!")
					iswin=1;
				}
				if (judgecolor == 2) {
					swal("White win!")
					iswin=2;
				}
			
			}
			return iswin;
		}

		
		
		function backtomainpage(){
			window.location="loginsuccess.html";
		}
		function restart(){
			window.location="startgamewithAI";
		}
		
		/* checklogin() is used to check whether a user is visiting page without login or not through "vs computer" button */
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