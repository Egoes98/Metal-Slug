package es.deusto.prog3.metalslug.utils;

public class CollisionUtils {
	
	public static float min(float... nums) {
		float min = Float.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			if(nums[i] < min) {
				min = nums[i];
			}
		}
		return min;
	}
}
