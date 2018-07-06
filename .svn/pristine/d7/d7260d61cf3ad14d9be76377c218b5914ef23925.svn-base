package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.Sequence;

/**
 * <p>
  * 编码单号实时记录表 Mapper 接口
 * </p>
 *
 * @author zqf
 * @since 2018-01-09
 */
public interface SequenceMapper extends BaseMapper<Sequence> {
	 List<Map<String, Object>> getSeqBytpdp(  @Param("seqtype") String seqtype, @Param("seqdept") String seqdept,@Param("roadnum")  Integer roadnum,@Param("seqcol") String seqcol,@Param("seqtab") String seqtab );
	 String getMaxSeqFromTable(  @Param("seqtab") String seqtab, @Param("seqcol") String seqcol,@Param("sequence")  String sequence );
	 Integer isExistsSeqFromTableForAdd(  @Param("seqtab") String seqtab, @Param("seqcol") String seqcol,@Param("sequence")  String sequence );
	 Integer isExistsSeqFromTableForUpdate(  @Param("seqtab") String seqtab, @Param("seqcol") String seqcol,@Param("sequence")  String sequence,@Param("id") Integer id );
	 List<Map<String, Object>> selectAll( @Param("seqname") String seqname, @Param("seqtype") String seqtype,@Param("seqcol") String seqcol,@Param("seqtab") String seqtab, @Param("seqdept") String seqdept );
}