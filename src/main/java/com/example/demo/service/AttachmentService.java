package com.example.demo.service;

import com.example.demo.entity.Attachment;
import com.example.demo.entity.AttachmentContent;
import com.example.demo.payload.Result;
import com.example.demo.repository.AttachmentContentRepository;
import com.example.demo.repository.AttachmentRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {


    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;


    @SneakyThrows
    public Result uploadFile(MultipartHttpServletRequest request){

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Attachment attachment = new Attachment();

        attachment.setName(file.getName());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);


        AttachmentContent attachmentContent = new AttachmentContent();

        attachmentContent.setBytes(file.getBytes());

        attachmentContent.setAttachment(savedAttachment);

        attachmentContentRepository.save(attachmentContent);

        return new Result("File saved",true,savedAttachment.getId());

    }


    @SneakyThrows
    public Result editFile(Integer id, MultipartHttpServletRequest request) {

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);

        if (optionalAttachment.isPresent()) {

            Iterator<String> fileNames = request.getFileNames();
            MultipartFile file = request.getFile(fileNames.next());

            if (file != null) {
                optionalAttachment.get().setName(file.getOriginalFilename());
                optionalAttachment.get().setSize(file.getSize());
                optionalAttachment.get().setContentType(file.getContentType());

                Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findAttachmentContentByAttachmentId(id);
                if (optionalAttachmentContent.isPresent()) {
                    optionalAttachmentContent.get().setAttachment(optionalAttachment.get());
                    optionalAttachmentContent.get().setBytes(file.getBytes());

                    attachmentContentRepository.save(optionalAttachmentContent.get());
                }

                return new Result("File changed!", true);
            }

            return new Result("File yuklanmadi!", false);
        }

        return new Result("Such file not found",false);



    }

    public List<Attachment> getFiles(){

        List<Attachment> attachmentList = attachmentRepository.findAll();

        return attachmentList;
    }

    public Attachment getFile(Integer id){


        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);

        if(!optionalAttachment.isPresent()){

            return new Attachment();

        }

        return optionalAttachment.get();

    }


    public Result deleteFile(Integer id){

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);

        if (!optionalAttachment.isPresent()){

            return new Result("File not found",false);

        }

        attachmentRepository.deleteById(id);

        return new Result("File  successfully deleted",true);
    }

}
