package cn.edu.zjut.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.zjut.entity.Emp;
import cn.edu.zjut.service.EmpService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zett0n
 * @date 2021/7/10 22:45
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/emp")
public class EmpController {
    // 注入yml
    @Value("${photo.dir}")
    private String realPath;

    @Autowired
    private EmpService empService;

    @GetMapping("/findAll")
    public List<Emp> findAll() {
        return this.empService.findAll();
    }

    @GetMapping("/findOne")
    public Emp findOne(Integer id) {
        log.debug("查询的员工id：[{}]", id);
        return this.empService.findOne(id);
    }

    @PostMapping("/save")
    // 形参名和前端一致（都为photo）
    public Map<String, Object> save(Emp emp, MultipartFile photo) {
        log.debug("报存的员工信息?：[{}]", emp.toString());
        log.debug("保存的头像信息：[{}]", photo.getOriginalFilename());

        Map<String, Object> map = new HashMap<>();
        String newFileName =
            UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(photo.getOriginalFilename());

        try {
            photo.transferTo(new File(this.realPath, newFileName));
            emp.setPath(newFileName);
            this.empService.save(emp);
            map.put("state", true);
            map.put("msg", "员工信息保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "员工信息保存失败！");
        }
        return map;
    }

    @PostMapping("/update")
    public Map<String, Object> update(Emp emp, MultipartFile photo) {
        log.debug("修改的员工信息：[{}]", emp.toString());

        Map<String, Object> map = new HashMap<>();

        try {
            if (photo != null && photo.getSize() != 0) {
                // 头像处理
                log.debug("修改的头像信息：[{}]", photo.getOriginalFilename());
                File oldFile = new File(this.realPath, emp.getPath());
                if (oldFile.exists())
                    oldFile.delete();

                String newFileName =
                    UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(photo.getOriginalFilename());
                photo.transferTo(new File(this.realPath, newFileName));
                emp.setPath(newFileName);
            }
            this.empService.update(emp);
            map.put("state", true);
            map.put("msg", "员工信息修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "员工信息修改失败！");
        }
        return map;
    }

    @GetMapping("/delete")
    public Map<String, Object> delete(Integer id) {
        log.debug("删除的员工id：[{}]", id);
        Map<String, Object> map = new HashMap<>();

        try {
            // 删除员工的头像
            Emp emp = this.empService.findOne(id);
            File oldFile = new File(this.realPath, emp.getPath());
            if (oldFile.exists())
                oldFile.delete();

            this.empService.delete(id);
            map.put("state", true);
            map.put("msg", "员工信息删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "员工信息删除失败！");
        }
        return map;
    }
}
