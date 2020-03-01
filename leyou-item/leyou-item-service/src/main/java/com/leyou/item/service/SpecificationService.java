package com.leyou.item.service;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper groupMapper;

    @Autowired
    private SpecParamMapper paramMapper;

    /**
     * 根据分类ID查询分类组
     * @param cid
     * @return
     */
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup record = new SpecGroup();
        record.setCid(cid);
        return this.groupMapper.select(record);
    }

    /**
     *根据条件查询规格参数
     * @param gid
     * @return
     */
    public List<SpecParam> queryParms(Long gid, Long cid, Boolean generic, Boolean searching) {

        SpecParam record = new SpecParam();
        record.setGroupId(gid);
        record.setCid(cid);
        record.setGeneric(generic);
        record.setSearching(searching);
        return this.paramMapper.select(record);
    }

    /**
     * 根据id查询组及规格参数
     * @param cid
     * @return
     */
    public List<SpecGroup> queryGroupsWithParam(Long cid) {
        List<SpecGroup> groups = this.queryGroupsByCid(cid);
        groups.forEach(group -> {
            List<SpecParam> params = this.queryParms(group.getId(), null, null, null);
            group.setParams(params);
        });
        return groups;
    }
}
