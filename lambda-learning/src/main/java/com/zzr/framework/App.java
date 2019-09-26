package com.zzr.framework;

import com.zzr.framework.lambda.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        List<Student> studentList =new ArrayList<>();
        studentList.add(new Student("路飞",22,175));
        studentList.add(new Student("红发",22,180));
        studentList.add(new Student("白胡子",22,185));

        studentList = Stream.of(new Student("路飞",22,175),new Student("路飞",22,175),new Student("路飞",22,175)).collect(Collectors.toList());

        List<Student> list = studentList.stream().filter(stu -> stu.getStature() < 180).collect(Collectors.toList());
        System.out.println(list);
    }
}
