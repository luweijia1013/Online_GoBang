package aialgo;

import javax.swing.colorchooser.ColorChooserComponentFactory;

public class Algo {

	private static int[][] arrayscore=new int[15][15];

	public  int[] AlgoOrigin(int[][] array){    /*choose the max 8 pieces according to their score */

		for(int m=0;m<15;m++){
		for(int n=0;n<15;n++){
			arrayscore[m][n]=0;
			}
		}
		int[] max=new int [8];
		for(int m=0;m<8;m++){
			max[m]=0;
		}
		int[] maxdot=new int [8];
		int judgecolorA=0;
		int judgecolorB=0;
		int jlr = 0;
		int jud = 0;
		int jlurd = 0;
		int jldru = 0;
		int jleft = 0;
		int jright = 0;
		int jup = 0;
		int jdown = 0;
		int jleftup = 0;
		int jleftdown = 0;
		int jrightup = 0;
		int jrightdown = 0; 
				for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
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
							for(int p=m;p>0;p--){
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
							for(int p=m;p<14;p++){
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
							for(int p=n;p>0;p--){
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
							for(int p=n;p<14;p++){
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
							for(int p=1;p<Math.min(m, n)+1;p++){
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
							for(int p=1;p<Math.min(14-m, 14-n)+1;p++){
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
							for(int p=1;p<Math.min(m, 14-n)+1;p++){
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
							for(int p=1;p<Math.min(14-m, n)+1;p++){
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
				int lastm=-1;
				int lastn=-1;
				int lastscore=-1;
				for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
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
				lastm =-1;
				lastn =-1;
				for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[1]){
							max[1]=arrayscore[m][n];
							maxdot[1]=15*n+m;
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
				lastm =-1;
				lastn =-1;
				for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[2]){
							max[2]=arrayscore[m][n];
							maxdot[2]=15*n+m;
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
				lastm =-1;
				lastn =-1;
				for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[3]){
							max[3]=arrayscore[m][n];
							maxdot[3]=15*n+m;
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
				lastm =-1;
				lastn =-1;
				for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[4]){
							max[4]=arrayscore[m][n];
							maxdot[4]=15*n+m;
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
				lastm =-1;
				lastn =-1;
				for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[5]){
							max[5]=arrayscore[m][n];
							maxdot[5]=15*n+m;
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
				lastm =-1;
				lastn =-1;
				for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						
						if(arrayscore[m][n]>max[6]){
							max[6]=arrayscore[m][n];
							maxdot[6]=15*n+m;
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
				lastm =-1;
				lastn =-1;
				for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[7]){
							max[7]=arrayscore[m][n];
							maxdot[7]=15*n+m;
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
				
				return maxdot;
	}

	public  int AlgoOrigin1(int[][] array){    /*choose the piece randomly from max 8 according to its score(with 20 grad) */

		for(int m=0;m<15;m++){
		for(int n=0;n<15;n++){
			arrayscore[m][n]=0;
			}
		}
		int[] max=new int [8];
		for(int m=0;m<8;m++){
			max[m]=0;
		}
		int[] maxdot=new int [8];
		int judgecolorA=0;
		int judgecolorB=0;
		int jlr = 0;
		int jud = 0;
		int jlurd = 0;
		int jldru = 0;
		int jleft = 0;
		int jright = 0;
		int jup = 0;
		int jdown = 0;
		int jleftup = 0;
		int jleftdown = 0;
		int jrightup = 0;
		int jrightdown = 0; 
				for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
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
							for(int p=m;p>0;p--){
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
							for(int p=m;p<14;p++){
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
							for(int p=n;p>0;p--){
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
							for(int p=n;p<14;p++){
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
							for(int p=1;p<Math.min(m, n)+1;p++){
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
							for(int p=1;p<Math.min(14-m, 14-n)+1;p++){
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
							for(int p=1;p<Math.min(m, 14-n)+1;p++){
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
							for(int p=1;p<Math.min(14-m, n)+1;p++){
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
							if(jlr==2) arrayscore[m][n]+=200;
							if(jlr==3) arrayscore[m][n]+=4000;
							if(jlr>=4) arrayscore[m][n]+=160000;
							if(jud==1) arrayscore[m][n]+=10;
							if(jud==2) arrayscore[m][n]+=200;
							if(jud==3) arrayscore[m][n]+=4000;
							if(jud>=4) arrayscore[m][n]+=160000;
							if(jlurd==1) arrayscore[m][n]+=10;
							if(jlurd==2) arrayscore[m][n]+=200;
							if(jlurd==3) arrayscore[m][n]+=4000;
							if(jlurd>=4) arrayscore[m][n]+=160000;
							if(jldru==1) arrayscore[m][n]+=10;
							if(jldru==2) arrayscore[m][n]+=200;
							if(jldru==3) arrayscore[m][n]+=4000;
							if(jldru>=4) arrayscore[m][n]+=160000;
						}
					}
				}
				
				for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[0]){
							max[0]=arrayscore[m][n];
							maxdot[0]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}
				for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[1]&&arrayscore[m][n]<=max[0]){
							max[1]=arrayscore[m][n];
							maxdot[1]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[2]&&arrayscore[m][n]<=max[1]){
							max[2]=arrayscore[m][n];
							maxdot[2]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[3]&&arrayscore[m][n]<=max[2]){
							max[3]=arrayscore[m][n];
							maxdot[3]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[4]&&arrayscore[m][n]<=max[3]){
							max[4]=arrayscore[m][n];
							maxdot[4]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[5]&&arrayscore[m][n]<=max[4]){
							max[5]=arrayscore[m][n];
							maxdot[5]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[6]&&arrayscore[m][n]<=max[5]){
							max[6]=arrayscore[m][n];
							maxdot[6]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[7]&&arrayscore[m][n]<=max[6]){
							max[7]=arrayscore[m][n];
							maxdot[7]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}
				
				
				int summax=0;
				for(int m=0;m<8;m++){
					summax+=max[m];
				}
				
				int r=(int) Math.floor(Math.random()*summax);
				for(int m=0;m<8;m++){
					r+=max[m];
					if(r>=summax){
						return maxdot[m];
					}
				}
				return -1;
				
				
			
		
	}

	public  int AlgoOrigin2(int[][] array){    /*choose the piece randomly from max 8 according to its score(with 10 grad) */

		for(int m=0;m<15;m++){
		for(int n=0;n<15;n++){
			arrayscore[m][n]=0;
			}
		}
		int[] max=new int [8];
		for(int m=0;m<8;m++){
			max[m]=0;
		}
		int[] maxdot=new int [8];
		int judgecolorA=0;
		int judgecolorB=0;
		int jlr = 0;
		int jud = 0;
		int jlurd = 0;
		int jldru = 0;
		int jleft = 0;
		int jright = 0;
		int jup = 0;
		int jdown = 0;
		int jleftup = 0;
		int jleftdown = 0;
		int jrightup = 0;
		int jrightdown = 0; 
				for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
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
							for(int p=m;p>0;p--){
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
							for(int p=m;p<14;p++){
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
							for(int p=n;p>0;p--){
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
							for(int p=n;p<14;p++){
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
							for(int p=1;p<Math.min(m, n)+1;p++){
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
							for(int p=1;p<Math.min(14-m, 14-n)+1;p++){
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
							for(int p=1;p<Math.min(m, 14-n)+1;p++){
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
							for(int p=1;p<Math.min(14-m, n)+1;p++){
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
							if(jlr==2) arrayscore[m][n]+=100;
							if(jlr==3) arrayscore[m][n]+=1000;
							if(jlr>=4) arrayscore[m][n]+=10000;
							if(jud==1) arrayscore[m][n]+=10;
							if(jud==2) arrayscore[m][n]+=100;
							if(jud==3) arrayscore[m][n]+=1000;
							if(jud>=4) arrayscore[m][n]+=10000;
							if(jlurd==1) arrayscore[m][n]+=10;
							if(jlurd==2) arrayscore[m][n]+=100;
							if(jlurd==3) arrayscore[m][n]+=1000;
							if(jlurd>=4) arrayscore[m][n]+=10000;
							if(jldru==1) arrayscore[m][n]+=10;
							if(jldru==2) arrayscore[m][n]+=100;
							if(jldru==3) arrayscore[m][n]+=1000;
							if(jldru>=4) arrayscore[m][n]+=10000;
						}
					}
				}
				
				for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[0]){
							max[0]=arrayscore[m][n];
							maxdot[0]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}
				for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[1]&&arrayscore[m][n]<=max[0]){
							max[1]=arrayscore[m][n];
							maxdot[1]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[2]&&arrayscore[m][n]<=max[1]){
							max[2]=arrayscore[m][n];
							maxdot[2]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[3]&&arrayscore[m][n]<=max[2]){
							max[3]=arrayscore[m][n];
							maxdot[3]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[4]&&arrayscore[m][n]<=max[3]){
							max[4]=arrayscore[m][n];
							maxdot[4]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[5]&&arrayscore[m][n]<=max[4]){
							max[5]=arrayscore[m][n];
							maxdot[5]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[6]&&arrayscore[m][n]<=max[5]){
							max[6]=arrayscore[m][n];
							maxdot[6]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[7]&&arrayscore[m][n]<=max[6]){
							max[7]=arrayscore[m][n];
							maxdot[7]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}
				
				
				int summax=0;
				for(int m=0;m<8;m++){
					summax+=max[m];
				}
				
				int r=(int) Math.floor(Math.random()*summax);
				for(int m=0;m<8;m++){
					r+=max[m];
					if(r>=summax){
						return maxdot[m];
					}
				}
				return -1;
				
				
			
		
	}

	public  int AlgoLittleRandom(int[][] array){  /*choose the piece from max 8 completely randomly */
		for(int m=0;m<15;m++){
		for(int n=0;n<15;n++){
			arrayscore[m][n]=0;
			}
		}
		int[] max=new int [8];
		for(int m=0;m<8;m++){
			max[m]=0;
		}
		int[] maxdot=new int [8];
		int judgecolorA=0;
		int judgecolorB=0;
		int jlr = 0;
		int jud = 0;
		int jlurd = 0;
		int jldru = 0;
		int jleft = 0;
		int jright = 0;
		int jup = 0;
		int jdown = 0;
		int jleftup = 0;
		int jleftdown = 0;
		int jrightup = 0;
		int jrightdown = 0; 
				for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
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
							for(int p=m;p>0;p--){
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
							for(int p=m;p<14;p++){
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
							for(int p=n;p>0;p--){
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
							for(int p=n;p<14;p++){
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
							for(int p=1;p<Math.min(m, n)+1;p++){
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
							for(int p=1;p<Math.min(14-m, 14-n)+1;p++){
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
							for(int p=1;p<Math.min(m, 14-n)+1;p++){
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
							for(int p=1;p<Math.min(14-m, n)+1;p++){
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
							if(jlurd==3) arrayscore[m][n]+=1000;
							if(jlurd>=4) arrayscore[m][n]+=270000;
							if(jldru==1) arrayscore[m][n]+=10;
							if(jldru==2) arrayscore[m][n]+=300;
							if(jldru==3) arrayscore[m][n]+=9000;
							if(jldru>=4) arrayscore[m][n]+=270000;
						}
					}
				}
				
				for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[0]){
							max[0]=arrayscore[m][n];
							maxdot[0]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}
				for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[1]&&arrayscore[m][n]<=max[0]){
							max[1]=arrayscore[m][n];
							maxdot[1]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[2]&&arrayscore[m][n]<=max[1]){
							max[2]=arrayscore[m][n];
							maxdot[2]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[3]&&arrayscore[m][n]<=max[2]){
							max[3]=arrayscore[m][n];
							maxdot[3]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[4]&&arrayscore[m][n]<=max[3]){
							max[4]=arrayscore[m][n];
							maxdot[4]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[5]&&arrayscore[m][n]<=max[4]){
							max[5]=arrayscore[m][n];
							maxdot[5]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[6]&&arrayscore[m][n]<=max[5]){
							max[6]=arrayscore[m][n];
							maxdot[6]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}for(int m=0;m<15;m++){
					for(int n=0;n<15;n++){
						if(arrayscore[m][n]>max[7]&&arrayscore[m][n]<=max[6]){
							max[7]=arrayscore[m][n];
							maxdot[7]=15*n+m;
							arrayscore[m][n]=0;
						}
					}
				}
				
				
				
				
				int r=(int) Math.floor(Math.random()*8);
				
						return maxdot[r];
					
				

				
				
			
		
	}

	public int AlgoCompleteRandom(int[][] array){  /*choose the piece completely randomly(without score)*/
		int x,y;
		while(true){
		x=(int) Math.floor(Math.random()*15);
		y=(int) Math.floor(Math.random()*15);
		if(array[x][y]==0) break;
		}
		return x+y*15;
	}
}
