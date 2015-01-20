package com.data.former;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class data_former {
	
	public static String file_path = "C:/Users/lyihan/Yihan/Spring2015/Sharon/Stack_data/";
	public static String in_qfile_name;
	public static String in_afile_name;
	public static String out_file_name;
	public static FileReader qfile_in = null;
	public static FileReader afile_in = null;
	public static FileWriter file_out = null;
	public static int file_count = 59;
	public static int cur_file = 1;
	public static int lines = 0;
	
	public static String[] sep_code(String content){
		String[] result = new String[2];
		content = content.replaceAll("\\\\n", " ");
		result[0] = content.replaceAll("(?s)<code>.*?</code>", " ");
		result[1] = "";
		int pos1 = content.indexOf("<code>");
		int pos2 = content.indexOf("</code>",pos1);
		
		while(pos1 != -1){
			result[1] = result[1].concat(content.substring(pos1+6, pos2));
			pos1 = content.indexOf("<code>", pos2);
			pos2 = content.indexOf("</code>", pos1);
		}
			
		
		result[0] = result[0].replaceAll("</p>", " ");
		result[0] = result[0].replaceAll("(?s)<.*?>", " ");
		return result;
		
	}
	
	public static void write_answer(String qid, String title, String tags) throws IOException{
		afile_in = new FileReader(in_afile_name);
		BufferedReader abr = new BufferedReader(afile_in);
		String aline = abr.readLine();
		String[] asplit;
		boolean found = false;
		int vote = 0;
		String aq_id;
		String[] content_sep;
		while((aline = abr.readLine()) != null){
			asplit = aline.split("\t");
			if(found && vote < Integer.parseInt(asplit[3]))
				break;
			aq_id = asplit[1];
			if(qid.equals(aq_id)){
				if(asplit[4].equals("true"))
					file_out.write("accepted-answer,");
				else
					file_out.write("answer,");
				
				content_sep = sep_code(asplit[10].replace(',', ' '));
				file_out.write(title+','+asplit[10].replace(',', ' ')+','+content_sep[0]+','+content_sep[1]+','+asplit[5]+','+asplit[2]+','
						+asplit[3]+','+asplit[6]+','+asplit[8]+','+tags+'\n');
				
				vote = Integer.parseInt(asplit[3]);
			}
		}
	}
	
	public static void match_data() throws IOException{
		BufferedReader qbr = new BufferedReader(qfile_in);
		String qline = qbr.readLine();
		String[] qsplit;
		String qid;
		String title;
		String[] content_sep;
		while((qline = qbr.readLine()) != null){
			if(lines >= 1000){
				lines = 0;
				++cur_file;
				file_out.close();
				out_file_name = file_path+"formed_data/01_01_2013-12_31_2013["+cur_file+"].csv";
				file_out = new FileWriter(out_file_name);
				file_out.write("type,title,content,text,code,user_id,time,vote,reputation,accept_rate,tag\n");
			}
			
			file_out.write("question,");
			qsplit = qline.split("\t");
			title = qsplit[14].replace(',', ' ').replaceAll("(?s)<.*?>", " ");
			content_sep = sep_code(qsplit[15].replace(',',' '));
			file_out.write(title+','+qsplit[15].replace(',', ' ')+','+content_sep[0]+','+content_sep[1]+','+qsplit[9]+','+qsplit[1]+','
					+qsplit[6]+','+qsplit[9]+','+qsplit[12]+','+qsplit[7]+'\n');
			
			qid = qsplit[0];
			++lines;
			if(qsplit[3].equals("true"))
				write_answer(qid, title, qsplit[7]);
		}
	}
	
	public static void main(String[] args) throws IOException{
		out_file_name = file_path+"formed_data/01_01_2013-12_31_2013[1].csv";
		file_out = new FileWriter(out_file_name);
		file_out.write("type,title,content,text,code,user_id,time,vote,reputation,accept_rate,tag\n");
		
		for(int i=1; i<=file_count; ++i){
			if(qfile_in != null)
				qfile_in.close();
			if(afile_in != null)
				afile_in.close();
			
			in_qfile_name = file_path+"01_01_2013-12_31_2013q"+'['+i+"].xls";
			qfile_in = new FileReader(in_qfile_name);

			in_afile_name = file_path+"01_01_2013-12_31_2013a"+'['+i+"].xls";
			
			match_data();
		}
	}
	
}
