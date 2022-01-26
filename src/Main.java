import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        HashMap<Character, Integer> sorted = sortByFreq(buildFreqMap());

        Set<Character> characters = sorted.keySet();
        Iterator<Character> itr = characters.iterator();

        char[] firstWord = new char[5];
        for (int i = 0; i < 5; i++) {
            firstWord[i] = (char) itr.next();
        }

        System.out.println(guessGivenLetters(firstWord));
    }

    private static HashMap<Character, Integer> buildFreqMap() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("words/words.txt"));
        HashMap<Character, Integer> letterFreq = new HashMap<>();
        while (sc.hasNext()) {
            String word = sc.nextLine();
            for (char c : word.toCharArray()) {
                if (letterFreq.containsKey(c)) {
                    letterFreq.put(c, letterFreq.get(c) + 1);
                } else {
                    letterFreq.put(c, 1);

                }
            }
        }
        return letterFreq;
    }

    private static HashMap<Character, Integer> sortByFreq(HashMap<Character, Integer> freqMap) {
        List<Map.Entry<Character, Integer>> list = new LinkedList<>(freqMap.entrySet());

        Collections.sort(list, (o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

        HashMap<Character, Integer> sorted = new LinkedHashMap<Character, Integer>();

        for (Map.Entry<Character, Integer> aa : list) {
            sorted.put(aa.getKey(), aa.getValue());
        }
        return sorted;
    }

    private static ArrayList<String> guessGivenLetters(char[] letters) throws FileNotFoundException {

        TreeSet<String> allGuesses = permutations(String.valueOf(letters));

        ArrayList<String> allWords = new ArrayList<>();
        Scanner sc = new Scanner(new File("words/guesses.txt"));
        while (sc.hasNext()) {
            allWords.add(sc.next());
        }

        Iterator itr = allGuesses.iterator();
        while (itr.hasNext()) {
            if(!allWords.contains(itr.next())) {
                itr.remove();
            }
        }

        return new ArrayList<>(allGuesses);
    }

    public static TreeSet<String> permutations(String input) {
        TreeSet<String> set = new TreeSet<String>();
        if (input == "") {
            return set;
        }

        char a = input.charAt(0);

        if (input.length() > 1) {
            input = input.substring(1);

            Set<String> permSet = permutations(input);

            for (String x : permSet) {
                for (int i = 0; i <= x.length(); i++) {
                    set.add(x.substring(0, i) + a + x.substring(i));
                }
            }
        } else {
            set.add(a + "");
        }
        return set;
    }
}