package code.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

class Vizury2 {
	
  static class Shop {
		int index;
		int happiness;
		int lValue;
		int totalHappiness;
		
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public int getHappiness() {
			return happiness;
		}
		public void setHappiness(int happiness) {
			this.happiness = happiness;
		}
		public int getlValue() {
			return lValue;
		}
		public void setlValue(int lValue) {
			this.lValue = lValue;
		}
		public int getTotalHappiness() {
			return totalHappiness;
		}
		public void setTotalHappiness(int totalHappiness) {
			this.totalHappiness = totalHappiness;
		}
		
		
	}
  
    private static int maxHappiness(List<Shop> shopList) {
    	Collections.sort(shopList, new Comparator<Shop>(){
    		   public int compare(Shop o1, Shop o2){
    		      return o1.getlValue() - o2.getlValue();
    		   }
    		});
    	int max_so_far = Integer.MIN_VALUE,max_from_here,interim_max;
    	/*for(int i = 0; i < shopList.size(); i++) {
    		//start from i
    		Shop shop;
    		max_from_here = shopList.get(i).getHappiness();
    		Iterator<Shop> it = shopList.subList(i, shopList.size()).iterator(); 
    		shop = it.next();
    		int current_index = shop.getIndex();
    		while(it.hasNext()) {
    			shop = it.next();
    			if(shop.getIndex() < current_index)
    				continue;
    			if(max_from_here + shop.getHappiness() >= max_from_here)
    				max_from_here = max_from_here + shop.getHappiness();
    		}
    		if(max_from_here > max_so_far)
    			max_so_far = max_from_here;
    	}*/
    	int N = shopList.size();
    	Map<Integer, Integer> map = new HashMap<>();
    	Iterator<Shop> it = shopList.iterator();
    	Shop shop = null;
    	int index = 0;
    	while(it.hasNext()) {
    		shop = it.next();
    		map.put(shop.getIndex(), index);
    		index++;
    	}
    	//map contains key as actual index of shop and value as the index in sorted list
    	shopList.get(map.get(N-1)).setTotalHappiness(shopList.get(map.get(N-1)).getHappiness());
    	max_so_far = shopList.get(map.get(N-1)).getHappiness();
    	for(int i = N-2; i >= 0; i--) {
    		max_from_here = Integer.MIN_VALUE;
    		for(int j = i+1; j < N; j++) {
    			if(map.get(j) < map.get(i))
    				continue;
    			if(shopList.get(map.get(j)).getTotalHappiness() > max_from_here) 
    				max_from_here = shopList.get(map.get(j)).getTotalHappiness();
    		}
    		if(max_from_here == Integer.MIN_VALUE)
    			max_from_here = shopList.get(map.get(i)).getHappiness();
    		else {
    			int happiness = shopList.get(map.get(i)).getHappiness();
    			if(max_from_here < 0 || happiness < 0) {
    				max_from_here = (max_from_here > happiness)?max_from_here:happiness;
    			} else {
    				max_from_here += happiness;
    			}
    		}
    		shopList.get(map.get(i)).setTotalHappiness(max_from_here);
    		if(max_from_here > max_so_far)
    			max_so_far = max_from_here;
    	}
    	
    	
    	return max_so_far;
	}
	
/*    public static void main(String args[] ) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        String[] elem;
        List<Shop> shopList = new ArrayList<>();
        Shop shop = null;
        for(int i = 0; i < N; i++) {
        	line = br.readLine();
        	elem = line.split(" ");
        	shop = new Shop();
        	shop.setIndex(i);
        	shop.setHappiness(Integer.parseInt(elem[0]));
        	shop.setlValue(Integer.parseInt(elem[0]));
        	shopList.add(shop);
        }
       
        System.out.println(maxHappiness(shopList));
       
    }*/
    
    public static class User {
    	int req_Time;
    	public int getReq_Time() {
			return req_Time;
		}
		public void setReq_Time(int req_Time) {
			this.req_Time = req_Time;
		}
		public int getTravel_Time() {
			return travel_Time;
		}
		public void setTravel_Time(int travel_Time) {
			this.travel_Time = travel_Time;
		}
		int travel_Time;
		int index;
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		
    	
    	
    }
    
    public static class Taxi implements Comparable<Taxi> {
    	int index;
		int free_Time;
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public int getFree_Time() {
			return free_Time;
		}
		public void setFree_Time(int free_Time) {
			this.free_Time = free_Time;
		}
		@Override
		public int compareTo(Taxi taxi) {
			return this.free_Time - taxi.free_Time;
		}
    }
    
    public static void main(String args[] ) throws Exception {
        

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] arr = line.split(" ");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);
        int [] taxiNum = new int[N];
        List<User> userList = new ArrayList<User>(N);
        User user;
        int index = 0;
        while((line=br.readLine()) != null && line.trim().length() > 0) {
        	arr = line.split(" ");
        	user = new User();
        	user.setIndex(index++);
        	user.setReq_Time(Integer.parseInt(arr[0]));
        	user.setTravel_Time(Integer.parseInt(arr[1]));
        	userList.add(user);
        }
        
        //now sort the userList based on request time
        Collections.sort(userList, new Comparator<User>(){
    		   public int compare(User o1, User o2){
    		      return o1.getReq_Time() - o2.getReq_Time();
    		   }
    		});
    	
        //this set contains free time 
        //initially all have same free time
       //map contains key as freetime and value as index
        Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 2; i <= M; i++) {
        	set.add(0);    // all are free in the beginning
        	if(map.get(0) == null) {
        		map.put(0,new ArrayList<Integer>());
        	}
        	map.get(0).add(i);    
        }
        
        Iterator<User> it = userList.iterator();
        User firstUser = it.next();
        taxiNum[firstUser.getIndex()] = 1;
        set.add(firstUser.getReq_Time()+firstUser.getTravel_Time());
        if(map.get(firstUser.getReq_Time()+firstUser.getTravel_Time()) == null) {
        	map.put(firstUser.getReq_Time()+firstUser.getTravel_Time(), new ArrayList<Integer>());
        }
        map.get(firstUser.getReq_Time()+firstUser.getTravel_Time()).add(1);
        
       
        while(it.hasNext()) {
        	User u = it.next();
        	//check the index of taxi whose free time is lower than request time
//        	Integer reqtime = set.lower(u.getReq_Time());
        	TreeSet<Integer> subset = (TreeSet<Integer>) set.headSet(u.getReq_Time(), true);
        	if(subset.isEmpty()) {
        		taxiNum[u.getIndex()] = -1;
        	} else {
        		int min = Integer.MAX_VALUE,value = 0;
        		for(Integer elem : subset) {
        			Iterator<Integer> ite = map.get(elem).iterator();
        			while(ite.hasNext()) {
        				int idx = ite.next();
        				if(idx < min) {
        				min = idx;
        				value = elem;
        				}
        		}
        		}
        		if(map.get(value).size() == 1) {
        		set.remove(value);
        		} 
        		map.get(value).remove((Integer)min);
        		set.add(u.getReq_Time() + u.getTravel_Time());
        		if(map.get(u.getReq_Time() + u.getTravel_Time()) == null) {
        			map.put(u.getReq_Time() + u.getTravel_Time(),new ArrayList<Integer>());
        		}
        		map.get(u.getReq_Time() + u.getTravel_Time()).add(min);
        		taxiNum[u.getIndex()] = min;
        	}
        }
        
        
    	 for(int i = 0; i < N; i++) {
    		 System.out.print(taxiNum[i] + ((i != N-1)?" ":""));
    	 }
    		
       

    }

	
}
