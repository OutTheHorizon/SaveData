package net.fiezhi.savedata;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
        test2();
        test3();
    }

    private void test() {
        boolean sdCardExist = false;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //为真则SD卡已装入，
            sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        }

        if (sdCardExist) {
            File sdDir = Environment.getExternalStorageDirectory();//获取跟目录
            //查找SD卡根路径
            sdDir.toString();
            StatFs statfs = new StatFs(sdDir.getPath());
            long blocSize = statfs.getBlockSize();
            long totalBlocks = statfs.getBlockCount();
            long availaBlock = statfs.getAvailableBlocks();
            Log.e("main", "得到的根目录路径:" + sdDir);
        }
    }

    private void initData() {
        // 判断是否有外存储设备
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            File path = Environment.getExternalStorageDirectory();
            File file = new File(path.getPath());
            File[] files = file.listFiles();
            getFileName(files);
        }
    }

    private void getFileName(File[] files) {
        String names="";
        for (File currentFile : files) {
            // 判断是一个文件夹还是一个文件
            if (currentFile.isDirectory()) {
                getFileName(currentFile.listFiles());

            } else {
                // 取得文件名
                String fileName = currentFile.getName();
                // 获取.info后缀的文件
//                if (fileName.endsWith(".info")) {
//                    // 保存去掉后缀的文件名
//                    names=names+(fileName.substring(0, fileName.lastIndexOf(".")));
//                }
            }
        }
    }

    private void test2() {
        File directory_doc = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        //使用这个方法需要传入公共目录的类型如Environment.DIRECTORY_DOCUMENTS
        //查看公共目录文档文件的路径
        Log.e("main2", "得到的公共目录:" + directory_doc);
    }

    private void test3() {
        File[] files;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            files = getExternalFilesDirs(Environment.MEDIA_MOUNTED);
            for (File file : files) {
                Log.e("main3", "得到的全部外存：" + String.valueOf(file));
                //便历所有外部存储
            }
        }
    }
}