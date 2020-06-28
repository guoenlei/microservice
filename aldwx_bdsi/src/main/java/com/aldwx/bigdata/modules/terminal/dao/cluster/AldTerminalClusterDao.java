package com.aldwx.bigdata.modules.terminal.dao.cluster;

import com.aldwx.bigdata.common.base.BaseDao;
import com.aldwx.bigdata.modules.terminal.entity.TerminalEntity;
import com.aldwx.bigdata.modules.terminal.vo.AldTerminalVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AldTerminalClusterDao extends BaseDao<TerminalEntity, AldTerminalVo>  {


}
