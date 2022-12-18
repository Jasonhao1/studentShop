package com.example.studentshop.Controller;

import com.example.studentshop.entity.Address;
import com.example.studentshop.mapper.AddressMapper;
import com.example.studentshop.service.AddressService;
import com.example.studentshop.util.JsonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @Author hao-sen
 * Date:  2022/12/15
 */

@RestController
@RequestMapping("address")
public class AddressController extends BaseController{
    @Autowired
    private AddressService addressService;
    @RequestMapping("addNew")
    public JsonResult<Void>addNew(Address address, HttpSession session){
        //从session中获取uid和username
        if(session.getAttribute("uid")!=null){
            Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
            address.setUID(uid);
            String username = session.getAttribute("username").toString();
            addressService.addNew(address,username,uid);
            return new JsonResult<>(SUCCESS);
        }
       return new JsonResult<>(FAILED);

    }

    // http://localhost:8080/address
    @GetMapping({"", "/"})
    public JsonResult<List<Address>> getByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<>(SUCCESS, data);
    }

    @RequestMapping("{aid}/setDefault")
    public JsonResult<Void> setDefault(
            @PathVariable("aid") Integer aid, HttpSession session){
        Integer uid=getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.setDefault(aid,uid,username);
        return new JsonResult<>(SUCCESS);
    }




}
