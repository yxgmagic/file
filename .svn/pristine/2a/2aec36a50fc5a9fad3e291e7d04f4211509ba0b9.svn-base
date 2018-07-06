package com.zhichao.service.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhichao.service.system.ISequenceService;
import com.zhichao.beans.guns.SeqGenerate;
import com.zhichao.beans.guns.Sequence;
import com.zhichao.core.util.SpringContextHolder;
import com.zhichao.dal.mapper.SeqGenerateMapper;
import com.zhichao.dal.mapper.SequenceMapper;

public class PubUtil {


	@Autowired
	private ISequenceService sequenceService=SpringContextHolder.getBean("sequenceServiceImpl");
	@Autowired
	private SequenceMapper sm= SpringContextHolder.getBean(SequenceMapper.class);

	@Autowired
	private SeqGenerateMapper sgm=SpringContextHolder.getBean(SeqGenerateMapper.class);

	Sequence seq =new Sequence();
	public Map<String, Object> getSequence( String seqtype){
		return  getSequence(seqtype, null, 0, null, null);
	}
	public Map<String, Object> getSequence( String seqtype, String seqdept){
		return  getSequence(seqtype, seqdept, 0, null, null);
	}
	public Map<String, Object> getSequence( String seqtype, String seqcol,   String seqtab){
		return  getSequence(seqtype, null, 0, seqcol, seqtab);
	}
	public Map<String, Object> getSequence( String seqtype, String seqdept,  String seqcol,String seqtab){
		return  getSequence(seqtype, seqdept, 0, seqcol, seqtab);
	}

	public Map<String, Object> getSequence( String seqtype, String seqdept, String roadnumstr,String seqcol,String seqtab) {
		if(null==roadnumstr||"".equals(roadnumstr)){
			roadnumstr="0";
		}
		Integer roadnum =0;
		try{
			roadnum=Integer.parseInt(roadnumstr);
		}catch(NumberFormatException e){
			roadnum =0;
		}
		return  getSequence(seqtype, seqdept, roadnum, seqcol, seqtab);

	}

