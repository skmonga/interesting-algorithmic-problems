package code.practice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BBazaar2 {

	static class IndexRadius {
		int index;
		int radius;
		
		public IndexRadius() {
		
		}
	}
	
	 static int[] Circles(int distance, int[] radius, int[] cost) {

		 int[] result = new int[radius.length];
		 for(int i = 0; i < radius.length; i++)
			 result[i] = -1;
		 List<IndexRadius> list = new ArrayList<>();
		 IndexRadius obj;
		 for(int i = 0; i < radius.length; i++) {
			 obj = new IndexRadius();
			 obj.index = i;
			 obj.radius = radius[i];
			 list.add(obj);
		 }
		 
		 Collections.sort(list, new Comparator<IndexRadius>(){
		     
			@Override
			public int compare(IndexRadius obj1, IndexRadius obj2) {
				if(obj1.radius == obj2.radius)
					return 0;
				return obj1.radius < obj2.radius ? -1:1;
			}
		});
		 
		int dist_left = 0,min_cost = Integer.MAX_VALUE,j = 0,actual_index=0;
		IndexRadius max_rad = list.get(radius.length-1);
		for(int i = 0; i < radius.length; i++) {
			dist_left = distance - radius[i];
			//last one has highest radius
			if(dist_left > max_rad.radius) {
				result[i] = 0;
				continue;
			}
			j = radius.length;
			min_cost = Integer.MAX_VALUE;
			
			while(--j >= 0 && list.get(j).radius >= dist_left) {
				actual_index = list.get(j).index;
				if(cost[actual_index] <= min_cost) {
					if(cost[actual_index] == min_cost) {
						if(radius[actual_index] > radius[result[i]]) {
							result[i] = actual_index;
						} else {
							result[i] = (result[i] > actual_index) ? actual_index:result[i];
						}
					} else {
						result[i] = actual_index;
						min_cost = cost[actual_index];
					}
				}
			}
			result[i] = result[i]+1;
			
		}
			 

		 return result;
	    }
	 
	 public static void main(String[] args) throws IOException{
	        Scanner in = new Scanner(System.in);
	        final String fileName = System.getenv("OUTPUT_PATH");
	        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
	        int[] res;
	        int _radius_size = 0;
	        _radius_size = in.nextInt();
	        
	        int _distance;
	        _distance = in.nextInt();
	        
	        
	        
	        int[] _radius = new int[_radius_size];
	        int _radius_item;
	        for(int _radius_i = 0; _radius_i < _radius_size; _radius_i++) {
	            _radius_item = in.nextInt();
	            _radius[_radius_i] = _radius_item;
	        }
	        
	        
	        int _cost_size = _radius_size;
	        int[] _cost = new int[_cost_size];
	        int _cost_item;
	        for(int _cost_i = 0; _cost_i < _cost_size; _cost_i++) {
	            _cost_item = in.nextInt();
	            _cost[_cost_i] = _cost_item;
	        }
	        
	        res = Circles(_distance, _radius, _cost);
	        for(int res_i=0; res_i < res.length; res_i++) {
	            bw.write(String.valueOf(res[res_i]));
	            bw.write(' ');
	        }
	        
	        bw.close();
	    }
	 
	 static int getIntegerComplement(int n) {

	        int max_index = 0;
	        int[] bits = new int[32];
	        for(int i = 0; i < 32; i++) {
	            if((n & (1 << i)) != 0) {
	                max_index = i;
	                bits[i] = 1;
	            }
	        }
	        
	        int new_num = 0;
	        for(int i = 0; i <= max_index; i++) {
	            if(bits[i] == 0)
	                new_num += Math.pow(2,i);
	        }
	        return new_num;

	    }

}
