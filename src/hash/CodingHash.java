package hash;

import java.util.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

public class CodingHash {

    //폰켓몬 - Hash
    public int solution(int[] nums) {
        int answer = nums.length / 2;

        HashSet<Integer> numsSet = new HashSet<>();

        for(int num : nums){
            numsSet.add(num);
        }

        if(numsSet.size() > answer){
            return answer;
        }else{
            return numsSet.size();
        }

    }

    //폰켓몬 - stream
    public int solution1(int[] nums) {
        return Arrays.stream(nums)
                .boxed()
                .collect(Collectors.collectingAndThen(Collectors.toSet(),
                        phonekemons -> Integer.min(phonekemons.size(), nums.length / 2)));
    }

    //완주하지 못한 선수 - Hash
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> map = new HashMap();
        for(String player : participant){
            map.put(player, map.getOrDefault(player, 0) + 1);
        }

        for(String player : completion){
            map.put(player, map.get(player) - 1);
        }

        Iterator<Map.Entry<String, Integer>> iter = map.entrySet().iterator();

        while(iter.hasNext()){
            Map.Entry<String, Integer> entry = iter.next();
            if(entry.getValue() != 0){
                answer = entry.getKey();
                break;
            }
        }

        return answer;
    }

    //전화번호 목록 - Hash
    public boolean solution(String[] phone_book) {
        HashMap<String, Integer> map = new HashMap();
        for(int i = 0; i < phone_book.length; i++){
            map.put(phone_book[i], 1);
        }

        for(int i = 0; i < phone_book.length; i++){
            for(int j = 1; j < phone_book[i].length(); j++){
                if(map.containsKey(phone_book[i].substring(0, j))){
                    return false;
                }
            }
        }
        return true;
    }

    //의상 - Hash
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        int answer = 1;

        for(int i = 0; i < clothes.length; i++) {
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1);
        }

        for(String key : map.keySet()) {
            answer *= (map.get(key) + 1);
        }

        answer -= 1;

        return answer;
    }

    //의상 - stream
    public int solution1(String[][] clothes) {
        return Arrays.stream(clothes)
                .collect(groupingBy(p -> p[1], mapping(p -> p[0], counting())))
                .values()
                .stream()
                .collect(reducing(1L, (x, y) -> x * (y + 1))).intValue() - 1;
    }

    //베스트앨범 - Hash
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<String, Integer> num = new HashMap<>();
        HashMap<String, HashMap<Integer, Integer>> music = new HashMap<>();

        for(int i = 0; i < plays.length; i++) {

            if(!num.containsKey(genres[i])) {

                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(i, plays[i]);
                music.put(genres[i], map);
                num.put(genres[i], plays[i]);
            } else {

                music.get(genres[i]).put(i, plays[i]);
                num.put(genres[i], num.get(genres[i]) + plays[i]);
            }
        }

        List<String> keySet = new ArrayList(num.keySet());

        Collections.sort(keySet, (s1, s2) -> num.get(s2) - (num.get(s1)));

        for(String key : keySet) {

            HashMap<Integer, Integer> map = music.get(key);
            List<Integer> genre_key = new ArrayList(map.keySet());

            Collections.sort(genre_key, (s1, s2) -> map.get(s2) - (map.get(s1)));

            answer.add(genre_key.get(0));
            if(genre_key.size() > 1)
                answer.add(genre_key.get(1));
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
