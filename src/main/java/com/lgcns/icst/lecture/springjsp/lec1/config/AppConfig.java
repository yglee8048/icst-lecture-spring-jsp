package com.lgcns.icst.lecture.springjsp.lec1.config;

import org.springframework.context.annotation.Configuration;

@Configuration  // 설정 정보
public class AppConfig {

//    @Bean
//    public DataSource dataSource() {
//        return DataSourceBuilder.create()
//                .driverClassName("oracle.jdbc.OracleDriver")
//                .url("jdbc:oracle:thin:@10.100.70.7:1521:XE")
//                .username("student##")
//                .password("student##")
//                .build();
//    }
//
//    @Bean   // 스프링 DI 컨테이너에 객체 등록
//    public MemberDAO memberDAO() {
//        return new MemberDAO();
//    }
//
//    @Bean
//    public FreeBoardDAO freeBoardDAO() {
//        return new FreeBoardDAO();
//    }
//
//    @Bean
//    public FixedPointPolicy fixedPointPolicy() {
//        return new FixedPointPolicy();
//    }
//
//    @Bean
//    public LengthPointPolicy lengthPointPolicy() {
//        return new LengthPointPolicy();
//    }
//
//    @Bean
//    public FixedRankPolicy fixedRankPolicy() {
//        return new FixedRankPolicy();
//    }
//
//    @Bean
//    public RelativeRankPolicy relativeRankPolicy() {
//        return new RelativeRankPolicy(memberDAO());
//    }
//
//    @Bean
//    public MemberBiz memberBiz() {
//        return new MemberBiz(memberDAO(), relativeRankPolicy());
//    }
//
//    @Bean
//    public FreeBoardBiz freeBoardBiz() {
//        return new FreeBoardBiz(memberDAO(), freeBoardDAO(), fixedPointPolicy());
//    }
}
