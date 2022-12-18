package com.example.studentshop.Controller;

import com.example.studentshop.entity.District;
import com.example.studentshop.service.DistrictService;
import com.example.studentshop.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @Author hao-sen
 * Date:  2022/12/15
 */


@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController {

    @Autowired
    private DistrictService districtService;

    // http://localhost:8080/districts?parent=86
    // http://localhost:8080/districts/?parent=86
    @RequestMapping ("")
    public JsonResult<List<District>> getByParent(String parent) {
        List<District> data = districtService.getByParent(parent);
        return new JsonResult<>(SUCCESS, data);
    }

}

