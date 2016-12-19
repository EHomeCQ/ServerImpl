
import java.util.ArrayList;  
import java.util.Collections;  
import java.util.Comparator;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;  

public class RecommendUsersServiceImpl implements RecommendUsersService{

	//�����û���Эͬ�����Ƽ��㷨
	
	
	public RecommendUsersServiceImpl(){
		
	}
	
	
	//��ȡ�����û�֮���Ƥ��ѷ���ƶ�,���ϵ���ľ���ֵԽ��,��ض�Խ��
	
	public static double getUserSimilar(Map<String, Integer> pm1, Map<String, Integer> pm2) {  
	    int n = 0;// ����n  
	    int sxy = 0;// ��xy=x1*y1+x2*y2+....xn*yn  
	    int sx = 0;// ��x=x1+x2+....xn  
	    int sy = 0;// ��y=y1+y2+...yn  
	    int sx2 = 0;// ��x2=(x1)2+(x2)2+....(xn)2  
	    int sy2 = 0;// ��y2=(y1)2+(y2)2+....(yn)2  
	    for (Entry<String, Integer> pme:pm1.entrySet()) {  
	        String key = pme.getKey();  
	        Integer x = pme.getValue();  
	        Integer y = pm2.get(key);  
	        if (x != null && y != null) {  
	        n++;  
	        sxy += x * y;  
	        sx += x;  
	        sy += y;  
	        sx2 += Math.pow(x, 2);  
	        sy2 += Math.pow(y, 2);  
	        }  
	    }  
	    // p=(��xy-��x*��y/n)/Math.sqrt((��x2-(��x)2/n)(��y2-(��y)2/n));  
	    double sd = sxy - sx * sy / n;  
	    double sm = Math.sqrt((sx2 - Math.pow(sx, 2) / n) * (sy2 - Math.pow(sy, 2) / n));  
	    return Math.abs(sm == 0 ? 1 : sd / sm);  
	    }  
	
	
	
	//��ȡ�����û�֮���ŷ����þ���,����ԽСԽ�� 
	
	 public static double getEuclidDistance(Map<String, Integer> pm1, Map<String, Integer> pm2) {  
		    double totalscore = 0.0;  
		    for (Entry<String, Integer> test : pm1.entrySet()) {  
		        String key = test.getKey();  
		        Integer a1 = pm1.get(key);  
		        Integer b1 = pm2.get(key);  
		        if (a1 != null && b1 != null) {  
		        double a = Math.pow(a1 - b1, 2);  
		        totalscore += Math.abs(a);  
		        }  
		    }  
		    return Math.sqrt(totalscore);  
	    }  
	 
	 
	 //�������ϵ���õ��Ƽ���Ʒ
	 
	 public static String getRecommend(Map<String, Map<String, Integer>> simUserObjMap, Map<String, Double> simUserSimMap) {  
		    Map<String, Double> objScoreMap = new HashMap<String, Double>();  
		    for (Entry<String, Map<String, Integer>> simUserEn : simUserObjMap.entrySet()) {  
		        String user = simUserEn.getKey();  
		        double sim = simUserSimMap.get(user);  
		        for (Entry<String, Integer> simObjEn : simUserEn.getValue().entrySet()) {  
		        double objScore = sim * simObjEn.getValue();  
		        String objName = simObjEn.getKey();  
		        if (objScoreMap.get(objName) == null) {  
		            objScoreMap.put(objName, objScore);  
		        }else {  
		            double totalScore = objScoreMap.get(objName);  
		            objScoreMap.put(objName, totalScore + objScore);  
		        }  
		        }  
		    }  
		    List<Entry<String, Double>> enList = new ArrayList<Entry<String, Double>>(objScoreMap.entrySet());  
		    Collections.sort(enList, new Comparator<Map.Entry<String, Double>>() {  
		        public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {  
		        Double a = o1.getValue() - o2.getValue();  
		        if (a == 0) {  
		            return 0;  
		        }else if (a > 0) {  
		            return 1;  
		        }else {  
		            return -1;  
		        }  
		        }  
		    });  
		    return enList.get(enList.size() - 1).getKey();  
	  }  
	 
	 
	

	@Override
	public List<User> getRecommendUserList(String userId, int pageIndex,
			int numberPerPage) {
		// TODO Auto-generated method stub
		List<User> user = new ArrayList<User>();
		return user;
	}
}
