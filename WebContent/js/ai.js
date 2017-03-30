/**
 * 
 */
function AIalgo() {
			var ret=[];
			var arrayscore=[]; 
			for(var m=0;m<15;m++){
				arrayscore[m]=[];
			for(var n=0;n<15;n++){
				arrayscore[m][n]=0;
				}
			}
			var max=[];
			for(m=0;m<8;m++){
				max[m]=0;
			}
			var maxdot=[]; 
			var judgecolorA=0;
			var judgecolorB=0;
			var jlr = 0;
			var jud = 0;
			var jlurd = 0;
			var jldru = 0;
			var jleft = 0;
			var jright = 0;
			var jup = 0;
			var jdown = 0;
			var jleftup = 0;
			var jleftdown = 0;
			var jrightup = 0;
			var jrightdown = 0; 
					for(m=0;m<15;m++){
						for(n=0;n<15;n++){
							if(array[m][n]!=0) {continue;}
							else{
								jleft = 0;
								jright = 0;
								jup = 0;
								jdown = 0;
								jleftup = 0;
								jleftdown = 0;
								jrightup = 0;
								jrightdown = 0;
								/* left and right */
								for(var p=m;p>0;p--){
									if(p==m) {
										judgecolorA=array[p-1][n];
										if(judgecolorA==0) break;
										jleft++;
										continue;
									}
									if(judgecolorA==array[p-1][n]){
										jleft++;
									}
									else if(array[p-1][n]==0){
										break;
									}
									else{
										if(jleft<4){jleft--;break;}
									}
								}
								for(var p=m;p<14;p++){
									if(p==m) {
										judgecolorB=array[p+1][n];
										if(judgecolorB==0) break;
										jright++;
										continue;
									}
									if(judgecolorB==array[p+1][n]){
										jright++;
									}
									else if(array[p+1][n]==0){
										break;
									}
									else{
										if(jright<4){jright--;break;}
									}
								}
								if(judgecolorA==judgecolorB)
									jlr=jleft+jright;
								else
									jlr=Math.max(jleft,jright);
								/* up and down */
								for(var p=n;p>0;p--){
									if(p==n) {
										judgecolorA=array[m][p-1];
										if(judgecolorA==0) break;
										jup++;
										continue;
									}
									if(judgecolorA==array[m][p-1]){
										jup++;
									}
									else if(array[m][p-1]==0){
										break;
									}
									else{
										if(jup<4){jup--;break;}
									}
								}
								for(var p=n;p<14;p++){
									if(p==n) {
										judgecolorB=array[m][p+1];
										if(judgecolorB==0) break;
										jdown++;
										continue;
									}
									if(judgecolorB==array[m][p+1]){
										jdown++;
									}
									else if(array[m][p+1]==0){
										break;
									}
									else{
										if(jdown<4){jdown--;break;}
									}
								}
								if(judgecolorA==judgecolorB)
									jud=jup+jdown;
								else
									jud=Math.max(jup,jdown); 
								/* left up and right down */
								for(var p=1;p<Math.min(m, n)+1;p++){
									if(p==1) {
										judgecolorA=array[m-1][n-1];
										if(judgecolorA==0) break;
										jleftup++;
										continue;
									}
									if(judgecolorA==array[m-p][n-p]){
										jleftup++;
									}
									else if(array[m-p][n-p]==0){
										break;
									}
									else{
										if(jleftup<4){jleftup--;break;}
									}
								}
								for(var p=1;p<Math.min(14-m, 14-n)+1;p++){
									if(p==1) {
										judgecolorB=array[m+1][n+1];
										if(judgecolorB==0) break;
										jrightdown++;
										continue;
									}
									if(judgecolorB==array[m+p][n+p]){
										jrightdown++;
									}
									else if(array[m+p][n+p]==0){
										break;
									}
									else{
										if(jrightdown<4){jrightdown--;break;}
									}
								}
								if(judgecolorA==judgecolorB)
									jlurd=jleftup+jrightdown;
								else
									jlurd=Math.max(jleftup,jrightdown);
								/* left down and right up */
								for(var p=1;p<Math.min(m, 14-n)+1;p++){
									if(p==1) {
										judgecolorA=array[m-1][n+1];
										if(judgecolorA==0) break;
										jleftdown++;
										continue;
									}
									if(judgecolorA==array[m-p][n+p]){
										jleftdown++;
									}
									else if(array[m-p][n+p]==0){
										break;
									}
									else{
										if(jleftdown<4){jleftdown--;break;}
									}
								}
								for(var p=1;p<Math.min(14-m, n)+1;p++){
									if(p==1) {
										judgecolorB=array[m+1][n-1];
										if(judgecolorB==0) break;
										jrightup++;
										continue;
									}
									if(judgecolorB==array[m+p][n-p]){
										jrightup++;
									}
									else if(array[m+p][n-p]==0){
										break;
									}
									else{
										if(jrightup<4){jrightup--;break;}
									}
								}
								if(judgecolorA==judgecolorB)
									jldru=jleftdown+jrightup;
								else
									jldru=Math.max(jleftdown,jrightup);
								/* evaluate the score */
								if(jlr==1) arrayscore[m][n]+=10;
								if(jlr==2) arrayscore[m][n]+=300;
								if(jlr==3) arrayscore[m][n]+=9000;
								if(jlr>=4) arrayscore[m][n]+=270000;
								if(jud==1) arrayscore[m][n]+=10;
								if(jud==2) arrayscore[m][n]+=300;
								if(jud==3) arrayscore[m][n]+=9000;
								if(jud>=4) arrayscore[m][n]+=270000;
								if(jlurd==1) arrayscore[m][n]+=10;
								if(jlurd==2) arrayscore[m][n]+=300;
								if(jlurd==3) arrayscore[m][n]+=9000;
								if(jlurd>=4) arrayscore[m][n]+=270000;
								if(jldru==1) arrayscore[m][n]+=10;
								if(jldru==2) arrayscore[m][n]+=300;
								if(jldru==3) arrayscore[m][n]+=9000;
								if(jldru>=4) arrayscore[m][n]+=270000;
							}
						}
					}
					
					var lastm=-1;
					var lastn=-1;
					var lastscore=-1;
					for(var m=0;m<15;m++){
						for(var n=0;n<15;n++){
							if(arrayscore[m][n]>max[0]){
								max[0]=arrayscore[m][n];
								maxdot[0]=15*n+m;
								if(lastm!=-1){
									arrayscore[lastm][lastn]=lastscore;
								}
								lastm=m;
								lastn=n;
								lastscore=arrayscore[m][n];
								arrayscore[m][n]=0;
							}
						}
					}
					
					
					ret[0]= maxdot[0]%15;
					ret[1]= (maxdot[0]-ret[0])/15;
				
			return ret;
		}