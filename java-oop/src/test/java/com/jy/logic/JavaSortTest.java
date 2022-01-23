package com.jy.logic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JavaSortTest {
    @DisplayName("자바소트 - 리소스를 넣으면 정렬된 결과를 출력한다.")
    @Test
    void given_List_WhenExcuting_ThenReturnSortedList() {
        // Given
        JavaSort<Integer> JavaSort = new JavaSort<>();

        //When
        List<Integer> actual = JavaSort.sort(List.of(3, 2, 4, 5, 1));

        //Then
        assertEquals(List.of(1, 2, 3, 4, 5), actual);
    }
}