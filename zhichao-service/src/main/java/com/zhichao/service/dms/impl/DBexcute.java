package com.zhichao.service.dms.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.zhichao.core.datasource.DruidProperties;
import com.zhichao.service.dms.DButil;
  

public class DBexcute    implements DButil {  
	/**   
	 * 创建数据库连接对象   
	 */    
	public static volatile Map<String,DruidPooledConnection> connnectionMap=new HashMap<String,DruidPooledConnection>();
	private DruidPooledConnection connnection = null;    

	/**   
	 * 创建PreparedStatement对象   
	 */    
	private PreparedStatement preparedStatement = null;    

	/**   
	 * 创建CallableStatement对象   
	 */    
	private CallableStatement callableStatement = null;    

	/**   
	 * 创建结果集对象   
	 */    
	private ResultSet resultSet = null;    

   
    private  static DruidProperties druidProperties =new DruidProperties();
    private  static  DruidDataSource ds = new DruidDataSource();
	//表结构对象 <type_tabname,sqlbuffer>
	private static  volatile Map<String,List<Object>> tabsqlMap= new HashMap<String,List<Object>> ();
 

 


	/**   
	 * 建立数据库连接   
	 * @return 数据库连接   
	 * @throws Exception   
	 */    
	public DruidPooledConnection getConnection(int type) throws Exception {    
		try {  
			if(null==connnection){
				if(ds.getPassword() == null||ds.getPassword().equals("")||ds.getUsername()==null||ds.getUsername().equals(""))
		         druidProperties.config(ds);
				connnection =ds.getConnection();  
 
			}
		

//			System.out.println("获取连接时间："+(System.currentTimeMillis()-connnection.getConnectedTimeMillis()));
 
		} catch (Exception e) {    
			System.out.println("获取连接失败"+e.getMessage());   
			throw e;
		}    
		return connnection;    
	}    

	/**   
	 * insert update delete SQL语句的执行的统一方法   
	 * @param sql SQL语句   
	 * @param params 参数数组，若没有参数则为null   
	 * @return 受影响的行数   
	 * @throws Exception   
	 */    
	public int executeUpdate(String sql, Object[] params,int type){    
		// 受影响的行数    
		int affectedLine = 0;    

		try {    
			// 获得连接    
			connnection = this.getConnection(type);    
			// 调用SQL    
			try { 
				
			preparedStatement = connnection.prepareStatement(sql);    
			} catch (SQLException e) { 
				closeAll();
				throw e;//本次放弃
//				if (connnection != null) {    
//					connnection.close();    
//					connnectionMap.remove(String.valueOf(type));
//					connnection=null;
//				}      
//				connnection = this.getConnection(type);   
//				preparedStatement = connnection.prepareStatement(sql);  
			}
			// 参数赋值    
			if (params != null) {    
				for (int i = 0; i < params.length; i++) {    
					preparedStatement.setObject(i + 1, params[i]);    
				}    
			}    
			// 执行    
			affectedLine = preparedStatement.executeUpdate();    

		} catch (Exception e) {    
			affectedLine=-1;
//			System.out.println(e.getMessage());    
		} finally {    
			// 释放资源    
			closeAll();    
		}    
		return affectedLine;    
	}    

	/**   
	 * SQL 查询将查询结果直接放入ResultSet中   
	 * @param sql SQL语句   
	 * @param params 参数数组，若没有参数则为null   
	 * @return 结果集   
	 * @throws Exception   
	 */    
	private ResultSet executeQueryRS(String sql, Object[] params,int type)throws Exception {    
		try {    
			// 获得连接    
			connnection = this.getConnection(type);    
			// 调用SQL    
			preparedStatement = connnection.prepareStatement(sql);    
			// 参数赋值    
			if (params != null) {    
				for (int i = 0; i < params.length; i++) {    
					preparedStatement.setObject(i + 1, params[i]);    
				}    
			}    
			// 执行    
			resultSet = preparedStatement.executeQuery();    

		} catch (Exception e) {    
			System.out.println("executeQueryRS=="+e.getMessage());    
			throw e;
		} finally{
			
			//closeAll();
		}

		return resultSet;    
	}    

