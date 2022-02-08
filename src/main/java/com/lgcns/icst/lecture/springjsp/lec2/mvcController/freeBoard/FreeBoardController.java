package com.lgcns.icst.lecture.springjsp.lec2.mvcController.freeBoard;

import com.lgcns.icst.lecture.springjsp.lec1.biz.FreeBoardBiz;
import com.lgcns.icst.lecture.springjsp.lec1.constant.SessionKey;
import com.lgcns.icst.lecture.springjsp.lec1.dto.FreeBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("/lec2/free-boards")
public class FreeBoardController {

    private final FreeBoardBiz freeBoardBiz;

    @Autowired
    public FreeBoardController(FreeBoardBiz freeBoardBiz) {
        this.freeBoardBiz = freeBoardBiz;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, @SessionAttribute(name = SessionKey.MEMBER_ID, required = false) String memberId) {

        if (memberId == null) {
            return "redirect:/lec2/member/login";
        }

        try {
            List<FreeBoardDTO> freeBoards = freeBoardBiz.findAll();

            model.addAttribute("freeBoards", freeBoards);
            return "lec2/freeBoard/list";

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "lec2/errorPage";
        }
    }

    @GetMapping("/write")
    public String writeView(@SessionAttribute(name = SessionKey.MEMBER_ID, required = false) String memberId) {
        if (memberId == null) {
            return "redirect:/lec2/member/login";
        }
        return "lec2/freeBoard/write";
    }

    @PostMapping("/write")
    public String write(@SessionAttribute(name = SessionKey.MEMBER_ID, required = false) String memberId,
                        @RequestParam String content, Model model) {
        if (memberId == null) {
            return "redirect:/lec2/member/login";
        }

        try {
            freeBoardBiz.save(content, memberId);
            return "redirect:/lec2/free-boards";

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "lec2/errorPage";
        }
    }

    @GetMapping("/{id}/update")
    public String updateView(@SessionAttribute(name = SessionKey.MEMBER_ID, required = false) String memberId,
                             @PathVariable Long id, Model model) {
        if (memberId == null) {
            return "redirect:/lec2/member/login";
        }

        try {
            FreeBoardDTO freeBoardDTO = freeBoardBiz.findFreeBoardById(id);
            model.addAttribute("freeBoard", freeBoardDTO);
            return "lec2/freeBoard/update";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "lec2/errorPage";
        }
    }

    @PostMapping("/{id}/update")
    public String updateFreeBoard(@SessionAttribute(name = SessionKey.MEMBER_ID, required = false) String memberId,
                                  @PathVariable Long id, @RequestParam String content, Model model) {
        if (memberId == null) {
            return "redirect:/lec2/member/login";
        }

        try {
            freeBoardBiz.update(id, content);
            return "redirect:/lec2/free-boards";

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "lec2/errorPage";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteFreeBoard(@SessionAttribute(name = SessionKey.MEMBER_ID, required = false) String memberId,
                                  @PathVariable Long id, Model model) {
        if (memberId == null) {
            return "redirect:/lec2/member/login";
        }

        try {
            freeBoardBiz.delete(id);
            return "redirect:/lec2/free-boards";

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "lec2/errorPage";
        }
    }
}
