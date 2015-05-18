package com.data.former;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
<<<<<<< HEAD
=======
import java.io.FileNotFoundException;
>>>>>>> origin/master
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.LinkedHashMap;
import java.util.List;
=======
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
>>>>>>> origin/master

import org.apache.commons.lang3.StringUtils;

public class countWords {

<<<<<<< HEAD
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		for(int file_count = 1; file_count < 388; ++ file_count){
			System.out.println(file_count);
			File wordmap = new File(
					"C:/Users/lyihan/Yihan/Spring2015/Sharon/Stack_result/ConstructiveAnalysisResults["+file_count+"].csv");
			FileWriter fw_1 = new FileWriter(wordmap.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw_1);
	
			// make the array of the words
			String seedWords = "seedwords_stemmed.txt";
			BufferedReader br_1 = null;
			String word = "";
	
			List<String> wordList = new ArrayList<String>();
			br_1 = new BufferedReader(new FileReader(seedWords));
			LinkedHashMap<String, Integer> wordCount = new LinkedHashMap<String, Integer>();
			LinkedHashMap<String, Integer> wordCount_temp = new LinkedHashMap<String, Integer>();
	
			while ((word = br_1.readLine()) != null) {
				wordList.add(word);
	
				wordCount.put(word, 0);
				wordCount_temp.put(word, 0);
			}
	
			String csvFile = "";
			if(file_count < 178)
				csvFile = "C:/Users/lyihan/Yihan/Spring2015/Sharon/Stack_data/Comment_data/formed_data/01_01_2013-12_31_2013["+(file_count)+"].csv";
			else
				csvFile = "C:/Users/lyihan/Yihan/Spring2015/Sharon/Stack_data/Comment_data/formed_data/01_01_2014-12_31_2014["+(file_count-177)+"].csv";
			BufferedReader br = null;
			String line = "";
			String text = "";
			StringBuilder sb = new StringBuilder();
	
			br = new BufferedReader(new FileReader(csvFile));
	
			// int wordCount = wordList.size();
			// int [] wordCountArray = new int[wordCount];
	
			int total;
	
			int count=0;
			String[] line_split;
			
			line = br.readLine();
			sb.append(line).append(",constructive\n");
			while ((line = br.readLine()) != null) {
				line_split = line.split(",");
				text = line_split[3];
				StringBuilder sb_1 = new StringBuilder();
				total = 0;
				for(String key: wordList){
					count = StringUtils.countMatches(text, key);
					if(count!=0){
						sb_1.append(key).append("/");
					}
					wordCount.put(key, count);
					total += count;
				}
	
				sb.append(line).append(",");
				sb.append((double)total/(text.length())).append("\n");
				wordCount.putAll(wordCount_temp);
			}
	
			bw.write(sb.toString());
			bw.flush();
			bw.close();
			br.close();
		}
=======
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File wordmap = new File(
				"D:/ASU2015Spring/Sharon/Stack_result/ConstructiveAnalysisResults.csv");
		FileWriter fw_1 = new FileWriter(wordmap.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw_1);

		// make the array of the words
		String seedWords = "seedwords_stemmed.txt";
		BufferedReader br_1 = null;
		String word = "";

		List<String> wordList = new ArrayList<String>();
		br_1 = new BufferedReader(new FileReader(seedWords));
		LinkedHashMap<String, Integer> wordCount = new LinkedHashMap<String, Integer>();
		LinkedHashMap<String, Integer> wordCount_temp = new LinkedHashMap<String, Integer>();

		while ((word = br_1.readLine()) != null) {
			wordList.add(word);

			wordCount.put(word, 0);
			wordCount_temp.put(word, 0);
		}

		String csvFile = "D:/ASU2015Spring/Sharon/Stack_data/formed_data/documents-export-2015-01-20/01_01_2014-12_31_2014[1].csv";
		BufferedReader br = null;
		String line = "";
		StringBuilder sb = new StringBuilder();

		br = new BufferedReader(new FileReader(csvFile));

		// int wordCount = wordList.size();
		// int [] wordCountArray = new int[wordCount];

		int i;
		int total;

		int count=0;
		int flag = 0;
		String[] line_split;
		 
		while ((line = br.readLine()) != null) {
			line_split = line.split(",");
			line = line_split[3];
			StringBuilder sb_1 = new StringBuilder();
			total = 0;
			for(String key: wordList){
				count = StringUtils.countMatches(line, key);
				if(count!=0){
					sb_1.append(key).append("/");
				}
				wordCount.put(key, count);
				total += count;
			}

			sb.append(line).append(",");

				for (String key : wordCount.keySet()) {
					sb.append(wordCount.get(key)).append(",");
				}

			sb.append(total).append(",").append(sb_1).append("\n");
			wordCount.putAll(wordCount_temp);
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();

>>>>>>> origin/master
	}

}
