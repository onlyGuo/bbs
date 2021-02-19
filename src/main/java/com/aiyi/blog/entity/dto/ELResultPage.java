package com.aiyi.blog.entity.dto;

import com.aiyi.core.beans.ResultPage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/24 14:49
 * @Email 719348277@qq.com
 * @Description: EL表达式方便使用的分页结果
 */
public class ELResultPage {

    private List<PageNum> pages;

    /**
     * 创建自标准分页结果集
     * @param resultPage
     *      标准分页结果集
     * @return
     */
    public static ELResultPage createByResultPage(ResultPage<?> resultPage){
        ELResultPage elResultPage = new ELResultPage();
        LinkedList<PageNum> list = new LinkedList<>();
        if (resultPage.getTotalPageSize() == 0){
            elResultPage.setPages(list);
            return elResultPage;
        }
        // 初始开始页码和结束页码
        int startNum = 1;
        int endNum = 12;

        if (resultPage.getTotalPageSize() > 12){
            if (resultPage.getPage() > 5){
                startNum = resultPage.getPage() - 5;
                endNum = resultPage.getPage() + 6;
            }
        }else{
            endNum = resultPage.getTotalPageSize();
        }
        if (endNum > resultPage.getTotalPageSize()){
            endNum = resultPage.getTotalPageSize();
        }
        if (endNum - startNum < 11){
            startNum = endNum - 11;
        }
        if (startNum < 1){
            startNum = 1;
        }

        for (int i = startNum; i <= endNum; i ++){
            PageNum num = new PageNum();
            num.setNum(i);
            if (i == resultPage.getPage()){
                num.setThisPage(true);
            }
            list.add(num);
        }

        if (list.getFirst().getNum() > 1){
            list.get(1).setAbst(true);
            list.getFirst().setNum(1);
        }

        PageNum left = new PageNum();
        if (resultPage.getPage() <= 1){
            left.setNum(1);
        }else{
            left.setNum(resultPage.getPage() - 1);
        }
        left.setFrist(true);
        list.addFirst(left);

        if (list.getLast().getNum() < resultPage.getTotalPageSize()){
            list.get(list.size() - 2).setAbst(true);
            list.getLast().setNum(resultPage.getTotalPageSize());
        }

        PageNum right = new PageNum();
        if (resultPage.getPage() >= resultPage.getTotalPageSize()){
            right.setNum(resultPage.getTotalPageSize());
        }else{
            right.setNum(resultPage.getPage() + 1);
        }
        right.setLast(true);
        list.addLast(right);

        elResultPage.setPages(list);
        return elResultPage;
    }

    public List<PageNum> getPages() {
        return pages;
    }

    public void setPages(List<PageNum> pages) {
        this.pages = pages;
    }

    public static void main(String[] args) {
        soutPage(130, 13);
    }

    private static void soutPage(int maxPage, int count){
        for (int c = 0; c < count; c ++){
            ResultPage<?> resultPage = new ResultPage<>(maxPage, c + 1, 10, new ArrayList<>());
            ELResultPage byResultPage = createByResultPage(resultPage);
            List<PageNum> pages = byResultPage.getPages();

            for (PageNum num: pages){
                if (num.isFrist()){
                    System.out.print(" < ");
                }

                if (num.isAbst()){
                    System.out.print(" ··· ");
                }

                if (num.isLast()){
                    System.out.print(" > ");
                }

                if (!num.isLast() && !num.isFrist() && !num.isAbst()){
                    if (num.isThisPage()){
                        System.out.print(" [" + (num.getNum() < 10 ? "  " : (num.getNum() < 100 ? " " : "")) + num.getNum() + "] ");
                    }else{
                        System.out.print(" " + (num.getNum() < 10 ? "  " : (num.getNum() < 100 ? " " : "")) + num.getNum() + " ");
                    }
                }
            }
            System.out.println();
        }

    }
}