package com.example.studentshop.service.impi;

import com.example.studentshop.entity.District;
import com.example.studentshop.mapper.DistrictMapper;
import com.example.studentshop.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @Author hao-sen
 * Date:  2022/12/15
 */

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> result = districtMapper.findByParent(parent);
        for (District district : result) {
            district.setId(null);
            district.setParent(null);
        }
        return result;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }

}
