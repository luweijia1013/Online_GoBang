ii = 0;
jj = 0;
count = 0;
MM = 0;
SS = 60;  // 秒 90s
MS = 0;
totle = (MM+1)*600;
d = 180*(MM+1);
MM = "0" + MM;
var gameTime = 60;
//count down
var showTime = function(){
    totle = totle - 1;
    if (totle == 0) {
        clearInterval(s);
        clearInterval(t1);
        clearInterval(t2);
        $(".pie2").css("-o-transform", "rotate(" + d + "deg)");
        $(".pie2").css("-moz-transform", "rotate(" + d + "deg)");
        $(".pie2").css("-webkit-transform", "rotate(" + d + "deg)");
    } else {
        if (totle > 0 && MS > 0) {
            MS = MS - 1;
            if (MS < 10) {
                MS = "0" + MS
            }
            ;
        }
        ;
        if (MS == 0 && SS > 0) {
            MS = 10;
            SS = SS - 1;
            if (SS < 10) {
                SS = "0" + SS
            }
            ;
        }
        ;
        if (SS == 0 && MM > 0) {
            SS = 60;
            MM = MM - 1;
            if (MM < 10) {
                MM = "0" + MM
            }
            ;
        }
        ;
    }
    ;
    $(".time").html(Math.floor(SS/totalcount) + "s");
	
	
	
	
	
	
	
	
	
	
	
};

var start1 = function(){
	//ii = ii + 0.6;
	ii = ii + 360/((gameTime)*10*totalcount);  //旋转的角度  90s 为 0.4  60s为0.6
	count = count + 1;
	if(count <= (gameTime/2*10*totalcount)){  // 一半的角度  90s 为 450
		$(".pie1").css("-o-transform","rotate(" + ii + "deg)");
		$(".pie1").css("-moz-transform","rotate(" + ii + "deg)");
		$(".pie1").css("-webkit-transform","rotate(" + ii + "deg)");
	}else{
		$(".pie2").css("backgroundColor", "#cdcd30");
		$(".pie2").css("-o-transform","rotate(" + ii + "deg)");
		$(".pie2").css("-moz-transform","rotate(" + ii + "deg)");
		$(".pie2").css("-webkit-transform","rotate(" + ii + "deg)");
	}
};

var start2 = function(){
    jj = jj + 0.6;
    count = count + 1;
    if (count == 300) {
        count = 0;
        clearInterval(t2);
        t1 = setInterval("start1()", 100);
    }
	$(".pie2").css("-o-transform","rotate(" + jj + "deg)");
	$(".pie2").css("-moz-transform","rotate(" + jj + "deg)");
	$(".pie2").css("-webkit-transform","rotate(" + jj + "deg)");
}

var countDown = function() {
    //80*80px 时间进度条
	
	
    ii = 0;
    jj = 0;
    count = 0;
    MM = 0;
    SS = gameTime*totalcount;
    MS = 0;
    totle = (MM + 1) * gameTime * 10*totalcount;
    d = 180 * (MM + 1);
    MM = "0" + MM;

    showTime();

    var s = setInterval("showTime()", 100);
    start1();
    //start2();
    var t1 = setInterval("start1()", 100);
    return;
}