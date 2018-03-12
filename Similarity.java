import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Similarity class for finding cosine similarity.
 * Data Structure I used from Java Collections Framework is HashMap.
 * Purpose: to store data from the files or the strings in the form of key-value mapping.
 * Andrew Id: pdeoskar
 * @author Priyanka Deoskar
 *
 */
public class Similarity {
    /**
     * Map object.
     */
    private Map<String, BigInteger> myMap;
    /**
     * frequency of the word from the text.
     */
    private BigInteger frequency = BigInteger.ZERO;
    /**
     * number of lines.
     */
    private int numOfLines = 0;
    /**
     * number of non-distinct words.
     */
    private BigInteger numOfWords = BigInteger.ZERO;
    /**
     * dot product of vectors.
     */
    private BigInteger dotProd = BigInteger.ZERO;
    /**
     * product of euclidean norm of 2 maps.
     */
    private double deno;
    /**
     * cosine similarity or the arc cosine.
     */
    private double distance;
    /**
     * euclidean norm.
     */
    private double sRoot;
    /**
     * parameterized constructor for string input.
     * @param string input string.
     */
    public Similarity(String string) {
        myMap = new HashMap<String, BigInteger>();
        if (string != null) {
            String[] wordsFromText = string.split("\\W");
        for (int i = 0; i < wordsFromText.length; i++)  {
            //convert to lowercase.
            wordsFromText[i] = wordsFromText[i].toLowerCase();
            //check for true word.
            if (wordsFromText[i].matches("[a-z]+")) {
                    //if true word then check if key is already existing.
                    if (myMap.containsKey(wordsFromText[i])) {
                        //if true then get the frequency of the key(i.e. word)
                        //and increment by 1.
                        frequency = myMap.get(wordsFromText[i]);
                        frequency = frequency.add(BigInteger.ONE);
                        myMap.put(wordsFromText[i], frequency);
                        //increment the count of total words.
                        numOfWords = numOfWords.add(BigInteger.ONE);
                    } else {
                        //if the word is being added for first time the set frequency
                        // to 1.
                        myMap.put(wordsFromText[i], BigInteger.ONE);
                        //increment the count of total words.
                        numOfWords = numOfWords.add(BigInteger.ONE);
                    }
                }
            }
        }
    }
    /**
     * parameterized constructor for file input.
     * @param file input file.
     */
    public Similarity(File file) {
        myMap = new HashMap<String, BigInteger>();
        Scanner scanner = null;
        try {
            if (file != null) {
                scanner = new Scanner(file, "latin1");
                while (scanner.hasNextLine()) {
                    //if there is next line then increment number of lines.
                    if (scanner.hasNextLine()) {
                        numOfLines++;
                    }
                    String line = scanner.nextLine();
                    //split the string at whitespace.
                    String[] wordsFromText = line.split("\\W");
                    for (int i = 0; i < wordsFromText.length; i++)  {
                        //convert to lowercase.
                        wordsFromText[i] = wordsFromText[i].toLowerCase();
                        //check for true words.
                        if (wordsFromText[i].matches("[a-z]+")) {
                            //check if word already exists.
                            if (myMap.containsKey(wordsFromText[i])) {
                                //if true then get the frequency of the key(i.e. word) and increment by 1.
                                frequency = myMap.get(wordsFromText[i]);
                                frequency = frequency.add(BigInteger.ONE);
                                myMap.put(wordsFromText[i], frequency);
                                //increment the number of total words.
                                numOfWords = numOfWords.add(BigInteger.ONE);
                            } else {
                                //if the word is being added for first time the set frequency
                                // to 1.
                                myMap.put(wordsFromText[i], BigInteger.ONE);
                                //increment the count of total words.
                                numOfWords = numOfWords.add(BigInteger.ONE);
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    /**
     * calculates the total number of lines.
     * @return number of lines.
     */
    public int numOfLines() {
        return numOfLines;
    }
    /**
     * calculates the total number of words.
     * @return number of words.
     */
    public BigInteger numOfWords() {
        return numOfWords;
    }
    /**
     * calculates the total number of distinct words.
     * @return number of distinct words.
     */
    public  int numOfWordsNoDups() {
        return myMap.size();
    }
    /**
     * calculates the euclidean norm.
     * @return sroot which is euclidean norm value.
     */
    public double euclideanNorm() {
        BigInteger freqVal = BigInteger.ZERO;
        BigInteger sqr = BigInteger.ZERO;
        double sqrVal = 0;
        if (myMap == null) {
            return 0.0;
        }
        for (String word: myMap.keySet()) {
          freqVal = myMap.get(word);
          sqr = sqr.add(freqVal.multiply(freqVal));
        }
        sqrVal = sqr.doubleValue();
        sRoot = Math.sqrt(sqrVal);
        return sRoot;
    }
    /**
     * reason for non-quadratic time complexity: Since HashMap's 'get' method provides O(1) complexity
     * so we need to use only one 'for loop' without any need for nesting. So the complexity is O(n)
     * instead of O(n2).
     * @param map map instance passed from the test classes.
     * @return dot product value of the  2 map instances (2 vectors).
     */
    public double dotProduct(Map<String, BigInteger> map) {
        if (map == null || map.size() == 0) {
            return 0.0;
        }
        for (String word: myMap.keySet()) {
            if (map.containsKey(word)) {
                dotProd = dotProd.add(myMap.get(word).multiply(map.get(word)));
            }
        }
        return dotProd.doubleValue();
    }
    /**
     * calculates the cosine similarity between 2 individual strings or 2 files.
     * @param map instance passed from the test classes.
     * @return dot product value of the  2 map instances (2 vectors).
     */
    public double distance(Map<String, BigInteger> map) {
        if (map == null || map.size() == 0) {
            return Math.PI / 2;
        }
        if (map.equals(myMap)) {
            return 0.0;
        }
        BigInteger freqVal = BigInteger.ZERO;
        BigInteger sqr = BigInteger.ZERO;
        double sqrVal;
        double eNorm1 = 0;
        double eNorm2 = 0;

        eNorm1 = euclideanNorm();
        for (String word: map.keySet()) {
            freqVal = map.get(word);
            sqr = sqr.add((freqVal.multiply(freqVal)));
        }
            sqrVal = sqr.doubleValue();
            eNorm2 = Math.sqrt(sqrVal);
        deno = eNorm1 * eNorm2;
        distance = Math.acos(dotProduct(map) / deno);
        return distance;
    }
    /**
     * for getting the reference to the Map instance.
     * @return a reference to the Map instance in my class.
     */
    public Map<String, BigInteger> getMap() {
        return new HashMap<String, BigInteger>(myMap);
    }
}
