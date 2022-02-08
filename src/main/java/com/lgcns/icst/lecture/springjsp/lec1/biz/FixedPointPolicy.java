package com.lgcns.icst.lecture.springjsp.lec1.biz;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class FixedPointPolicy implements PointPolicy {

    @Override
    public int getPoint(String content) {
        return 5;
    }
}
