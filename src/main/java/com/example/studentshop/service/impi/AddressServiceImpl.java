package com.example.studentshop.service.impi;

import com.example.studentshop.entity.Address;
import com.example.studentshop.mapper.AddressMapper;
import com.example.studentshop.service.AddressService;
import com.example.studentshop.service.DistrictService;
import com.example.studentshop.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Date;
import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @Author hao-sen
 * Date:  2022/12/15
 */

@Service
public class AddressServiceImpl implements AddressService {
    @Value("${project.address-max-size}")
    private int maxSize;
    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private DistrictService districtService;


    @Override
    public void addNew(Address address, String username, Integer uid) {
        Integer count = addressMapper.countByUid(uid);
        //判断统计结果是否达到最大值
        if (count > maxSize) {
            //达到最大值，抛出异常AddressSizeLimitException
            throw new AddressSizeLimitException("增加收货地址失败，您的收货地址数量已达到上限（" + maxSize + "条");
        }
        //新建时间对象
        Date now = new Date();
        //基于统计结果判断得到的地址是否是默认值地址
        Integer isDefault = (count == 0) ? 1 : 0;
        //补全参数address中的属性
        address.setUID(uid);
        //设置地址默认属性
        address.setIsDefault(isDefault);
        //设置修改人，修改时间
        address.setModifiedTime(now);
        address.setModifiedUser(username);
        address.setCreatedTime(now);
        address.setCreatedUser(username);
        String district = districtService.getNameByCode(address.getProvince()) + "," +
                districtService.getNameByCode(address.getCity()) + "," +
                districtService.getNameByCode(address.getArea());
        address.setDistrict(district);


        //调用函数insert添加收货人地址
        Integer rows = addressMapper.insert(address);
        if (rows != 1) {
            //抛出异常InsertException
            throw new InsertException("增加收货人失败！插入收货地址时出现未知错误");
        }


    }

    @Override
    public List<Address> getByUid(Integer uid) {
        // 调用持久层对象执行查询，得到收货地址列表
        List<Address> addresses = addressMapper.findByUid(uid);
        // 遍历查询到的收货地址列表
        for (Address address : addresses) {
            // -- 将不需要响应到客户端的属性设置为null：uid, province, city, area, is_default, 4个日志属性
            address.setUID(null);
            address.setProvince(null);
            address.setCity(null);
            address.setArea(null);
            address.setIsDefault(null);

            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }
        // 返回收货地址列表
        return addresses;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        //查询地址是否存在
        Address result = addressMapper.findByAid(aid);
        //判断地址是否为空
        if (result == null) {
            //访问的地址为空，抛出异常
            throw new AddressNotFoundException("设置默认收货地址失败！尝试访问的数据不存在");
        }
        //判断要修改的uid和从session获取的uid是否一致
        if (!result.getUID().equals(uid)) {
            //要修改的uid和从session获取的uid不一致
            throw new AccessDeniedException("设置默认收货地址失败！非法访问已被拒绝");
        }
        //基于uid将用户的所有地址设置为非默认
        Integer rows = addressMapper.updateNonDefaultByUid(uid);
        //判断返回值是否小于1
        if (rows < 1) {
            //返回值小于1，
            throw new UpdateException("设置默认收货地址失败，更新收货地址时出现未知错误，请联系系统管理员");
        }

        //基于aid将地址设为默认
        rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (rows !=1) {
            throw new UpdateException("设置默认收货地址异常！更新收货地址时出现未知错误，请联系系统管理员");
}
    }


}
