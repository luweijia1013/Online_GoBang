package action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class AssistFunction {

	/* get writer of file whose name is filename */
	public BufferedWriter getwriter(String filename) throws IOException{
		File file = new File(filename);
		if(!file.exists()) file.createNewFile();
		FileWriter fileWriter=new FileWriter(file.getName(),true);
		BufferedWriter bufferedwriter=new BufferedWriter(fileWriter);
		return bufferedwriter;
	}
	
	/* get reader of file whose name is filename */
	public BufferedReader getreader(String filename) throws FileNotFoundException{
		FileReader fileReader=new FileReader(filename);
		BufferedReader bf= new BufferedReader(fileReader);
		return bf;
	}
	
	/* turn a long string into its hashed one */
	public String hashstring (String target){
		String ret;
		long result=0;
		long model=1000007;
		long midnum;
		for(int i=0;i<target.length();i++){
			midnum=1;
			for(int p=0;p<i;p++){
				midnum=(3*midnum)%model;
			}
			if(target.charAt(i)=='1')
				result+=(1*midnum)%model;
			else if (target.charAt(i)=='2')
				result+=(2*midnum)%model;
		}
		ret=String.valueOf(result);
		return ret;
	}

	/* change a situation at array form to form of string (9x9 situation) */
	public String arraytosmallcode(int[][] array){
		String result="";
		for(int j=3;j<12;j++){
			for(int i=3;i<12;i++){
				result+=String.valueOf(array[i][j]);
			}
		}
		return result;
	}
	
	/* change a situation at string form to array form (9*9 situation)  */
	public int[][] smallcodetoarray (String situationcode){
		int[][] result=new int[15][15];
		for(int i=0;i<225;i++){
			int x=i%15;
			int y=(i-i%15)/15;
			if(x<3||x>11||y<3||y>11){
				result[x][y]=0;
			}
			else{
				result[x][y]=situationcode.charAt((y-3)*9+(x-3))-'0';
			}
		}
		return result;
	}
	
	/* get next situation's hash value through its situationcode now, its color and the position of next piece */
	public String nexthash(String nowsituation,int color,int position){
		int x=position%15;
		int y=(position-position%15)/15;
		String nextsituation="";
		if(x<3||x>11||y<3||y>11){
			return nextsituation;
		}
		position=(y-3)*9+(x-3);
		String mycolor;
		if(color==1){
			mycolor="1";
		}
		else mycolor="2";
		String sub1=nowsituation.substring(0, position);
		String sub2=nowsituation.substring(position+1,81);
		nextsituation=sub1+mycolor+sub2;
		return nextsituation;
	}

	/* parse a string line, get the win condition and hash's key */
	public String ParseHashAndWinrate(String string, Integer[] value) {
		String[] Svalue=new String[]{"","",""};
		String key="";
			int i=0;
			int index=0;
			for(int m=0;m<string.length();m++){
				if(string.charAt(m)==' '){
					i=m+1;
					break;
				}
				key+=string.charAt(m);
			}
			for(int m=i;m<string.length();m++){
				if(string.charAt(m)==' '){
					index++;
					continue;
				}
		
				Svalue[index]+=string.charAt(m);
			}
			
			for(int m=0;m<3;m++){
				value[m]= Integer.parseInt(Svalue[m]);
			}
			return key;
	}
	
}
