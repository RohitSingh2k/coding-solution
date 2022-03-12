package com.leetcode.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
/**
 *  Author : Rohit Singh
 *  Date : 09 March 2022
 */
public class LargestRectangleInHistogram {
    /**
     *    Inputs :
     *    ----------------
     *    1) size of the array
     *    2) followed by Array space separated
     *
     *    Outputs :
     *    ----------------
     *    1) single line containing values space separated of leftSmaller Array.
     *    2) single line containing values space separated of RightSmaller Array.
     *    3) The Largest area of histogram.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sizeOfArray = Integer.parseInt(br.readLine());
        Integer[] histogram = new Integer[sizeOfArray];

        int i = 0;

        for(var val : br.readLine().split(" "))
            histogram[i++] = Integer.parseInt(val);

        int area = maximumAreaOfHistogram(histogram);
        System.out.println(area);
    }

    /**
     * This method print the Arrays value space separated in one line.
     * @param arr
     */
    public static void printArray(Integer[] arr) {
        for(var val : arr) System.out.print(val + " ");
        System.out.println();
    }

    /**
     * This will calculate the maximum area of a histogram.
     * @param histogram
     * @return maximum area
     */
    public static int maximumAreaOfHistogram(Integer[] histogram) {
        Integer[] leftArray = leftSmallerArray(histogram);
        Integer[] rightArray = rightSmallerArray(histogram);

        System.out.print("Left Smaller Array : ");
        printArray(leftArray);
        System.out.print("Right Smaller Array : ");
        printArray(rightArray);

        int area = 0;

        for(int i = 0; i < histogram.length; i++) {
            int curr = (rightArray[i] - leftArray[i] - 1) * histogram[i];
            area = Math.max(curr,area);
        }

        return area;
    }

    /**
     * This will calculate the left smaller elements index for each element of the array.
     * @param histogram
     * @return left smaller array.
     */
    public static Integer[] leftSmallerArray(Integer[] histogram) {
        Integer[] leftSmaller = new Integer[histogram.length];

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < histogram.length; i++) {
            while(!stack.isEmpty() && histogram[stack.peek()] >= histogram[i])
                stack.pop();

            if(stack.isEmpty())
                leftSmaller[i] = -1;
            else
                leftSmaller[i] = stack.peek();

            stack.push(i);
        }

        return leftSmaller;
    }

    /**
     * This will calculate the right smaller elements index for each element of the array.
     * @param histogram
     * @return right smaller array.
     */
    public static Integer[] rightSmallerArray(Integer[] histogram) {
        Integer[]  rightSmaller = new Integer[histogram.length];

        Stack<Integer> stack = new Stack<>();

        for(int i = histogram.length - 1; i >= 0; i--) {
            while(!stack.isEmpty() && histogram[stack.peek()] >= histogram[i])
                stack.pop();

            if(stack.isEmpty())
                rightSmaller[i] = histogram.length;
            else
                rightSmaller[i] = stack.peek();

            stack.push(i);
        }

        return rightSmaller;
    }
}
