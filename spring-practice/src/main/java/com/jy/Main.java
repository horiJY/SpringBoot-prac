package com.jy;

import com.jy.logic.JavaSort;
import com.jy.logic.Sort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Sort<String> sort = new JavaSort<>();
        System.out.println("result] " + sort.sort(Arrays.asList(args)));
    }
}