	/**   
	 * 获取结果集，并将结果放在List中   
	 *    
	 * @param sql   
	 *            SQL语句   
	 * @return List   
	 *                       结果集   
	 * @throws Exception   
	 */    
	public List<Object> excuteQuery(String sql, Object[] params,int type) throws Exception {    
		// 执行SQL获得结果集    
		ResultSet rs = executeQueryRS(sql, params,type);    

		// 创建ResultSetMetaData对象    
		ResultSetMetaData rsmd = null;    

		// 结果集列数    
		int columnCount = 0;    
		try {    
			rsmd = rs.getMetaData();    

			// 获得结果集列数    
			columnCount = rsmd.getColumnCount();    
		} catch (SQLException e1) {    
			System.out.println(e1.getMessage());    
		}    

		// 创建List    
		List<Object> list = new ArrayList<Object>();    

		try {    
			// 将ResultSet的结果保存到List中    
			while (rs.next()) {    
				Map<String, Object> map = new HashMap<String, Object>();    
				for (int i = 1; i <= columnCount; i++) {    
					map.put(rsmd.getColumnLabel(i), rs.getObject(i));    
				}    
				list.add(map);    
			}    
		} catch (SQLException e) {    
			System.out.println(e.getMessage());    
			throw e;
		} finally {    
			// 关闭所有资源    
			closeAll();    
		}    

		return list;    
	}    

	/**   
	 * 存储过程带有一个输出参数的方法   
	 * @param sql 存储过程语句   
	 * @param params 参数数组   
	 * @param outParamPos 输出参数位置   
	 * @param SqlType 输出参数类型   
	 * @return 输出参数的值   
	 * @throws Exception   
	 */    
	public Object excuteQuery(String sql, Object[] params,int outParamPos, int SqlType,int type)throws Exception {    
		Object object = null;  
		try {   
			connnection = this.getConnection(type);    

			// 调用存储过程    
			callableStatement = connnection.prepareCall(sql);    

			// 给参数赋值    
			if(params != null) {    
				for(int i = 0; i < params.length; i++) {    
					callableStatement.setObject(i + 1, params[i]);    
				}    
			}    

			// 注册输出参数    
			callableStatement.registerOutParameter(outParamPos, SqlType);    

			// 执行    
			callableStatement.execute();    

			// 得到输出参数    
			object = callableStatement.getObject(outParamPos);    

		} catch (Exception e) {    
			System.out.println(e.getMessage());    
			throw e;
		} finally {    
			// 释放资源    
			closeAll();    
		}    

		return object;    
	}    

	/**   
	 * 关闭所有资源   
	 */    
	private void closeAll() {    
		// 关闭结果集对象    
		if (resultSet != null) {    
			try {    
				resultSet.close();    
			} catch (SQLException e) {    
				System.out.println(e.getMessage());    
			}    
		}    

		// 关闭PreparedStatement对象    
		if (preparedStatement != null) {    
			try {    
				preparedStatement.close();    
			} catch (SQLException e) {    
				System.out.println(e.getMessage());    
			}    
		}    

		// 关闭CallableStatement 对象    
		if (callableStatement != null) {    
			try {    
				callableStatement.close();    
			} catch (SQLException e) {    
				System.out.println(e.getMessage());    
			}    
		}    

//		 关闭Connection 对象    
		if (connnection != null) {    
			try {    
				connnection.close();    
				connnection=null;
			} catch (SQLException e) {    
				System.out.println(e.getMessage());    
			}    
		}       
	}

 

	@Override
	public List<Object> executeQueryList(String sql, Object[] params, int id) throws Exception {
		// TODO Auto-generated method stub
		return excuteQuery(sql,params,id);
	} 


