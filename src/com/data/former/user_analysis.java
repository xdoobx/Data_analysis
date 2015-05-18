package com.data.former;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;

public class user_analysis {
	public static String file_path = "C:/Users/lyihan/Yihan/Spring2015/Sharon/Stack_data/Comment_data/";
	public static String in_qfile_name;
	public static String in_afile_name;
	public static String in_cfile_name;
	public static String out_file_name;
	public static FileReader qfile_in = null;
	public static FileReader afile_in = null;
	public static FileReader cfile_in = null;
	public static FileWriter file_out = null;
	public static int cur_file = 1;
	public static int lines = 0;
	
	public static Map<String, ArrayList<String>> user_profile;
}
