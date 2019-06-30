package com.study_online.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatis 会话工具类
 * */
public class SqlSessionFactoryUtil {

    /**
     * 获得会话工厂
     *
     * */
    public static SqlSessionFactory getFactory(){
        InputStream inputStream = null;
        SqlSessionFactory sqlSessionFactory=null;
        try{
            //加载conf.xml配置文件，转换成输入流
            inputStream = Resources.getResourceAsStream("conf.xml");
            //根据配置文件的输入流构造一个SQL会话工厂
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sqlSessionFactory;
    }

    /**
     * 获得sql会话，是否自动提交
     * */
    public static SqlSession openSession(boolean isAutoCommit){//是否自动提交
        return getFactory().openSession(isAutoCommit);
    }

    /**
     * 关闭会话
     * */
    public static void closeSession(SqlSession session){
        if(session!=null){
            session.close();
        }
    }

}