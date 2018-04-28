package assignment3;

public class Building {

	OneBuilding data;
	Building older;
	Building same;
	Building younger;
	
	public Building(OneBuilding data){
		this.data = data;
		this.older = null;
		this.same = null;
		this.younger = null;
	}
	
	public String toString(){
		String result = this.data.toString() + "\n";
		if (this.older != null){
			result += "older than " + this.data.toString() + " :\n";
			result += this.older.toString();
		}
		if (this.same != null){
			result += "same age as " + this.data.toString() + " :\n";
			result += this.same.toString();
		}
		if (this.younger != null){
			result += "younger than " + this.data.toString() + " :\n";
			result += this.younger.toString();
		}
		return result;
	}
	
	public Building addBuilding (OneBuilding b){
		// ADD YOUR CODE HERE
		//if null originally
		if(this.data==null) {
			Building temp = new Building(b);
			return temp;
		}
		//to OLDER subtree
		if(this.data.yearOfConstruction > b.yearOfConstruction) {
			if(this.older == null){
				Building temp = new Building(b);
				this.older = temp;
			}else {
				this.older.addBuilding(b);
			}
		}
		//to SAME subtree
		if(this.data.yearOfConstruction == b.yearOfConstruction) {
			if(this.data.height<b.height) {
				Building temp = new Building(this.data);
				temp.same = this.same;
				this.same = temp;
				this.data=b;
			}else {
				if(this.same == null) {
					Building temp = new Building(b);
					this.same = temp;
				}else {
					this.same.addBuilding(b);
				}
			}
		}
		//to YOUNGER subtree
		if(this.data.yearOfConstruction < b.yearOfConstruction) {
			if(this.younger == null) {
				Building temp = new Building(b);
				this.younger = temp;
			}else {
				this.younger.addBuilding(b);
			}
		}
		
		return this; // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	public Building addBuildings (Building b){
		// ADD YOUR CODE HERE
		//add the ROOT first
		if(b!=null) {
			this.addBuilding(b.data);
		}else {
			return this;
		}
		//then add the OLDER subtree
		if(b.older != null) {
			this.addBuildings(b.older);
		}
		//and then add the SAME subtree
		if(b.same != null) {
			this.addBuildings(b.same);
		}
		//finally add the YOUNGER subtree
		if(b.younger != null) {
			this.addBuildings(b.younger);
		}
		
		return this; // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	public Building removeBuilding (OneBuilding b){
		// ADD YOUR CODE HERE
		//if the tree is empty
		if(this.data==null) {
			return this;
		}
		
		//if b is the SAME year as the root
		if(b.yearOfConstruction==this.data.yearOfConstruction) {
			if(b.equals(this.data)) {
				if(this.same!=null) {
					this.same.addBuildings(this.older);
					this.same.addBuildings(this.younger);
					return this.same;
				}
				else if(this.same==null && this.older!=null) {
					this.older.addBuildings(this.younger);
					return this.older;
				}
				else if(this.same==null&&this.older==null) {
						return this.younger;
				}
			}else if(this.same!=null){
				this.same = this.same.removeBuilding(b);
			}
		}
		//if b is YOUNGER than the root
		if(b.yearOfConstruction>this.data.yearOfConstruction && this.younger!=null) {
			this.younger = this.younger.removeBuilding(b);
		}
		//if b is OLDER than the root
		if(b.yearOfConstruction<this.data.yearOfConstruction && this.older!=null) {
			this.older = this.older.removeBuilding(b);
		}

		return this; // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	public int oldest(){
		// ADD YOUR CODE HERE
		int oldest = this.data.yearOfConstruction;
		if(this.older !=null) {
			oldest = this.older.oldest();
		}
		return oldest; // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	public int highest(){
		// ADD YOUR CODE HERE
		int highest = this.data.height;
		if(this.older !=null) {
			if(this.older.highest()>highest) {
				highest = this.older.highest();
			}
		}
		if(this.same !=null) {
			if(this.same.highest()>highest) {
				highest = this.older.highest();
			}
		}
		if(this.younger !=null) {
			if(this.younger.highest()>highest) {
				highest = this.younger.highest();
			}
		}
		return highest; // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	public OneBuilding highestFromYear (int year){
		// ADD YOUR CODE HERE
		//if the years are the same, the first one should be the highest
		OneBuilding temp = null;
		if(year==this.data.yearOfConstruction) {
			temp = this.data;
		}
		//if the year is OLDER than the root
		else if(year<this.data.yearOfConstruction && this.older!=null) {
			temp = this.older.highestFromYear(year);
		}
		//if the year is Younger than the root
		else if(year>this.data.yearOfConstruction && this.younger!=null) {
			temp = this.younger.highestFromYear(year);
		}
		return temp;
	}
	
	public int numberFromYears (int yearMin, int yearMax){
		// ADD YOUR CODE HERE
		//if the range is wrong
		if(yearMin>yearMax) {
			return 0;
		}else {
			int counter = 0;
			for(int i = yearMin;i<=yearMax;i++) {
				counter = counter + this.numberOneYear(i);
			}
			return counter;
		}
	}
	
	///////helper method to return the number of buildings in ONE given year////////
	public int numberOneYear (int year) {
		int number = 0;
		//go to the SAME subtree
		if(this.data.yearOfConstruction==year) {
			if(this.same!=null) {
				number = number + 1;
				number = number + this.same.numberOneYear(year);
			}else {
				number = number + 1;
			}
		}
		//go to the OLDER subtree
		if(this.data.yearOfConstruction>year && this.older!=null) {
			number = number + this.older.numberOneYear(year);
		}
		//go to the YOUNGER subtree
		if(this.data.yearOfConstruction<year && this.younger!=null) {
			number = number + this.younger.numberOneYear(year);
		}
		return number;
	}
	
	public int[] costPlanning (int nbYears){
		// ADD YOUR CODE HERE
		int[] costarr = new int[nbYears];
		for(int i =0; i<nbYears;i++) {
			costarr[i]=this.costOneYear(2018+i);
		}
		return costarr; // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	///////helper method to return the cost of repair in ONE given year////////
	public int costOneYear (int repairYear) {
		int cost = 0;
		//same years
		if(this.data.yearForRepair == repairYear) {
			cost = cost + this.data.costForRepair;
			if(this.same!=null) {
				cost = cost + this.same.costOneYear(repairYear);
			}
			if(this.older!=null) {
				cost = cost + this.older.costOneYear(repairYear);
			}
			if(this.younger!=null) {
				cost = cost + this.younger.costOneYear(repairYear);
			}
		}else{
			if(this.same!=null) {
				cost = cost + this.same.costOneYear(repairYear);
			}
			if(this.older!=null) {
				cost = cost + this.older.costOneYear(repairYear);
			}
			if(this.younger!=null) {
				cost = cost + this.younger.costOneYear(repairYear);
			}
		}	
		return cost;
	}
	
	
}
