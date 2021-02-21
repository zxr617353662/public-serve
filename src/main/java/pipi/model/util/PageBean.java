package pipi.model.util;

import java.util.List;

/**
 * 封装分页相关的数据
 */
public class PageBean<T> {

    /**
     * 当前页的数据
     */
    private List<T> list;
    /**
     * 每页记录数
     */
    private int pageSize;

    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 总记录数
     */
    private int total;
    /**
     * 显示页码的数量
     * 默认是10
     */
    private int navigatePages;

    /**
     * 开始页号，用于下面的页面导航
     */
    private int pageBeginIndex;
    /**
     * 结束页号
     */
    private int pageEndIndex;

    /**
     * 总页数
     */
    private int pageCount;

    /**
     * @param list          当前页数据
     * @param pageSize      每页记录数
     * @param currentPage   当前页
     * @param total         总记录数
     * @param navigatePages 显示页码的数量，默认10
     */
    public PageBean(List<T> list, int pageSize, int currentPage,
                    int total, int navigatePages) {
        this.list = list;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.total = total;
        this.navigatePages = (navigatePages < 2) ? 10 : navigatePages;
        count();
    }

    /**
     * 计算页码
     */

    private void count() {
        //计算总页数
        this.pageCount = (total + pageSize - 1) / pageSize;
        //计算起止页码
        pageBeginIndex = Math.max(1, currentPage - (navigatePages / 2));
        pageEndIndex = Math.min(pageCount, pageBeginIndex + navigatePages - 1);
        pageBeginIndex = Math.max(1, pageEndIndex - navigatePages + 1);
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageBeginIndex() {
        return pageBeginIndex;
    }

    public void setPageBeginIndex(int pageBeginIndex) {
        this.pageBeginIndex = pageBeginIndex;
    }

    public int getPageEndIndex() {
        return pageEndIndex;
    }

    public void setPageEndIndex(int pageEndIndex) {
        this.pageEndIndex = pageEndIndex;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }
}
