import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ScrambedStrings {
    /*this is an example

    sihtsinaxalpeme
    
    maybe
    is
    this
    can 
    be
    an
    example
    */

    public static void main(String[] args) {
      List<String> dict = new ArrayList<String>(Arrays.asList("maybe","is", "this", "can", "be", "an","example"));
      String test = "sihtsinaxalpeme";
      String out = unscramble(test, dict);
      System.out.println("\n" + out);
    }
    
    static String unscramble(String input, List<String> dict) {
      int curr = 0;
      String output = "";
      if (input == null || input.length() == 0) {
        throw new IllegalArgumentException("invalid input");
      }
    
      while( curr < input.length() ) {
        // try to match against dict
        String match = findMatch(input, curr, dict);
        // if we didn't, throw Exception
        if (match == null) {
           throw new IllegalArgumentException("couldn't match");
        }
        output += match + " ";
        curr += match.length();
        //System.out.println(output);
      }
      return output;
    }
    
    static String findMatch(String input, int startIndex, List<String> dict) {
      Optional<String> match = dict.stream()
        .filter(p -> startIndex + p.length() <= input.length() )   // exclude words too big
        .sorted((p, q)-> Integer.compare(q.length(), p.length())) // match longest first
        .filter(p -> 
          isAnagramMatch(input.substring(startIndex, startIndex + p.length()), p))
        .findFirst();
      return match.isPresent() ? match.get() : null;
      // assume returns null if it didn't find
    }
    
    static Boolean isAnagramMatch(String inputWord, String dictWord) {
       // create charCountMap of one
       Map<Character, Integer> oneMap = getCharMap(inputWord);
       Map<Character, Integer> twoMap = getCharMap(dictWord);
       Boolean isMatch = oneMap.entrySet().stream()
       .allMatch(e -> e.getValue().equals(twoMap.get(e.getKey())));
       return isMatch;
    }
    
    
    static Map<Character, Integer> getCharMap(String input) {
       Map<Character, Integer> oneMap = new HashMap<Character, Integer>();
       for(int i = 0; i < input.length(); ++i) {
         int val = oneMap.get(input.charAt(i)) == null ? 
           0 : oneMap.get(input.charAt(i)); // assume get return null
         oneMap.put(input.charAt(i), val + 1);
       }
       return oneMap;
    }
    
    
    
    
    
    
}