	/**
	 * 根据编码规则，生成全局唯一单号
	 * 成功：返回{"sequence","HN0001"}类似Object
	 * 失败：返回{"ERROR","失败原因"}
	 *  编码规则名:seqtype,取值为表bs_seq_generate.seqtype
	 *  站点号或者部门号：seqdept 
	 *  车道：roadnum
	 *  列名：seqcol
	 *  表名：seqtab
	 */
	public Map<String, Object> getSequence( String seqtype, String seqdept, Integer roadnum,String seqcol,String seqtab) {

		Map<String, Object>  map =new HashMap<String, Object>();
		try{

			List<Map<String, Object>> seqlist= sm.getSeqBytpdp(seqtype, seqdept,roadnum,seqcol,seqtab);
			if(seqlist.size()==1){

				seq =(Sequence)seqlist.get(0);

				return getseq( false,seqtype,seqdept);

			}else if(seqlist.size()>1){
				map.put("ERROR", "存在多条编码规则名为"+seqtype+"的记录！请检测编码规则配置！"+"\n\t") ;
				return   map;
			}else {
				if(null==seqtype||"".equals(seqtype)||null==seqcol||null==seqtab||"".equals(seqcol)||"".equals(seqtab)){
					map.put("ERROR", "编码规则名:("+seqtype+")！表名:("+seqtab+")或列名("+seqcol+")参数传入不完全，无法自动新建规则！" );
					return   map;
				}

				seq.setRoadnum(roadnum);
				seq.setSeqcol(seqcol);
				seq.setSeqdept(seqdept);
				seq.setSeqtab(seqtab);
				seq.setSeqtype(seqtype);
				seq.setIsseries(1);
				seq.setSeqmax(0);
				map= getseq(true,seqtype,seqdept);
				if(map.containsKey("sequence")){
					return getseq(false,seqtype,seqdept);
				}else {
					return map;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("ERROR", e);
			return map;
		}
	}


	private Map<String, Object> getseq(boolean seqnew,String seqtype, String seqdept){

		Map<String, Object>  map =new HashMap<String, Object>();
		List<Map<String, Object>> sgmlist= sgm.getSeqGenBytype(seqtype);
		StringBuffer  sequence= new StringBuffer("");
		SeqGenerate segm;
		if(sgmlist.size()==0){
			map.put("ERROR",seqtype+"编码规则下没有对应的规则明细！") ;
			return   map;
		}else{
			Calendar now =Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
			String maxseq="";
			synchronized (this){
				if(1==seq.getIsseries()
						&&!seqnew
						&&null!=seq.getSequence()
						&&!"".equals(seq.getSequence())
						&&seq.getSequence().length()>seq.getSeqlen()){
					int indexlen=0;
					String nyr="";
					for(int i=0;i<sgmlist.size();i++){
						segm =(SeqGenerate)sgmlist.get(i);
						if("YYMMDD".equals(segm.getSeqcol())||"HHmmss".equals(segm.getSeqcol())||"YYMMDDHHmmss".equals(segm.getSeqcol())||"seqdate.YY".equals(segm.getSeqcol())||"seqdate.MM".equals(segm.getSeqcol())||"seqdate.DD".equals(segm.getSeqcol())||"seqdate.HH".equals(segm.getSeqcol())||"seqdate.mm".equals(segm.getSeqcol())||"seqdate.ss".equals(segm.getSeqcol())||"seqdate.sss".equals(segm.getSeqcol()))
						{indexlen+=segm.getSeqlen();
						switch(segm.getSeqcol()){
						default:
							break;
						case "seqdate.YY":
							nyr+=getStringByLen(String.valueOf(now.get(Calendar.YEAR)),segm.getSeqlen());
							break;
						case "seqdate.MM":
							nyr+=getStringByLen(String.valueOf(now.get(Calendar.MONTH)+1),segm.getSeqlen());
							break;
						case "seqdate.DD":
							nyr+=getStringByLen((String.valueOf(now.get(Calendar.DATE))),segm.getSeqlen());
							break;
						case "YYMMDD":
							nyr=getStringByLen(String.valueOf(now.get(Calendar.YEAR))+
									getStringByLen(String.valueOf(now.get(Calendar.MONTH)+1),2)+
									getStringByLen(String.valueOf(now.get(Calendar.DATE)),2),segm.getSeqlen());
							break;
								
						case "YYMMDDHHmmss":
							nyr=getStringByLen(String.valueOf(now.get(Calendar.YEAR))+
									getStringByLen(String.valueOf(now.get(Calendar.MONTH)+1),2)+
									getStringByLen(String.valueOf(now.get(Calendar.DATE)),2),segm.getSeqlen()-6);
							break;
 
						}
						}
						else if("seqmax".equals(segm.getSeqcol())){
							seq.setSeqlen(segm.getSeqlen());
						}
					}
					maxseq = (seq.getSequence()).substring(0, seq.getSequence().length()-seq.getSeqlen()-indexlen);
					if(indexlen>0){
						maxseq+=nyr;
					}
					maxseq=sm.getMaxSeqFromTable(checkSql(seq.getSeqtab()), checkSql(seq.getSeqcol()),maxseq);
					if(null!=maxseq&&!"".equals(maxseq)){
						try{
							if(maxseq.length()>=seq.getSeqlen()){
								maxseq=maxseq.substring(maxseq.length()-seq.getSeqlen(), maxseq.length());
							}
							seq.setSeqmax(Integer.valueOf(maxseq));
						}catch(NumberFormatException e){

						}
					}
				}

				for(int i=0;i<sgmlist.size();i++){
					segm =(SeqGenerate)sgmlist.get(i);
					if(segm.getSeqnum()==i+1){
						String str="";
						switch(segm.getSeqcol()){
						default:

							break;
						case "seqstr":
							if(seqnew){
								seq.setSeqstr("HN");
							}	
							str=getStringByLen(seq.getSeqstr(),segm.getSeqlen());

							break;
						case "seqdept":
							str=getStringByLen(seqdept,segm.getSeqlen());
							break;
						case "roadnum":
							str=getStringByLen(String.valueOf(seq.getRoadnum()),segm.getSeqlen());
							break;
						case "seqdate.YY":
							str=getStringByLen(String.valueOf(now.get(Calendar.YEAR)),segm.getSeqlen());
							break;
						case "seqdate.MM":
							str=getStringByLen(String.valueOf(now.get(Calendar.MONTH)+1),segm.getSeqlen());
							break;
						case "seqdate.DD":
							str=getStringByLen((String.valueOf(now.get(Calendar.DATE))),segm.getSeqlen());
							break;
						case "seqdate.HH":
							str=getStringByLen(String.valueOf(now.get(Calendar.HOUR_OF_DAY)),segm.getSeqlen());
							break;
						case "seqdate.mm":
							str=getStringByLen(String.valueOf(now.get(Calendar.MINUTE)),segm.getSeqlen());
							break;
						case "seqdate.ss":
							str=getStringByLen(String.valueOf(now.get(Calendar.SECOND)),segm.getSeqlen());
							break;
						case "seqdate.sss":
							str=getStringByLen(String.valueOf(now.get(Calendar.MILLISECOND)),segm.getSeqlen());
							break;
						case "YYMMDD":
							str=getStringByLen(String.valueOf(now.get(Calendar.YEAR))+
									getStringByLen(String.valueOf(now.get(Calendar.MONTH)+1),2)+
									getStringByLen(String.valueOf(now.get(Calendar.DATE)),2),segm.getSeqlen());
							break;
						case "HHmmss":
							str=getStringByLen(
									getStringByLen(String.valueOf(now.get(Calendar.HOUR_OF_DAY)),2)+
									getStringByLen(String.valueOf(now.get(Calendar.MINUTE)),2)+
									getStringByLen(String.valueOf(now.get(Calendar.SECOND)),2),segm.getSeqlen());
							break;							
						case "YYMMDDHHmmss":
							str=getStringByLen(String.valueOf(now.get(Calendar.YEAR))+
									getStringByLen(String.valueOf(now.get(Calendar.MONTH)+1),2)+
									getStringByLen(String.valueOf(now.get(Calendar.DATE)),2)+
									getStringByLen(String.valueOf(now.get(Calendar.HOUR_OF_DAY)),2)+
									getStringByLen(String.valueOf(now.get(Calendar.MINUTE)),2)+
									getStringByLen(String.valueOf(now.get(Calendar.SECOND)),2),segm.getSeqlen());
							break;
						case "seqmax":
							str=getStringByLen(String.valueOf(seq.getSeqmax()+1),segm.getSeqlen());
							 
								seq.setSeqlen(segm.getSeqlen());
							 
							break;


						}
						sequence.append(str);
					}else {
						map.put("ERROR","第"+(i+1)+"条编码规则明细不应该对应序号第"+segm.getSeqnum()+"的编码规则明细!") ;
						return   map;
					}//if
				}//for
				seq.setSeqdate(sdf.format(now.getTime()));
				seq.setSeqmax(seq.getSeqmax()+1);
				seq.setSequence(sequence.toString());
				if(seqnew){
					sequenceService.insert(seq);
				}else{
					sequenceService.updateById(seq);
				}
			}
		}
		map.put("sequence",sequence.toString());

		return map;

	}

	/**
	 * 动态检测表中是否有单号对应记录(适应新增）
	 */
	public boolean isExistsSeqFromTableForAdd(String seqtab,String seqcol,String sequence){
		Integer k =0;
		k=sm.isExistsSeqFromTableForAdd(checkSql(seqtab), checkSql(seqcol), sequence);
		if(k>0)return true;
		else return false;
	}
	/**
	 * 动态检测表中是否有单号对应记录（适应修改）
	 */
	public boolean isExistsSeqFromTableForUpdate(String seqtab,String seqcol,String sequence,Integer id){
		Integer k =0;
		k=sm.isExistsSeqFromTableForUpdate(checkSql(seqtab), checkSql(seqcol), sequence,id);
		if(k>0)return true;
		else return false;
	}
	/**
	 * 动态SQL防注入
	 */
	private String checkSql(String str){
		if(null==str||"".equals(str)){
			return str;
		}else{
			str=str.toLowerCase();
			return str.replaceAll("update", "").replaceAll("drop", "").replaceAll("insert", "").replaceAll("delete", "").replaceAll("select", "")
					.replaceAll("=", "").replaceAll(",", "").replaceAll("'", "").replaceAll("\"", "").replaceAll("/", "");//.replaceAll("exec", "");
		}
	}
	/**
	 * 定长字符串截取
	 */
	private String getStringByLen(String str,Integer len){
		if(null==str){
			str= "";
		} 
		if(str.length()>=len){
			str=str.substring(str.length()-len,str.length());

		}else{

			int k=len-str.length();
			for(int i=0;i<k;i++){
				str="0"+str;
			}
		}
		return str;
	}

}