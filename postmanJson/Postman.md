# Postman of Backend API

****
## Upload the algorithm to Database
http://localhost:8081/api/v1/game/upload-algorithm

```json
{
    "language": "JAVA",
    "algorithm": "class Solution {\\n    public int[] twoSum(int[] nums, int target) {\\n        Map<Integer, Integer> numMap = new HashMap<>();\\n        int n = nums.length;\\n\\n        // Build the hash table\\n        for (int i = 0; i < n; i++) {\\n            numMap.put(nums[i], i);\\n        }\\n\\n        // Find the complement\\n        for (int i = 0; i < n; i++) {\\n            int complement = target - nums[i];\\n            if (numMap.containsKey(complement) && numMap.get(complement) != i) {\\n                return new int[]{i, numMap.get(complement)};\\n            }\\n        }\\n\\n        return new int[]{}; // No solution found\\n    }\\n}"
}
```
![Alt text](/image/image.png)
****
## find the algorithm in Database (random)
http://localhost:8081/api/v1/game/random-algorithm

![Alt text](/image/image2.png)