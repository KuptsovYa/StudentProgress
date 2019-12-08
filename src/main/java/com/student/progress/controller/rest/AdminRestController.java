package com.student.progress.controller.rest;


import com.student.progress.entity.dto.GroupDataTransferObject;
import com.student.progress.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminRestController {

    private final AdminService adminService;

    @Autowired
    public AdminRestController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin/getInfo")
    public List<List<String>> getAllInfo(){
        List<List<String>> info = new ArrayList<>();
        info.add(adminService.getDates());
        info.add(adminService.getAssessments());
        info.add(adminService.getGroupNumbers());
        info.add(adminService.getGroups());
        return info;
    }

    @PostMapping("/admin/getDisciplines")
    public List<String> getDiscipline(@RequestBody GroupDataTransferObject group){
        return adminService.getDisciplineByGroup(group);
    }
}
