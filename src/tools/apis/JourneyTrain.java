package tools.apis;

import java.util.Date;

import services.TrainService;



public class JourneyTrain {

	int user_id;
	String numT;
	String time;
	String numG;
	String term;
	
	
	public JourneyTrain(int user_id, String numT, String time, String numG, String term) {
		super();
		this.user_id = user_id;
		this.numT = numT;
		this.time = time;
		this.numG = numG;
		this.term = term;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getNumT() {
		return numT;
	}


	public void setNumT(String numT) {
		this.numT = numT;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getNumG() {
		return numG;
	}


	public void setNumG(String numG) {
		this.numG = numG;
	}


	public String getTerm() {
		return term;
	}


	public void setTerm(String term) {
		this.term = term;
	}
	
	
	
	@Override
	public String toString() {
		return "JourneyTrain [user_id=" + user_id + ", numT=" + numT
				+ ", time=" + time + ", numG=" + numG + ", term=" + term + "]";
	}


	public boolean equals(JourneyTrain jt){
		if(jt.getNumT().equals(this.getNumT()) && TrainService.isInInterval(this.getTime(),jt.getTime(),60) && jt.getTerm().equals(this.getTerm())){
			return true;
		}else{
			return false;
		}
	}
	
	
}