	public List<Object> getTabList(String tab ,int id) throws Exception {
		String key=tab+"_|_"+String.valueOf(id);
		List<Object> list =null;
		if(tabsqlMap.containsKey(key)){
			list=tabsqlMap.get(key);
		}else{
			connnection = this.getConnection(id); 
			String systype="mysql";
			String sqlstr="";
			if("mysql".equals(systype)){
				sqlstr=" select COLUMN_NAME,COLUMN_COMMENT,IS_NULLABLE,DATA_TYPE,CHARACTER_MAXIMUM_LENGTH,"
						+ "NUMERIC_PRECISION,NUMERIC_SCALE from information_schema.columns "
						+ "where table_schema = ?   and table_name = ? and EXTRA!='auto_increment' "
						+ "order by ORDINAL_POSITION ";
			}else if("sqlserver".equals(systype)){

				sqlstr="SELECT  a.colorder columnno,a.name COLUMN_NAME, "
						+ "COLUMNPROPERTY(a.id,a.name,'PRECISION') as CHARACTER_MAXIMUM_LENGTH, "
						+ "  COLUMNPROPERTY(a.id,a.name,'PRECISION') as NUMERIC_PRECISION, "
						+ "isnull(COLUMNPROPERTY(a.id,a.name,'Scale'),0) as NUMERIC_SCALE,"
						+ "(case when a.isnullable=1 then 'YES' else 'NO'  end) IS_NULLABLE,   "
						+ "isnull(e.text,'') defaulvalue,isnull(g.[value], ' ') AS COLUMN_COMMENT "
						+ "FROM  syscolumns a  left join systypes b on a.xtype=b.xusertype   "
						+ "inner join sysobjects d on a.id=d.id and d.xtype='U' "
						+ "and d.name<>'dtproperties'  left join syscomments e on a.cdefault=e.id  "
						+ " left join sys.extended_properties g on a.id=g.major_id "
						+ "AND a.colid=g.minor_id left join sys.extended_properties f "
						+ "on d.id=f.class and f.minor_id=0  where d.name ='"+tab+"'  "
								+ "order by a.id,a.colorder";
				
			}else if("oracle".equals(systype)){
				sqlstr="select  t.column_name as COLUMN_NAME,t.data_type as DATA_TYPE,"
						+ "t.data_length as CHARACTER_MAXIMUM_LENGTH,t.nullable as IS_NULLABLE,"
						+ "t.column_id as columnno,c.comments as COLUMN_COMMENT,    "
						+ " (SELECT CASE WHEN t.column_name=m.column_name THEN 1 ELSE 0 END FROM DUAL) "
						+ "iskey    FROM user_tab_cols t, user_col_comments c, "
						+ "(select m.column_name from user_constraints s, user_cons_columns m   "
						+ "     where lower(m.table_name)='"+tab+"' and m.table_name=s.table_name    "
						+ "   and m.constraint_name=s.constraint_name and s.constraint_type='P') m  "
						+ "  WHERE lower(t.table_name)='"+tab+"'     "
						+ "  and c.table_name=t.table_name        and c.column_name=t.column_name    "
						+ "    and t.hidden_column='NO' order by t.column_id";
			}
			
			Object[] obj=new Object[2];
			obj[0]="glzc";
			obj[1]=tab;
			list =executeQueryList(sqlstr,obj,id);
			tabsqlMap.put(key, list);
			closeAll();
		}
		return list;

	}

@SuppressWarnings("unchecked")
public int excuteList(List<Object> list,String tab ,Map<String,Object> params,int id )throws Exception {
	int result=0;
	connnection = this.getConnection(id); 
	Object[] paramsobj=new Object[list.size()];
	StringBuffer sqlbuf= new StringBuffer(" insert into "+tab+"(");
	String colname="";
	String datatype="";
	String values=" values(";
	for(int k=0;k<list.size();k++){
		colname=((Map<String, Object>)list.get(k)).get("COLUMN_NAME").toString();
		//System.out.println(colname);
		if(k==list.size()-1){
			sqlbuf.append(colname).append(" )");
			values+="? )";
		}else {
			sqlbuf.append(colname).append(",");
			values+="?,";
		}
		if(params.containsKey(colname)){
			paramsobj[k]=params.get(colname);
		}else {
			datatype=((Map<String, Object>)list.get(k)).get("DATA_TYPE").toString();
			if(datatype.contains("int"))paramsobj[k]=0;
			else{
				paramsobj[k]=null;
			}

		}
	}
	sqlbuf.append(values);
	  result= executeUpdate(sqlbuf.toString(),paramsobj,id);
	closeAll();
	return result;
	
}
	@Override
	public   int insertTableOne(String tab ,Map<String,Object> params,int id) throws Exception{
		int result=0;
		String key=tab+"_|_"+String.valueOf(id);
		// 获得连接    

		 
		List<Object> list =getTabList(tab,id);
//		System.out.println(list);
		try{
		result= excuteList(list,tab,params,id);
		}catch(Exception e){
			e.printStackTrace();
			tabsqlMap.remove(key);
			list =getTabList(tab,id);
			result= excuteList(list,tab,params,id);
		}
		return result;
	}

}  