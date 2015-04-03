package com.data.former;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class countWords {

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

	}

}
