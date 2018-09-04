package co.fatboa.filesystem.restcontroller;


import co.fatboa.core.restcontroller.BaseController;
import co.fatboa.filesystem.domain.params.FileParams;
import co.fatboa.filesystem.service.IFileService;

import com.mongodb.gridfs.GridFSDBFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Author: hl
 * @Description: 文件管理控制器
 * @Date: 19:35 2018/8/25
 * @Modified By:
 * @Version 1.0
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600) //跨域请求
@RequestMapping("/japi/filesystem")
@Api(value = "filesystem", description = "处理服务器文件存储相关的请求")
public class FileRestController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileRestController.class);
    @Autowired
    private IFileService fileService;

    @ApiOperation("文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<Map> upload(@ApiParam("二进制文件数据") @RequestParam List<MultipartFile> files, HttpServletRequest request) {
        List<Map> gridFSFiles = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        MultipartFile[] multipartFiles = new MultipartFile[files.size()];
        files.toArray(multipartFiles);
        try {
            map.put("status", 1);
            map.put("message", "上传成功");
            map.put("data", this.fileService.save(request, multipartFiles));
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            map.put("status", 0);
            map.put("message", "上传失败");
            return new ResponseEntity<Map>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @ApiOperation("富文本文件上传")
    @RequestMapping(value = "/richUpload", method = RequestMethod.POST)
    public ResponseEntity<Object> richUpload(@ApiParam("二进制文件数据") @RequestParam List<MultipartFile> files, HttpServletRequest request) {
        List<Map> gridFSFiles = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        MultipartFile[] multipartFiles = new MultipartFile[files.size()];
        files.toArray(multipartFiles);
        List<Map> result = null;
        try {
            result = this.fileService.save(request, multipartFiles);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            map.put("status", 0);
            map.put("message", "上传失败");
            return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (result.size() == 1) {
            return new ResponseEntity<Object>(result.get(0), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @ApiOperation("多个文件删除")
    @RequestMapping(value = "/deleteBatch", method = RequestMethod.GET)
    public ResponseEntity<Map> delete(@ApiParam("文件id数组") @RequestParam String... id) {
        try {
            this.fileService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return new ResponseEntity<Map>(errorResult(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map>(successResult(null, "删除成功"), HttpStatus.OK);
    }

    @ApiOperation("获取文件")
    @RequestMapping(value = "/getFile", method = RequestMethod.GET)
    public void getFile(@ApiParam("文件id") @RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
        GridFSDBFile gb = null;
        try {
            gb = this.fileService.findOne(new FileParams(id));
            response.setCharacterEncoding("UTF-8");
            ServletContext servletContext = request.getServletContext();
            String mimeType = servletContext.getMimeType(gb.getFilename());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setContentLength((int) gb.getLength());
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(gb.getFilename(), "UTF-8"));
            OutputStream out = response.getOutputStream();
            gb.writeTo(out);
            out.flush();
            out.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
