package com.dohyeon.kiosk.controller;

import com.dohyeon.kiosk.dto.MenuDTO;
import com.dohyeon.kiosk.dto.UploadResultDto;
import com.dohyeon.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import net.coobird.thumbnailator.Thumbnailator;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@Log4j2
@RequiredArgsConstructor
public class MenuApiController {

    private final MenuService menuService;

    @Value("${com.dohyeon.kiosk.upload.path}")
    private String uploadPath;

    @PostMapping("/menuRemove/{menu_code}")
    public void delete(@PathVariable Long menu_code) {
        menuService.menuRemove(menu_code);
    }

    @PostMapping("/menuUpdate")
    public ResponseEntity<MenuDTO> update(@RequestBody MenuDTO menuDTO) {

        log.info(menuDTO.getMenu_name());
        log.info(menuDTO.getMenu_price());
        log.info(menuDTO.getImg_url());
        log.info(menuDTO.getAdmin_code());
        log.info(menuDTO.getMenu_priority());
        log.info(menuDTO.getCategory());
        log.info(menuDTO.getReal_img_url());
        log.info(menuDTO.getStockQuantity());

        menuService.menuUpdate(menuDTO);

        return new ResponseEntity<>(menuDTO, HttpStatus.OK);
    }

    @PostMapping("/menuResister")
    public ResponseEntity<Long> register(@RequestBody MenuDTO menuDTO) {

        log.info(menuDTO.getMenu_name());
        log.info(menuDTO.getMenu_price());
        log.info(menuDTO.getImg_url());
        log.info(menuDTO.getAdmin_code());
        log.info(menuDTO.getMenu_priority());
        log.info(menuDTO.getCategory());
        log.info(menuDTO.getStockQuantity());


        Long menuId = menuService.menuResister(menuDTO);



        return new ResponseEntity<>(menuId,HttpStatus.OK);
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<UploadResultDto> uploadFile(MultipartFile file) {


        UploadResultDto dto = null;

        log.info("files : " +file);

        // ????????? ????????? ????????? ???????????? ??????
        if (!file.getContentType().startsWith("image")) {
            log.warn("????????? ????????? ????????????");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        String originalName = file.getOriginalFilename();
        log.info("originalName :" + originalName);
        String fileName = originalName.substring(originalName.lastIndexOf("\\")+1);
        log.info("uploadFile fileName" + fileName);

        // ?????? ?????? ??????
        String folderPath = makeFolder();
        log.info("folderPath :" + folderPath);

        String uuid = UUID.randomUUID().toString();
// ????????? ?????? ?????? ????????? "_"??? ???????????? ??????
        String saveName = uploadPath + File.separator + folderPath + File.separator + uuid +
                "_" + fileName;

        Path savePath = Paths.get(saveName);

        log.info("uuid" + uuid);
        log.info("saveName" + saveName);

        try {
            // ?????? ?????? ??????
            file.transferTo(savePath);
            String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + "_" + fileName;
            // ????????? ?????? ????????? ????????? s_??? ??????????????? ??????
            File thumbnailFile = new File(thumbnailSaveName);
            // ????????? ??????
            Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 640, 427);
            dto = new UploadResultDto(fileName, uuid, folderPath);
        }catch (IOException e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    private String makeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);
        //make folder
        File uploadPathFolder = new File(uploadPath, folderPath);
        if (!uploadPathFolder.exists()) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName) {

        System.out.println("???????????????=?" + fileName);
        ResponseEntity<byte[]> result = null;
        try {
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");
            log.info("fileName : " + srcFileName);
            File file = new File(uploadPath + File.separator + srcFileName);
            log.info("display file :" + file);
            HttpHeaders header = new HttpHeaders();

            //MIME?????? ??????
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            //?????? ???????????????
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(String fileName) {
        System.out.println(fileName);


        try {
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");
            File file = new File(uploadPath + File.separator + srcFileName);
            System.out.println("file =" + file);
            boolean result = file.delete();
            System.out.println("???????????? ??????"+ result);
            File thumbnail = new File(file.getParent(), "s_" + file.getName());
            System.out.println("thumbnail =" + thumbnail);

            result = thumbnail.delete();
            System.out.println("srcFileName =" + srcFileName);
            System.out.println("????????????????????? =" + result);

            String imageName = thumbnail.toString();

            System.out.println("uuid ??????????????? ??????" + imageName);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
