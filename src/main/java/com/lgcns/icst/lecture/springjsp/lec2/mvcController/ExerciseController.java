package com.lgcns.icst.lecture.springjsp.lec2.mvcController;

import com.lgcns.icst.lecture.springjsp.lec1.dto.FreeBoardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/lec2/exercise")
public class ExerciseController {

    @RequestMapping(value = "/param", method = RequestMethod.GET)
    public String requestParam(@RequestParam(value = "member-id", required = false, defaultValue = "test") String memberId, @RequestParam(required = false) Integer age) {
        System.out.println("memberId = " + memberId);
        System.out.println("age = " + age);

        return null;
    }

    @RequestMapping(value = "/model", method = RequestMethod.GET)
    public String modelAttribute(@ModelAttribute(name = "freeBoard") FreeBoardDTO freeBoardDTO){
        System.out.println("content = " + freeBoardDTO.getContent());
        System.out.println("writerId = " + freeBoardDTO.getWriterId());
        System.out.println("writerName = " + freeBoardDTO.getWriterName());
        System.out.println("freeBoardDTO = " + freeBoardDTO);

        freeBoardDTO.setId(20L);

        return "lec2/sample";
    }
}
