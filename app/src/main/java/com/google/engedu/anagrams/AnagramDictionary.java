/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    ArrayList<String> wordList = new ArrayList<String>();
    HashMap<String, ArrayList<String>> lettersToWord = new HashMap<String, ArrayList<String>>();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;

        while((line = in.readLine()) != null) {
            String word = line.trim();
            String sortedWord = sortLetters(word);
            if(lettersToWord.containsKey(sortedWord)){
                ArrayList<String> anagrams = lettersToWord.get(sortedWord);
                anagrams.add(word);
                lettersToWord.put(sortedWord, anagrams);
            }
            else{
                ArrayList<String> anagrams = new ArrayList<>();
                anagrams.add(word);
                lettersToWord.put(sortedWord, anagrams);
            }
            wordList.add(word);
        }
    }

    public boolean isGoodWord(String word, String base) {
        if(word.substring(1).equals(base) || word.substring(0,word.length()-1).equals(base))
            return false;
        String sortedWord = sortLetters(word);
        if(lettersToWord.containsKey(sortedWord))
        {
            if(lettersToWord.get(sortedWord).contains(word))
                return true;
            else
                return false;
        }
        else
            return false;
    }

//    public ArrayList<String> getAnagrams(String targetWord) {
//        ArrayList<String> result = new ArrayList<String>();
//        String sortedTargetWord = sortLetters(targetWord);
//        for(int i=0;i<wordList.size();i++){
//            if(wordList.get(i).length() == targetWord.length()){
//                String sortedWordListWord = sortLetters(wordList.get(i));
//                if(sortedTargetWord.equalsIgnoreCase(sortedWordListWord)){
//                    result.add(wordList.get(i));
//                }
//            }
//        }
//
//        if(lettersToWord.containsKey(sortedTargetWord)) {
//                result = lettersToWord.get(sortedTargetWord);
//                return result;
//        }
//
//        else
//            {return result;}
//    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        for(char c='a';c <= 'z';c++)
        {
            String sortedAddedWord = sortLetters(word+c);
            if(lettersToWord.containsKey(sortedAddedWord)){
                for(int z=0;z<lettersToWord.get(sortedAddedWord).size();z++){
                    if(isGoodWord(lettersToWord.get(sortedAddedWord).get(z),"stop"))
                        {result.add(lettersToWord.get(sortedAddedWord).get(z));}
                }
            }
        }
        return result;
    }

    public String sortLetters(String word) {
        char c[] = word.toCharArray();
        char temp;
        for(int i=0;i<word.length()-1;i++){
            for(int j=0;j<word.length()-1;j++){
                if(c[j]>c[j+1]){
                    temp = c[j];
                    c[j] = c[j+1];
                    c[j+1] = temp;
                }
            }
        }
        String sortedWord = new String(c);
        return sortedWord;
    }

    public String pickGoodStarterWord() {

        return "stop";
    }
}
