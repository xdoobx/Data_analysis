package com.data.former;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class data_former {
	
<<<<<<< HEAD
	public static String file_path = "C:/Users/lyihan/Yihan/Spring2015/Sharon/Stack_data/Comment_data/";
	//public static String corpus_path = "C:/Users/lyihan/Yihan/Spring2015/Sharon/Stack_data/qa_corpus/";
=======
	public static String file_path = "D:/ASU2015Spring/Sharon/Stack_data/";
>>>>>>> origin/master
	public static String in_qfile_name;
	public static String in_afile_name;
	public static String in_cfile_name;
	public static String out_file_name;
	public static FileReader qfile_in = null;
	public static FileReader afile_in = null;
	public static FileReader cfile_in = null;
	public static FileWriter file_out = null;
	//public static FileWriter corpus_out = null;
	public static int cur_file = 1;
	public static int lines = 0;
	
	public static String[] sep_code(String content){
		String[] result = new String[2];
<<<<<<< HEAD
		content = content.toLowerCase();
=======
		content = content.toLowerCase().replaceAll("\\\\n", " ");
>>>>>>> origin/master
		result[0] = content.replaceAll("(?s)<code>.*?</code>", " ");
		result[1] = "";
		int pos1 = content.indexOf("<code>");
		int pos2 = content.indexOf("</code>",pos1);
		
		while(pos1 != -1){
			result[1] = result[1].concat(content.substring(pos1+6, pos2).replaceAll("\\\\n+|\\\\r\\\\n+", " ")+' ');
			pos1 = content.indexOf("<code>", pos2);
			pos2 = content.indexOf("</code>", pos1);
		}
			
<<<<<<< HEAD
		result[0] = result[0].substring(1, result[0].length()-1).replaceAll("(?s)<.*?>+|\\\\n+|\\\\r\\\\n+", " ");
=======
		result[0] = result[0].replaceAll("</p>", " ");
		result[0] = result[0].replaceAll("(?s)<.*?>", " ");
>>>>>>> origin/master
		return result;
		
	}
	
	public static void write_comment(String pid, String title, String tags) throws IOException{
		cfile_in = new FileReader(in_cfile_name);
		BufferedReader abr = new BufferedReader(cfile_in);
		String cline = abr.readLine();
		String[] csplit;
		String cq_id;
		String[] content_sep;
		while((cline = abr.readLine()) != null){
			csplit = cline.split("\t");
			
			cq_id = csplit[1];
			if(pid.equals(cq_id)){
				content_sep = sep_code(csplit[9].replace(',', ' '));
				file_out.write("comment," + title+','+csplit[9].replace(',', ' ')+','+content_sep[0]+','+content_sep[1]+','+csplit[4]+','+csplit[2]+','
						+csplit[3]+','+csplit[5]+','+csplit[7]+','+tags+'\n');
				
			}
		}
	}
	
	public static void write_answer(String qid, String title, String tags, int num_answer) throws IOException{
		afile_in = new FileReader(in_afile_name);
		BufferedReader abr = new BufferedReader(afile_in);
		String aline = abr.readLine();
		String[] asplit;
		String aq_id;
		String[] content_sep;
		while((aline = abr.readLine()) != null && num_answer > 0){
			asplit = aline.split("\t");
			
			aq_id = asplit[1];
			if(qid.equals(aq_id)){
				if(asplit[4].equals("true"))
					file_out.write("accepted-answer,");
				else
					file_out.write("answer,");
				
				content_sep = sep_code(asplit[10].replace(',', ' '));
				file_out.write(title+','+asplit[10].replace(',', ' ')+','+content_sep[0]+','+content_sep[1]+','+asplit[5]+','+asplit[2]+','
						+asplit[3]+','+asplit[6]+','+asplit[8]+','+tags+'\n');
				
				--num_answer;
				write_comment(asplit[0], title, tags);
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
				out_file_name = file_path+"formed_data/01_01_2014-12_31_2014["+cur_file+"].csv";
				file_out = new FileWriter(out_file_name);
				file_out.write("type,title,content,text,code,user_id,time,vote,reputation,accept_rate,tag\n");
			}
			file_out.write("question,");
			qsplit = qline.split("\t");
			//String corpus_file_name = corpus_path + qsplit[0]+"_q";
			//corpus_out = new FileWriter(corpus_file_name+"t_"+qsplit[4]+"_"+(qsplit[5].isEmpty()?0:1));
			title = qsplit[14].substring(1, qsplit[14].length()-1).replaceAll("(?s)<.*?>", " ").replace(',', ' ');
			content_sep = sep_code(qsplit[15].replace(',',' '));
			file_out.write(title+','+qsplit[15].replace(',', ' ')+','+content_sep[0]+','+content_sep[1]+','+qsplit[9]+','+qsplit[1]+','
					+qsplit[6]+','+qsplit[10]+','+qsplit[12]+','+qsplit[7]+'\n');
<<<<<<< HEAD
			//corpus_out.write(title+"\n"+content_sep[0]);
			//corpus_out.close();
=======
>>>>>>> origin/master
			
			//corpus_out = new FileWriter(corpus_file_name+"c_"+qsplit[4]+"_"+(qsplit[5].isEmpty()?0:1));
			//corpus_out.write(content_sep[1]);
			//corpus_out.close();
			qid = qsplit[0];
			++lines;
<<<<<<< HEAD
			write_comment(qid, title, qsplit[7]);
			if(!qsplit[4].equals("0"))
				write_answer(qid, title, qsplit[7], Integer.parseInt(qsplit[4]));
=======
			if(qsplit[3].equals("true"))
				write_answer(qid, title, qsplit[7]);
			file_out.flush();
>>>>>>> origin/master
		}
		/*afile_in = new FileReader(in_afile_name);
		BufferedReader abr = new BufferedReader(afile_in);
		String aline = abr.readLine();
		String[] asplit;
		while((aline = abr.readLine()) != null){
			asplit = aline.split("\t");
			String corpus_file_name = corpus_path + asplit[0];
			if(asplit[4].equals("true"))
				corpus_file_name += "_aa";
			else
				corpus_file_name += "_a";
			corpus_out = new FileWriter(corpus_file_name+"t_"+asplit[1]);
			content_sep = sep_code(asplit[10].replace(',', ' '));
			corpus_out.write(content_sep[0]);
			corpus_out.close();
			corpus_out = new FileWriter(corpus_file_name+"c_"+asplit[1]);
			corpus_out.write(content_sep[1]);
			corpus_out.close();
		}*/
	}
	
	public static void main(String[] args) throws IOException{
		out_file_name = file_path+"formed_data/01_01_2014-12_31_2014[1].csv";
		file_out = new FileWriter(out_file_name);
		file_out.write("type,title,content,text,code,user_id,time,vote,reputation,accept_rate,tag\n");
		int i=1;
		while(true){
			System.out.println(i);
			if(qfile_in != null)
				qfile_in.close();
			if(afile_in != null)
				afile_in.close();
			if(cfile_in != null)
				cfile_in.close();
			
<<<<<<< HEAD
			if(i == 72)
				break;
			in_qfile_name = file_path+"01_01_2014-12_31_2014q"+'['+i+"].xls";
			File f = new File(in_qfile_name);
			qfile_in = new FileReader(in_qfile_name);

			in_afile_name = file_path+"01_01_2014-12_31_2014a"+'['+i+"].xls";
			
			in_cfile_name = file_path+"01_01_2014-12_31_2014c"+'['+i+"].xls";

			cfile_in = new FileReader(in_cfile_name);
=======
			in_qfile_name = file_path+"01_01_2014-12_31_2014q"+'['+i+"].xls";
			qfile_in = new FileReader(in_qfile_name);

			in_afile_name = file_path+"01_01_2014-12_31_2014a"+'['+i+"].xls";
>>>>>>> origin/master
			
			match_data();
			++i;
		}
		//file_out.close();
	}
	
}
