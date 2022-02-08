package com.lgcns.icst.lecture.springjsp.lec1.biz;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("length-point")
@Service
public class LengthPointPolicy implements PointPolicy {

    @Override
    public int getPoint(String content) {
        if (content.length() > 20) {
            return 5;
        } else if (content.length() > 10) {
            return 3;
        } else {
            return 0;
        }
    }
}
