package action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import aialgo.Algo;

public class AiAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	String jsonResult;
	
	/**
	 * @return the jsonResult
	 */
	public String getJsonResult() {
		return jsonResult;
	}
	
	public String waitAIpiece() throws  IOException{
		Algo algo=new Algo();
		AssistFunction assistant= new AssistFunction();
		HashMap<String,Integer[]> hash= new HashMap<String,Integer[]>();
		String string=new String("");
		int[] nextpiece=new int[8];
		String[] nextsituation=new String[8];
		Double[] winrate=new Double[8];
		BufferedWriter log=assistant.getwriter("d:/eclipsejava/eclipse/workspace/lwj_jiaogobang _mytestversion/logfiles/logfile.txt");
		/* get parameter*/
		HttpServletRequest request = ServletActionContext.getRequest();
     	int AIcolor=Integer.parseInt(request.getParameter("AIcolor"));
     	String situationcode=request.getParameter("situationcode");
     	/* give piece selector situationcode to get the piece candidates */
     	nextpiece=algo.AlgoOrigin(assistant.smallcodetoarray(situationcode));
     	/* combine piece candidate with situationcode to form the nextsituations*/
     	for(int i=0;i<8;i++){
     		nextsituation[i]=assistant.nexthash(situationcode,AIcolor,nextpiece[i]);
     	}
     	for(int p=0;p<8;p++){
     		if(nextsituation[p].length()==0) {/*length==0 means the piece candidate is out of 9*9 range*/
     			winrate[p]=0.0;
     			continue;
     			}
     		String situationhash=assistant.hashstring(nextsituation[p]); 
     		/*get hash file and create hash map in memory*/
     		int tenth=Integer.parseInt(String.valueOf(situationhash.charAt(0)));
     		int oneth=Integer.parseInt(String.valueOf(situationhash.charAt(1)));
     		int filenum=10*tenth+oneth;
     		BufferedReader reader=assistant.getreader("d:/eclipsejava/eclipse/workspace/lwj_jiaogobang _mytestversion/situation data/hashtest"+filenum+".txt");
     		while((string=reader.readLine())!=null){
     			Integer[] value=new Integer[3];
     			String key=new String("");
     			key=assistant.ParseHashAndWinrate(string,value); 	
     			hash.put(key, value);
     		}
     		/* find situation in the hash map */
     		Integer[] temp=new Integer[3];
     		if((temp=hash.get(situationhash))==null){
     			winrate[p]=(double) 0;
     		}
     		else{
     			if(temp[0]+temp[1]+temp[2]>3){
     			winrate[p]=(double) (((temp[AIcolor]+temp[0])*100)/(2*temp[0]+temp[1]+temp[2]));
     			}
     			else winrate[p]=(double)0;
     		}
     	}
     	/*select the piece which has the maximum win rate*/
     	double maxrate=0.0;
     	int maxpiece=-1;
     	for(int i=0;i<8;i++){
     		if(winrate[i]>maxrate){
     			maxrate=winrate[i];
     			maxpiece=nextpiece[i];
     		}
     	}
     	/*return the piece according to win rate*/
     	java.sql.Timestamp time = new java.sql.Timestamp(new java.util.Date().getTime());
     	if(maxpiece==-1){
     		log.write("Piece time:"+time+",situation not found,choose ordinary AI\n");
     		log.close();
     		System.err.println("not found");
     		int x=0;
     		int y=0;
     		jsonResult="{\"x\":"+x+",\"y\":"+y+"}";
     		return SUCCESS;
     	}
     	else{
     		int x=maxpiece%15;
     		int y=(maxpiece-maxpiece%15)/15;
     		log.write("Piece time:"+time+",situation found,piece at: ("+x+","+y+")\n");
     		log.close();
     		jsonResult="{\"x\":"+x+",\"y\":"+y+"}";
     		return SUCCESS;
     	}
	}

	

	/*private String[] nexthash(String situation, int aIcolor) {
		// TODO Auto-generated method stub
		String[] result= new String[81];
		char AIcolor='0';
		if(aIcolor==1){
			AIcolor='1';
		}
		else if(aIcolor==2){
			AIcolor='2';
		}
		else System.err.println("color wrong");
		if(situation.charAt(0)=='0'){
			String sub=situation.substring(1,80);
			result[0]=""+AIcolor+sub;
		}
		int index=1;
		while(index<80){
			if(situation.charAt(index)=='0'){
				String sub1=situation.substring(0, index);
				System.out.println(index+"th sub1  ="+sub1);
				String sub2=situation.substring(index+1,81);
				System.out.println(index+"th sub2  ="+sub2);
				String replace=""+AIcolor;
				String old=situation;
				situation=sub1+replace+sub2;
				result[index]=situation;
				System.out.println(index+"th result="+result[index]);
				situation=old;
				System.out.println(index+"th old   ="+old);
			}
			else{
				result[index]="";
			}
			index++;
		}
		if(situation.charAt(80)=='0'){
			String sub=situation.substring(0,79);
			result[80]=""+sub+AIcolor;
		}
		return result;
	}*/
}
