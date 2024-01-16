package 判断文件后缀;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) throws IOException {
        copy_txt();
        file_test();
        reName_all();
    }
    //输出包含指定文件后缀的文件名
    public static void file_test(){
        File f = new File("d:\\dir");
        String end = ".jpg";
        File[] f_l = f.listFiles(new re_FNF(end));
        System.out.println("指定的后缀"+end);
        for(File file:f_l){
            System.out.println(file.getName());
        }
    }
    //批量重命名(第三题)
    public static void reName_all(){
        File folder = new File("d:\\dir\\西游记");

//        if (folder.isDirectory()) {
//            // 获取文件夹中的所有txt文件
//            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
//            if (files != null) {
//                //创建链表存储数据
//                Map<String, Integer> fileNameMap = new HashMap<>();
//                for (File file : files) {
//                    String originalName = file.getName();
//                    String[] nameParts = originalName.split("_");
//                    if (nameParts.length > 1) {
//                        String fileNamePart = nameParts[nameParts.length - 1];
//                        int lastIndex = fileNamePart.lastIndexOf(".");
//                        String nameWithoutExtension;
//                        String newName;
//                        if (lastIndex != -1) {
//                            nameWithoutExtension = fileNamePart.substring(0, lastIndex);
//                        } else {
//                            nameWithoutExtension = fileNamePart;
//                        }
//                        String key = nameParts[1] + "_" + nameWithoutExtension;
//                        int number = fileNameMap.getOrDefault(key, 1);
//                        fileNameMap.put(key, number + 1);
//                        newName = key + ".txt";
//                        File newFile = new File(folder, newName);
//                        if (file.renameTo(newFile)) {
//                            System.out.println("文件 " + originalName + " 重命名为 " + newName);
//                        } else {
//                            System.out.println("无法重命名文件 " + originalName);
//                        }
//                    }
//                }
//            }
//        } else {
//            System.out.println("指定路径不是一个文件夹！");
//        }

        // 检查这个路径是否确实是一个文件夹
        if (folder.isDirectory()) {
            // listFiles方法接收一个过滤器，这里使用lambda表达式来指定过滤条件
            // 只接受路径下扩展名为".txt"的文件
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

            // 确保files不是null，避免NullPointerException
            if (files != null) {
                // 创建一个HashMap来跟踪每个文件前缀的数字计数
                Map<String, Integer> fileNameMap = new HashMap<>();

                for (File file : files) {
                    // 获取文件的原始名字
                    String originalName = file.getName();
                    // 将文件名使用"_"分割
                    String[] nameParts = originalName.split("_");

                    // 检查文件名是否按照预期的格式拆分成了多部分
                    if (nameParts.length > 1) {
                        // 取得不包含路径的文件名部分
                        String fileNamePart = nameParts[nameParts.length - 1];
                        // 找到文件名中"."的位置，以获取不带扩展名的文件名
                        int lastIndex = fileNamePart.lastIndexOf(".");
                        String nameWithoutExtension;
                        String newName;

                        // 检查是否找到了"."，然后相应地分割
                        if (lastIndex != -1) {
                            nameWithoutExtension = fileNamePart.substring(0, lastIndex);
                        } else {
                            nameWithoutExtension = fileNamePart;
                        }

                        // 构造一个Hashmap的键，由第二部分的文件名和不带扩展名的原始文件名组成
                        String key = nameParts[1] + "_" + nameWithoutExtension;
                        // 从Hashmap中获取当前计数，如果没有则默认为1
                        int number = fileNameMap.getOrDefault(key, 1);
                        // 更新Hashmap中的计数
                        fileNameMap.put(key, number + 1);
                        // 构造出新文件名
                        newName = number + "_" + key + ".txt";
                        // 创建一个新的文件对象来表示新的文件路径
                        File newFile = new File(folder, newName);

                        // 尝试重命名文件，如果成功则输出消息
                        if (file.renameTo(newFile)) {
                            System.out.println("文件 " + originalName + " 重命名为 " + newName);
                        } else {
                            // 如果无法重命名，输出错误消息
                            System.out.println("无法重命名文件 " + originalName);
                        }
                    }
                }
            }
        } else {
            // 如果指定路径不是一个文件夹，则输出错误消息
            System.out.println("指定路径不是一个文件夹！");
        }
    }
    //
    public static void copy_txt() throws IOException{
        FileInputStream fis = new FileInputStream("d:\\dir\\我的青春谁做主.txt");
        File f = new File("d:\\dir\\myFile");
        boolean mkdirs = f.mkdirs();
        System.out.println("创建路径："+mkdirs);
        FileOutputStream fos = new FileOutputStream("d:\\dir\\myFile\\myPrime.txt");
        System.out.println("输出成功！");
        int b = 0;
        while ((b = fis.read()) != -1) {
            fos.write(b);
        }
        fis.close();
        fos.close();
    }
}
//

//判断文件后缀
class re_FNF implements FilenameFilter {
    String name;
    public re_FNF(String name) {
        this.name = name;
    }
    @Override
    public boolean accept(File f_l, String name) {
        return name.endsWith(this.name);
    }
}

