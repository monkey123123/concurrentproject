package me.monkey.gateway.other.thread;

public class Demo {
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main2(String[] args) {
        //关键代码
//        CommonsMultipartFile cFile = (CommonsMultipartFile) file;
//        DiskFileItem fileItem = (DiskFileItem) cFile.getFileItem();
//        InputStream inputStream = fileItem.getInputStream();

        //        ImportParams params = new ImportParams();

        //直接转实体
//        List<YourEntity> entities = ExcelImportUtil.importExcel(inputStream, YourEntity.class, params);
    }

    public static void main(String[] args) {
        System.out.println(formatSize(16));
    }

    /**
     * 将大小格式化为 2的N次
     * @param cap 初始大小
     * @return 格式化后的大小，2的N次
     */
    public static int formatSize(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
